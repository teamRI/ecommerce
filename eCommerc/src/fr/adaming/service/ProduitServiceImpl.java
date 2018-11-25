package fr.adaming.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.dao.IProduitDao;
import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;

@Stateful
public class ProduitServiceImpl implements IProduitService{

	@EJB
	public IProduitDao prDao;
	
	@Override
	public List<Produit> getAllProduit(Categorie c) {
		
		return prDao.getAllProduit(c);
	}

	@Override
	public Produit addProduit(Produit pr, Categorie c) {
		pr.setpCategorie(c);
		return prDao.addProduit(pr);
	}

	@Override
	public Produit getProduit(Produit pr, Categorie c) {
		Produit pOut= prDao.getProduit(pr);
		return pOut;
	}

	@Override
	public Produit upDateProduit(Produit pr, Categorie c) {
		pr.setpCategorie(c);
		return prDao.upDateProduit(pr);
	}

	@Override
	public int delateProduit(Produit pr) {
		
		return prDao.delateProduit(pr);
	}

	@Override
	public Produit getProduitByNom(Produit pr) {
		
		return prDao.getProduitByNom(pr);
	}
	

	
	
}
