package model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "passager")
@NamedQueries({@NamedQuery(name = "Passager.findByIdWithReservation" ,query = "select distinct p from Passager p left join fetch p.reservations where p.id =:id" ),
	@NamedQuery(name = "Passager.findAllWithReservation", query = "select distinct p from Passager p left join fetch p.reservations")})

@SequenceGenerator(name = "seqPassager", sequenceName = "seq_passager", initialValue = 1, allocationSize = 10)
public class Passager {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqPassager")
	@Column(name = "pass_id")
	private Integer id;
	@Column(name = "pass_nom", length = 50)
	private String nom;
	@Column(name = "pass_prenom", length = 50)
	private String prenom;
	@Version
	private int version;
	@OneToMany(mappedBy="passager")
	private List<Reservation> reservations;
	@OneToOne(mappedBy = "passager")
	private Adresse adresse;

	public Passager() {
	}

	public Passager(String nom, String prenom) {
		super();
		this.nom = nom;
		this.prenom = prenom;
	}


	public void addReservation(Reservation reservation) {
		this.reservations.add(reservation);
	}
	public void enleverReservation(Reservation reservation) {
		this.reservations.remove(reservation);
	}
	
	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
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

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}
	
	

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
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
		Passager other = (Passager) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
