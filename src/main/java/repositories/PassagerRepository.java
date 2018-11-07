package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Passager;

public interface PassagerRepository extends JpaRepository<Passager, Integer> {
	List<Passager> findAllWithReservation(Integer key);

	Passager findByIdWithReservation(Integer key);
}
