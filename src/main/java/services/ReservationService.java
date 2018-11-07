package services;

import org.springframework.beans.factory.annotation.Autowired;

import model.Client;
import model.Passager;
import model.Reservation;
import model.Vol;
import repositories.ClientRepository;

public class ReservationService {

	@Autowired
	private ClientRepository clientRepository;// on considère que le vol est automatiquement en base (menu déroulant
												// proposant des vols)

	public void CreerReservation(Client client, Vol vol, String nomPassager, String prenomPassager) {

		Passager passager = new Passager(nomPassager, prenomPassager);

		this.CreerReservation(client, vol, passager);
	}

	public void CreerReservation(Client client, Vol vol, Passager passager) {

		if (client.getId() == null) {
			clientRepository.save(client);
			System.out.println("Un compte client va être créé");
		} else {
			System.out.println("Merci pour votre réservation");
		}
		this.CreerReservation(client, vol, passager);

		Reservation reservation = new Reservation();

		System.out.println("Merci " + client.getNom() + " pour votre réservation numéro : " + reservation.getId()
				+ " du " + reservation.getDate() + " à bord du vol " + vol.getCompagnieAerienneVols() + " numéro : "
				+ vol.getId() + "." + " /n" + "Pour rappel, votre vol numéro" + vol.getId() + " partira le "
				+ vol.getDateDepart() + " à " + vol.getHeureDepart() + " et arrivera le " + vol.getDateArrivee() + " à "
				+ vol.getHeureArrivee() + ". ");
	}

}
