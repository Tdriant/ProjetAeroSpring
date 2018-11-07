package repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import model.Passager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationcontext.xml"})
public class TestPassager {

	@Autowired
	private PassagerRepository passagerRepository;

	@Test
	public void test() {
		assertNotNull(passagerRepository);
	}
	
	@Test
	public void insert() {
		Passager passager = new Passager("toto", "tata");
		passagerRepository.save(passager);
		assertNotNull(passager.getId());
		
	}

	@Test
	public void findByKey() {
		Passager passager = new Passager("toto", "tata");
		passagerRepository.save(passager);
		assertNotNull(passagerRepository.findById(passager.getId()));
		Optional<Passager> pass = passagerRepository.findById(passager.getId());
		if (pass.isPresent()) {
			assertNotNull(pass.get());
		}
	}

	@Test
	public void update() {
		Passager passager = new Passager("toto", "tata");
		passagerRepository.save(passager);
		assertNotNull(passagerRepository.findById(passager.getId()));
		Optional<Passager> pass = passagerRepository.findById(passager.getId());
		if (pass.isPresent()) {
			assertNotNull(pass.get());
		}
		passager.setNom("coco");
		passagerRepository.save(passager);
		pass = passagerRepository.findById(passager.getId());
		if (pass.isPresent()) {
			assertEquals("coco", pass.get().getNom());
		}
	}
	

	@Test
	public void FindAll() {
		assertNotNull(passagerRepository.findAll());
	}

	@Test
	public void delete() {
		Passager passager = new Passager("toto", "tata");
		passagerRepository.save(passager);
		passagerRepository.delete(passager);
		assertFalse(passagerRepository.findById(passager.getId()).isPresent());

	}

	@Test
	public void deleteByKey() {
		Passager passager = new Passager("toto", "tata");
		passagerRepository.save(passager);
		passagerRepository.deleteById(passager.getId());
		assertFalse(passagerRepository.findById(passager.getId()).isPresent());

	}

}
