package fr.adaming.managedBean;

import java.io.IOException;
import java.io.InputStream;
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
import fr.adaming.service.IProduitService;

@ManagedBean(name="prMB")
@RequestScoped
public class ProduitManagedBean implements Serializable{

	//1***********************UML EN JAVA***********************************************************************************
	
	@EJB
	private IProduitService prService;
	
	@EJB
	private ICategorieService cService;
	
	
	//2************************ATTRIBUTS***********************************************************************************
	
	private Categorie categorie;
	
	private Produit produit;
	
	private List<Produit> listProduit;

	HttpSession adminSession;
	
	private UploadedFile file;
	
	private boolean i;

	//3*************************************CONSTRUCTEUR VIDE**************************************************************
	
	public ProduitManagedBean() {
		super();
	}
	
	//4*************************************OBJETS****************************************************************************
	
		@PostConstruct
		public void init(){
			this.adminSession= (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
			adminSession.getAttribute("verifSession");
			this.categorie= new Categorie();
			this.produit= new Produit();
			i=false;
			this.listProduit= prService.getAllProduit(this.categorie);
			this.file= new UploadedFile() {
				
				@Override
				public void write(String arg0) throws Exception {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public long getSize() {
					// TODO Auto-generated method stub
					return 0;
				}
				
				@Override
				public InputStream getInputstream() throws IOException {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public String getFileName() {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public byte[] getContents() {
					// TODO Auto-generated method stub
					return null;
				}
				
				@Override
				public String getContentType() {
					// TODO Auto-generated method stub
					return null;
				}
			};
			
		}

		//5************************************GETTERS AND SETTERS**********************************************************
		
		public Categorie getCategorie() {
			return categorie;
		}

		public void setCategorie(Categorie categorie) {
			this.categorie = categorie;
		}

		public Produit getProduit() {
			return produit;
		}

		public void setProduit(Produit produit) {
			this.produit = produit;
		}

		public List<Produit> getListProduit() {
			return listProduit;
		}

		public void setListProduit(List<Produit> listProduit) {
			this.listProduit = listProduit;
		}

		public UploadedFile getFile() {
			return file;
		}

		public void setFile(UploadedFile file) {
			this.file = file;
		}

		public boolean isI() {
			return i;
		}

		public void setI(boolean i) {
			this.i = i;
		}
	
	//6*****************************************AUTRES METHODES*************************************************************
		
		public String addProduit() {
			this.produit.setPhoto(file.getContents());
			this.produit.setpCategorie(this.categorie);
			this.produit= prService.addProduit(this.produit, this.categorie);
			if(this.produit.getId()!=0) {
				i=true;
				FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("prodListe", prService.getAllProduit(categorie));
				
				return "addproduit";
			}else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("L'ajout n'est pas fait"));
				i=false;
			return "addproduit";
			}
		}
		
		public String getProduits() {
//			this.produit.getPhoto;
			
			this.produit= prService.getProduit(this.produit);
			
			if(this.produit!=null) {
				i=true;
				return "getproduit";
			}else {
				
				i=false;
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Le produit n'existe pas"));
				return "getproduit";
			}
		}
	
		public String upDateProduit() {
			this.produit.setPhoto(file.getContents());
			this.produit.setpCategorie(this.categorie);
			Produit pOut= prService.upDateProduit(this.produit, this.categorie);
			if(pOut!=null) {
				List<Produit> list= prService.getAllProduit(categorie);
				i=true;
				adminSession.setAttribute("listProd", list);
				return "updateproduit";
			}else {
				
				i=false;
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Le produit n'est pas modifi�"));
				return "updateproduit";
			}
		}
		
		public String delateProduit() {
			this.produit.setPhoto(file.getContents());
			this.produit.setpCategorie(this.categorie);
		prService.delateProduit(this.produit);
		if(this.produit!=null) {
			List<Produit> list= prService.getAllProduit(categorie);
			adminSession.setAttribute("listProd", list);
			
			return "deleteproduit";
			
		}else{
			//Recuperer le contexte (c'est ici o� les messages d'erreur sont stoqu�es) de la req 
    		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Le produit n'est pas effac�"));
		
		return "deleteproduit";
		}
		}
		
		public String getProduitByNom() {
			this.produit=prService.getProduitByNom(produit);
			if(this.produit!=null) {
				return "produit";
			}
			return "acceuil";
		}
}
