package fr.adaming.managedBean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import fr.adaming.model.Client;
import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;
import fr.adaming.service.IClientService;

@ManagedBean(name = "clMB")
@RequestScoped
public class ClientManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private IClientService clSer;

	private Client cl;
	private List<LigneCommande> pannier;
	private Commande co;
	private boolean i;

	public ClientManagedBean() {
		super();
	}

	@PostConstruct
	public void init() {
		this.cl = new Client();
		this.co = new Commande();

	}

	public Client getCl() {
		return cl;
	}

	public void setCl(Client cl) {
		this.cl = cl;
	}

	public List<LigneCommande> getPannier() {
		return pannier;
	}

	public void setPannier(List<LigneCommande> pannier) {
		this.pannier = pannier;
	}

	public Commande getCo() {
		return co;
	}

	public void setCo(Commande co) {
		this.co = co;
	}

	public boolean isI() {
		return i;
	}

	public void setI(boolean i) {
		this.i = i;
	}

	public String addClient() {
		Client clOut = clSer.addClient(this.cl);
		if (clOut != null) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Le client a �t� ajout�!"));
			return "addclient";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("l'ajout a �chou�!"));
			return "adclient";
		}
	}

	public String upDateClient() {
		Client clOut = clSer.upDateClient(cl);
		if (clOut != null) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Le client a �t� modifier!"));
			return "updateclient";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("la modification a �chou�!"));
			return "updateclient";
		}
	}

	public String getClient() {
		Client clOut = clSer.getClient(cl);
		if (clOut != null) {
			i=true;
			this.cl=clOut;
			System.out.println(cl);
			return "getclient";
		} else {
			i=false;
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("la recherche a �chou�!"));
			return "getclient";
		}
	}
	
	public String deleteClient() {
		int verif = clSer.deleteClient(cl);
		if (verif != 0) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Le client a �t� supprim�!"));
			return "deleteclient";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("la suppression a �chou�!"));
			return "deleteclient";
		}
	}
}
