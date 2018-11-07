import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import dao.DaoReservation;
import dao.DaoReservationFactory;
import dao.DaoVol;
import dao.DaoVolFactory;
import model.Reservation;
import model.Vol;
import util.Context;

public class TestResaVol {

	private static DaoVol daoVol;
	private static DaoReservation daoReservation;

	@BeforeClass
	public static void initiDaoPersonne() {
		daoVol = DaoVolFactory.getInstance();
		daoReservation = DaoReservationFactory.getInstance();
	}

	@AfterClass
	public static void fermetureJpa() {
		Context.destroy();
	}

	@Test
	public void insertPassagerReservation() {
		Reservation a1;
		Reservation a2;
		a1 = new Reservation();
		a2 = new Reservation();
		daoReservation.create(a2);
		assertNull(a1.getId());
		daoReservation.create(a1);
		assertNotNull(a1.getId());
		SimpleDateFormat date = new SimpleDateFormat("DD/MM/YYYY");
		SimpleDateFormat heure = new SimpleDateFormat("HH-mm-ss");
		try {
			Vol v1 = new Vol(date.parse("26/10/2018"), date.parse("27/10/2018"), heure.parse("11-50-00"),
					heure.parse("06-45-00"));
			daoVol.create(v1);
			a1.setVol(v1);
			a2.setVol(v1);
			daoReservation.update(a1);
			daoReservation.update(a2);
			assertNotNull(v1.getId());
			assertNotNull(daoReservation.findByKey(a1.getId()).getVol());
			assertNotNull(daoReservation.findByKey(a2.getId()).getVol());
			assertEquals(v1, daoReservation.findByKey(a1.getId()).getVol());
			assertEquals(v1, daoReservation.findByKey(a2.getId()).getVol());
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

}
