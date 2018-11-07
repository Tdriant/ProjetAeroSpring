package model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "VilleAeroport")
public class VilleAeroport {
	@EmbeddedId
	private VilleAeroportKey key;

	// constructeur
	public VilleAeroport() {

	}

	public VilleAeroport(VilleAeroportKey key) {
		super();
		this.key = key;
	}

	// methodes
	public VilleAeroportKey getKey() {
		return key;
	}

	public void setKey(VilleAeroportKey key) {
		this.key = key;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
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
		VilleAeroport other = (VilleAeroport) obj;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		return true;
	}

}
