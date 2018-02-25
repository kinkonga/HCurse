package hcurse.human;

public class SkillBox {
	Skill skill[] = new Skill[13];
	// Penser ensuite a faire un type competences qui récupere de l'experience
	// 0:Deplacement / 1:Fouille / 2:Recolte / 3:Cuisine / 4:Combat /
	// 5:Administration / 6:Medicale /
	// 7:GeoScience / 8:Biologie / 9:Genetique / 10:Mecanique / 11:Informatique /
	// 12:Electronique

	public SkillBox() {

		this.createSkills();
	}

	private void createSkills() {

		skill[0] = new Skill("Deplacement", 0);
		skill[1] = new Skill("Fouille", 0);
		skill[2] = new Skill("Recolte", 0);
		skill[3] = new Skill("Cuisine", 0);
		skill[4] = new Skill("Combat", 0);
		skill[5] = new Skill("Administration", 0);
		skill[6] = new Skill("Medicale", 0);
		skill[7] = new Skill("GeoScience", 0);
		skill[8] = new Skill("Biologie", 0);
		skill[9] = new Skill("Genetique", 0);
		skill[10] = new Skill("Mecanique", 0);
		skill[11] = new Skill("Informatique", 0);
		skill[12] = new Skill("Electronique", 0);
	}

	public void printSkills() {

		for (int i = 0; i < skill.length; i++) {
			skill[i].printSkill();
		}
	}

}
