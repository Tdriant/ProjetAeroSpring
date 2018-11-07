package repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import model.Escale;
import model.EscaleKey;

public interface EscaleRepository extends JpaRepository<Escale, EscaleKey> {

}
