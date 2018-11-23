package fr.adaming.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.dao.ICommandeDao;
import fr.adaming.dao.ILigneCommandeDao;
import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;

@Stateful
public class LigneCommandeServiceImpl implements ILigneCommandeService{

	@EJB
	private ILigneCommandeDao lcoDao;
	
	@Override
	public LigneCommande addLigneCommande(LigneCommande lco) {
		LigneCommande lcoOut=lcoDao.addLigneCommande(lco);
		return lcoOut;
	}

	@Override
	public LigneCommande upDateLigneCommande(LigneCommande lco) {
		return lcoDao.upDateLigneCommande(lco);
	}

	@Override
	public LigneCommande getLigneCommande(LigneCommande lco) {
		return lcoDao.getLigneCommande(lco);
	}

	@Override
	public List<LigneCommande> getAllLigneCommandeByCo(Commande co) {
		return lcoDao.getAllLigneCommandeByCo(co);
	}

	@Override
	public int deleteLigneCommande(LigneCommande lco) {
		return lcoDao.deleteLigneCommande(lco);
	}

}
