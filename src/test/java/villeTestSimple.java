import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.hamcrest.core.IsInstanceOf;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import dao.DaoAeroport;
import dao.DaoAeroportFactory;
import dao.DaoVille;
import dao.DaoVilleAeroport;
import dao.DaoVilleAeroportFactory;
import dao.DaoVilleFactory;
import javassist.expr.Instanceof;
import model.Aeroport;
import model.Ville;
import model.VilleAeroport;
import model.VilleAeroportKey;
import util.Context;

public class villeTestSimple {

	private static DaoVille daoVille;
	private static DaoAeroport daoAeroport;
	private static DaoVilleAeroport daoVilleAeroport;

	@BeforeClass
	public static void initDaoVille() {
		daoVille = DaoVilleFactory.getInstance();
		daoAeroport = DaoAeroportFactory.getInstance();
		daoVilleAeroport = DaoVilleAeroportFactory.getInstance();
	}

	@AfterClass
	public static void fermeturJpa() {
		Context.destroy();
	}

	@Test
	public void insert() {
		Ville paris;
		paris = new Ville("paris");
		assertNull(paris.getId());
		daoVille.create(paris);
		assertNotNull(paris.getId());
	}

	@Test
	public void findByKey() {
		Ville paris;
		paris = new Ville("paris");
		daoVille.create(paris);
		assertNotNull(daoVille.findByKey(paris.getId()));
	}

	@Test
	public void update() {
		Ville antony;
		antony = new Ville("antony");
		daoVille.create(antony);
		antony = daoVille.findByKey(antony.getId());
		antony.setNom("antony");
		daoVille.update(antony);
		assertEquals("antony", daoVille.findByKey(antony.getId()).getNom());
	}

	@Test
	public void findAll() {
		assertNotNull(daoVille.findAll());
	}

	@Test
	public void delete() {
		Ville paris;
		paris = new Ville("paris");
		daoVille.create(paris);
		daoVille.delete(paris);
		assertNull(daoVille.findByKey(paris.getId()));
	}

	@Test
	public void deleteByKey() {
		Ville paris;
		paris = new Ville("paris");
		daoVille.create(paris);
		daoVille.deleteByKey(paris.getId());
		assertNull(daoVille.findByKey(paris.getId()));
	}

	@Test
	public void findAllWithAeroport() {
		Ville paris;
		Aeroport a1 = new Aeroport("Paris");
		Aeroport a2 = new Aeroport("Madrid");
		paris = new Ville("paris");

		daoVille.create(paris);
		daoAeroport.create(a1);
		daoAeroport.create(a2);
		daoVilleAeroport.create(new VilleAeroport(new VilleAeroportKey(paris, a1)));
		daoVilleAeroport.create(new VilleAeroport(new VilleAeroportKey(paris, a2)));

		assertNotNull(daoVille.findAllWithAeroport());
		for (Ville ville : daoVille.findAllWithAeroport()) {
			assertNotNull(ville.getVillesAeroport());
			for (VilleAeroport villeAeroport : ville.getVillesAeroport()) {
				assertTrue(villeAeroport.getKey().getAeroport() instanceof Aeroport);
			}
		}
	}

	@Test
	public void findByKeyWithAeroport() {
		Ville paris;
		Aeroport a1 = new Aeroport("Paris");
		Aeroport a2 = new Aeroport("Madrid");
		paris = new Ville("paris");

		daoVille.create(paris);
		daoAeroport.create(a1);
		daoAeroport.create(a2);
		daoVilleAeroport.create(new VilleAeroport(new VilleAeroportKey(paris, a1)));
		daoVilleAeroport.create(new VilleAeroport(new VilleAeroportKey(paris, a2)));

		assertNotNull(daoVille.findByKeyWithAeroport(paris.getId()));
		for (VilleAeroport villeAeroport : daoVille.findByKeyWithAeroport(paris.getId()).getVillesAeroport()) {
			assertTrue(villeAeroport.getKey().getAeroport() instanceof Aeroport);
		}

	}
}
