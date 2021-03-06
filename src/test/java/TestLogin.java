import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import model.Login;
import repositories.LoginRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationcontext.xml"})
public class TestLogin {

	@Autowired
	private LoginRepository loginRepository;

	@Test
	public void test() {
		assertNotNull(loginRepository);
	}
	
	@Test
	public void insert() {
		Login login = new Login("boubou", "azerty", false);
		loginRepository.save(login);
		assertNotNull(login.getId());
		
	}

	@Test
	public void findByKey() {
		Login login = new Login("toto", "tata", true);
		loginRepository.save(login);
		assertNotNull(loginRepository.findById(login.getId()));
		Optional<Login> log = loginRepository.findById(login.getId());
		if (log.isPresent()) {
			assertNotNull(log.get());
		}
	}

	@Test
	public void update() {
		Login login = new Login("tutu", "huhuhu", true);
		loginRepository.save(login);
		assertNotNull(loginRepository.findById(login.getId()));
		Optional<Login> log = loginRepository.findById(login.getId());
		if (log.isPresent()) {
			assertNotNull(log.get());
		}
		login.setLogin("coco");
		loginRepository.save(login);
		log = loginRepository.findById(login.getId());
		if (log.isPresent()) {
			assertEquals("coco", log.get().getLogin());
		}

	}

	@Test
	public void FindAll() {
		assertNotNull(loginRepository.findAll());
	}

	@Test
	public void delete() {
		Login login = new Login("toto", "tata", true);
		loginRepository.save(login);
		loginRepository.delete(login);
		assertFalse(loginRepository.findById(login.getId()).isPresent());

	}

	@Test
	public void deleteByKey() {
		Login login = new Login("toto", "tata", true);
		loginRepository.save(login);
		loginRepository.deleteById(login.getId());
		assertFalse(loginRepository.findById(login.getId()).isPresent());
	}

}
