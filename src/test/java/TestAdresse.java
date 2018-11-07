import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import repositories.AdresseRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationcontext.xml"})
public class TestAdresse {

	@Autowired
	private AdresseRepository adresseRepository;

	@Test
	public void repo() {
		assertNotNull(adresseRepository);
	}
	
//	@Test
//	public void insert() {
//		Adresse a1;
//		a1 = new Adresse(5, "rue du bac", "75009", "Paris", "France");
//		assertNull(a1.getId());
//		daoadresse.create(a1);
//		assertNotNull(a1.getId());
//	}
//
//	@Test
//	public void findByKey() {
//		Adresse a2;
//		a2 = new Adresse(4, "rue Marc Sangnier", "94700", "Maisons-Alfort", "Ecosse");
//		daoadresse.create(a2);
//		assertNotNull(daoadresse.findByKey(a2.getId()));
//	}
//
//	@Test
//	public void update() {
//		Adresse a3;
//		a3 = new Adresse(25, "rue Adrien Damalix", "94410", "Saint-Maurice", "Espagne");
//		daoadresse.create(a3);
//		a3 = daoadresse.findByKey(a3.getId());
//		a3.setCodePostal("94850");
//		daoadresse.update(a3);
//		assertEquals("94850", daoadresse.findByKey(a3.getId()).getCodePostal());
//	}
//
//	@Test
//	public void findAll() {
//		assertNotNull(daoadresse.findAll());
//	}
//
//	@Test
//	public void delete() {
//		Adresse a4;
//		a4 = new Adresse(95, "rue du four", "77055", "Creil", "Finlande");
//		daoadresse.create(a4);
//		daoadresse.delete(a4);
//		assertNull(daoadresse.findByKey(a4.getId()));
//	}
//
//	@Test
//	public void deleteByKey() {
//		Adresse a5;
//		a5 = new Adresse(155, "rue du sport", "93420", "Bobigny", "Equateur");
//		daoadresse.create(a5);
//		daoadresse.create(a5);
//		daoadresse.deleteByKey(a5.getId());
//		assertNull(daoadresse.findByKey(a5.getId()));
//
//	}
}
