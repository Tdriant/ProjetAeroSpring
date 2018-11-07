package model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@Entity
public class Escale {
	@EmbeddedId
	@Column(name="id_escale")
	private EscaleKey id;
	@Column(name="heure_depart")
	@Temporal(TemporalType.TIMESTAMP)
	private Date heureDepart;
	@Column(name="heure_arrivee")
	@Temporal(TemporalType.TIMESTAMP)
	private Date heureArrivee;
	@Version
	private int version;
	
	public Date getHeureDepart() {
		return heureDepart;
	}

	public void setHeureDepart(Date heureDepart) {
		this.heureDepart = heureDepart;
	}

	public Date getHeureArrivee() {
		return heureArrivee;
	}

	public void setHeureArrivee(Date heureArrivee) {
		this.heureArrivee = heureArrivee;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public EscaleKey getId() {
		return id;
	}

	public void setId(EscaleKey id) {
		this.id = id;
	}

	public Escale(EscaleKey id) {
		super();
		this.id = id;
	}

	public Escale() {
		super();
	}

	public Escale(EscaleKey id, Date heureDepart, Date heureArrivee) {
		super();
		this.id = id;
		this.heureDepart = heureDepart;
		this.heureArrivee = heureArrivee;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Escale other = (Escale) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
	
	
	
}
