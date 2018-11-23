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
import fr.adaming.model.Produit;
import fr.adaming.service.ICategorieService;

@ManagedBean(name="catMB")
@RequestScoped
public class CategorieManagedBean implements Serializable{
	
	//1***********************UML EN JAVA***********************************************************************************
	
	@EJB
	public ICategorieService catService;
	
	
	//2************************ATTRIBUTS***********************************************************************************
	
	private Categorie categorie;
	
	private List<Produit> listProduit;

	HttpSession adminSession;
	
	private UploadedFile file;
	
	
	//3*************************************CONSTRUCTEUR VIDE**************************************************************
	
	public CategorieManagedBean() {
		super();
	}
	
	//4*************************************OBJETS****************************************************************************
	
	@PostConstruct
	public void init(){
		this.adminSession= (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		adminSession.getAttribute("verifSession");
		this.categorie= new Categorie();
	}


	
	
	//5************************************GETTERS AND SETTERS**********************************************************
	
		public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public List<Produit> getListProduit() {
		return listProduit;
	}

	public void setListProduit(List<Produit> listProduit) {
		this.listProduit = listProduit;
	}
	
	public HttpSession getAdminSession() {
		return adminSession;
	}

	public void setAdminSession(HttpSession adminSession) {
		this.adminSession = adminSession;
	}
	
	
	
	//6*****************************************AUTRES METHODES*************************************************************
	
	
	

	public String getAllCategorie() {
		List<Categorie> listOut= catService.getAllCategorie();
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("listCategorie", listOut);
	
		return "acceuil";
	}

	public String addCategorie() {
		
		Categorie cOut= catService.addCategorie(this.categorie);
		if(cOut.getId()!=0) {
			
			List<Categorie> list= catService.getAllCategorie();
		adminSession.setAttribute("listCategorie", list);
		
		return "acceuil";
		
			
		}else {
FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("L'ajout n'est pas fait"));
			
			return "addcategorie";
		}
	}

}
