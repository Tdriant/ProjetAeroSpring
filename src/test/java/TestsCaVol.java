import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import dao.DaoCAVol;
import dao.DaoCAVolFactory;
import dao.DaoCompagnieAerienne;
import dao.DaoCompagnieAerienneFactory;
import dao.DaoVol;
import dao.DaoVolFactory;
import model.CompagnieAerienne;
import model.CompagnieAerienneVol;
import model.CompagnieAerienneVolKey;
import model.Vol;
import util.Context;

public class TestsCaVol {

	private static DaoCAVol daoCAVol;
	private static DaoVol daoVol;
	private static DaoCompagnieAerienne daoCompagnieAerienne;
	private static Vol v1;
	private static Vol v2;
	private static CompagnieAerienne ca1;
	private static CompagnieAerienne ca2;

	@BeforeClass
	public static void initDaoCAVol() {
		daoCAVol = DaoCAVolFactory.getInstance();
		daoVol = DaoVolFactory.getInstance();
		daoCompagnieAerienne = DaoCompagnieAerienneFactory.getInstance();
		SimpleDateFormat date = new SimpleDateFormat("DD/MM/YYYY");
		SimpleDateFormat heure = new SimpleDateFormat("HH-mm-ss");
		try {
			v1 = new Vol(date.parse("26/10/2018"), date.parse("27/10/2018"), heure.parse("11-50-00"), heure.parse("06-45-00"));
			v2 = new Vol(date.parse("15/05/2009"), date.parse("15/05/2009"), heure.parse("06-05-00"), heure.parse("12-30-00"));
			ca1 = new CompagnieAerienne("air france");
			ca2 = new CompagnieAerienne("delta");
			assertNull(v1.getId());
			assertNull(v2.getId());
			assertNull(ca1.getId());
			assertNull(ca2.getId());
			daoVol.create(v1);
			daoVol.create(v2);
			daoCompagnieAerienne.create(ca1);
			daoCompagnieAerienne.create(ca2);
			assertNotNull(v1.getId());
			assertNotNull(v2.getId());
			assertNotNull(ca1.getId());
			assertNotNull(ca2.getId());
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@AfterClass
	public static void fermeturJpa() {
		Context.destroy();
	}

	@Test
	public void insert() {
		CompagnieAerienneVol cav1 = new CompagnieAerienneVol(new CompagnieAerienneVolKey(ca1, v1));
		assertNotNull(cav1.getKey());
		assertNotNull(cav1.getKey().getVol());
		assertNotNull(cav1.getKey().getCompagnieAerienne());
		daoCAVol.create(cav1);
	}

	@Test
	public void findByKey() {
		CompagnieAerienneVol cav2 = new CompagnieAerienneVol(new CompagnieAerienneVolKey(ca2, v2));
		daoCAVol.create(cav2);
		assertNotNull(daoCAVol.findByKey(new CompagnieAerienneVolKey(cav2.getKey().getCompagnieAerienne(), cav2.getKey().getVol())));
	}


	@Test
	public void findAll() {
		assertNotNull(daoCAVol.findAll());
	}

	@Test
	public void delete() {
		CompagnieAerienneVol cav3 = new CompagnieAerienneVol(new CompagnieAerienneVolKey(ca1, v2));;
		daoCAVol.create(cav3);
		assertNotNull(daoCAVol.findByKey(new CompagnieAerienneVolKey(cav3.getKey().getCompagnieAerienne(), cav3.getKey().getVol())));
		cav3 = daoCAVol.findByKey(cav3.getKey());
		daoCAVol.delete(cav3);
		assertNull(daoCAVol.findByKey(new CompagnieAerienneVolKey(cav3.getKey().getCompagnieAerienne(), cav3.getKey().getVol())));
	}

	@Test
	public void deleteByKey() {
	CompagnieAerienneVol cav3 = new CompagnieAerienneVol(new CompagnieAerienneVolKey(ca1, v2));;
	daoCAVol.create(cav3);
	assertNotNull(daoCAVol.findByKey(new CompagnieAerienneVolKey(cav3.getKey().getCompagnieAerienne(), cav3.getKey().getVol())));
	cav3 = daoCAVol.findByKey(cav3.getKey());
	daoCAVol.deleteByKey(cav3.getKey());
	assertNull(daoCAVol.findByKey(new CompagnieAerienneVolKey(cav3.getKey().getCompagnieAerienne(), cav3.getKey().getVol())));
	}
}
