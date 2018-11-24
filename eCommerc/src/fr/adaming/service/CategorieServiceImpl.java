package fr.adaming.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.dao.ICategorieDao;
import fr.adaming.model.Categorie;

@Stateful
public class CategorieServiceImpl implements ICategorieService{

	@EJB
	public ICategorieDao catDao;

	@Override
	public List<Categorie> getAllCategorie() {
		
		return catDao.getAllCategorie();
	}

	@Override
	public Categorie addCategorie(Categorie c) {
	
		return catDao.addCategorie(c);
	}

	@Override
	public Categorie getCategorie(Categorie c) {
		Categorie cOut=catDao.getCategorie(c);
		return cOut;
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
