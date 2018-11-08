package repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import model.Reservation;
import model.Vol;

public interface VolRepository extends JpaRepository<Vol, Integer>{
	
	Optional<Reservation> findReservationByVolId(@Param("id") Integer id);
	Optional <Vol> findByIdWithReservation(@Param("id") Integer id);
	List<Vol> findAllWithReservation();
	Optional<Vol> findByKeyWithEscales(@Param("id") Integer id);

}
