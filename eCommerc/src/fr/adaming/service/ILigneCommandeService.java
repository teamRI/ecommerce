package fr.adaming.service;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;

@Local
public interface ILigneCommandeService {

	public LigneCommande addLigneCommande(LigneCommande lco);
	public LigneCommande upDateLigneCommande(LigneCommande lco);
	public LigneCommande getLigneCommande(LigneCommande lco);
	public List<LigneCommande> getAllLigneCommandeByCo(Commande co);
	public int deleteLigneCommande(LigneCommande lco);
}
