package model;

public enum Titre {

	M("monsieur"), MME("madame"), MLLE("mademoiselle");
	
	private String titre;
	private Titre(String titre) {
		this.titre=titre;
	}
	public String getTitre() {
		return titre;
	}
	
}
