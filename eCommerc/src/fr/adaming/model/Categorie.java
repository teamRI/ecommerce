package fr.adaming.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="categories")
public class Categorie {

	//1************************ATTRIBUTS***************************************************
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private String nomCat;
	private byte photo;
	private String description;
	
	
	//2*************CONSTRUCTEURS***************************************************************
	
	public Categorie() {
		super();
	}


	public Categorie(String nomCat, byte photo, String description) {
		super();
		this.nomCat = nomCat;
		this.photo = photo;
		this.description = description;
	}


	public Categorie(long id, String nomCat, byte photo, String description) {
		super();
		this.id = id;
		this.nomCat = nomCat;
		this.photo = photo;
		this.description = description;
	}

	

	//3*****************************GETTERS AND SETTERS*******************************************************
	
	
	
	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getNomCat() {
		return nomCat;
	}


	public void setNomCat(String nomCat) {
		this.nomCat = nomCat;
	}


	public byte getPhoto() {
		return photo;
	}


	public void setPhoto(byte photo) {
		this.photo = photo;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
	
	
	
}
