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
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "aeroports")
@SequenceGenerator(name = "seqAeroport", sequenceName = "seq_aeroport", initialValue = 50, allocationSize = 1)
public class Aeroport {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqAeroport")
	private Integer aero_id;
	@Column(name = "aeroport", length = 150)
	private String nom;
	@Version
	private int version;
	@OneToMany(mappedBy = "key.aeroport")
	private List<VilleAeroport> villeAeroports = new ArrayList<>();// a voir si on garde l'instan arraylist
	@OneToMany(mappedBy= "id.aeroport")
	private List<Escale> escales= new ArrayList<>();
	
	// constructeur

	public Aeroport(String nom) {
		this.nom = nom;
	}

	public Aeroport() {

		// TODO Auto-generated constructor stub
	}

	// methodes
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Integer getId() {
		return aero_id;
	}

	public void setId(Integer id) {
		this.aero_id = id;
	}

	public Integer getAero_id() {
		return aero_id;
	}

	public void setAero_id(Integer aero_id) {
		this.aero_id = aero_id;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public List<VilleAeroport> getVilleAeroports() {
		return villeAeroports;
	}

	public void setVilleAeroports(List<VilleAeroport> villeAeroports) {
		this.villeAeroports = villeAeroports;
	}

	public List<Escale> getEscales() {
		return escales;
	}

	public void setEscales(List<Escale> escales) {
		this.escales = escales;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aero_id == null) ? 0 : aero_id.hashCode());
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
		Aeroport other = (Aeroport) obj;
		if (aero_id == null) {
			if (other.aero_id != null)
				return false;
		} else if (!aero_id.equals(other.aero_id))
			return false;
		return true;
	}

}
