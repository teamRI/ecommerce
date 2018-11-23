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

import org.primefaces.model.UploadedFile;

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
	
	private List<Categorie> listCategorie;
	
	private List<Produit> listProduit;

	HttpSession adminSession;
	
	private UploadedFile file;
	
	private boolean i;
	
	
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
		i=false;
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
	
	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}
	
	
	public List<Categorie> getListCategorie() {
		return listCategorie;
	}

	public void setListCategorie(List<Categorie> listCategorie) {
		this.listCategorie = listCategorie;
	}
	
	
	public boolean isI() {
		return i;
	}

	public void setI(boolean i) {
		this.i = i;
	}
	
	
	
	//6*****************************************AUTRES METHODES*************************************************************
	


	public String getAllCategorie() {
	this.listCategorie= catService.getAllCategorie();
	
		return "acceuil";
	}

	public String addCategorie() {
	   this.categorie.setPhoto(file.getContents());
		this.categorie= catService.addCategorie(categorie);
		System.out.println(this.categorie.getNomCat());
		if(this.categorie.getId()!=0) {
			i=true;
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("catListe", catService.getAllCategorie());
		
		return "acceuil";
		
			
		}else {
FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("L'ajout n'est pas fait"));
			i=false;
			return "addcategorie";
		}
	}

}
