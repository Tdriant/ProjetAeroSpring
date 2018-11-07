package services;

import org.springframework.beans.factory.annotation.Autowired;

import model.Client;
import model.Passager;
import model.Reservation;
import model.Vol;
import repositories.ClientRepository;

public class ReservationService {

	@Autowired
	private ClientRepository clientRepository;// on consid�re que le vol est automatiquement en base (menu d�roulant
												// proposant des vols)

	public void CreerReservation(Client client, Vol vol, String nomPassager, String prenomPassager) {

		Passager passager = new Passager(nomPassager, prenomPassager);

		this.CreerReservation(client, vol, passager);
	}

	public void CreerReservation(Client client, Vol vol, Passager passager) {

		if (client.getId() == null) {
			clientRepository.save(client);
			System.out.println("Un compte client va �tre cr��");
		} else {
			System.out.println("Merci pour votre r�servation");
		}
		this.CreerReservation(client, vol, passager);

		Reservation reservation = new Reservation();

		System.out.println("Merci " + client.getNom() + " pour votre r�servation num�ro : " + reservation.getId()
				+ " du " + reservation.getDate() + " � bord du vol " + vol.getCompagnieAerienneVols() + " num�ro : "
				+ vol.getId() + "." + " /n" + "Pour rappel, votre vol num�ro" + vol.getId() + " partira le "
				+ vol.getDateDepart() + " � " + vol.getHeureDepart() + " et arrivera le " + vol.getDateArrivee() + " � "
				+ vol.getHeureArrivee() + ". ");
	}

}
