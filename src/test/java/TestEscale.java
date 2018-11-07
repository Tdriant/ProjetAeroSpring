import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import model.Aeroport;
import model.Escale;
import model.EscaleKey;
import model.Vol;
import repositories.AeroportRepository;
import repositories.EscaleRepository;
import repositories.VolRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationcontext.xml" })
public class TestEscale {

	@Autowired
	private EscaleRepository escaleRepository;

	@Autowired
	private VolRepository volRepository;

	@Autowired
	private AeroportRepository aeroportRepository;

	private static Aeroport a1;

	private static Vol v1;

	@BeforeClass
	public static void before() {
		SimpleDateFormat date = new SimpleDateFormat("DD/MM/YYYY");
		SimpleDateFormat heure = new SimpleDateFormat("HH-mm-ss");
		a1 = new Aeroport("Paris");
		try {
			v1 = new Vol(date.parse("26/10/2018"), date.parse("27/10/2018"), heure.parse("11-50-00"),
					heure.parse("06-45-00"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@Before
	public void creaObj() {
		aeroportRepository.save(a1);
		volRepository.save(v1);
		Escale e1 = new Escale(new EscaleKey(v1, a1));
		escaleRepository.save(e1);
	}


	@Test
	public void findById() {
		aeroportRepository.save(a1);
		volRepository.save(v1);
		Escale e1 = new Escale(new EscaleKey(v1, a1));
		assertNotNull(e1.getId());
		Optional<Escale> esc1 = escaleRepository.findById(e1.getId());
		if (esc1.isPresent()) {
			assertNotNull(esc1.get());
		}
	}

	@Test
	public void update() {
		SimpleDateFormat heure = new SimpleDateFormat("HH-mm-ss");
		aeroportRepository.save(a1);
		volRepository.save(v1);
		try {
			Escale e2 = new Escale(new EscaleKey(v1, a1), heure.parse("11-25-00"), heure.parse("13-45-00"));
			assertNotNull(e2.getId());
			Optional<Escale> esc1 = escaleRepository.findById(e2.getId());
			if (esc1.isPresent()) {
				e2 = esc1.get();
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void findAll() {
		assertNotNull(escaleRepository.findAll());
	}

	@Test
	public void delete() {
		aeroportRepository.save(a1);
		volRepository.save(v1);
		Escale e1 = new Escale(new EscaleKey(v1, a1));
			escaleRepository.delete(e1);
			assertFalse(escaleRepository.findById(e1.getId()).isPresent());
	}

	@Test
	public void deleteById() {
		aeroportRepository.save(a1);
		volRepository.save(v1);
		Escale e1 = new Escale(new EscaleKey(v1, a1));
		escaleRepository.deleteById(e1.getId());
		assertFalse(escaleRepository.findById(e1.getId()).isPresent());
	}
}
