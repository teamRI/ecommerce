package fr.adaming.managedBean;


import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import fr.adaming.model.Client;
import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;
import fr.adaming.service.IClientService;

@ManagedBean(name="clMB")
@RequestScoped
public class ClientManagedBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@EJB
	private IClientService clSer;
	
	private Client cl;
	private List<LigneCommande> pannier;
	private Commande co;
	
	public ClientManagedBean() {
		super();
	}
	
	@PostConstruct
	public void init() {
		this.cl=new Client();
		this.co=new Commande();
		
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
	
	public String addClient() {
		Client clOut=clSer.addClient(this.cl);
		if(clOut!=null) {
			return "success";
		}else {
			return "fail";
		}
	}
}
