package fr.adaming.managedBean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import fr.adaming.model.Client;
import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;
import fr.adaming.service.ILigneCommandeService;

@ManagedBean(name="lcoMB")
@RequestScoped
public class LigneCommandeManagedBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@EJB
	private ILigneCommandeService lcoSer;
	
	private LigneCommande lco;
	private List<LigneCommande> listelco;
	private Commande co;
	private Client cl;
	private boolean i;
	HttpSession maSession;
	public LigneCommandeManagedBean() {
		super();
	}
	
	@PostConstruct
	public void init() {
		this.lco=new LigneCommande();
		this.cl=new Client();
		this.co=new Commande();
		this.i=false;
		maSession=(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	}
	
	public LigneCommande getLco() {
		return lco;
	}
	public void setLco(LigneCommande lco) {
		this.lco = lco;
	}
	public List<LigneCommande> getListelco() {
		return listelco;
	}
	public void setListelco(List<LigneCommande> listelco) {
		this.listelco = listelco;
	}
	public Commande getCo() {
		return co;
	}
	public void setCo(Commande co) {
		this.co = co;
	}
	public Client getCl() {
		return cl;
	}
	public void setCl(Client cl) {
		this.cl = cl;
	}
	public boolean isI() {
		return i;
	}
	public void setI(boolean i) {
		this.i = i;
	}
	
	public String addLigneCommande() {
		this.co.setCl(this.cl);
		this.lco.setCo(this.co);
		this.lco=lcoSer.addLigneCommande(this.lco);
		if(lco!=null) {
			i=true;
			return "acceuil";
		}else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("l'ajout a �chou�!"));
			return "addlignecommande";
		}
	}
	
	public String deleteLigneCommande() {
		int verif=lcoSer.deleteLigneCommande(this.lco);
		if(verif!=0) {
			i=true;
			return "acceuil";
		}else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("la suppression a �chou�!"));
			return "deletelignecommande";
		}
	}
	
	public String upDateLigneCommande() {
		this.co.setCl(this.cl);
		this.lco.setCo(this.co);
		this.lco=lcoSer.upDateLigneCommande(this.lco);
		if(this.lco!=null) {
			i=true;
			return "acceuil";
		}else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("la modification a �chou�!"));
			return "updatelignecommande";
		}
	}

	public String getLigneCommande() {
		this.lco=lcoSer.getLigneCommande(this.lco);
		if(this.lco!=null) {
			i=true;
			return "getlignecommande";
		}else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("la recherche a �chou�!"));
			return "getlignecommande";
		}
	}
	public String getAllLigneComandeByCo() {
		this.listelco=lcoSer.getAllLigneCommandeByCo(this.co);
		if(this.listelco!=null) {
			i=true;
			return "getalllignecommande";
		}else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("la recherche a �chou�!"));
			return "getalllignecommande";
		}
		
	}
	
}
