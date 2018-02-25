package hcurse.human;

public class Skill {
	private String name;
	private int lvl;

	public Skill() {

	}

	public Skill(String name, int bonus) {

		this.name = name;
		int rdmNum = (int) (Math.random() * 10);
		this.lvl = rdmNum + bonus;

	}

	public void printSkill() {
		System.out.println(name + " : " + lvl);
	}
}
