import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import dao.DaoAeroportFactory;
import dao.DaoClient;
import dao.DaoClientFactory;
import dao.DaoReservation;
import dao.DaoReservationFactory;
import dao.DaoVilleAeroportFactory;
import dao.DaoVilleFactory;
import model.Aeroport;
import model.Client;
import model.ClientEI;
import model.Reservation;
import model.Titre;
import model.Ville;
import model.VilleAeroport;
import model.VilleAeroportKey;
import util.Context;

public class TestResaClient {

	private static DaoClient daoClient;
	private static DaoReservation daoReservation;
	private static Client a;
	private static Reservation r;

	@BeforeClass
	public static void initiDaoClientReservation() {
		daoClient = DaoClientFactory.getInstance();
		daoReservation = DaoReservationFactory.getInstance();
	}

	@AfterClass
	public static void fermetureJpa() {
		Context.destroy();
	}

	@Test
	public void findByKey() {
		SimpleDateFormat date = new SimpleDateFormat("DD/MM/YYYY");
		try {
			a = new ClientEI("toto", 25, 47, "yeah");
			r = new Reservation(date.parse("11/12/03"), 38);
			r.setClient(a);
			daoClient.create(a);
			daoReservation.create(r);
			assertEquals(a, daoReservation.findByKey(r.getId()).getClient());

		} catch (

		ParseException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void insertClientReservation() {
		Reservation a1;
		Reservation a2;
		a1 = new Reservation();
		a2 = new Reservation();
		daoReservation.create(a2);
		assertNull(a1.getId());
		daoReservation.create(a1);
		assertNotNull(a1.getId());
		ClientEI client = new ClientEI(Titre.MLLE, "tata");
		daoClient.create(client);
		a1.setClient(client);
		a2.setClient(client);
		daoReservation.update(a1);
		daoReservation.update(a2);

		assertNotNull(client.getId());
	}
}
