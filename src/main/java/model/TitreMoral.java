package model;

public enum TitreMoral {
SARL("Soci�t� a responsabilit� limit�e"), EURL("Entreprise unipersonnelle � responsabilit� limit�e"), SELARL("Soci�t� d'exercice lib�ral � responsabilit� limit�e"),
	SA("Soci�t� anonyme"), SAS("Soci�t� par actions simplifi�e"), SASU("Soci�t� par actions simplifi�e unipersonnelle"), SNC("Soci�t� en nom collectif"),
	SCP("Soi�t� civile professionnelle");
	
	private String titreMoral;
	private TitreMoral(String titreMoral) {
		this.titreMoral=titreMoral;
	}
	public String getTitreMoral() {
		return titreMoral;
	}
	
}
