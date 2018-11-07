package dao;

import java.util.List;

import model.Ville;

public interface DaoVille extends DaoGeneric<Ville, Integer> {
	List<Ville> findAllWithAeroport();
	Ville findByKeyWithAeroport(Integer key);


}
