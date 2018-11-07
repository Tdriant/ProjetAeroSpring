import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import dao.DaoPassager;
import dao.DaoPassagerFactory;
import dao.DaoReservation;
import dao.DaoReservationFactory;
import model.Passager;
import model.Reservation;
import util.Context;

public class TestResaPassager {

	private static DaoPassager daoPassager;
	private static DaoReservation daoReservation;

	@BeforeClass
	public static void initiDaoPersonne() {
		daoPassager = DaoPassagerFactory.getInstance();
		daoReservation = DaoReservationFactory.getInstance();
	}

	@AfterClass
	public static void fermetureJpa() {
		Context.destroy();
	}

//	//@Test
//	public void insertPassagerReservation() {
//		Reservation a1;
//		Reservation a2;
//		a1 = new Reservation();
//		a2 = new Reservation();
//		daoReservation.create(a2);
//		assertNull(a1.getId());
//		daoReservation.create(a1);
//		assertNotNull(a1.getId());
//		Passager passager = new Passager("toto", "tata");
//		daoPassager.create(passager);
//		a1.setPassager(passager);
//		a2.setPassager(passager);
//		daoReservation.update(a1);
//		daoReservation.update(a2);
//
//		assertNotNull(passager.getId());
//
//	}

	@Test
	public void deletePassagerResa() {
//		SimpleDateFormat date = new SimpleDateFormat("DD/MM/YYYY");
//		try {
//			Reservation reservation = new Reservation(date.parse("11/10/2003"), 82);
//			Passager passager = new Passager("tutu", "boubou");
//			daoPassager.create(passager);
//			reservation.setPassager(passager);
//			daoReservation.create(reservation);
//
//			daoPassager.update(reservation.getPassager());
//			daoPassager.deleteByKey(passager.getId());
//			assertNotNull(daoReservation.findByKey(reservation.getId()));
//
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
		
	
			Reservation reservation = new Reservation(null, 82);
			Passager passager = new Passager("tutu", "boubou");
			daoPassager.create(passager);
			reservation.setPassager(passager);
			daoReservation.create(reservation);

			daoPassager.update(reservation.getPassager());
			daoPassager.deleteByKey(passager.getId());
			assertNull(daoReservation.findByKey(reservation.getId()));

//			daoReservation.update(passager.getReservations());
//			daoReservation.deleteByKey(passager.getId());
//			assertNotNull(daoReservation.findByKey(reservation.getId()));


	}

}
