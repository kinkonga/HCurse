package hcurse.nature;

public class BioEntity {
	
	/* 
	 * Les bioElements sont tous les objets composant le monde exterieur.
	 * -> 3 bioGenes Maximum qui définissent leur composante chimique.
	 * -> 1 element qui definie sa composante physique (fibre, roche, bois, ...)
	 * 
	 * Les bioGenes peuvent avoir des effets sur :
	 * - les attributs
	 * - les besoins
	 */
	
	private int name;
	private BioGene[] bioGene = new BioGene[4];
	
	
	
	
}
