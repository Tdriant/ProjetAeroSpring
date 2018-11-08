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

	public void createVol(Vol vol) {

        Optional<Aeroport> aer = aeroRepo.findById(vol.getAeroportDepart().getId());
        Optional<Aeroport> aere = aeroRepo.findById(vol.getAeroportArrivee().getId());
        if (aer.isPresent() && aere.isPresent()) {
            if (vol.getHeureDepart().before(vol.getHeureArrivee())) {
                if (vol.getDateDepart().before(vol.getDateArrivee())
                        || vol.getDateDepart().equals(vol.getDateArrivee())) {
                    volRepo.save(vol);
                }
            } else if (vol.getDateDepart().before(vol.getDateArrivee())) {
                volRepo.save(vol);
            } else {
                System.out.print("Votre vol n'est pas valide");
            }
        }
    }


//    public void deleteVolById(Integer id) {
//        Optional<Vol> aer = volRepo.findById(vol.getId());
//        if (aer.isPresent()) {
//            for (Reservation r : vol.getReservations()) {
//                Optional<Reservation> aero = resaRepo.findById(r.getId());
//                if (aero.isPresent()) {
//                    resaRepo.delete(r);
//                }
//            }
//            volRepo.delete(vol);
//        }
//    }

    
    public void deleteVolById(Integer id) {
    	Optional<Vol> ero = volRepo.findByIdWithReservation(id);
    	if(ero.isPresent()) {
    		for(Reservation r : ero.get().getReservations()) {
    			resaRepo.delete(r);
    		}
    		volRepo.delete(ero.get());
    	}
    }
    
    public void deleteVol(Vol vol) {
        deleteVolById(vol.getId());
    }

    public Optional<Reservation> getReservations(Integer volId) {
        Optional<Reservation> resas = volRepo.findReservationByVolId(volId);
        return resas;
    }

    public void addReservation(Reservation reservation, Vol vol) {

        Optional<Reservation> aer = resaRepo.findById(reservation.getId());
        if (aer.isPresent()) {
            vol.addReservation(reservation);
            volRepo.save(vol);
            System.out.println("Reservation validée et ajoutée ! Bon vol.");
        }
    }
}