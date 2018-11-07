
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import dao.DaoClient;
import dao.DaoClientFactory;
import dao.DaoLogin;
import dao.DaoLoginFactory;
import model.Client;
import model.ClientEI;
import model.Login;
import model.Titre;
import util.Context;

public class TestClientLogin {
	private static DaoLogin daoLogin;
	private static DaoClient daoClient;

	@BeforeClass
	public static void initDao() throws Exception {
		daoLogin = DaoLoginFactory.getInstance();
		daoClient = DaoClientFactory.getInstance();
	}

	@AfterClass
	public static void afterDao() throws Exception {
		Context.destroy();
	}

//	@Test
	public void insert() {
		Login login = new Login("coco", "azerty", false);
		Client client = new ClientEI(Titre.M, "tata");
		daoClient.create(client);
		login.setClient(client);
		daoLogin.create(login);
		
		assertNotNull(login.getId());

		login = daoLogin.findByKey(login.getId());
		assertNotNull(login.getClient());

		client = daoClient.findByKey(client.getId());
		assertNotNull(client.getLogin());
	}

//	@Test
	public void findByKey() {
		Login login = new Login("coco", "azerty", false);
		Client client = new ClientEI(Titre.M, "tata");
		daoClient.create(client);
		login.setClient(client);
		daoLogin.create(login);
		
		assertNotNull(client.getId());
		assertNotNull(daoLogin.findByKey(login.getId()));
	}

//	@Test
	public void update() {
		Login login = new Login("coco", "azerty", false);
		Client client = new ClientEI(Titre.M, "tata");
		daoClient.create(client);
		login.setClient(client);
		daoLogin.create(login);
		
		client.setNom("tata");
		daoClient.update(client);
		assertNotNull(client.getId());
		login.setClient(client);
		assertNotNull(login.getId());
	}

//	@Test
	public void FindAll() {
		assertNotNull(daoClient.findAll());
	}

//	@Test
	public void delete() {
		Login login = new Login("coco", "azerty", false);
		Client client = new ClientEI(Titre.M, "tata");
		daoClient.create(client);
		login.setClient(client);
		daoLogin.create(login);
		
		daoClient.update(login.getClient());
		daoLogin.delete(login);
		assertNull(daoLogin.findByKey(login.getId()));
	}

//	@Test
	public void deleteByKey() {
		Login login = new Login("coco", "azerty", false);
		Client client = new ClientEI(Titre.M, "tata");
		daoClient.create(client);
		login.setClient(client);
		daoLogin.create(login);
		
		daoClient.update(login.getClient());
		daoLogin.deleteByKey(login.getId());
		assertNull(daoLogin.findByKey(login.getId()));
	}

	@Test
	public void deleteClientLogin() {
		Login login = new Login("toto", "qwerty", true);
		Client client = new ClientEI(Titre.MME, "titi");
		daoClient.create(client);
		login.setClient(client);
		daoLogin.create(login);
		
		daoClient.update(login.getClient());
		daoClient.deleteByKey(client.getId());
		assertNull(daoLogin.findByKey(login.getId()));
		
		
	}
}
