import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import model.Aeroport;
import model.Ville;
import model.VilleAeroport;
import model.VilleAeroportKey;
import repositories.AeroportRepository;
import repositories.VilleAeroportRepository;
import repositories.VilleRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationcontext.xml" })
public class VilleAeroportTestSimple {

	@Autowired
	private VilleAeroportRepository villeAeroportRepository;
	@Autowired
	private VilleRepository villeRepository;
	@Autowired
	private AeroportRepository aeroportRepository;

	@Test
	public void testJointureVilleAeroport() {// test équivalent au insert
		Ville v = new Ville();
		v.setNom("paris");
		villeRepository.save(v);
		Aeroport a = new Aeroport("cdg");
		aeroportRepository.save(a);
		VilleAeroport villeAeroport = new VilleAeroport();
		villeAeroport.setKey(new VilleAeroportKey(v, a));
		villeAeroportRepository.save(villeAeroport);
		assertNotNull(villeAeroportRepository.findById(villeAeroport.getKey()));
	}

	@Test
	public void findById() {
		Ville v = new Ville();
		v.setNom("paris");
		villeRepository.save(v);
		Aeroport a = new Aeroport("cdg");
		aeroportRepository.save(a);
		VilleAeroport t = new VilleAeroport(new VilleAeroportKey(v, a));
		villeAeroportRepository.save(t);
		assertNotNull(villeAeroportRepository.findById(t.getKey()));
		Optional<VilleAeroport> vil = villeAeroportRepository.findById(t.getKey());
		if (vil.isPresent()) {
			assertNotNull(vil.get());
		}
	}

	@Test
	public void delete() {
		Ville v = new Ville();
		v.setNom("paris");
		villeRepository.save(v);
		Aeroport a = new Aeroport("cdg");
		aeroportRepository.save(a);
		VilleAeroport t = new VilleAeroport(new VilleAeroportKey(v, a));
		villeAeroportRepository.save(t);
		villeAeroportRepository.delete(t);
		assertFalse(villeAeroportRepository.findById(t.getKey()).isPresent());
	}

}
