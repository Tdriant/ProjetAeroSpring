package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import model.CompagnieAerienne;
import util.Context;

public class DaoCompagnieAerienneJpa implements DaoCompagnieAerienne {

	@Override
	public void create(CompagnieAerienne obj) {
		// INstancier em, voir si un existe ou non, cr�er le manager.
		// Cr�er la transaction : les if apres v�rifient que tx est une transaction en
		// cours et que em est bien ouvert.
		// Dans le try, on a le d�but, la cr�ation d'objet peristant, et la sauvegarde
		// (commit)
		// Le rollback du catch sert � annuler les changements cr�ant le bug
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
	public CompagnieAerienne findByKey(Integer Key) {
		EntityManager em = Context.getInstance().getEntityManagerFactory().createEntityManager();
		CompagnieAerienne compagnieAerienne = null;
		compagnieAerienne = em.find(CompagnieAerienne.class, Key);
		em.close();
		return compagnieAerienne;
	}

	@Override
	public CompagnieAerienne update(CompagnieAerienne obj) {
		EntityManager em = Context.getInstance().getEntityManagerFactory().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		CompagnieAerienne compagnieAerienne = null;
		try {
			tx.begin();
		compagnieAerienne=em.merge(obj);
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
		return compagnieAerienne;
	}

	@Override
	public void delete(CompagnieAerienne obj) {
		EntityManager em = Context.getInstance().getEntityManagerFactory().createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			// remove obj directement traite uniquement l'objet java, non celui en bdd.
			// merge cherche l'objet donn� en base, et le remonte rattach� � la base.
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
			em.remove(em.find(CompagnieAerienne.class, Key));
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
	public List<CompagnieAerienne> findAll() {
		EntityManager em= Context.getInstance().getEntityManagerFactory().createEntityManager();
		// Cr�er une requete JPQL (java persistance query langage) demandant les caract�ristiques de la table compagnieAerienne (ici toutes les carac, 
		// de toutes les instances de la classe CompagnieAerienne). Si on veut toutes les variables, pas besoin de select p
		Query query = em.createQuery("from CompagnieAerienne p");
		List<CompagnieAerienne> compagnieAeriennes =null;
		compagnieAeriennes=query.getResultList();
		em.close();
		return compagnieAeriennes;
	}

}
