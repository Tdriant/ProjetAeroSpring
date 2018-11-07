package dao;

import java.util.List;

import model.Passager;

public interface DaoPassager extends DaoGeneric<Passager, Integer>{
	List<Passager> findAllWithReservation(Integer key);
	
	Passager findByKeyWithReservation(Integer key);


}
