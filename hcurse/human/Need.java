package hcurse.human;

import java.awt.Color;

public class Need {
	
	// ENUM -----------------------------------------------------
	
	public enum Nee {
		FOOD(0, "FOOD", "F"), HEALTH(2, "HEALTH", "H"), SOCIAL(4, "SONIAL", "S"), ENVIRONMENT(5, "ENVIRONMENT", "E");
		private int id = 0;
		private String name = null;
		private String firstCh = null;

		Nee(int id, String name, String firstCh) {
			this.id = id;
			this.name = name;
			this.firstCh = firstCh;
		}

		@Override
		public String toString() {
			return "" + id;
		}
	}
	
	// VARIABLES ------------------------------------------------
	
	private double[] value = new double[6];
	private int[] limitMin = new int[6];
	private int[] limitMax = new int[6];
	
	// CONSTRUCTOR ----------------------------------------------
	
	public static Need build() {
		return new Need();
	}
	
	// PRIVATE --------------------------------------------------
	
	private Need() {
		for (Nee nee : Nee.values()) {
			CreateNeed(nee);
		}	
	}
	private void CreateNeed(Nee n) {

		int rdmNum = (int) (Math.random() * 100);
		value[n.id] = rdmNum;
		
		int rdmNum2 = (int) (Math.random() * 30);
		int rdmNum3 = (int) (Math.random() * 30);

		this.limitMin[n.id] = rdmNum2;
		this.limitMax[n.id] = 100 - rdmNum3;
	}
	
	// PUBLIC ---------------------------------------------------
	
	public void handleTime() {
		value[Nee.FOOD.id] -= 0.1;
	}
	
	public String getWarningMessage() {
		String s = "";
		
		if(value[Nee.FOOD.id] <= limitMin[Nee.FOOD.id]) {
			s += "I'm hungry !\n";
		}
		
		return s;
	}
	
	public Color setColor(Nee n) {
		Color c = Color.GREEN;
		
		if(value[n.id] <= limitMin[n.id]) {
			c = Color.RED;
		}
		else if(value[n.id] >= limitMax[n.id]) {
			c = Color.CYAN;
		}
		
		return c;
	}
	public String getName(Nee n) {
		return this.getName(n, false);
	}
	public String getName(Nee n, boolean shortName) {
		return (shortName) ? n.firstCh : n.name;
	}
	public double getValue(Nee n) {
		return value[n.id];
	}
	public int getLimitMax(Nee n) {
		return limitMax[n.id];
	}
	public int getLimitMin(Nee n) {
		return limitMin[n.id];
	}

	
	
	
	
	
}
