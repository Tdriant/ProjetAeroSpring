package dao;

import java.util.List;

import model.Client;

public interface DaoClient extends DaoGeneric<Client, Integer>{
	List<Client> findAllWithReservation(Integer key);
	
	Client findByKeyWithReservation(Integer key);
}
