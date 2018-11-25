package fr.adaming.managedBean;

import java.io.Serializable;
import java.text.SimpleDateFormat;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Header;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.codec.Base64.OutputStream;

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
	 public void writePdf(OutputStream outputStream) throws Exception {
	        Document document = new Document();
	        PdfWriter.getInstance(document, outputStream);
	         
	       
	         
	    PdfPTable table= new PdfPTable(4); 
	    PdfPTable tableCo= new PdfPTable(6);
	    
	    Header header= new Header("Récapitulatif du client" + client.getNom(), null);
	    
	    try {
	    	PdfWriter.getInstance(document, outputStream);
	    	 document.open();
	         
	        document.addTitle("Récapitulatif commande n°"+commande.getId());
	        document.addSubject("Récapitulatif commande");
	        document.addKeywords("iText, email");
	        document.addAuthor("ECommerce");
	        document.addCreator("ECommerce");
	   
	        PdfPCell cell;
	        
	        document.add(header);
	        
	        cell= new PdfPCell(new Phrase("Client"));
	        cell.setColspan(4);
	        table.addCell(cell);
	        
	        cell= new PdfPCell(new Phrase("Id"));
	        table.addCell(cell);
	        cell= new PdfPCell(new Phrase("Nom"));
	        table.addCell(cell);
	        cell= new PdfPCell(new Phrase("Mail"));
	        table.addCell(cell);
	        cell= new PdfPCell(new Phrase("Téléphone"));
	        table.addCell(cell);
	        cell= new PdfPCell(new Phrase("Adresse"));
	        table.addCell(cell);
	        
	        String id= Long.toString(client.getId());
	        cell= new PdfPCell(new Phrase(id));
	        table.addCell(cell);
	        
	        cell= new PdfPCell(new Phrase(client.getNom()));
	        table.addCell(cell);
	        
	        cell= new PdfPCell(new Phrase(client.getEmail()));
	        table.addCell(cell);
	        
	        cell= new PdfPCell(new Phrase(client.getTel()));
	        table.addCell(cell);
	      
	        cell= new PdfPCell(new Phrase(client.getAdresse()));
	        table.addCell(cell);
	        
	        
	        document.add(table);

	        document.add(new Paragraph(
	        
	    "\n-------------------------------------------------------------------------------------------------------------------------------\n"));
	    
	        document.add(new Paragraph("\n"));
	        
	        cell = new PdfPCell(new Phrase("Commande"));
			cell.setColspan(5);
			tableCo.addCell(cell);
			
			
			cell= new PdfPCell(new Phrase("Id"));
	        tableCo.addCell(cell);
	        
	        cell= new PdfPCell(new Phrase("date commande"));
	        tableCo.addCell(cell);
	        
	        cell= new PdfPCell(new Phrase("produit"));
	        tableCo.addCell(cell);
	        
	        cell= new PdfPCell(new Phrase("quantité"));
	        tableCo.addCell(cell);
	        
	        cell= new PdfPCell(new Phrase("prix final"));
	        tableCo.addCell(cell);
	        
	        String idC= Long.toString(lc.getId());
	        cell= new PdfPCell(new Phrase(id));
	        tableCo.addCell(cell);
	        
	        SimpleDateFormat formatDateJour = new SimpleDateFormat("dd/MM/yyyy");
			String dateC = formatDateJour.format(lc.getCo().getDateCommande());
			cell = new PdfPCell(new Phrase(dateC));
			tableCo.addCell(cell);
			
			 cell= new PdfPCell(new Phrase(lc.getPr().getDesignation()));
		        tableCo.addCell(cell);
		        
		        cell= new PdfPCell(new Phrase(lc.getQuantiteCo()));
		        tableCo.addCell(cell);
		        
		        
		        String pxf= Double.toString(lc.getPrixfinal());
		        cell= new PdfPCell(new Phrase(pxf));
		        tableCo.addCell(cell);
		        
		        document.add(tableCo);
		        
	    } catch (DocumentException de) {
			de.printStackTrace();
		}
	        
	        document.close();
	  }   }
	
	
