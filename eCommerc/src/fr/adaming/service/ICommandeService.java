package fr.adaming.service;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.model.Client;
import fr.adaming.model.Commande;

@Local
public interface ICommandeService {

	public Commande addCommande(Commande co);
	public Commande upDateCommande(Commande co);
	public int deleteCommande(Commande co);
	public Commande getCommande(Commande co);
	public List<Commande> getAllCommandeByCl(Client cl);
}
