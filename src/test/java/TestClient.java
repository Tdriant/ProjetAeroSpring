import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import model.Client;
import model.ClientEI;
import model.ClientMoral;
import model.ClientPhysique;
import model.Titre;
import model.TitreMoral;
import repositories.ClientRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationcontext.xml"})
public class TestClient {
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Test
	public void repoClient() {
		assertNotNull(clientRepository);
	}
	
	@Test
	public void insert() {
		Client c = new ClientPhysique("tutu", 2022222222, 5284, "dlnvoqizsvb");
		assertNull(c.getId());
		((ClientPhysique) c).setTitre(Titre.M);
		clientRepository.save(c);
		assertNotNull(c.getId());	
	}

	@Test
	public void findByKey() {
		ClientEI client = new ClientEI("tutu", 2022222222, 5284, "dlnvoqizsvb");
		clientRepository.save(client);
		assertNotNull(clientRepository.findById(client.getId()));
		Optional<Client> cli = clientRepository.findById(client.getId());
		if (cli.isPresent()) {
			assertNotNull(cli.get());
		}
	}

	@Test
	public void update() {
		ClientMoral client = new ClientMoral("tutu", 2022222222, 5284, "dlnvoqizsvb");
		client.setSiret("wtf");
		client.setTitreMoral(TitreMoral.SA);
		clientRepository.save(client);
		Optional<Client> cli = clientRepository.findById(client.getId());
		if (cli.isPresent()) {
			assertNotNull(cli.get());
		}
		client.setNom("coco");
		clientRepository.save(client);
		cli = clientRepository.findById(client.getId());
		if (cli.isPresent()) {
			assertEquals("coco", cli.get().getNom());;
		}
	}

	
	
	@Test
	public void FindAll() {
		assertNotNull(clientRepository.findAll());
	}

	@Test
	public void delete() {
		ClientEI client = new ClientEI("tutu", 2022222222, 5284, "dlnvoqizsvb");
		clientRepository.save(client);
		clientRepository.delete(client);
		assertFalse(clientRepository.findById(client.getId()).isPresent());

	}

	@Test
	public void deleteByKey() {
		Client client = new ClientEI("tutu", 2022222222, 5284, "dlnvoqizsvb");
		clientRepository.save(client);
		clientRepository.deleteById(client.getId());
		assertFalse(clientRepository.findById(client.getId()).isPresent());

	}

}
