package hcurse.human;

import java.awt.Color;

import hcurse.console.KinConsole;
import hcurse.human.Attributes.Attribute;
import hcurse.human.Identity.Id;
import hcurse.human.Needs.Need;

public class Human {

	// VARIABLES ------------------------------------------------

	Identity id;
	Attributes att;
	SkillBox skillBox;
	Needs need;

	// CONSTRUCTOR ----------------------------------------------

	public static Human Build(String[] names) {

		return new Human(names);
	}
	
	// PRIVATE --------------------------------------------------
	
	private Human(String[] names) {
		id = Identity.buildFull(names);
		att = Attributes.build(0);
		need = Needs.build();
	}
	
	// PUBLIC ---------------------------------------------------

	public void humanTime() {
		need.handleTime();
	}
	
	public void printWarning(KinConsole kCons) {
		kCons.sPrint("Name : ", Color.WHITE, KinConsole.RIGHT_C, false, 14);
		kCons.sPrint(" '" + id.getName(Id.NICK) + "' : \n", Color.GREEN, KinConsole.RIGHT_C, false, 14);
		kCons.sPrint(need.getWarningMessage(), Color.GREEN, KinConsole.RIGHT_C, false, 14);
	}
	
	public void print(KinConsole kCons) {
		kCons.sPrint("IDENTITY :\n", Color.WHITE, KinConsole.LEFT_C, false, 16);
		kCons.sPrint("Name : ", Color.WHITE, KinConsole.LEFT_C, false, 14);
		printName(kCons);
		kCons.sPrint("ATTRIBUTES :\n", Color.WHITE, KinConsole.LEFT_C, false, 16);
		printAttributs(kCons);
		kCons.sPrint("NEEDS :\n", Color.WHITE, KinConsole.LEFT_C, false, 16);
		printNeeds(kCons);
	}

	public void printName(KinConsole kCons) {

		kCons.sPrint(id.getName(Id.FIRST), Color.GREEN, KinConsole.LEFT_C, false, 14);
		kCons.sPrint(" '" + id.getName(Id.NICK) + "' ", Color.GREEN, KinConsole.LEFT_C, false, 14);
		kCons.sPrint(id.getName(Id.LAST)+"\n", Color.GREEN, KinConsole.LEFT_C, false, 14);

	}

	public void printAttributs(KinConsole kCons) {
		for (Attribute a : Attribute.values()) {
			kCons.sPrint(att.getName(a,true) + " : ", Color.WHITE, KinConsole.LEFT_C, false, 14);
			kCons.sPrint(att.getValue(a) + "\n", Color.GREEN, KinConsole.LEFT_C, false, 14);
		}
	}
	
	public void printNeeds(KinConsole kCons) {
		for (Need a : Need.values()) {
			kCons.sPrint(need.getName(a) + " : ", Color.WHITE, KinConsole.LEFT_C, false, 14);
			kCons.sPrint(need.getValue(a)+"\n", need.setColor(a), KinConsole.LEFT_C, false, 14);
		}
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
