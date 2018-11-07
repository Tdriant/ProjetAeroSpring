package services;

import static org.junit.Assert.assertFalse;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import model.Client;
import model.Passager;
import model.Reservation;
import model.Vol;
import repositories.ClientRepository;
import repositories.ReservationRepository;

public class ReservationService {
	@Autowired
	private ClientRepository clientRepository;// on consid�re que le vol est automatiquement en base (menu d�roulant
												// proposant des vols)
	@Autowired
	private ReservationRepository reservationRepository;

	public void CreerReservation(Client client, Vol vol, String nomPassager, String prenomPassager, Date date) {
		Passager passager = new Passager(nomPassager, prenomPassager);
		this.CreerReservation(client, vol, passager, date);
	}

	public void CreerReservation(Client client, Vol vol, Passager passager, Date date) {
		if (client.getId() == null) {
			clientRepository.save(client);
			System.out.println("Un compte client va �tre cr��");
		}
		Reservation reservation = new Reservation(date, vol.getId());
		reservation.setPassager(passager);
		reservation.setClient(client);
		this.CreerReservation(reservation);
	}

	public Integer CreerReservation(Reservation reservation) {
		reservationRepository.save(reservation);
		System.out.println("Merci " + reservation.getClient().getNom() + " pour votre r�servation num�ro : "
				+ reservation.getId() + " du " + reservation.getDate() + " � bord du vol "
				+ reservation.getVol().getCompagnieAerienneVols() + " num�ro : " + reservation.getVol().getId() + "."
				+ " /n" + "Pour rappel, votre vol num�ro" + reservation.getVol().getId() + " partira le "
				+ reservation.getVol().getDateDepart() + " � " + reservation.getVol().getHeureDepart()
				+ " et arrivera le " + reservation.getVol().getDateArrivee() + " � "
				+ reservation.getVol().getHeureArrivee() + ". ");
		return reservation.getId();
	}

	public void SupprimerReservation(Reservation reservation) {
		reservationRepository.delete(reservation);
		System.out.println("Votre r�servation num�ro : "+reservation.getId()+" a bien �t� annul�e");
	}
}
