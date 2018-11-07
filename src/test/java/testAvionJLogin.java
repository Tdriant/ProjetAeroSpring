

import static org.junit.Assert.*;


import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import dao.DaoLogin;
import dao.DaoLoginFactory;
import model.Login;
import util.Context;


public class testAvionJLogin {
	private static DaoLogin daoLogin;
	
	@BeforeClass
	public static void initiDaoPersonne() {
		daoLogin = DaoLoginFactory.getInstance();
	}

	@AfterClass
	public static void fermetureJpa() {
		Context.destroy();
	}

	@Test
	public void insert() {
		Login login = new Login("boubou", "azerty", false);
		daoLogin.create(login);
		assertNotNull(login.getId());
		
	}

	@Test
	public void findByKey() {
		Login login = new Login("toto", "tata", true);
		daoLogin.create(login);
		assertNotNull(daoLogin.findByKey(login.getId()));
	}

	@Test
	public void update() {
		Login login = new Login("tutu", "huhuhu", true);
		daoLogin.create(login);
		login = daoLogin.findByKey(login.getId());
		login.setLogin("coco");
		daoLogin.update(login);
		assertEquals("coco", daoLogin.findByKey(login.getId()).getLogin());
	}

	@Test
	public void FindAll() {
		assertNotNull(daoLogin.findAll());
	}

	@Test
	public void delete() {
		Login login = new Login("toto", "tata", true);
		daoLogin.create(login);
		daoLogin.delete(login);
		assertNull(daoLogin.findByKey(login.getId()));

	}

	@Test
	public void deleteByKey() {
		Login login = new Login("toto", "tata", true);
		daoLogin.create(login);
		daoLogin.deleteByKey(login.getId());
		assertNull(daoLogin.findByKey(login.getId()));

	}


}
