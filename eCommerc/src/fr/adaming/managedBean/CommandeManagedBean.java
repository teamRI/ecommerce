package fr.adaming.managedBean;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.lowagie.text.pdf.codec.Base64.OutputStream;

import fr.adaming.model.Client;
import fr.adaming.model.Commande;
import fr.adaming.model.CreatePdf;
import fr.adaming.model.LigneCommande;
import fr.adaming.model.Mail;
import fr.adaming.service.ICommandeService;
import fr.adaming.service.ILigneCommandeService;

@ManagedBean(name = "coMB")
@RequestScoped
public class CommandeManagedBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private ICommandeService coSer;

	@EJB
	private ILigneCommandeService lcoSer;
	
	Mail envoyermail= new Mail();
	
	CreatePdf createpdf= new CreatePdf();

	private Commande co;
	private Client cl;
	private List<LigneCommande> listeLco;
	private boolean i;
	HttpSession maSession;

	public CommandeManagedBean() {
		super();
	}

	@PostConstruct
	public void init() {
		this.cl = new Client();
		this.co = new Commande();
		maSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		i = false;

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

	public List<LigneCommande> getListeLco() {
		return listeLco;
	}

	public void setListeLco(List<LigneCommande> listeLco) {
		this.listeLco = listeLco;
	}

	public boolean isI() {
		return i;
	}

	public void setI(boolean i) {
		this.i = i;
	}

	public String addCommande() {
		this.co.setCl(this.cl);
		this.co = coSer.addCommande(this.co);
		if (co != null) {
			i = true;
			return "acceuil";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("l'ajout a �chou�!"));
			return "addcommande";
		}
	}

	public String deleteCommande() {
		List<LigneCommande> listlco = (List<LigneCommande>) maSession.getAttribute("listlco");
		for (LigneCommande lco : listlco) {

			int verif1 = lcoSer.deleteLigneCommande(lco);
		}
		int verif = coSer.deleteCommande(this.co);
		if (verif != 0) {
			i = true;
			return "acceuil";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("la suppression a �chou�!"));
			return "pannier";
		}
	}

	public String upDateCommande() {
		this.co.setCl(this.cl);
		this.co = coSer.upDateCommande(this.co);
		if (this.co != null) {
			i = true;
			return "acceuil";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("la modification a �chou�!"));
			return "updatecommande";
		}
	}

	public String getCommande() {
		this.co = coSer.getCommande(this.co);
		if (this.co != null) {
			System.out.println(co);
			i = true;
			return "getcommande";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("la recherche a �chou�!"));
			return "getcommande";
		}
	}

	public String getAllComandeByCl() {
		this.co = coSer.getAllCommandeByCl(this.cl);
		if (this.co != null) {
			i = true;
			return "getallcommande";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("la recherche a �chou�!"));
			return "getallcommande";
		}

	}

	public String validerCommande() {
		this.cl=(Client) maSession.getAttribute("client");
		this.co= cl.getCo();
		List<LigneCommande> listlco = (List<LigneCommande>) maSession.getAttribute("listlco");
		for (LigneCommande lco : listlco) {

			int verif1 = lcoSer.deleteLigneCommande(lco);
		}
		int verif = coSer.deleteCommande(this.co);

		if (verif != 0) {

			OutputStream os;
		try {
			
			
			os = (OutputStream) FacesContext.getCurrentInstance().getExternalContext().getResponseOutputStream();
		FacesContext.getCurrentInstance().getExternalContext().responseReset();
	
			createpdf.writePdf(os);
		} catch (Exception e) {
			// TODO Auto-generated catch block
		e.printStackTrace();}
	
envoyermail.sendMail(co.getCl(), co, listlco);
			i = true;
			return "acceuil";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("la suppression a �chou�!"));
			return "pannier";
		}
	}
}
