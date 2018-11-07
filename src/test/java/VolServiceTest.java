import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import model.Aeroport;
import model.CompagnieAerienne;
import model.Reservation;
import model.Vol;
import repositories.AeroportRepository;
import repositories.ReservationRepository;
import repositories.VolRepository;
import services.VolService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationcontext.xml" })
public class VolServiceTest {

	@Autowired
	private VolRepository volRepo;

	@Autowired
	private AeroportRepository aeroRepo;

	@Autowired
	private ReservationRepository resaRepo;

	@Autowired
	private VolService volService;

	@Test
	public void test() {

		SimpleDateFormat date = new SimpleDateFormat("DD/MM/YYYY");
		SimpleDateFormat heure = new SimpleDateFormat("HH-mm-ss");

		try {
			Aeroport cdg;
			cdg = new Aeroport("cdg");
			Aeroport cde;
			cde = new Aeroport("cde");
			aeroRepo.save(cdg);
			aeroRepo.save(cde);

			volService.createVol(date.parse("26/10/2018"), date.parse("27/10/2018"), heure.parse("11-50-00"),
					heure.parse("06-45-00"), cdg, cde);

		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testListResa() {

		SimpleDateFormat date = new SimpleDateFormat("DD/MM/YYYY");
		SimpleDateFormat heure = new SimpleDateFormat("HH-mm-ss");
		Reservation a1;
		a1 = new Reservation();
		resaRepo.save(a1);
		
		try {
			Aeroport cdg;
			cdg = new Aeroport("cdg");
			Aeroport cde;
			cde = new Aeroport("cde");
			aeroRepo.save(cdg);
			aeroRepo.save(cde);

			volService.createVol(date.parse("26/10/2018"), date.parse("27/10/2018"), heure.parse("11-50-00"),
					heure.parse("06-45-00"), cdg, cde);
			volService.addReservation(a1);
			volService.getResa(50);

		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}
