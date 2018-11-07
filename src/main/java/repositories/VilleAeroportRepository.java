package repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import model.VilleAeroport;
import model.VilleAeroportKey;

public interface VilleAeroportRepository extends JpaRepository<VilleAeroport, VilleAeroportKey> {

}
