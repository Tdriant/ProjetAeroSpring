import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import model.CompagnieAerienne;
import model.CompagnieAerienneVol;
import model.CompagnieAerienneVolKey;
import model.Vol;
import repositories.CaVolRepository;
import repositories.CompagnieAerienneRepository;
import repositories.VolRepository;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationcontext.xml" })
public class TestsCaVol {

	@Autowired 
	private static CaVolRepository CaVolRepo;
	@Autowired
	private static VolRepository volRepo;
	@Autowired
	private static CompagnieAerienneRepository caRepo;



	@Test
	public static void initDaoCAVol() {

		SimpleDateFormat date = new SimpleDateFormat("DD/MM/YYYY");
		SimpleDateFormat heure = new SimpleDateFormat("HH-mm-ss");
		try {
			Vol v1 = new Vol(date.parse("26/10/2018"), date.parse("27/10/2018"), heure.parse("11-50-00"), heure.parse("06-45-00"));
			Vol v2 = new Vol(date.parse("15/05/2009"), date.parse("15/05/2009"), heure.parse("06-05-00"), heure.parse("12-30-00"));
			CompagnieAerienne ca1 = new CompagnieAerienne("air france");
			CompagnieAerienne ca2 = new CompagnieAerienne("delta");
			assertNull(v1.getId());
			assertNull(v2.getId());
			assertNull(ca1.getId());
			assertNull(ca2.getId());
			volRepo.save(v1);
			volRepo.save(v2);
			caRepo.save(ca1);
			caRepo.save(ca2);
			CaVolRepo cav1 = new CompagnieAerienneVol(new CompagnieAerienneVolKey(ca1, v1));
			CaVolRepo cav2 = new CompagnieAerienneVol(new CompagnieAerienneVolKey(ca1, v1));
			CaVolRepo cav3 = new CompagnieAerienneVol(new CompagnieAerienneVolKey(ca1, v2));;

			assertNotNull(v1.getId());
			assertNotNull(v2.getId());
			assertNotNull(ca1.getId());
			assertNotNull(ca2.getId());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
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
