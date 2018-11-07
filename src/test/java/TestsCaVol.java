import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

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
	private   CaVolRepository CaVolRepo;

	@Autowired
	private  CompagnieAerienneRepository caRepo;

	@Autowired
	private VolRepository volRepo;
	
	

	@Test
	public  void initDaoCAVol() {

		SimpleDateFormat date = new SimpleDateFormat("DD/MM/YYYY");
		SimpleDateFormat heure = new SimpleDateFormat("HH-mm-ss");
		try {
			Vol v1 = new Vol(date.parse("26/10/2018"), date.parse("27/10/2018"), heure.parse("11-50-00"),
					heure.parse("06-45-00"));
			Vol v2 = new Vol(date.parse("15/05/2009"), date.parse("15/05/2009"), heure.parse("06-05-00"),
					heure.parse("12-30-00"));
			CompagnieAerienne ca1 = new CompagnieAerienne("air france");
			CompagnieAerienne ca2 = new CompagnieAerienne("delta");
			volRepo.save(v1);
			volRepo.save(v2);
			caRepo.save(ca1);
			caRepo.save(ca2);
			CompagnieAerienneVol cav1 = new CompagnieAerienneVol(new CompagnieAerienneVolKey(ca1, v1));
			CompagnieAerienneVol cav2 = new CompagnieAerienneVol(new CompagnieAerienneVolKey(ca1, v1));
			CompagnieAerienneVol cav3 = new CompagnieAerienneVol(new CompagnieAerienneVolKey(ca1, v2));
			CompagnieAerienneVol cav4 = new CompagnieAerienneVol(new CompagnieAerienneVolKey(ca2, v2));
			CaVolRepo.save(cav4);
			CaVolRepo.save(cav1);
			CaVolRepo.save(cav2);
			CaVolRepo.save(cav3);
			
			assertNotNull(v1.getId());
			assertNotNull(v2.getId());
			assertNotNull(ca1.getId());
			assertNotNull(ca2.getId());
			assertNotNull(cav4.getKey());
			assertNotNull(cav1.getKey());
			assertNotNull(cav2.getKey());
			assertNotNull(cav3.getKey());
			assertNotNull(CaVolRepo.findById(cav1.getKey()));
			System.out.println(CaVolRepo.findAll());
			CaVolRepo.deleteById(cav1.getKey());
			CaVolRepo.delete(cav4);
			Optional<CompagnieAerienneVol> opt = CaVolRepo.findById(cav4.getKey());
			Optional<CompagnieAerienneVol> optt = CaVolRepo.findById(cav1.getKey());
			assertFalse(optt.isPresent());
			assertFalse(opt.isPresent());
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
}
