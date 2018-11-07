package repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Ville;

public interface VilleRepository extends JpaRepository<Ville, Integer>{
	
	List<Ville> findAllWithAeroport();
	List<Ville> findByIdWithAeroport(Integer key);

}
