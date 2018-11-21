package fr.adaming.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="commandes")
public class Commande {
  
	
	//1************************ATTRIBUTS***************************************************
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_com")
	private Long id;
	private Date dateCommande;
	
	
	//2*************CONSTRUCTEURS***************************************************************
	
	
	public Commande() {
		super();
	}
	
	public Commande(Date dateCommande) {
		super();
		this.dateCommande = dateCommande;
	}
	
	
	public Commande(Long id, Date dateCommande) {
		super();
		this.id = id;
		this.dateCommande = dateCommande;
	}

	//3*****************************GETTERS AND SETTERS*******************************************************
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Date getDateCommande() {
		return dateCommande;
	}


	public void setDateCommande(Date dateCommande) {
		this.dateCommande = dateCommande;
	}
	
	
	
	
	
}
