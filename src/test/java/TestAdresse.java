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

import model.Adresse;
import repositories.AdresseRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationcontext.xml" })
public class TestAdresse {

	@Autowired
	private AdresseRepository adresseRepository;

	@Test
	public void repo() {
		assertNotNull(adresseRepository);
	}

	@Test
	public void insert() {
		Adresse a1;
		a1 = new Adresse(5, "rue du bac", "75009", "Paris", "France");
		assertNull(a1.getId());
		adresseRepository.save(a1);
		assertNotNull(a1.getId());
	}

	@Test
	public void findById() {
		Adresse a2;
		a2 = new Adresse(4, "rue Marc Sangnier", "94700", "Maisons-Alfort", "Ecosse");
		adresseRepository.save(a2);
		assertNotNull(adresseRepository.findById(a2.getId()));
		Optional<Adresse> adr = adresseRepository.findById(a2.getId());
		if (adr.isPresent()) {
			assertNotNull(adr.get());
		}
	}

	@Test
	public void update() {
		Adresse a3;
		a3 = new Adresse(25, "rue Adrien Damalix", "94410", "Saint-Maurice", "Espagne");
		adresseRepository.save(a3);
		Optional<Adresse> adr = adresseRepository.findById(a3.getId());
		if (adr.isPresent()) {
			a3 = adr.get();
		}
		a3.setCodePostal("94850");
		adresseRepository.save(a3);
		adr = adresseRepository.findById(a3.getId());
		if (adr.isPresent()) {
			assertEquals("94850", adr.get().getCodePostal());
		}
	}

	@Test
	public void findAll() {
		assertNotNull(adresseRepository.findAll());
	}

	@Test
	public void delete() {
		Adresse a4;
		a4 = new Adresse(95, "rue du four", "77055", "Creil", "Finlande");
		adresseRepository.save(a4);
		adresseRepository.delete(a4);
		assertFalse(adresseRepository.findById(a4.getId()).isPresent());
	}

	@Test
	public void deleteById() {
		Adresse a5;
		a5 = new Adresse(155, "rue du sport", "93420", "Bobigny", "Equateur");
		adresseRepository.save(a5);
		adresseRepository.save(a5);
		adresseRepository.deleteById(a5.getId());
		assertFalse(adresseRepository.findById(a5.getId()).isPresent());
	}
}
