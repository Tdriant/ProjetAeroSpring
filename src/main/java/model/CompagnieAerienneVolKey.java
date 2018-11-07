package model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Embeddable
public class CompagnieAerienneVolKey implements Serializable {
	// attributs
	@ManyToOne
	@JoinColumn(name = "compagnie_aerienne_id")
	private CompagnieAerienne compagnieAerienne;
	@ManyToOne
	@JoinColumn(name = "vol_id")
	private Vol vol;

	// contructeurs
	public CompagnieAerienneVolKey() {
	}

	public CompagnieAerienneVolKey(CompagnieAerienne compagnieAerienne, Vol vol) {
		this.compagnieAerienne = compagnieAerienne;
		this.vol = vol;
	}

	// getters
	public CompagnieAerienne getCompagnieAerienne() {
		return compagnieAerienne;
	}

	public Vol getVol() {
		return vol;
	}

	// setters
	public void setCompagnieAerienne(CompagnieAerienne compagnieAerienne) {
		this.compagnieAerienne = compagnieAerienne;
	}

	public void setVol(Vol vol) {
		this.vol = vol;
	}

	// methodes
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((compagnieAerienne == null) ? 0 : compagnieAerienne.hashCode());
		result = prime * result + ((vol == null) ? 0 : vol.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CompagnieAerienneVolKey other = (CompagnieAerienneVolKey) obj;
		if (compagnieAerienne == null) {
			if (other.compagnieAerienne != null)
				return false;
		} else if (!compagnieAerienne.equals(other.compagnieAerienne))
			return false;
		if (vol == null) {
			if (other.vol != null)
				return false;
		} else if (!vol.equals(other.vol))
			return false;
		return true;
	}
}
