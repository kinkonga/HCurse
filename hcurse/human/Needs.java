package hcurse.human;

import java.awt.Color;

public class Needs {
	
	// ENUM -----------------------------------------------------
	
	public enum Need {
		FOOD(0, "FOOD", "F"), HEALTH(2, "HEALTH", "H"), SOCIAL(4, "SONIAL", "S"), ENVIRONMENT(5, "ENVIRONMENT", "E");
		private int id = 0;
		private String name = null;
		private String firstCh = null;

		Need(int id, String name, String firstCh) {
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
	
	public static Needs build() {
		return new Needs();
	}
	
	// PRIVATE --------------------------------------------------
	
	private Needs() {
		for (Need nee : Need.values()) {
			CreateNeed(nee);
		}	
	}
	private void CreateNeed(Need n) {

		int rdmNum = (int) (Math.random() * 100);
		value[n.id] = rdmNum;
		
		int rdmNum2 = (int) (Math.random() * 30);
		int rdmNum3 = (int) (Math.random() * 30);

		this.limitMin[n.id] = rdmNum2;
		this.limitMax[n.id] = 100 - rdmNum3;
	}
	
	// PUBLIC ---------------------------------------------------
	
	public void handleTime() {
		value[Need.FOOD.id] -= 0.1;
	}
	
	public String getWarningMessage() {
		String s = "";
		
		if(value[Need.FOOD.id] <= limitMin[Need.FOOD.id]) {
			s += "I'm hungry !\n";
		}
		
		return s;
	}
	
	public Color setColor(Need n) {
		Color c = Color.GREEN;
		
		if(value[n.id] <= limitMin[n.id]) {
			c = Color.RED;
		}
		else if(value[n.id] >= limitMax[n.id]) {
			c = Color.CYAN;
		}
		
		return c;
	}
	public String getName(Need n) {
		return this.getName(n, false);
	}
	public String getName(Need n, boolean shortName) {
		return (shortName) ? n.firstCh : n.name;
	}
	public double getValue(Need n) {
		return value[n.id];
	}
	public int getLimitMax(Need n) {
		return limitMax[n.id];
	}
	public int getLimitMin(Need n) {
		return limitMin[n.id];
	}

	
	
	
	
	
}
