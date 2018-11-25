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

import fr.adaming.model.Categorie;
import fr.adaming.model.Client;
import fr.adaming.model.Commande;
import fr.adaming.model.Produit;
import fr.adaming.service.ICategorieService;
import fr.adaming.service.IClientService;
import fr.adaming.service.IProduitService;

@ManagedBean(name = "acMB")
@RequestScoped
public class AcceuilManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private IClientService clSer;
	@EJB
	private ICategorieService caSer;
	@EJB
	private IProduitService prSer;

	private Client cl;
	private boolean i;
	private List<Produit> prodliste;
	private List<Categorie> catliste;
	private int size;
	HttpSession maSession;

	public AcceuilManagedBean() {
		super();
	}

	@PostConstruct
	public void init() {
		this.cl = new Client();
		this.i = false;
		maSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
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

	public List<Produit> getProdliste() {
		return prodliste;
	}

	public void setProdliste(List<Produit> prodliste) {
		this.prodliste = prodliste;
	}

	public List<Categorie> getCatliste() {
		return catliste;
	}

	public void setCatliste(List<Categorie> catliste) {
		this.catliste = catliste;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String Entrer() {

		catliste = caSer.getAllCategorie();
		size = catliste.size() - 1;
		System.out.println("**************size*************");
		System.out.println(size);
		System.out.println("**************NomCat*************");
		System.out.println(catliste.get(0).getNomCat());
		for (int j = 0; j <= size; j++) {
			prodliste = prSer.getAllProduit(catliste.get(j));
			maSession.setAttribute("prodliste" + j, this.prodliste);
			maSession.setAttribute("size" + j, this.prodliste.size() - 1);
			System.out.println("**************Designation*************");
			System.out.println(prodliste.get(0).getDesignation());
		}
		System.out.println("**************prodliste1*************");
		System.out.println(((List<Produit>) maSession.getAttribute("prodliste1")).get(0).getDesignation());
		maSession.setAttribute("catliste", this.catliste);
		return "acceuil";
	}

	public String login() {
		Client clOut = clSer.isExist(this.cl);
		this.cl = clOut;
		if (clOut != null) {
			maSession.setAttribute("listeCo", this.cl.getListeCo());
			for (int i = 0; i <= this.cl.getListeCo().size()-1; i++) {
				maSession.setAttribute("listlco" + i, cl.getListeCo().get(i).getListelco());
			}
			maSession.setAttribute("client", this.cl);
			catliste = caSer.getAllCategorie();
			maSession.setAttribute("catliste", this.catliste);
			return "acceuil";
		}
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Email et/ou nom erroné(s)"));
		return "loginCl";
	}
}
