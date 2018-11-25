package fr.adaming.service;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.dao.ICommandeDao;
import fr.adaming.model.Client;
import fr.adaming.model.Commande;

@Stateful
public class CommandeServiceImpl implements ICommandeService{

	@EJB
	private ICommandeDao coDao;
	
	@Override
	public Commande addCommande(Commande co) {
		Commande coOut=coDao.addCommande(co);
		return coOut;
	}

	@Override
	public Commande upDateCommande(Commande co) {
		return coDao.upDateCommande(co);
	}

	@Override
	public int deleteCommande(Commande co) {
		return coDao.deleteCommande(co);
	}

	@Override
	public Commande getCommande(Commande co) {
		return coDao.getCommande(co);
	}

	@Override
	public Commande getAllCommandeByCl(Client cl) {

		return coDao.getAllCommandeByCl(cl);
	}

	

}
