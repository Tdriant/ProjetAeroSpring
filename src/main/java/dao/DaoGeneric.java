package dao;

import java.util.List;

public interface DaoGeneric<T, K> {
	void create(T obj);

	T findByKey(K key);

	T update(T obj);

	void delete(T obj);
	
	void deleteByKey(K key);
	
	List<T> findAll();
}
