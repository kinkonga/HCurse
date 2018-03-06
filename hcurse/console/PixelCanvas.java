package hcurse.console;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import hcurse.game.InputHandler;
import hcurse.game.gfx.Colours;
import hcurse.game.gfx.Font;
import hcurse.game.gfx.Screen;
import hcurse.game.gfx.SpriteSheet;
import hcurse.game.level.Level;
import hcurse.human.CHuman;


public class PixelCanvas extends Canvas {
	
	// VARIABLES ------------------------------------------------
	
	public int width, height, scale;
	private static final long serialVersionUID = 1L;
	public static final String NAME = "Game";
	
	private BufferedImage image;
	private int[] pixels;
	public int[] colours = new int[6*6*6];
	private Screen screen;
	public InputHandler input;
	public Level level;
	public CHuman player;
	
	public boolean running = false;
	public int tickCount = 0;
	
	// CONSTRUCTOR ----------------------------------------------
	
	public static PixelCanvas Build(int w, int h, int s) {
		return new PixelCanvas(w, h, s);
	}
	
	// PRIVATE --------------------------------------------------
	
	/**
	 * @param w witdh 
	 * @param h height
	 * @param s scale
	 */
	private PixelCanvas(int w, int h, int s) {
		
		this.scale = s;
		this.width = w/2;
		this.height = h/2; 
		
		//set number of pixels
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
		
		//setting Window
		setMinimumSize(new Dimension(width*scale,height*scale));
		setMaximumSize(new Dimension(width*scale,height*scale));
		setPreferredSize(new Dimension(width*scale,height*scale));
	}

	// PUBLIC ---------------------------------------------------
	
	public void init() {
		
		//initialize colours[]
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
		
		//initialize Screen
		screen = new Screen(width,height,new SpriteSheet("/SpriteSheet8x8.png"));
		input = new InputHandler(this);
		level = new Level(64, 64); //TODO level à mettre dans le controller
		player = new CHuman(level, 0, 0, input); //TODO integrer a Human et a controller
		level.addEntity(player);//TODO addEntity recuperable dans le controller
	}
	
	public void tick() {
		tickCount ++;
		
		for(int i = 0; i < pixels.length; i++) {
			pixels[i] = i * tickCount;
			
		}
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
				if(colourCode<255) pixels[x+y*width] = colours[colourCode];
			}
		}
		
		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(),null);
		g.dispose();
		bs.show();
		
		
	}

}
