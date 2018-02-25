package hcurse.human;

import java.awt.Color;
import java.util.Vector;

import hcurse.console.KinConsole;
import hcurse.tools.FileAction;

public class HumanBox {
	
	// VARIABLES ------------------------------------------------
	
	FileAction rf = new FileAction();
	public Vector<String> vName;
	public Vector<Human> box = new Vector<Human>();

	// CONSTRUCTOR ----------------------------------------------
	
	public static HumanBox build() {
		
		return new HumanBox();
	}
	
	// PRIVATE --------------------------------------------------
	
	private HumanBox() { 
		rf.openFile();
		vName = new Vector<String>(rf.readFile());
	}
	
	// PUBLIC ---------------------------------------------------
	
	public void allHumanTime() {
		for (int i = 0; i < box.size(); i++) {
			box.get(i).humanTime();
		}
	}
	public void allHumanPrintWarning(KinConsole kCons) {
		for (int i = 0; i < box.size(); i++) {
			kCons.sPrint("ID : " + i+ " ", Color.CYAN , KinConsole.RIGHT_C);
			box.get(i).printWarning(kCons);
		}
	}
	
	// --> PRINT ------------------------------------------------
	
	/**
	 * Create a Human in HumanBox
	 */
	public void CreateHuman() {
		
		//Create random number to chose name.
		int rN1 = (int)(Math.random() * vName.size());
		int rN2 = (int)(Math.random() * vName.size());
		int rN3 = (int)(Math.random() * vName.size());
		
		//Create the String of Names
		String[] str = {vName.get(rN1),vName.get(rN2),vName.get(rN3)};
		
		//Add Human to Vector
		box.add(Human.Build(str));
	}
	
	public int nbrHuman(){
		return box.size();
	}
	
	/**
	 * Print Last Human in KinConsole
	 * @param kCons KinConsole Console
	 */
	public void printLastHuman(KinConsole kCons) {
		box.get(box.size()-1).print(kCons);
	}
	 
	public void printHumanList(KinConsole kCons) {
		for (int i = 0; i < box.size(); i++) {
			kCons.sPrint("  #"+i+"  ", Color.LIGHT_GRAY);
			box.get(i).printName(kCons);
			kCons.sPrint("\n");
		}
	}

}
