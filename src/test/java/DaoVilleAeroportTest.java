import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import dao.DaoAeroport;
import dao.DaoAeroportFactory;
import dao.DaoVille;
import dao.DaoVilleAeroport;
import dao.DaoVilleAeroportFactory;
import dao.DaoVilleFactory;
import model.Aeroport;
import model.Ville;
import model.VilleAeroport;
import model.VilleAeroportKey;
import util.Context;

public class DaoVilleAeroportTest {

	private static DaoVilleAeroport daoVilleAeroport;
	private static DaoVille daoVille;
	private static DaoAeroport daoAeroport;

	private static Aeroport a;
	private static Ville v;
	private static Aeroport b;
	private static Ville w;

	@BeforeClass
	public static void initDaoVilleAeroport() {

		daoVilleAeroport = DaoVilleAeroportFactory.getInstance();
		daoVille = DaoVilleFactory.getInstance();
		daoAeroport = DaoAeroportFactory.getInstance();
		a = new Aeroport("cdg");
		v = new Ville("paris");
		daoVille.create(v);
		daoAeroport.create(a);
		b = new Aeroport("orly");
		w = new Ville("antony");
		daoVille.create(w);
		daoAeroport.create(b);
	}

	@AfterClass
	public static void fermeturJpa() {

		Context.destroy();

	}

	@Test
	public void insert() {
		VilleAeroport t = new VilleAeroport(new VilleAeroportKey(v, a));

		assertNotNull(t.getKey().getAeroport());
		assertNotNull(t.getKey().getVille());
		daoVilleAeroport.create(t);

	}

	@Test
	public void findByKey() {

		VilleAeroport t = new VilleAeroport(new VilleAeroportKey(v, b));
		daoVilleAeroport.create(t);
		assertNotNull(daoVilleAeroport.findByKey(t.getKey()));

	}

//	@Test
//	public void update() {
//
//		VilleAeroport t = new VilleAeroport(new VilleAeroportKey(v, a));
//		daoVilleAeroport.create(t);
//		t = daoVilleAeroport.findByKey(t.getKey());
//		t.getKey().setAeroport(b);
//		daoVilleAeroport.update(t);
//		assertEquals(a, daoVilleAeroport.findByKey(t.getKey()).getKey().getAeroport());
//	}

	@Test
	public void delete() {

		VilleAeroport t = new VilleAeroport(new VilleAeroportKey(v, a));
		daoVilleAeroport.create(t);
		daoVilleAeroport.delete(t);
		assertNull(daoVilleAeroport.findByKey(t.getKey()));

	}

//	@Test
//	public void testJointureVilleAeroport() {
//		Ville v = new Ville();
//		v.setNom("paris");
//		daoVille.create(v);
//		Aeroport a = new Aeroport("cdg");
//		daoAeroport.create(a);
//
//		VilleAeroport villeAeroport = new VilleAeroport();
//		villeAeroport.setKey(new VilleAeroportKey(v, a));
//
//		daoVilleAeroport.create(villeAeroport);
//
//		assertNotNull(daoVilleAeroport.findByKey(villeAeroport.getKey()));
//	}

}
