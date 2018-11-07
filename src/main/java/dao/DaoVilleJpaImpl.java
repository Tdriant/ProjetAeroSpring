package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import model.Ville;
import util.Context;

public class DaoVilleJpaImpl implements DaoVille {

	@Override
	public void create(Ville obj) {
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

	@Override
	public Ville findByKey(Integer key) {
		EntityManager em = Context.getInstance().getEntityManagerFactory().createEntityManager();
		Ville v = null;
		v = em.find(Ville.class, key);
		return v;
	}

	@Override
	public Ville update(Ville obj) {
		EntityManager em = Context.getInstance().getEntityManagerFactory().createEntityManager();
		EntityTransaction tx = null;
		Ville v = null;
		try {
			tx = em.getTransaction();
			tx.begin();
			v = em.merge(obj);
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
		return v;
	}

	@Override
	public void delete(Ville obj) {
		EntityManager em = Context.getInstance().getEntityManagerFactory().createEntityManager();
		EntityTransaction tx = null;
		try {
			tx = em.getTransaction();
			tx.begin();
			obj = em.merge(obj);
//			for (Aeroport a : obj.getAeroports()) {// A enlever ?
//				System.out.println(a);
//				em.remove(a);
//			}
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

	@Override
	public void deleteByKey(Integer key) {
		EntityManager em = Context.getInstance().getEntityManagerFactory().createEntityManager();
		EntityTransaction tx = null;
		try {
			tx = em.getTransaction();
			tx.begin();
			em.remove(em.find(Ville.class, key));
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Ville> findAll() {
		EntityManager em = Context.getInstance().getEntityManagerFactory().createEntityManager();
		Query query = em.createQuery("from Ville v");
		List<Ville> villes = null;
		villes = query.getResultList();
		em.close();
		return villes;
	}

	@SuppressWarnings("unchecked")
	public List<Ville> findAllWithAeroport() {
		EntityManager em = Context.getInstance().getEntityManagerFactory().createEntityManager();
		Query query = em.createNamedQuery("Ville.findAllWithAeroport");
		List<Ville> villes = null;
		villes = query.getResultList();
		em.close();
		return villes;
	}

	@Override
	public Ville findByKeyWithAeroport(Integer key) {
		EntityManager em = Context.getInstance().getEntityManagerFactory().createEntityManager();
		Query query = em.createNamedQuery("Ville.findByKeyWithAeroport");
		query.setParameter("id", key);
		Ville ville = null;
		try {
			ville = (Ville) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		em.close();
		return ville;
	}
}
