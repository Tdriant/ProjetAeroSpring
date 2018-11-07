import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;


import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import model.Vol;
import repositories.VolRepository;

import static org.junit.Assert.*;


import org.junit.Test;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationcontext.xml" })
public class TestVol {
	@Autowired
	private VolRepository volRepo;

	@Test
	public void tryVol() {
	SimpleDateFormat date = new SimpleDateFormat("DD/MM/YYYY");
	Vol bouwa = new Vol();
	Vol bouwi = new Vol();
	Vol Baboum = new Vol();
	
	
	try
	{
		bouwa.setDateDepart(date.parse("12/12/1998"));
		bouwa.setDateArrivee(date.parse("12/12/1998"));
	}catch(
	ParseException e)
	{
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	volRepo.save(bouwa);
	assertNotNull(volRepo);
}

}
