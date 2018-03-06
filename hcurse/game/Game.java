package hcurse.game;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import hcurse.game.gfx.Colours;
import hcurse.game.gfx.Font;
import hcurse.game.gfx.Screen;
import hcurse.game.gfx.SpriteSheet;
import hcurse.game.level.Level;
import hcurse.human.CHuman;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 600; //a sortir
	public static final int HEIGHT = WIDTH/12*9; // a sortir
	public static final int SCALE = 2; // a sortir
	public static final String NAME = "Game"; // a sortir
	
	private JFrame frame; // a sortir
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
	public int[] colours = new int[6*6*6];
	private Screen screen;
	public InputHandler input; // a sortir
	public Level level; // a sortir
	public CHuman player; // a sortir
	
	public boolean running = false;
	public int tickCount = 0;
	
	public Game() {
		
		//setting Window
		setMinimumSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
		setMaximumSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
		setPreferredSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
		
		frame = new JFrame(NAME);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		
		frame.add(this, BorderLayout.CENTER);
		frame.pack();
		
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	public void init() {
		
		int index = 0;
		for(int r=0; r<6;r++) {
			for(int g=0; g<6;g++) {
				for(int b=0; b<6;b++) {
					int rr = (r*255/5);
					int gg = (g*255/5);
					int bb = (b*255/5);
					colours[index++]= rr << 16 | gg << 8 | bb;
					
				}
			}
		}
		
		
		screen = new Screen(WIDTH,HEIGHT,new SpriteSheet("/SpriteSheet8x8.png"));
		input = new InputHandler(this);
		level = new Level(64, 64);
		player = new CHuman(level, 0, 0, input); // a sortir
		level.addEntity(player); // rendre public
		
		
	}
	
	public synchronized void start() {
		running = true;
		new Thread(this).start();
	}
	public synchronized void stop() {
		running = false;
	}
	
	// a sortir pour mettre dans le controller
	@Override
	public void run() { 
		
		long lastTime = System.nanoTime();
		double nsPerTick = 1000000000D/60D;
		
		int ticks = 0;
		int frames = 0;
		
		long lastTimer = System.currentTimeMillis();
		double delta = 0;
		
		init();
		
		while(running) {
			long now = System.nanoTime();
			
			delta += (now - lastTime) / nsPerTick;
			lastTime = now;
			
			boolean shouldRender = true;
			
			while(delta >= 1) {
				ticks++;
				tick();
				delta -= 1;
				shouldRender = true;
			}
			
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			if (shouldRender) {
				frames++;
				render();
				
			}
			
			
			if(System.currentTimeMillis() - lastTimer > 1000) {
				lastTimer += 1000;
				System.out.println("[TICKS:"+ticks+"/FRAMES:"+frames+"]");
				System.out.println("[Colours.get = "+Colours.get(000, 111, 222, 333));
				frames = 0;
				ticks = 0;
			}
			
		}
	}
	
//	private int x = 0, y = 0;
	
	public void tick() {
		tickCount ++;
		
		for(int i = 0; i < pixels.length; i++) {
			pixels[i] = i * tickCount;
			
		}
//		if(input.up.isPressed()) {y--;}
//		if(input.down.isPressed()) {y++;}
//		if(input.left.isPressed()) {x--;}
//		if(input.right.isPressed()) {x++;}
		
		level.tick();
	}
	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if(bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		int xOffset = player.x - (screen.width /2) ;
		int yOffset = player.y - (screen.height /2) ;
		
		level.renderTiles(screen, xOffset, yOffset);
		
		for (int x = 0; x < level.width; x++) {
			int colour = Colours.get(-1, -1, -1, 000);
			if(x%10 == 0 && x != 0) {
				colour = Colours.get(-1, -1, -1, 500);
			}
			Font.render((x%10)+"", screen, 0+(x*8), 0, colour);
			Font.render((x%10)+"", screen, 0, 0+(x*8), colour);
		}
		
		level.renderEntities(screen);
		
		String msg = "HCurse v.0";
		Font.render(msg, screen, screen.xOffset + screen.width/2 - (msg.length()*8/2), screen.yOffset + 16, Colours.get(000, 400, 400, 555));
		
		for (int y = 0; y < screen.height; y++) {
			for (int x = 0; x < screen.width; x++) {
				int colourCode = screen.pixels[x+y*screen.width];
				if(colourCode<255) pixels[x+y*WIDTH] = colours[colourCode];
			}
		}
		
		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(),null);
		g.dispose();
		bs.show();
		
		
	}
	
	public static void main(String[] args) {

		new Game().start();
		
		
	}

	
	
}
