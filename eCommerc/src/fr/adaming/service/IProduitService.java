package fr.adaming.service;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;

@Local
public interface IProduitService {

	 public List<Produit> getAllProduit(Categorie c);
		
		public Produit addProduit(Produit pr, Categorie c);
		
		public Produit getProduit(Produit pr, Categorie c);
		
		public Produit upDateProduit(Produit pr, Categorie c);
		
		public int delateProduit(Produit pr);

}
