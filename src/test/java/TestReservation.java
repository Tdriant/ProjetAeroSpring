import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import model.Reservation;
import repositories.ReservationRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/applicationcontext.xml" })
public class TestReservation {

	@Autowired
	private ReservationRepository reservationRepository;

	@Test
	public void repo() {
		assertNotNull(reservationRepository);
	}
	
	@Test
	public void insert() {
		Reservation a1;
		a1 = new Reservation();
		assertNull(a1.getId());
		reservationRepository.save(a1);
		assertNotNull(a1.getId());
	}

	@Test
	public void findByKey() {
		Reservation a2;
		a2 = new Reservation();
		reservationRepository.save(a2);
		assertNotNull(reservationRepository.findById(a2.getId()));
		Optional<Reservation> res = reservationRepository.findById(a2.getId());
		if (res.isPresent()) {
			assertNotNull(res.get());
		}
	}

	@Test
	public void update() {
		Reservation a3;
		SimpleDateFormat sdf = new SimpleDateFormat("DD/MM/YYYY");
		a3 = new Reservation();
		reservationRepository.save(a3);
		Optional<Reservation> res = reservationRepository.findById(a3.getId());
		if (res.isPresent()) {
			a3 = res.get();
		}
		try {
			a3.setDate(sdf.parse("15/05/2018"));
			reservationRepository.save(a3);
			res = reservationRepository.findById(a3.getId());
			if (res.isPresent()) {
				assertEquals(sdf.parse("15/05/2018"), res.get().getDate());
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void findAll() {
		assertNotNull(reservationRepository.findAll());
	}

	@Test
	public void delete() {
		Reservation a4;
		a4 = new Reservation();
		reservationRepository.save(a4);
		reservationRepository.delete(a4);
		assertFalse(reservationRepository.findById(a4.getId()).isPresent());
	}

	@Test
	public void deleteByKey() {
		Reservation a5;
		a5 = new Reservation();
		reservationRepository.save(a5);
		reservationRepository.save(a5);
		reservationRepository.deleteById(a5.getId());
		assertFalse(reservationRepository.findById(a5.getId()).isPresent());
	}
}
