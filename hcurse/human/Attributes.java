package hcurse.human;


/**
 * Attributs - Create Attributs
 * @author Nicola Lacambre
 */
public class Attributes {

	public enum Attribute { 
		STRENGHT(0, "Strenght" , "STR"), 
		AGILITY(1, "Agility" , "AGI"), 
		TOUGHNESS(2, "Toughness", "TOU"), 
		ENDURANCE(3,"Endurance", "END"), 
		RECUPERATION(4,"Recuperation", "REC"), 
		DISEASE_RESISTANCE(5, "Disease Resistance", "DRE"), 
		ANALYTICAL_ABILITY(6, "Analytical Ability", "AAB"),
		CREATIVITY(7, "Creativity", "CRE"),
		EMPATHY(8, "Empathy", "EMP"),
		FOCUS(9, "Focus", "FOC"),
		INTUITION(10, "Intuition", "INT"),
		KINESTHETIC_SENSE(11,"Kinesthetic Sense", "KIS"),
		LINGUISTIC_ABILITY(12,"Linguistic Ability", "LAB"),
		MEMORY(13,"Memory", "MEM"),
		MUSICALITY(14,"Musicality", "MUS"),
		PATIENCE(15,"Patience", "PAT"),
		SOCIAL_AWARENESS(16,"Social Awareness", "SAW"),
		SPACIAL_SENSE(17,"Spacial Sense", "SPS"),
		WILLPOWER(18, "Willpower", "WIL");

		private int id = 0;
		private String name = null;
		private String shortName = null;

		Attribute(int id, String n, String sn) {
			this.id = id;
			this.name = n;
			this.shortName = sn;

		}

		public String toString() {
			return id + " " + name;
		}
	}

	// VARIABLES ------------------------------------------------
	
	int[] value = new int[19];
	
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
		
		for (Attribute att : Attribute.values()) {
			create(0, att.id);
		}
	}
	private void create(int bonus, int id) {

		int rdmNum = (int) (Math.random() * 100);
		value[id] = rdmNum + bonus;
		
	}
	
	// PUBLIC ---------------------------------------------------

	/**
	 * Return a String containing the attributes value you want.
	 * @param n Name  
	 * @return int value
	 */
	public int getValue(Attribute n) {
		return this.value[n.id];

	}
	/**
	 * Return a String containing the attributes Name you want.
	 * @param n Name 
	 * @return String name
	 */
	public String getName(Attribute n) {
		return n.name;
	}
	/**
	 * Return a String containing the attributes Name you want.
	 * @param n Name 
	 * @param shortName boolean true if you want short name
	 * 				 
	 * @return String name
	 */
	public String getName(Attribute n, boolean shortName) {
		return (shortName) ? n.shortName : n.name;
	}
	
}
