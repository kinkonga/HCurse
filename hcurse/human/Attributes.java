package hcurse.human;


/**
 * Attributs - Create Attributs
 * @author Nicola Lacambre
 */
public class Attributes {

	public enum Att { 
		STRENGHT(0, "Strenght" , "STR"), 
		DEXTERITY(1, "Dexterity" , "DEX"), 
		CONSTITUTION(2, "Constitution", "CON"), 
		STAMINA(3,"Stamina", "STA"), 
		INTELLIGENCE(4,"Intelligence", "INT"), 
		PERCEPTION(5, "Perception", "PER"), 
		CHARISM(6, "Charism", "CHA"), 
		WILLPOWER(7, "Willpower", "WIL");

		private int id = 0;
		private String name = null;
		private String shortName = null;

		Att(int id, String n, String sn) {
			this.id = id;
			this.name = n;
			this.shortName = sn;

		}

		public String toString() {
			return id + " " + name;
		}
	}

	// VARIABLES ------------------------------------------------
	
	int[] value = new int[8];
	
	// CONSTRUCTOR ----------------------------------------------
	
	/**
	 * Build attributes using an int
	 * @param bonus add a bonus value to attributes
	 * @return Attributes Ctor(int)
	 */
	public static Attributes build(int bonus) {
		return new Attributes(bonus);
	}
	
	// PRIVATE --------------------------------------------------
	
	private Attributes(int bonus) {
		
		for (Att att : Att.values()) {
			create(att.name, 0, att.id);
		}
	}
	private void create(String name, int bonus, int id) {

		int rdmNum = (int) (Math.random() * 10);
		value[id] = rdmNum + bonus;
		
	}
	
	// PUBLIC ---------------------------------------------------

	/**
	 * Return a String containing the attributes value you want.
	 * @param n Name STRENGHT
	 * 				 DEXTERITY
	 * 				 CONSTITUTION
	 * 				 STAMINA
	 * 				 INTELLIGENCE
	 * 				 PERCEPTION
	 * 				 CHARISM
	 * 				 WILLPOWER
	 * 				 
	 * @return int value
	 */
	public int getValue(Att n) {
		return this.value[n.id];

	}
	/**
	 * Return a String containing the attributes Name you want.
	 * @param n Name STRENGHT
	 * 				 DEXTERITY
	 * 				 CONSTITUTION
	 * 				 STAMINA
	 * 				 INTELLIGENCE
	 * 				 PERCEPTION
	 * 				 CHARISM
	 * 				 WILLPOWER
	 * 				 
	 * @return String name
	 */
	public String getName(Att n) {
		return n.name;
	}
	/**
	 * Return a String containing the attributes Name you want.
	 * @param n Name STRENGHT
	 * 				 DEXTERITY
	 * 				 CONSTITUTION
	 * 				 STAMINA
	 * 				 INTELLIGENCE
	 * 				 PERCEPTION
	 * 				 CHARISM
	 * 				 WILLPOWER
	 * @param shortName boolean true if you want short name
	 * 				 
	 * @return String name
	 */
	public String getName(Att n, boolean shortName) {
		return (shortName) ? n.shortName : n.name;
	}
	
}
