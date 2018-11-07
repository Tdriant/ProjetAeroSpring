
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import dao.DaoAdresse;
import dao.DaoAdresseFactory;
import dao.DaoClient;
import dao.DaoClientFactory;
import model.Adresse;
import model.Client;
import model.ClientEI;
import model.Titre;
import util.Context;

public class TestAdresseClient {
	private static DaoAdresse daoAdresse;
	private static DaoClient daoClient;

	@BeforeClass
	public static void initDao() throws Exception {
		daoAdresse = DaoAdresseFactory.getInstance();
		daoClient = DaoClientFactory.getInstance();
	}

	@AfterClass
	public static void afterDao() throws Exception {
		Context.destroy();
	}

	@Test
	public void insert() {
		Adresse adresse = new Adresse(15, "rue saint honoré", "75001", "Paris", "France");
		Client client = new ClientEI(Titre.MLLE, "baba");
		daoClient.create(client);
		adresse.setClient(client);
		daoAdresse.create(adresse);
		

		assertNotNull(adresse.getId());

		adresse = daoAdresse.findByKey(adresse.getId());
		assertNotNull(adresse.getClient());

		client = daoClient.findByKey(client.getId());
		assertNotNull(client.getAdresse());

	}

	@Test
	public void findByKey() {
		Adresse adresse = new Adresse(15, "rue saint honoré", "75001", "Paris", "France");
		Client client = new ClientEI(Titre.MLLE, "baba");
		daoClient.create(client);
		adresse.setClient(client);
		daoAdresse.create(adresse);
		
		assertNotNull(client.getId());
		assertNotNull(daoAdresse.findByKey(adresse.getId()));
	}

	@Test
	public void update() {
		Adresse adresse = new Adresse(15, "rue saint honoré", "75001", "Paris", "France");
		Client client = new ClientEI(Titre.MLLE, "baba");
		daoClient.create(client);
		adresse.setClient(client);
		daoAdresse.create(adresse);
		
		client.setNom("tata");
		daoClient.update(client);
		assertNotNull(client.getId());
		adresse.setClient(client);
		assertNotNull(adresse.getId());
	}

	@Test
	public void FindAll() {
		assertNotNull(daoAdresse.findAll());
		assertNotNull(daoClient.findAll());

	}

	@Test
	public void delete() {
		Adresse adresse = new Adresse(15, "rue saint honoré", "75001", "Paris", "France");
		Client client = new ClientEI(Titre.MLLE, "baba");
		daoClient.create(client);
		adresse.setClient(client);
		daoAdresse.create(adresse);
		
		daoClient.update(adresse.getClient());
		daoAdresse.delete(adresse);
		assertNull(daoAdresse.findByKey(adresse.getId()));
	}

	@Test
	public void deleteByKey() {
		Adresse adresse = new Adresse(15, "rue saint honoré", "75001", "Paris", "France");
		Client client = new ClientEI(Titre.MLLE, "baba");
		daoClient.create(client);
		adresse.setClient(client);
		daoAdresse.create(adresse);
		
		daoClient.update(adresse.getClient());
		daoAdresse.deleteByKey(adresse.getId());
		assertNull(daoAdresse.findByKey(adresse.getId()));
	}

}
