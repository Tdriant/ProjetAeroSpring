package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "adresse")
@SequenceGenerator(name = "seqAdresse", sequenceName = "adre_seq_id", initialValue = 50, allocationSize = 1)
public class Adresse {
	// attributs
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqAdresse")
	@Column(name = "adre_id")
	private Integer id;
	@Column(name = "adre_numero")
	private Integer numero;
	@Column(name = "adre_rue", length = 200)
	private String rue;
	@Column(name = "adre_code_postal", length = 5)
	private String codePostal;
	@Column(name = "adre_vill", length = 100)
	private String ville;
	@Column(name = "adhe_pays", length = 100)
	private String pays;
	@OneToOne
	@JoinColumn(name = "client")
	private Client client;
	@OneToOne
	@JoinColumn(name = "passager")
	private Passager passager;
	@Version
	@Column(name = "adre_version")
	private int version;

	// constructeurs
	public Adresse() {
	}

	public Adresse(Integer id, Integer numero, String rue, String codePostal, String ville, String pays) {
		this(numero, rue, codePostal, ville, pays);
		this.id = id;

	}

	public Adresse(Integer numero, String rue, String codePostal, String ville, String pays) {
		super();
		this.numero = numero;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.pays = pays;
	}

	// getters
	public Integer getId() {
		return id;
	}

	public Integer getNumero() {
		return numero;
	}

	public String getRue() {
		return rue;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public String getVille() {
		return ville;
	}

	public String getPays() {
		return pays;
	}

	public int getVersion() {
		return version;
	}

	public Client getClient() {
		return client;
	}

	public Passager getPassager() {
		return passager;
	}

	// setters
	public void setId(Integer id) {
		this.id = id;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public void setPassager(Passager passager) {
		this.passager = passager;
	}

	// methodes
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Adresse other = (Adresse) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
