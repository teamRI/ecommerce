package fr.adaming.managedBean;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import fr.adaming.model.Client;
import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;
import fr.adaming.model.Produit;
import fr.adaming.service.IClientService;
import fr.adaming.service.ICommandeService;
import fr.adaming.service.ILigneCommandeService;
import fr.adaming.service.IProduitService;

@ManagedBean(name="pdfMB")
@RequestScoped
public class PDFcommandesMB implements Serializable{
	
	//1****************************************ASSOCIATION UML************************************************************************
	
	@EJB
	private ICommandeService coService;
	
	@EJB
	private IClientService clService;
	
	@EJB
	private IProduitService prService;
	
	@EJB
	private ILigneCommandeService lcService;
	
	
	//2***************************************************ATTRIBUTS********************************************************************
	
	private Client client;

	private Commande commande;
	
	private Produit produit;
	
	private LigneCommande lc;
	
	private boolean i;

	
	
	//3********************************************CONSTRUCTEUR VIDE********************************************************************
	
	public PDFcommandesMB() {
		super();
	}


	
	//4*************************************************GETTERS AND SETTERS*************************************************************
	
	
	

	public Client getClient() {
		return client;
	}



	public void setClient(Client client) {
		this.client = client;
	}



	public Commande getCommande() {
		return commande;
	}



	public void setCommande(Commande commande) {
		this.commande = commande;
	}



	public boolean isI() {
		return i;
	}



	public void setI(boolean i) {
		this.i = i;
	}



	public Produit getProduit() {
		return produit;
	}



	public void setProduit(Produit produit) {
		this.produit = produit;
	}



	public LigneCommande getLc() {
		return lc;
	}



	public void setLc(LigneCommande lc) {
		this.lc = lc;
	}

	
	
	
	//************************************************METHODES***********************************************
	
	
	
}
