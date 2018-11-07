package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import model.Client;

public interface ClientRepository extends JpaRepository<Client, Integer>{

	List<Client> findAllWithReservation(Integer key);
	
	Client findByIdWithReservation(@Param("id") Integer key);
}
