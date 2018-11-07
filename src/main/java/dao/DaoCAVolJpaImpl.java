package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import model.CompagnieAerienneVol;
import model.CompagnieAerienneVolKey;
import util.Context;

@SuppressWarnings("unchecked")
class DaoCAVolJpaImpl implements DaoCAVol {

	public void create(CompagnieAerienneVol obj) {
		EntityManager em = Context.getInstance().getEntityManagerFactory().createEntityManager();
		EntityTransaction tx = null;
		try {
			tx = em.getTransaction();
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

	public CompagnieAerienneVol findByKey(CompagnieAerienneVolKey key) {
		EntityManager em = Context.getInstance().getEntityManagerFactory().createEntityManager();
		CompagnieAerienneVol recherche = null;
		recherche = em.find(CompagnieAerienneVol.class, key);
		em.close();
		return recherche;
	}

	public CompagnieAerienneVol update(CompagnieAerienneVol obj) {
		EntityManager em = Context.getInstance().getEntityManagerFactory().createEntityManager();
		EntityTransaction tx = null;
		CompagnieAerienneVol compagnieAerienneVol = null;
		try {
			tx = em.getTransaction();
			tx.begin();
			compagnieAerienneVol = em.merge(obj);
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
		return compagnieAerienneVol;
	}

	public void delete(CompagnieAerienneVol obj) {
		EntityManager em = Context.getInstance().getEntityManagerFactory().createEntityManager();
		EntityTransaction tx = null;
		try {
			tx = em.getTransaction();
			tx.begin();
			obj = em.merge(obj);
			em.remove(obj);
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

	public void deleteByKey(CompagnieAerienneVolKey key) {
		EntityManager em = Context.getInstance().getEntityManagerFactory().createEntityManager();
		EntityTransaction tx = null;
		try {
			tx = em.getTransaction();
			tx.begin();
			em.remove(em.find(CompagnieAerienneVol.class, key));
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

	public List<CompagnieAerienneVol> findAll() {
		EntityManager em = Context.getInstance().getEntityManagerFactory().createEntityManager();
		Query query = em.createQuery("from CompagnieAerienneVol a");
		List<CompagnieAerienneVol> compagnieAerienneVols = null;
		compagnieAerienneVols = query.getResultList();
		em.close();
		return compagnieAerienneVols;
	}
}
