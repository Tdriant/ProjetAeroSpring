package model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@DiscriminatorValue(value="personne")
public class ClientPhysique extends Client {
	@Enumerated(EnumType.STRING)
	@Column(name="titre")
	private Titre titre;
	@Column(name="first_name")
	private String prenom;
	
	
	public Titre getTitre() {
		return titre;
	}
	public void setTitre(Titre titre) {
		this.titre = titre;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public ClientPhysique(Titre titre, String prenom) {
		super();
		this.titre = titre;
		this.prenom = prenom;
	}
	public ClientPhysique() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ClientPhysique(String nom, Integer numeroTelephone, Integer numeroFax, String email) {
		super(nom, numeroTelephone, numeroFax, email);
		// TODO Auto-generated constructor stub
	}
	
		
}
