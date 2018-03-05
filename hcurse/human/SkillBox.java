package hcurse.human;

public class SkillBox {
	Skills skill[] = new Skills[13];
	// Penser ensuite a faire un type competences qui récupere de l'experience
	// 0:Deplacement / 1:Fouille / 2:Recolte / 3:Cuisine / 4:Combat /
	// 5:Administration / 6:Medicale /
	// 7:GeoScience / 8:Biologie / 9:Genetique / 10:Mecanique / 11:Informatique /
	// 12:Electronique

	public SkillBox() {

		this.createSkills();
	}

	private void createSkills() {

		skill[0] = new Skills("Deplacement", 0);
		skill[1] = new Skills("Fouille", 0);
		skill[2] = new Skills("Recolte", 0);
		skill[3] = new Skills("Cuisine", 0);
		skill[4] = new Skills("Combat", 0);
		skill[5] = new Skills("Administration", 0);
		skill[6] = new Skills("Medicale", 0);
		skill[7] = new Skills("GeoScience", 0);
		skill[8] = new Skills("Biologie", 0);
		skill[9] = new Skills("Genetique", 0);
		skill[10] = new Skills("Mecanique", 0);
		skill[11] = new Skills("Informatique", 0);
		skill[12] = new Skills("Electronique", 0);
	}

	public void printSkills() {

		for (int i = 0; i < skill.length; i++) {
			skill[i].printSkill();
		}
	}

}
