
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import dao.DaoAeroport;
import dao.DaoAeroportFactory;
import model.Aeroport;
import util.Context;

public class AeroportTestSimple {

	private static DaoAeroport daoAeroport;

	@BeforeClass
	public static void initDaoAeroport() {

		daoAeroport = DaoAeroportFactory.getInstance();

	}

	@AfterClass
	public static void fermeturJpa() {

		Context.destroy();

	}

	@Test
	public void insert() {

		Aeroport cdg;

		cdg = new Aeroport("cdg");

		assertNull(cdg.getId());

		daoAeroport.create(cdg);

		assertNotNull(cdg.getId());

	}

	@Test

	public void findByKey() {

		Aeroport cdg;

		cdg = new Aeroport("cdg");

		daoAeroport.create(cdg);

		assertNotNull(daoAeroport.findByKey(cdg.getId()));

	}

	@Test

	public void update() {

		Aeroport orly;

		orly = new Aeroport("orly");

		daoAeroport.create(orly);

		orly = daoAeroport.findByKey(orly.getId());

		orly.setNom("orly");

		daoAeroport.update(orly);

		assertEquals("orly", daoAeroport.findByKey(orly.getId()).getNom());

	}

	@Test

	public void findAll() {

		assertNotNull(daoAeroport.findAll());

	}

	@Test

	public void delete() {

		Aeroport cdg;

		cdg = new Aeroport("cdg");

		daoAeroport.create(cdg);

		daoAeroport.delete(cdg);

		assertNull(daoAeroport.findByKey(cdg.getId()));

	}

	@Test

	public void deleteByKey() {

		Aeroport cdg;

		cdg = new Aeroport("cdg");

		daoAeroport.create(cdg);

		daoAeroport.deleteByKey(cdg.getId());

		assertNull(daoAeroport.findByKey(cdg.getId()));

	}

}
