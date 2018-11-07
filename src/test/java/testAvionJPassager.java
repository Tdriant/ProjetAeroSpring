

import static org.junit.Assert.*;


import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import dao.DaoPassager;
import dao.DaoPassagerFactory;
import model.Passager;
import model.Reservation;
import util.Context;


public class testAvionJPassager {
	private static DaoPassager daoPassager;
	
	@BeforeClass
	public static void initiDaoPersonne() {
		daoPassager = DaoPassagerFactory.getInstance();
	}

	@AfterClass
	public static void fermetureJpa() {
		Context.destroy();
	}

	@Test
	public void insert() {

		Passager passager = new Passager("toto", "tata");
		daoPassager.create(passager);
		assertNotNull(passager.getId());
		
	}

	@Test
	public void findByKey() {
		Passager passager = new Passager("toto", "tata");
		daoPassager.create(passager);
		assertNotNull(daoPassager.findByKey(passager.getId()));
	}

	@Test
	public void update() {
		Passager passager = new Passager("toto", "tata");
		daoPassager.create(passager);
		passager = daoPassager.findByKey(passager.getId());
		passager.setNom("coco");
		daoPassager.update(passager);
		assertEquals("coco", daoPassager.findByKey(passager.getId()).getNom());
	}

	@Test
	public void FindAll() {
		assertNotNull(daoPassager.findAll());
	}

	@Test
	public void delete() {
		Passager passager = new Passager("toto", "tata");
		daoPassager.create(passager);
		daoPassager.delete(passager);
		assertNull(daoPassager.findByKey(passager.getId()));

	}

	@Test
	public void deleteByKey() {
		Passager passager = new Passager("toto", "tata");
		daoPassager.create(passager);
		daoPassager.deleteByKey(passager.getId());
		assertNull(daoPassager.findByKey(passager.getId()));

	}


}
