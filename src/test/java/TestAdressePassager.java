
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import dao.DaoAdresse;
import dao.DaoAdresseFactory;
import dao.DaoPassager;
import dao.DaoPassagerFactory;
import model.Adresse;
import model.Passager;
import util.Context;

public class TestAdressePassager {
	private static DaoAdresse daoAdresse;
	private static DaoPassager daoPassager;

	@BeforeClass
	public static void initDao() throws Exception {
		daoAdresse = DaoAdresseFactory.getInstance();
		daoPassager = DaoPassagerFactory.getInstance();
	}

	@AfterClass
	public static void afterDao() throws Exception {
		Context.destroy();
	}

	@Test
	public void insert() {
		Adresse adresse = new Adresse(15, "rue saint honoré", "75001", "Paris", "France");
		Passager passager = new Passager("toto", "tutu");
		daoPassager.create(passager);
		adresse.setPassager(passager);
		daoAdresse.create(adresse);

		assertNotNull(adresse.getId());

		adresse = daoAdresse.findByKey(adresse.getId());
		assertNotNull(adresse.getPassager());

		passager = daoPassager.findByKey(passager.getId());
		assertNotNull(passager.getAdresse());

	}

	@Test
	public void findByKey() {
		Adresse adresse = new Adresse(15, "rue saint honoré", "75001", "Paris", "France");
		Passager passager = new Passager("toto", "tutu");
		daoPassager.create(passager);
		adresse.setPassager(passager);
		daoAdresse.create(adresse);
		
		assertNotNull(passager.getId());
		assertNotNull(daoAdresse.findByKey(adresse.getId()));
	}

	@Test
	public void update() {
		Adresse adresse = new Adresse(15, "rue saint honoré", "75001", "Paris", "France");
		Passager passager = new Passager("toto", "tutu");
		daoPassager.create(passager);
		adresse.setPassager(passager);
		daoAdresse.create(adresse);
		
		passager.setNom("tata");
		daoPassager.update(passager);
		assertNotNull(passager.getId());
		adresse.setPassager(passager);
		assertNotNull(adresse.getId());
	}

	@Test
	public void FindAll() {
		assertNotNull(daoAdresse.findAll());
		assertNotNull(daoPassager.findAll());

	}

	@Test
	public void delete() {
		Adresse adresse = new Adresse(15, "rue saint honoré", "75001", "Paris", "France");
		Passager passager = new Passager("toto", "tutu");
		daoPassager.create(passager);
		adresse.setPassager(passager);
		daoAdresse.create(adresse);
		
		daoPassager.update(adresse.getPassager());
		daoAdresse.delete(adresse);
		assertNull(daoAdresse.findByKey(adresse.getId()));
	}

	@Test
	public void deleteByKey() {
		Adresse adresse = new Adresse(15, "rue saint honoré", "75001", "Paris", "France");
		Passager passager = new Passager("toto", "tutu");
		daoPassager.create(passager);
		adresse.setPassager(passager);
		daoAdresse.create(adresse);
		
		daoPassager.update(adresse.getPassager());
		daoAdresse.deleteByKey(adresse.getId());
		assertNull(daoAdresse.findByKey(adresse.getId()));
	}

}
