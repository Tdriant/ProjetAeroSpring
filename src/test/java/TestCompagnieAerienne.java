import static org.junit.Assert.*;

import java.util.Optional;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import model.CompagnieAerienne;
import repositories.CompagnieAerienneRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationcontext.xml" })
public class TestCompagnieAerienne {

	@Autowired
	private CompagnieAerienneRepository caRepo;

	@Test
	public void test() {
		CompagnieAerienne ca1 = new CompagnieAerienne("AeroAirLine");
		CompagnieAerienne ca2 = new CompagnieAerienne("StayFreshAirLine");
		CompagnieAerienne ca3 = new CompagnieAerienne("GodDamnCheapLine");
		CompagnieAerienne ca4 = new CompagnieAerienne("RichLineSky");

		caRepo.save(ca1);
		caRepo.save(ca2);
		caRepo.save(ca3);
		caRepo.save(ca4);
		ca3.setNom("CheapSkyLine");
		caRepo.save(ca3);
		caRepo.delete(ca1);
		caRepo.deleteById(ca3.getId());
		System.out.println(caRepo.findAll());
		System.out.println(caRepo.findById(ca4.getId()));
		assertNotNull(caRepo.findById(ca4.getId()));

		Optional<CompagnieAerienne> opt = caRepo.findById(ca4.getId());
		Optional<CompagnieAerienne> optt = caRepo.findById(ca3.getId());
		assertFalse(optt.isPresent());
		

		
	}

}
