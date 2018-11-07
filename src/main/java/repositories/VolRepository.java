package repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import model.Vol;

public interface VolRepository extends JpaRepository<Vol, Integer>{
	
	Optional<Vol> findByIdWithReservations(@Param("id") Integer id);
	List<Vol> findAllWithReservation();
	Optional<Vol> findByKeyWithEscales(@Param("id") Integer id);
	
}
