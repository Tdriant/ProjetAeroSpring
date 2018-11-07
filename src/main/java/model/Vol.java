package model;

import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@Entity
@NamedQueries({ // Requetes SQL préparées
	@NamedQuery(name = "Vol.findByIdWithReservations" ,query = "select distinct v from Vol v left join fetch v.reservations where v.id =:id " ),
	@NamedQuery(name = "Vol.findAllWithReservation", query = "select distinct v from Vol v left join fetch v.reservations"),
	@NamedQuery(name="Vol.findByKeyWithEscales", query="select distinct v from Vol v left join fetch v.escales where v.id=:id")
	})
@SequenceGenerator(name = "seqVol", sequenceName = "seq_vol", initialValue = 50, allocationSize = 1)
public class Vol {

	@Id
	@Column(name = "id_vol")
	@GeneratedValue(generator = "seqVol", strategy = GenerationType.SEQUENCE)
	private Integer id;
	@Column(name = "date_depart_vol")
	@Temporal(TemporalType.DATE)
	private Date dateDepart;
	@Column(name = "date_arrivee_vol")
	@Temporal(TemporalType.DATE)
	private Date dateArrivee;
	@Column(name = "heure_depart_vol")
	@Temporal(TemporalType.TIMESTAMP)
	private Date heureDepart;
	@Column(name = "heure_arrivee_vol")
	@Temporal(TemporalType.TIMESTAMP)
	private Date heureArrivee;
	@OneToMany(mappedBy = "key.vol")
	private List<CompagnieAerienneVol> compagnieAerienneVols = new ArrayList<>();
	@OneToMany(mappedBy = "vol")
	private List<Reservation> reservations = new ArrayList<>();
	@OneToMany(mappedBy= "id.vol")
	private List<Escale> escales = new ArrayList<>();
	@Version
	private Integer version;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Date getDateDepart() {
		return dateDepart;
	}

	public void setDateDepart(Date dateDepart) {
		this.dateDepart = dateDepart;
	}

	public Date getDateArrivee() {
		return dateArrivee;
	}

	public void setDateArrivee(Date dateArrivee) {
		this.dateArrivee = dateArrivee;
	}

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

	public List<CompagnieAerienneVol> getCompagnieAerienneVols() {
		return compagnieAerienneVols;
	}

	public void setCompagnieAerienneVols(List<CompagnieAerienneVol> compagnieAerienneVols) {
		this.compagnieAerienneVols = compagnieAerienneVols;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
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
		Vol other = (Vol) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Vol() {
	}

	public Vol(Date dateDepart, Date dateArrivee) {
		this.dateDepart = dateDepart;
		this.dateArrivee = dateArrivee;
	}

	public Vol(Date dateDepart, Date dateArrivee, Date heureDepart, Date heureArrivee) {
		this.dateDepart = dateDepart;
		this.dateArrivee = dateArrivee;
		this.heureDepart = heureDepart;
		this.heureArrivee = heureDepart;
	}

//	@Transient
////	private List<Escale> escales=new ArrayList <>();

}
