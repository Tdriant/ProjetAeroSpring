package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import model.Passager;
import model.Reservation;
import util.Context;


public class DaoPassagerJpaImpl implements DaoPassager {

	@Override
	public void create(Passager obj) {
		EntityManager em = Context.getInstance().getEntityManagerFactory().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.persist(obj);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
		} finally {
			if (em != null && em.isOpen()) {
				em.close();
			}
		}
	}

	@Override
	public model.Passager findByKey(Integer key) {
		EntityManager em = Context.getInstance().getEntityManagerFactory().createEntityManager();
		Passager passager = null;
		passager = em.find(Passager.class, key);
		em.close();
		return passager;
	}

	@Override
	public Passager update(Passager obj) {
		EntityManager em = Context.getInstance().getEntityManagerFactory().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		Passager passager = null;
		try {
			tx.begin();
			passager = em.merge(obj);
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
		} finally {
			if (em != null && em.isOpen()) {
				em.close();
			}
		}
		return passager;
		
	}

	@Override
	public void delete(Passager obj) {
		EntityManager em = Context.getInstance().getEntityManagerFactory().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.remove(em.merge(obj));
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
		} finally {
			if (em != null && em.isOpen()) {
				em.close();
			}
		}
	}

	@Override
	public void deleteByKey(Integer key) {
		EntityManager em = Context.getInstance().getEntityManagerFactory().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			Passager obj = em.merge((em.find(Passager.class, key)));
			for (Reservation reservation :obj.getReservations()) {
				em.remove(reservation);
			}
			em.remove(em.find(Passager.class, key));
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
		} finally {
			if (em != null && em.isOpen()) {
				em.close();
			}
		}
	}


	@Override
	public List<Passager> findAll() {
		EntityManager em = Context.getInstance().getEntityManagerFactory().createEntityManager();
		Query query = em.createQuery("from Passager p");
		List<Passager> passagers = null;
		passagers = query.getResultList();
		em.close();
		return passagers;
	}

	@Override
	public List<Passager> findAllWithReservation(Integer key) {
		EntityManager em = Context.getInstance().getEntityManagerFactory().createEntityManager();
		Query query = em.createNamedQuery("Passager.findByKeyWithReservation");
		List<Passager> passagers = query.getResultList();
		em.close();
		return passagers;
	}

	@Override
	public Passager findByKeyWithReservation(Integer key) {
		EntityManager em = Context.getInstance().getEntityManagerFactory().createEntityManager();
		Query query = em.createNamedQuery("Passager.findByKeyWithReservation");
		query.setParameter("id", key);
		Passager passager = null;
		try {
			passager = (Passager) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		em.close();
		return passager;
	}


}
