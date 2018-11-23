package fr.adaming.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.codec.binary.Base64;

import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;

@Stateless
public class ProduitDaoImpl implements IProduitDao{

	@PersistenceContext(unitName="eCommerc")
	private EntityManager em;
	
	
	@Override
	public List<Produit> getAllProduit(Categorie c) {

		String req="SELECT p FROM Produit as p WHERE p.pCategorie.id=:pCategorie";
		
		Query query= em.createQuery(req);
		
		query.setParameter("pCategorie", c.getId());
		
		List<Produit> liste= query.getResultList();
		
	
		for(Produit pr: liste) {
			pr.setImage("data:image/png;base64," + Base64.encodeBase64String(pr.getPhoto()));
		}
		return liste;
	}

	@Override
	public Produit addProduit(Produit pr) {
		em.persist(pr);
		return pr;
	}

	@Override
	public Produit getProduit(Produit pr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Produit upDateProduit(Produit pr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delateProduit(Produit pr) {
		// TODO Auto-generated method stub
		return 0;
	}

}
