package fr.adaming.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;

@Stateless
public class LigneCommandeDaoImpl implements ILigneCommandeDao {

	@PersistenceContext(unitName = "eCommerc")
	private EntityManager em;

	@Override
	public LigneCommande addLigneCommande(LigneCommande lco) {
		em.persist(lco);
		return lco;
	}

	@Override
	public LigneCommande upDateLigneCommande(LigneCommande lco) {
		em.find(LigneCommande.class, lco.getId());
		em.merge(lco);
		return lco;
	}

	@Override
	public LigneCommande getLigneCommande(LigneCommande lco) {
		return em.find(LigneCommande.class, lco.getId());
	}

	@Override
	public List<LigneCommande> getAllLigneCommandeByCo(Commande co) {
		// ecrire la requette JPQL
		String req = "SELECT lco FROM LigneCommande lco WHERE lco.co.id=:pIdCo";

		// recupérer Query
		Query query = em.createQuery(req);
		query.setParameter("pIdCo", co.getId());

		// recuperation du resultat
		List<LigneCommande> liste = query.getResultList();
		return liste;
	}

	@Override
	public int deleteLigneCommande(LigneCommande lco) {
		try {
			// ecrire la requette JPQL
			String req = "DELETE FROM LigneCommande lco WHERE lco.id=:pIdlCo";

			// recupérer Query
			Query query = em.createQuery(req);
			query.setParameter("pIdlCo", lco.getId());
			int verif = query.executeUpdate();
			return verif;
		} catch (Exception ex) {

		}
		return 0;
	}

}
