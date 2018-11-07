
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

import model.Aeroport;
import repositories.AeroportRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationcontext.xml" })
public class AeroportTestSimple {

	@Autowired
	private AeroportRepository aeroportRepository;

	@Test
	public void repo() {
		assertNotNull(aeroportRepository);
	}

	@Test
	public void insert() {
		Aeroport cdg;
		cdg = new Aeroport("cdg");
		assertNull(cdg.getId());
		aeroportRepository.save(cdg);
		assertNotNull(cdg.getId());
	}

	@Test
	public void findById() {
		Aeroport cdg;
		cdg = new Aeroport("cdg");
		aeroportRepository.save(cdg);
		assertNotNull(aeroportRepository.findById(cdg.getId()));
		Optional<Aeroport> aer = aeroportRepository.findById(cdg.getId());
		if (aer.isPresent()) {
			assertNotNull(aer.get());
		}
	}

	@Test
	public void update() {
		Aeroport orly;
		orly = new Aeroport("orly");
		aeroportRepository.save(orly);
		Optional<Aeroport> aer = aeroportRepository.findById(orly.getId());
		if (aer.isPresent()) {
			orly = aer.get();
		}
		orly.setNom("orly");
		aeroportRepository.save(orly);
		if (aer.isPresent()) {
			assertEquals("orly", aer.get().getNom());
		}
	}

	@Test
	public void findAll() {
		assertNotNull(aeroportRepository.findAll());
	}

	@Test
	public void delete() {
		Aeroport cdg;
		cdg = new Aeroport("cdg");
		aeroportRepository.save(cdg);
		aeroportRepository.delete(cdg);
		assertFalse(aeroportRepository.findById(cdg.getId()).isPresent());
	}

	@Test
	public void deleteById() {
		Aeroport cdg;
		cdg = new Aeroport("cdg");
		aeroportRepository.save(cdg);
		aeroportRepository.deleteById(cdg.getId());
		assertFalse(aeroportRepository.findById(cdg.getId()).isPresent());
	}

}
