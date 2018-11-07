package services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Reservation;
import model.Aeroport;
import model.Vol;
import repositories.AeroportRepository;
import repositories.ReservationRepository;
import repositories.VolRepository;

@Service
public class VolService {

	@Autowired
	private VolRepository volRepo;

	private Vol vol;
	@Autowired
	private AeroportRepository aeroRepo;
	@Autowired
	private ReservationRepository resaRepo;

	// Quels services désire t on ?

	// Liste de réservations pour un vol (# named query appelée en méthode)
	// Liste de passagers pour pour un vol(# named query appelée en méthode)
	// Annuler un vol (# prévenir les passagers de l'annulation par message et
	// annuler leur réservation : idéalement; fournir un voyage gratuit en échange)
	// Get infos vol : obtenir les infos liées au vol ; avion, ca, lieu
	// départ/arrivée, heure/date départ/arrivée
	// création de vol # Classique

	public void createVol(Date dateDepart, Date dateArrivee, Date HeureDepart, Date Heurearrivee, Aeroport LieuDepart,
			Aeroport LieuArrivee) {

		for (Aeroport a : aeroRepo.findAll()) {
			if (a == LieuDepart) {
				for (Aeroport ae : aeroRepo.findAll()) {
					if (ae == LieuArrivee) {
						if (HeureDepart.before(Heurearrivee)) {
							if (dateDepart.before(dateArrivee) || dateDepart.equals(dateArrivee)) {
								 vol = new Vol(dateDepart, dateArrivee, HeureDepart, Heurearrivee, LieuDepart,
										LieuArrivee);
								volRepo.save(vol);
								System.out.println("Votre vol a été créé et enregistré");
							}
						} else if (dateDepart.before(dateArrivee)) {
							 vol = new Vol(dateDepart, dateArrivee, HeureDepart, Heurearrivee, LieuDepart,
									LieuArrivee);
							volRepo.save(vol);
							System.out.println("Votre vol a été créé et enregistré");
						} else {
							System.out.print("Votre vol n'est pas valide");
						}
					}
				}
			}
		}
	}

	public Optional<Reservation> getResa(Integer volId) {
		Optional<Reservation> resas = volRepo.findReservationByVolId(volId);
		return resas;
	}

	public void addReservation(Reservation reservation) {
		for (Reservation r : resaRepo.findAll()) {
			if (r == reservation) {
				vol.addReservation(reservation);
				volRepo.save(vol);
				System.out.println("Reservation validée et ajoutée ! Bon vol.");
			}
		}
	}
}
