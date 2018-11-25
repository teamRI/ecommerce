package fr.adaming.model;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
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

import fr.adaming.dao.LigneCommandeDaoImpl;

public class Mail {

	public static void sendMail(Client cl, Commande co, Pannier pa) {
		final String username= "conseilleuringrid@gmail.com";
		final String password= "mclikfnxfzacsyrj";
		
		//Le mail du client
		String to= "conseilleuringrid@gmail.com";
		
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
					message.setSubject("ECommerce : commande num�ro " + co.getId());

					// Create the message part 
					BodyPart messageBodyPart = new MimeBodyPart(); 
					
					Multipart multipart = new MimeMultipart(); 
					multipart.addBodyPart(messageBodyPart); 
					
					// Now set the actual message
					String recap = "";
					for (LigneCommande lc : pa.getListeLigneCommandes()) {
						recap = recap + "\n - " + lc.getQuantiteCo() + " " + lc.getPr().getDesignation()+" � "+lc.getPr().getPrix()+"� pi�ce";
					}
					
					messageBodyPart.setText("Mr/Mme " + cl.getNom()
							+ ", \nBonjour,\nNous vous confirmons l'enregistrement de votre commande num�ro " + co.getId()
							+ ".\nR�capitulatif de la commande :" + recap +"\n\nMontant total de la commande :"+ pa.getPrixTotal()+"�"
							+ "\n\nVous trouverez le d�tail de votre facture en pi�ce jointe au format pdf.\n\n"
							+ "En esp�rant vous revoir bient�t sur notre site, cordialement\n\nToute l'�quipe de ECommerce");

					// Part two is attachment 
					messageBodyPart = new MimeBodyPart(); 
					DataSource source = new FileDataSource("C:\\Users\\inti0487\\Desktop\\Formation\\Workspace\\GenerationPDF\\PDFtest.pdf"); 
					messageBodyPart.setDataHandler(new DataHandler(source)); 
					messageBodyPart.setFileName("R�capitulatif commande n�"+co.getId()+".pdf"); 
					multipart.addBodyPart(messageBodyPart); 

					// Put parts in message 
					message.setContent(multipart); 
					
					// Send message
					Transport.send(message);
					System.out.println("Sent message successfully....");

				} catch (MessagingException mex) {
					mex.printStackTrace();
				}
			}




	}
	
