package fr.adaming.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.adaming.model.Client;
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
		String req = "DELETE FROM Commande co WHERE co.id=:pIdCo";

		// recupérer Query
		Query query = em.createQuery(req);
		query.setParameter("pIdCo", co.getId());
		int verif=query.executeUpdate();
		return verif;
		}catch(Exception ex) {

		}
		return 0;
	}

	@Override
	public Commande getCommande(Commande co) {
		return em.find(Commande.class, co.getId());
	}

	@Override
	public Commande getAllCommandeByCl(Client cl) {
		// ecrire la requette JPQL
		String req = "SELECT co FROM Commande co WHERE co.cl.id=:pIdCl";

		// recupérer Query
		Query query = em.createQuery(req);
		query.setParameter("pIdCl", cl.getId());

		// recuperation du resultat
		Commande Co = (Commande) query.getSingleResult();
		return Co;
	}

}
