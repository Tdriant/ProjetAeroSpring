package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "seqCA", sequenceName = "seq_ca", initialValue = 50, allocationSize = 1)
public class CompagnieAerienne {
	@Id
	@Column(name = "id_compagnie_aerienne", length = 20)
	@GeneratedValue(generator = "seqCA", strategy = GenerationType.SEQUENCE)
	private Integer id;
	@Column(name = "nom_compagnie_aerienne", length = 20)
	private String nom;
	@OneToMany(mappedBy = "key.compagnieAerienne")
	private List<CompagnieAerienneVol> compagnieAeriennesVol = new ArrayList<>();

	public CompagnieAerienne(String nom) {
		super();
		this.nom = nom;
	}

	public CompagnieAerienne() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<CompagnieAerienneVol> getCompagnieAeriennesVol() {
		return compagnieAeriennesVol;
	}

	public void setCompagnieAeriennesVol(List<CompagnieAerienneVol> compagnieAeriennesVol) {
		this.compagnieAeriennesVol = compagnieAeriennesVol;
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
		CompagnieAerienne other = (CompagnieAerienne) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
