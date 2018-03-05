package hcurse.human;

import hcurse.human.Attributes.Attribute;

public class Skills {
	
	// ENUM -----------------------------------------------------
	
	public enum Skill { 
		FARMING(0,"Farming"),
		COLLECTING(1,"Collecting");

		private int id = 0;
		private String name = null;

		Skill(int id, String n) {
			this.id = id;
			this.name = n;

		}

		public String toString() {
			return "[" +id+ "]" + " " + name;
		}
	}
	
	// VARIABLES ------------------------------------------------
	
	private int lvl[] = new int[2];
	
	// CONSTRUCTOR ----------------------------------------------
	
	public static Skills build(Attributes att) {
		return new Skills(att);
	}
	
	// PRIVATE --------------------------------------------------
	
	private Skills(Attributes att) {
		for (Skill a : Skill.values()) {
			createSkill(a, att, getLinkAttribute(a));
		}
	}
	private void createSkill(Skill s, Attributes att, Attributes.Attribute[] a) {
		
		int temp = 0;
		
		for(Attribute b : a) {
		temp += att.getValue(b);	
		}
		
		lvl[s.id] = (temp/a.length) ;
		
	}

	// PUBLIC ---------------------------------------------------
	
	public Attribute[] getLinkAttribute(Skill s) {
		Attribute[] a = new Attribute[3];
		
		//Farming
		if(s == Skill.FARMING) {
			a[0] = Attribute.STRENGHT;
			a[1] = Attribute.AGILITY;
			a[2] = Attribute.KINESTHETIC_SENSE;
		} 
		//Collecting
		else if(s == Skill.COLLECTING) {
			a[0] = Attribute.AGILITY;
			a[1] = Attribute.SPACIAL_SENSE;
			a[2] = Attribute.PATIENCE;
		}
		
		return a; 
	}
	public String getName(Skill s) {
		return s.name;
	}
	public int getLvl(Skill s) {
		return this.lvl[s.id];
	}
	
}
