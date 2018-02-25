package hcurse.engine;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import hcurse.console.KinConsole;
import hcurse.human.HumanBox;

public class Controller {
	
	KinConsole kCons = new KinConsole();
	HumanBox hBox = HumanBox.build();
	Clock c = new Clock();
	int remainMinutes = 0;
	String buffer = "";

	public static void main(String[] args) {

		boolean run = true;
		Controller cont = new Controller();

		cont.help();
		cont.kCons.topPrint(cont.c.toString(), Color.CYAN);
		cont.kCons.topScrollBottom();
		cont.kCons.leftScrollBottom();
		
		cont.kCons.input.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String text = cont.kCons.input.getText();

				if (text.length() > 1) {
					cont.doCommand(text);
				}
			}
		});


		while (run == true) {
			cont.kCons.topClear();
			
			if (cont.remainMinutes > 0) {
				if (cont.remainMinutes == 1) {
					cont.kCons.rightPrint("# Time Stop ! \n");
					cont.hBox.allHumanPrintWarning(cont.kCons);
				}
				cont.c.addMinute(1);
				cont.hBox.allHumanTime();
				cont.remainMinutes -= 1;
			}
			cont.doTopPrint(cont.remainMinutes);
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			
			
		}
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
		kCons.leftPrintln("/help /addTime /stop /print /clear", Color.GREEN);

	}

}
