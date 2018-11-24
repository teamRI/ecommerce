package fr.adaming.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.codec.binary.Base32;
import org.apache.commons.codec.binary.Base64;

import fr.adaming.model.Categorie;

@Stateless
public class CategorieDaoImpl implements ICategorieDao{

	@PersistenceContext(unitName="eCommerc")
	private EntityManager em;
	
	@Override
	public List<Categorie> getAllCategorie() {
		
		String req="SELECT c FROM Categorie c";
		
		Query query= em.createQuery(req);
		
		List<Categorie> liste= query.getResultList();
		
		for(Categorie cat: liste) {
			cat.setImage("data:image/png;base64," + Base64.encodeBase64String(cat.getPhoto()));
		}
		return liste; 
	}

	@Override
	public Categorie addCategorie(Categorie c) {
		
		em.persist(c);
		return c;
	}

	@Override
	public Categorie getCategorie(Categorie c) {
		Categorie cOut= em.find(Categorie.class, c.getId());
		cOut.setImage("data:image/png;base64," + Base64.encodeBase64String(c.getPhoto()));
		return cOut;
	}

	@Override
	public Categorie upDateCategorie(Categorie c) {
		Categorie cOut= em.find(Categorie.class, c.getId());
		
		cOut.setDescription(c.getDescription());
		cOut.setNomCat(c.getNomCat());
		cOut.setImage("data:image/png;base64," + Base64.encodeBase64String(c.getPhoto()));
		return em.merge(cOut);
	}

	@Override
	public int delateCategorie(Categorie c) {
		Categorie cOut= em.find(Categorie.class, c.getId());
		if(cOut!=null) {
			em.remove(cOut);
			return 1;
		}else {
		return 0;
		}
	}
	

}
