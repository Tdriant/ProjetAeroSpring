

import static org.junit.Assert.*;


import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import dao.DaoClient;
import dao.DaoClientFactory;
import model.Client;
import model.ClientEI;
import model.ClientMoral;
import model.ClientPhysique;
import model.Titre;
import model.TitreMoral;
import util.Context;


public class testAvionJClient {
	private static DaoClient daoClient;
	
	@BeforeClass
	public static void initiDaoPersonne() {
		daoClient = DaoClientFactory.getInstance();
	}

	@AfterClass
	public static void fermetureJpa() {
		Context.destroy();
	}

	@Test
	public void insert() {
		Client c = new ClientPhysique("tutu", 2022222222, 5284, "dlnvoqizsvb");
		assertNull(c.getId());
		((ClientPhysique) c).setTitre(Titre.M);
		daoClient.create(c);
		assertNotNull(c.getId());
		
	}

	@Test
	public void findByKey() {
		ClientEI client = new ClientEI("tutu", 2022222222, 5284, "dlnvoqizsvb");
		daoClient.create(client);
		assertNotNull(daoClient.findByKey(client.getId()));
	}

	@Test
	public void update() {
		ClientMoral client = new ClientMoral("tutu", 2022222222, 5284, "dlnvoqizsvb");
		client.setSiret("wtf");
		client.setTitreMoral(TitreMoral.SA);
		daoClient.create(client);
		client = (ClientMoral) daoClient.findByKey(client.getId());
		client.setNom("coco");
		daoClient.update(client);
		assertEquals("coco", daoClient.findByKey(client.getId()).getNom());
	}

	@Test
	public void FindAll() {
		assertNotNull(daoClient.findAll());
	}

	@Test
	public void delete() {
		ClientEI client = new ClientEI("tutu", 2022222222, 5284, "dlnvoqizsvb");
		daoClient.create(client);
		daoClient.delete(client);
		assertNull(daoClient.findByKey(client.getId()));

	}

	@Test
	public void deleteByKey() {
		Client client = new ClientEI("tutu", 2022222222, 5284, "dlnvoqizsvb");
		daoClient.create(client);
		daoClient.deleteByKey(client.getId());
		assertNull(daoClient.findByKey(client.getId()));

	}


}
