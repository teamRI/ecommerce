package fr.adaming.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.adaming.model.Categorie;

@Stateless
public class CategorieDaoImpl implements ICategorieDao{

	@PersistenceContext(unitName="eCommerc")
	private EntityManager em;
	
	@Override
	public List<Categorie> getAllCategorie() {
		
		String req="SELECT c FROM Categorie c";
		
		Query query= em.createQuery(req);
		
		
		return query.getResultList();
	}

	@Override
	public Categorie addCategorie(Categorie c) {
		
		em.persist(c);
		return c;
	}

	@Override
	public Categorie getCategorie(Categorie c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Categorie upDateCategorie(Categorie c) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delateCategorie(Categorie c) {
		// TODO Auto-generated method stub
		return 0;
	}
	

}
