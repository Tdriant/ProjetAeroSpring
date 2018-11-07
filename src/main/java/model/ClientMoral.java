package model;


import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;


@Entity
@DiscriminatorValue(value="Moral")
public class ClientMoral extends Client {
	@Enumerated(EnumType.STRING)
	@Column(name="titre_moral")
	private TitreMoral titreMoral;
	@Column(name="siret")
	private String siret;
	
	
	
	public ClientMoral() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ClientMoral(String nom, Integer numeroTelephone, Integer numeroFax, String email) {
		super(nom, numeroTelephone, numeroFax, email);
		// TODO Auto-generated constructor stub
	}
	public ClientMoral(TitreMoral titreMoral, String siret) {
		super();
		this.titreMoral = titreMoral;
		this.siret = siret;
	}
	public TitreMoral getTitreMoral() {
		return titreMoral;
	}
	public void setTitreMoral(TitreMoral titreMoral) {
		this.titreMoral = titreMoral;
	}
	public String getSiret() {
		return siret;
	}
	public void setSiret(String siret) {
		this.siret = siret;
	}
	
	
	
}
