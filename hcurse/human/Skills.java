package hcurse.human;

public class Skills {
	
	// ENUM -----------------------------------------------------
	
	public enum Skill { 
		FARMING(0,"Farming"),
		ENERGY_MAKING(1,"Energy Making");

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
	
	public static void build(Attributes att) {
		new Skills(att);
	}
	
	// PRIVATE --------------------------------------------------
	private Skills(Attributes att) {
		for (Skill skill : Skill.values()) {
			
		}
	}

	// PUBLIC ---------------------------------------------------

	public void printSkill() {
		//System.out.println(name + " : " + lvl);
	}
}
