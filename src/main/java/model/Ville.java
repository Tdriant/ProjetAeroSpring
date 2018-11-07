package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "villes")
@NamedQueries({
@NamedQuery(name = "Ville.findAllWithAeroport", query = "select distinct v from Ville v left join fetch v.villesAeroport va left join fetch va.key.aeroport"),
@NamedQuery(name = "Ville.findByIdWithAeroport", query = "select distinct v from Ville v left join fetch v.villesAeroport va left join fetch va.key.aeroport where v.id = :id"),
})
@SequenceGenerator(name = "seqVille", sequenceName = "seq_ville", initialValue = 50, allocationSize = 1)
public class Ville {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqVille")
	private Integer vill_id;
	@Column(name = "ville", length = 150)
	private String nom;
	@Version
	private int version;
	@OneToMany(mappedBy = "key.ville")
	private List<VilleAeroport> villesAeroport = new ArrayList<>();

	// constructeur
	public Ville() {

		// TODO Auto-generated constructor stub
	}

	public Ville(String nom) {
		super();
		this.nom = nom;
	}

	// methodes
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Integer getId() {
		return vill_id;
	}

	public void setId(Integer id) {
		this.vill_id = id;
	}

	public Integer getVill_id() {
		return vill_id;
	}

	public void setVill_id(Integer vill_id) {
		this.vill_id = vill_id;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public List<VilleAeroport> getVillesAeroport() {
		return villesAeroport;
	}

	public void setVillesAeroport(List<VilleAeroport> villesAeroport) {
		this.villesAeroport = villesAeroport;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((vill_id == null) ? 0 : vill_id.hashCode());
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
		Ville other = (Ville) obj;
		if (vill_id == null) {
			if (other.vill_id != null)
				return false;
		} else if (!vill_id.equals(other.vill_id))
			return false;
		return true;
	}
}
