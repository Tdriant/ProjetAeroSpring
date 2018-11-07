import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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

}
