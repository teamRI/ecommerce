package fr.adaming.managedBean;

import java.io.Serializable;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpSession;

import fr.adaming.model.Client;
import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;
import fr.adaming.model.Produit;
import fr.adaming.service.IClientService;
import fr.adaming.service.ICommandeService;
import fr.adaming.service.ILigneCommandeService;
import fr.adaming.service.IProduitService;

@ManagedBean(name="mailMB")
@RequestScoped
public class MailManagedBean implements Serializable{

	public Client client;
	
	public Commande commande;
	
	LigneCommande lc;
	
	HttpSession adminSession;

	public MailManagedBean() {
		this.client=new Client();
		adminSession= (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		
	}

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

	public LigneCommande getLc() {
		return lc;
	}

	public void setLc(LigneCommande lc) {
		this.lc = lc;
	}

	public String sendMail() {
		final String username= "conseilleuringrid@gmail.com";
		final String password= "mclikfnxfzacsyrj";
		
		//Le mail du client
		String to= "ingridregada@gmail.com";
		
		//Mail de sortie
	
		String from= "e_commerce@gmail.com";
		
		
		//Params aplic host/properties
		String host = "smtp.gmail.com";
		Properties props = System.getProperties();
		
		// Setup mail server

				props.put("mail.smtp.auth", "true");
				props.put("mail.smtp.starttls.enable", "true");
				props.put("mail.smtp.host", host);
				props.put("mail.smtp.port", "587");
				
				// Get the default Session object.
				Session session = Session.getInstance(props, new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});

				try {
					// Create a default MimeMessage object.
					MimeMessage message = new MimeMessage(session);

					// Set From: header field of the header.
					message.setFrom(new InternetAddress(from));

					// Set To: header field of the header.
					message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

					// Set Subject: header field
					message.setSubject("ECommerce : commande numéro " + commande.getId());

					// Create the message part 
					BodyPart messageBodyPart = new MimeBodyPart(); 
					
					Multipart multipart = new MimeMultipart(); 
					multipart.addBodyPart(messageBodyPart); 
					
					// Now set the actual message
					String recap = "";
					for (LigneCommande lc : commande.getListelco()) {
						recap = recap + "\n - " + lc.getQuantiteCo() + " " + lc.getPr().getDesignation()+" à "+lc.getPr().getPrix()+"€ pièce";
					}
					
					messageBodyPart.setText("Mr/Mme " + client.getNom()
							+ ", \nBonjour,\nNous vous confirmons l'enregistrement de votre commande numéro " + commande.getId()
							+ ".\nRécapitulatif de la commande :" + recap +"\n\nMontant total de la commande :"+ lc.getPrixfinal()+"€"
							+ "\n\nVous trouverez le détail de votre facture en pièce jointe au format pdf.\n\n"
							+ "En espérant vous revoir bientôt sur notre site, cordialement\n\nToute l'équipe de ECommerce");

					// Part two is attachment 
					messageBodyPart = new MimeBodyPart(); 
					DataSource source = new FileDataSource("C:\\Users\\inti0487\\Desktop\\Formation\\Workspace\\GenerationPDF\\PDFtest.pdf"); 
					messageBodyPart.setDataHandler(new DataHandler(source)); 
					messageBodyPart.setFileName("Récapitulatif commande n°"+commande.getId()+".pdf"); 
					multipart.addBodyPart(messageBodyPart); 

					// Put parts in message 
					message.setContent(multipart); 
					
					// Send message
					Transport.send(message);
					System.out.println("Sent message successfully....");
return ""
				} catch (MessagingException mex) {
					mex.printStackTrace();
				}
			}


}

