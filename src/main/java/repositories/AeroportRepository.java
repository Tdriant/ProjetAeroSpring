package repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import model.Aeroport;
import model.Vol;

public interface AeroportRepository extends JpaRepository<Aeroport, Integer> {
//	
//	@Query("select a from Aeroport a left join fetch Vol v on a.aero_id = v.where s.numero =:numero")
//	List<Vol> findByIdWithVolDepartAtDate(Integer id, Date date);

}
