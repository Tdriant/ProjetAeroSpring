package model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;
import javax.persistence.Version;

@Entity
@SequenceGenerator(name = "seqResa", sequenceName = "seq_resa", allocationSize = 1, initialValue = 50)
public class Reservation {
	@Id
	@GeneratedValue(generator = "seqResa", strategy = GenerationType.SEQUENCE)
	@Column(name = "id_reservation")
	private Integer id;
	@Column(name = "date_resa")
	private Date date;
	@Transient
	@Column(name = "numero_vol")
	private Integer numeroVol;
	@Version
	private Integer version;
	@ManyToOne
	@JoinColumn(name = "id_passager")
	private Passager passager;
	@ManyToOne 
	@JoinColumn(name = "id_client")
	private Client client;
	@ManyToOne
	@JoinColumn(name = "id_vol")
	private Vol vol;

	public Reservation() {
	}

	public Integer getId() {
		return id;
	}

	public Passager getPassager() {
		return passager;
	}

	public void setPassager(Passager passager) {
		this.passager = passager;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getNumeroVol() {
		return numeroVol;
	}

	public void setNumeroVol(Integer numeroVol) {
		this.numeroVol = numeroVol;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	
	public Vol getVol() {
		return vol;
	}

	public void setVol(Vol vol) {
		this.vol = vol;
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
		Reservation other = (Reservation) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Reservation(Date date, Integer numeroVol) {
		super();
		this.date = date;
		this.numeroVol = numeroVol;
	}

}
