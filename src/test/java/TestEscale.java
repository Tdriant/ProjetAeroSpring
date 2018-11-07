import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

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
	private static Aeroport a2;
	private static Vol v1;
	private static Vol v2;

	@BeforeClass
	public static void before() {
		SimpleDateFormat date = new SimpleDateFormat("DD/MM/YYYY");
		SimpleDateFormat heure = new SimpleDateFormat("HH-mm-ss");
		a1 = new Aeroport("Paris");
		a2 = new Aeroport("Madrid");
		try {
			v1 = new Vol(date.parse("26/10/2018"), date.parse("27/10/2018"), heure.parse("11-50-00"),
					heure.parse("06-45-00"));
			v2 = new Vol(date.parse("15/05/2009"), date.parse("15/05/2009"), heure.parse("06-05-00"),
					heure.parse("12-30-00"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void insert() {
		aeroportRepository.save(a1);
		aeroportRepository.save(a2);
		volRepository.save(v1);
		volRepository.save(v2);
		Escale e1 = new Escale(new EscaleKey(v1, a1));
		Escale e2 = new Escale(new EscaleKey(v2, a2));
		escaleRepository.save(e1);
		escaleRepository.save(e2);
		assertNotNull(e1.getId());
		assertNotNull(e2.getId());
	}

	@Test
	public void findById() {
		aeroportRepository.save(a1);
		aeroportRepository.save(a2);
		volRepository.save(v1);
		volRepository.save(v2);
		Escale e1 = new Escale(new EscaleKey(v1, a1));
		Escale e2 = new Escale(new EscaleKey(v2, a2));
		escaleRepository.save(e1);
		escaleRepository.save(e2);
		assertNotNull(e1.getId());
		assertNotNull(e2.getId());
		Optional<Escale> esc1 = escaleRepository.findById(e1.getId());
		Optional<Escale> esc2 = escaleRepository.findById(e2.getId());
		if (esc1.isPresent()) {
			assertNotNull(esc1.get());
		}
		if (esc2.isPresent()) {
			assertNotNull(esc2.get());
		}
	}

	@Test
	public void update() {
		SimpleDateFormat heure = new SimpleDateFormat("HH-mm-ss");
		aeroportRepository.save(a1);
		aeroportRepository.save(a2);
		volRepository.save(v1);
		volRepository.save(v2);
		Escale e1;
		try {
			e1 = new Escale(new EscaleKey(v1, a1), heure.parse("11-25-00"), heure.parse("13-45-00"));
			Escale e2 = new Escale(new EscaleKey(v2, a2), heure.parse("15-00-00"), heure.parse("16-00-00"));
			escaleRepository.save(e1);
			escaleRepository.save(e2);
			assertNotNull(e1.getId());
			assertNotNull(e2.getId());
			Optional<Escale> esc1 = escaleRepository.findById(e1.getId());
			Optional<Escale> esc2 = escaleRepository.findById(e2.getId());
			if (esc1.isPresent()) {
				e1 = esc1.get();
			}
			if (esc2.isPresent()) {
				e2 = esc2.get();
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
		SimpleDateFormat heure = new SimpleDateFormat("HH-mm-ss");
		aeroportRepository.save(a1);
		aeroportRepository.save(a2);
		volRepository.save(v1);
		volRepository.save(v2);
		Escale e1;
		try {
			e1 = new Escale(new EscaleKey(v1, a1), heure.parse("11-25-00"), heure.parse("13-45-00"));
			Escale e2 = new Escale(new EscaleKey(v2, a2), heure.parse("15-00-00"), heure.parse("16-00-00"));
			escaleRepository.save(e1);
			escaleRepository.save(e2);
			escaleRepository.delete(e2);
			assertFalse(escaleRepository.findById(e2.getId()).isPresent());
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void deleteById() {
		SimpleDateFormat heure = new SimpleDateFormat("HH-mm-ss");
		aeroportRepository.save(a1);
		aeroportRepository.save(a2);
		volRepository.save(v1);
		volRepository.save(v2);
		Escale e1;
		try {
			e1 = new Escale(new EscaleKey(v1, a1), heure.parse("11-25-00"), heure.parse("13-45-00"));
			Escale e2 = new Escale(new EscaleKey(v2, a2), heure.parse("15-00-00"), heure.parse("16-00-00"));
			escaleRepository.save(e1);
			escaleRepository.save(e2);
			escaleRepository.deleteById(e2.getId());
			assertFalse(escaleRepository.findById(e2.getId()).isPresent());
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}
