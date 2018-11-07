package services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Aeroport;
import model.Vol;
import repositories.AeroportRepository;
import repositories.ReservationRepository;
import repositories.VolRepository;

@Service
public class VolService {

	@Autowired
	private VolRepository volRepo;

	@Autowired
	private AeroportRepository aeroRepo;
	@Autowired
	private ReservationRepository resaRepo;
	// Quels services d�sire t on ?

	// Liste de r�servations pour un vol (# named query appel�e en m�thode)
	// Liste de passagers pour pour un vol(# named query appel�e en m�thode)
	// Annuler un vol (# pr�venir les passagers de l'annulation par message et
	// annuler leur r�servation : id�alement; fournir un voyage gratuit en �change)
	// Get infos vol : obtenir les infos li�es au vol ; avion, ca, lieu
	// d�part/arriv�e, heure/date d�part/arriv�e
	// cr�ation de vol # Classique

	public void createVol(Date dateDepart, Date dateArrivee, Date HeureDepart, Date Heurearrivee, Aeroport LieuDepart,
			Aeroport LieuArrivee) {
		for (Aeroport a : aeroRepo.findAll()) {
			if (a == LieuDepart) {
				for (Aeroport ae : aeroRepo.findAll()) {
					if (ae == LieuArrivee) {
						if (HeureDepart.before(Heurearrivee)) {
							if (dateDepart.before(dateArrivee) || dateDepart.equals(dateArrivee)) {
								Vol vol = new Vol(dateDepart, dateArrivee, HeureDepart, Heurearrivee);
								volRepo.save(vol);
								System.out.println("Votre vol a �t� cr�� et enregistr�");
							}
						} else if (dateDepart.before(dateArrivee)) {
							Vol vol = new Vol(dateDepart, dateArrivee, HeureDepart, Heurearrivee);
							volRepo.save(vol);
							System.out.println("Votre vol a �t� cr�� et enregistr�");
						} else {
							System.out.print("Votre vol n'est pas valide");
						}
					}
				}
			}
		}
	}
	public List<Reservation> getResa()
}
