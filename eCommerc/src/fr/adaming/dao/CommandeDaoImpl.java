package fr.adaming.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.adaming.model.Commande;

@Stateless
public class CommandeDaoImpl implements ICommandeDao {

	@PersistenceContext(unitName = "eCommerc")
	private EntityManager em;

	@Override
	public Commande addCommande(Commande co) {
		em.persist(co);
		return co;
	}

	@Override
	public Commande upDateCommande(Commande co) {
		em.find(Commande.class, co.getId());
		em.merge(co);
		return co;
	}

	@Override
	public int deleteCommande(Commande co) {
		try {
		// ecrire la requette JPQL
		String req = "DELETE co FROM Commande co WHERE co.id=:pIdCo";

		// recupérer Query
		Query query = em.createQuery(req);
		query.setParameter("pIdCo", co.getId());

		return 1;
		}catch(Exception ex) {

		}
		return 0;
	}

	@Override
	public Commande getCommande(Commande co) {
		return em.find(Commande.class, co.getId());
	}

	@Override
	public List<Commande> getAllCommandeByCl(Commande co) {
		// ecrire la requette JPQL
		String req = "SELECT co FROM Commande co WHERE co.cl.id=:pIdCl";

		// recupérer Query
		Query query = em.createQuery(req);
		query.setParameter("pIdCl", co.getCl().getId());

		// recuperation du resultat
		List<Commande> liste = query.getResultList();
		return liste;
	}

}
