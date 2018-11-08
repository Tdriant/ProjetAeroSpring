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

	// Quels services d�sire t on ?

	// Liste de r�servations pour un vol (# named query appel�e en m�thode)
	// Liste de passagers pour pour un vol(# named query appel�e en m�thode)
	// Annuler un vol (# pr�venir les passagers de l'annulation par message et
	// annuler leur r�servation : id�alement; fournir un voyage gratuit en �change)
	// Get infos vol : obtenir les infos li�es au vol ; avion, ca, lieu
	// d�part/arriv�e, heure/date d�part/arriv�e
	// cr�ation de vol # Classique

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
            System.out.println("Reservation valid�e et ajout�e ! Bon vol.");
        }
    }
}