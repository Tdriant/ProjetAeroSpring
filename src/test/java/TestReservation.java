import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import dao.DaoReservation;
import dao.DaoReservationFactory;
import model.Reservation;
import util.Context;


public class TestReservation {

	private static DaoReservation daoreservation;

	@BeforeClass
	public static void initDaoRealisateur() {
		daoreservation = DaoReservationFactory.getInstance();
	}

	@AfterClass
	public static void fermeturJpa() {
		Context.destroy();
	}

	@Test
	public void insert() {
		Reservation a1;
		a1 = new Reservation();
		assertNull(a1.getId());
		daoreservation.create(a1);
		assertNotNull(a1.getId());
	}

	@Test
	public void findByKey() {
		Reservation a2;
		a2 = new Reservation();
		daoreservation.create(a2);
		assertNotNull(daoreservation.findByKey(a2.getId()));
	}

	@Test
	public void update() {
		Reservation a3;
		a3 = new Reservation();
		daoreservation.create(a3);
		a3 = daoreservation.findByKey(a3.getId());
		daoreservation.update(a3);
	}

	@Test
	public void findAll() {
		assertNotNull(daoreservation.findAll());
	}

	@Test
	public void delete() {
		Reservation a4;
		a4 = new Reservation();
		daoreservation.create(a4);
		daoreservation.delete(a4);
		assertNull(daoreservation.findByKey(a4.getId()));
	}

	@Test
	public void deleteByKey() {
		Reservation a5;
		a5 = new Reservation();
		daoreservation.create(a5);
		daoreservation.create(a5);
		daoreservation.deleteByKey(a5.getId());
		assertNull(daoreservation.findByKey(a5.getId()));

	}
}
