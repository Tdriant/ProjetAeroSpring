package repositories;

import org.springframework.data.jpa.repository.JpaRepository;


import model.CompagnieAerienneVol;
import model.CompagnieAerienneVolKey;

public interface CaVolRepository extends JpaRepository<CompagnieAerienneVol, CompagnieAerienneVolKey>{

}
