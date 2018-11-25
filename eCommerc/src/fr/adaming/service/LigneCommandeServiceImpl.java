package fr.adaming.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.dao.ICommandeDao;
import fr.adaming.dao.ILigneCommandeDao;
import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;
import fr.adaming.model.Produit;

@Stateful
public class LigneCommandeServiceImpl implements ILigneCommandeService{

	@EJB
	private ILigneCommandeDao lcoDao;
	@EJB
	private IProduitService prSer;
	
	@Override
	public LigneCommande addLigneCommande(LigneCommande lco) {
		Produit pr=prSer.getProduit(lco.getPr(), lco.getPr().getpCategorie());
		lco.setPr(pr);
		lco.setPrixfinal(pr.getPrix()*lco.getQuantiteCo());
		LigneCommande lcoOut=lcoDao.addLigneCommande(lco);
		return lcoOut;
	}

	@Override
	public LigneCommande upDateLigneCommande(LigneCommande lco) {
		Produit pr=prSer.getProduit(lco.getPr(), lco.getPr().getpCategorie());
		lco.setPr(pr);
		lco.setPrixfinal(pr.getPrix()*lco.getQuantiteCo());
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
