package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import model.Vol;
import util.Context;


public class DaoVolJpa implements DaoVol {

	@Override
	public void create(Vol obj) {
		// INstancier em, voir si un existe ou non, créer le manager.
		// Créer la transaction : les if apres vérifient que tx est une transaction en
		// cours et que em est bien ouvert.
		// Dans le try, on a le début, la création d'objet peristant, et la sauvegarde
		// (commit)
		// Le rollback du catch sert à annuler les changements créant le bug
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
	public Vol findByKey(Integer Key) {
		EntityManager em = Context.getInstance().getEntityManagerFactory().createEntityManager();
		Vol vol = null;
		vol = em.find(Vol.class, Key);
		em.close();
		return vol;
	}

	@Override
	public Vol update(Vol obj) {
		EntityManager em = Context.getInstance().getEntityManagerFactory().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		Vol vol = null;
		try {
			tx.begin();
		vol=em.merge(obj);
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
		return vol;
	}

	@Override
	public void delete(Vol obj) {
		EntityManager em = Context.getInstance().getEntityManagerFactory().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			// remove obj directement traite uniquement l'objet java, non celui en bdd.
			// merge cherche l'objet donné en base, et le remonte rattaché à la base.
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
	public void deleteByKey(Integer Key) {
		EntityManager em = Context.getInstance().getEntityManagerFactory().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.remove(em.find(Vol.class, Key));
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
	public List<Vol> findAll() {
		EntityManager em= Context.getInstance().getEntityManagerFactory().createEntityManager();
		// Créer une requete JPQL (java persistance query langage) demandant les caractéristiques de la table vol (ici toutes les carac, 
		// de toutes les instances de la classe Vol). Si on veut toutes les variables, pas besoin de select p
		Query query = em.createQuery("from Vol p");
		List<Vol> vols =null;
		vols=query.getResultList();
		em.close();
		
		return vols;
	}

}
