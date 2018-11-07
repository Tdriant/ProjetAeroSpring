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

import model.Vol;
import repositories.VolRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationcontext.xml" })
public class TestVol {
	@Autowired
	private VolRepository volRepo;

	@Test
	public void tryVol() {
	SimpleDateFormat date = new SimpleDateFormat("DD/MM/YYYY");

	try
	{
		Vol bouwa = new Vol();
	Vol bouwi = new Vol(date.parse("12/12/2018"), date.parse("12/12/2019"));
	Vol Baboum = new Vol(date.parse("07/11/2018"), date.parse("08/11/2018"));
	
		bouwa.setDateDepart(date.parse("12/12/1998"));
		bouwa.setDateArrivee(date.parse("12/12/1998"));
		volRepo.save(bouwa);
		volRepo.save(bouwi);
		volRepo.save(Baboum);
		bouwa.setDateArrivee(date.parse("14/12/1998"));
		volRepo.save(bouwa);
		volRepo.deleteById(bouwa.getId());
		assertNotNull(volRepo.findAll());
		assertNotNull(volRepo.findById(Baboum.getId()));

		Optional<Vol> optt = volRepo.findById(bouwa.getId());
		assertFalse(optt.isPresent());
	}catch(
	ParseException e)
	{
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	assertNotNull(volRepo);
}

}
