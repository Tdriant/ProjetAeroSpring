package repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Aeroport;

public interface AeroportRepository extends JpaRepository<Aeroport, Integer> {
//	
//	@Query("select a from Aeroport a left join fetch Vol v on a.aero_id = v.where s.numero =:numero")
//	List<Vol> findByIdWithVolDepartAtDate(Integer id, Date date);

}
