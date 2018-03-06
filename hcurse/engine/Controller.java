package hcurse.engine;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import hcurse.console.KinConsole;
import hcurse.console.PixelCanvas;
import hcurse.game.InputHandler;
import hcurse.game.gfx.Colours;
import hcurse.human.HumanBox;

public class Controller implements Runnable {
	
	KinConsole kCons = new KinConsole();
	HumanBox hBox = HumanBox.build();
	Clock c = new Clock();
	int remainMinutes = 0;
	String buffer = "";
	//PixelCanvas pCanvas = new PixelCanvas();
	
	public boolean running = false;
	public int tickCount = 0;
	
	@Override
	public void run() {
		
		//Initialize Variable for time
		
		long lastTime = System.nanoTime();
		double nsPerTick = 1000000000D/60D;
		int ticks = 0;
		int frames = 0;
		long lastTimer = System.currentTimeMillis();
		double delta = 0;
		
		//Terminal initialization
		help();
		kCons.topPrint(c.toString(), Color.CYAN);
		kCons.topScrollBottom();
		kCons.leftScrollBottom();
		
		
		kCons.input.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String text = kCons.input.getText();

				if (text.length() > 1) {
					doCommand(text);
				}
			}
		});
		
		//Start Running Loop
		while(running) {
			long now = System.nanoTime();
			
			delta += (now - lastTime) / nsPerTick;
			lastTime = now;
			
			boolean shouldRender = true;
			
			while(delta >= 1) {
				ticks++;
				//pCanvas.tick();
				kCons.pCanvas.tick();
				
				//render terminal
				kCons.topClear();
				
				if (remainMinutes > 0) {
					if (remainMinutes == 1) {
						kCons.rightPrint("# Time Stop ! \n");
						hBox.allHumanPrintWarning(kCons);
					}
					c.addMinute(1);
					hBox.allHumanTime();
					remainMinutes -= 1;
				}
				doTopPrint(remainMinutes);
				
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
				//pCanvas.render();
	
				
				kCons.pCanvas.render();
				
				
				
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
		
	public synchronized void start() {
		running = true;
		new Thread(this).start();
	}
	public synchronized void stop() {
		running = false;
	}
	
	
	
	
		
		
	
	public void doTopPrint(int i) {
		kCons.sPrint("- Human Curse - ", Color.RED, KinConsole.TOP_C, true,14);
		kCons.sPrint(c.toString() + " ", Color.CYAN, KinConsole.TOP_C, true);

		
		for (int j = 0; j < i%5; j++) {
			kCons.sPrint(".", Color.CYAN, KinConsole.TOP_C, true);
			
			for (int j2 = 0; j2 < i/30; j2++) {
				if(j2<15) {
					kCons.sPrint(".", Color.RED, KinConsole.TOP_C, true);
				}
			}
		}
		
		
	}

	public void doCommand(String s) {

		final String[] commands = s.split(" ");
		
		try {
			if (commands[0].equalsIgnoreCase("/clear")) {
				kCons.ClearAll();
				
			} else if (commands[0].equalsIgnoreCase("/create")) {
				hBox.CreateHuman();
				kCons.sPrint("New Human / ID: " + (int)hBox.nbrHuman() + "/\n", Color.GRAY, KinConsole.LEFT_C, true);
				
				
			} else if(commands[0].equalsIgnoreCase("/print")) {
				kCons.leftClear();
				hBox.printLastHuman(kCons);
				
			} else if(commands[0].equalsIgnoreCase("/printList")) {
				kCons.leftClear();
				hBox.printHumanList(kCons);
				
			} else if (commands[0].equalsIgnoreCase("/help")) {
				kCons.leftClear();
				this.help();
				
			} else if (commands[0].equalsIgnoreCase("/addTime")) {
				
				remainMinutes += 100;
				kCons.rightPrint("# " + remainMinutes + " min to go ! \n", Color.RED); ;
				
			} else if (commands[0].equalsIgnoreCase("/stop")) {
				remainMinutes = 1;
			} else if (commands[0].equalsIgnoreCase("/add")) {
				
				
			}

			else {
				kCons.leftPrintln(s, Color.WHITE);
			}

		} catch (Exception ex) {
			kCons.leftPrintln("Error Command List -> " + ex.getMessage(), new Color(255, 155, 155));
		}
		
		kCons.leftScrollBottom();
		kCons.rightScrollBottom();
		kCons.input.selectAll();
	}

	public void help() {
		kCons.leftPrintln("commands : ", Color.WHITE);
		kCons.leftPrintln("/help /addTime /stop /print /list /create /clear", Color.GREEN);

	}

	public static void main(String[] args) {
		Controller cont = new Controller();
		cont.start();
	}

}
