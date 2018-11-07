package repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Integer>{

}
