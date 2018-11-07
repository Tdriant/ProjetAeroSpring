import static org.junit.Assert.assertNotNull;

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

public class VilleAeroportTestSimple {

	private static DaoVilleAeroport daoVilleAeroport;
	private static DaoVille daoVille;
	private static DaoAeroport daoAeroport;

	@BeforeClass
	public static void initDaoVilleAeroport() {

		daoVilleAeroport = DaoVilleAeroportFactory.getInstance();
		daoVille = DaoVilleFactory.getInstance();
		daoAeroport = DaoAeroportFactory.getInstance();

	}

	@AfterClass
	public static void fermeturJpa() {

		Context.destroy();

	}

	@Test
	public void testJointureVilleAeroport() {
		Ville v = new Ville();
		v.setNom("paris");
		daoVille.create(v);
		Aeroport a = new Aeroport("cdg");
		daoAeroport.create(a);

		VilleAeroport villeAeroport = new VilleAeroport();
		villeAeroport.setKey(new VilleAeroportKey(v, a));

		daoVilleAeroport.create(villeAeroport);

		assertNotNull(daoVilleAeroport.findByKey(villeAeroport.getKey()));
	}
	

}
