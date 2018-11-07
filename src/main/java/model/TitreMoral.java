package model;

public enum TitreMoral {
SARL("Société a responsabilité limitée"), EURL("Entreprise unipersonnelle à responsabilité limitée"), SELARL("Société d'exercice libéral à responsabilité limitée"),
	SA("Société anonyme"), SAS("Société par actions simplifiée"), SASU("Société par actions simplifiée unipersonnelle"), SNC("Société en nom collectif"),
	SCP("Soiété civile professionnelle");
	
	private String titreMoral;
	private TitreMoral(String titreMoral) {
		this.titreMoral=titreMoral;
	}
	public String getTitreMoral() {
		return titreMoral;
	}
	
}
