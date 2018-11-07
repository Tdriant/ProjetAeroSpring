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

import model.Ville;
import repositories.VilleRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationcontext.xml" })
public class villeTestSimple {

	@Autowired
	private VilleRepository villeRepository;

	@Test
	public void repo() {
		assertNotNull(villeRepository);
	}

	@Test
	public void insert() {
		Ville paris;
		paris = new Ville("paris");
		assertNull(paris.getId());
		villeRepository.save(paris);
		assertNotNull(paris.getId());
	}

	@Test
	public void findById() {
		Ville paris;
		paris = new Ville("paris");
		villeRepository.save(paris);
		assertNotNull(villeRepository.findById(paris.getId()));
		Optional<Ville> vil = villeRepository.findById(paris.getId());
		if (vil.isPresent()) {
			assertNotNull(vil.get());
		}
	}

	@Test
	public void update() {
		Ville antony;
		antony = new Ville("antony");
		villeRepository.save(antony);
		Optional<Ville> vil = villeRepository.findById(antony.getId());
		if (vil.isPresent()) {
			antony = vil.get(); // le get() récupère l'objet présent dans l'Optional, ici on dit que si il y a
								// bien un objet vil dans Optional, c'est bien antony.
		}
		antony.setNom("antony");
		villeRepository.save(antony);
		vil = villeRepository.findById(antony.getId());
		if (vil.isPresent()) {
			assertEquals("antony", vil.get().getNom()); // ici on vérifie que le changement de ville a bien été fait
		}
	}

	@Test
	public void findAll() {
		assertNotNull(villeRepository.findAll());
	}

	@Test
	public void delete() {
		Ville paris;
		paris = new Ville("paris");
		villeRepository.save(pa
		villeRepository.delete(paris);
		assertFalse(villeRepository.findById(paris.getId()).isPresent());
	}

	@Test
	public void deleteById() {
		Ville paris;
		paris = new Ville("paris");
		villeRepository.save(paris);
		villeRepository.deleteById(paris.getId());
		assertFalse(villeRepository.findById(paris.getId()).isPresent());
	}

//	@Test
//	public void findAllWithAeroport() {
//		Ville paris;
//		Aeroport a1 = new Aeroport("Paris");
//		Aeroport a2 = new Aeroport("Madrid");
//		paris = new Ville("paris");
//
//		daoVille.create(paris);
//		daoAeroport.create(a1);
//		daoAeroport.create(a2);
//		daoVilleAeroport.create(new VilleAeroport(new VilleAeroportKey(paris, a1)));
//		daoVilleAeroport.create(new VilleAeroport(new VilleAeroportKey(paris, a2)));
//
//		assertNotNull(daoVille.findAllWithAeroport());
//		for (Ville ville : daoVille.findAllWithAeroport()) {
//			assertNotNull(ville.getVillesAeroport());
//			for (VilleAeroport villeAeroport : ville.getVillesAeroport()) {
//				assertTrue(villeAeroport.getKey().getAeroport() instanceof Aeroport);
//			}
//		}
//	}
//
//	@Test
//	public void findByKeyWithAeroport() {
//		Ville paris;
//		Aeroport a1 = new Aeroport("Paris");
//		Aeroport a2 = new Aeroport("Madrid");
//		paris = new Ville("paris");
//
//		daoVille.create(paris);
//		daoAeroport.create(a1);
//		daoAeroport.create(a2);
//		daoVilleAeroport.create(new VilleAeroport(new VilleAeroportKey(paris, a1)));
//		daoVilleAeroport.create(new VilleAeroport(new VilleAeroportKey(paris, a2)));
//
//		assertNotNull(daoVille.findByKeyWithAeroport(paris.getId()));
//		for (VilleAeroport villeAeroport : daoVille.findByKeyWithAeroport(paris.getId()).getVillesAeroport()) {
//			assertTrue(villeAeroport.getKey().getAeroport() instanceof Aeroport);
//		}
//
//	}
}
