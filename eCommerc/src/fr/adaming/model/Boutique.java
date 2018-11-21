package fr.adaming.model;




public class Boutique {

	
	//1************************ATTRIBUTS*******************************
	
	
	private String nom;

	
	//2*************CONSTRUCTEURS***************************************************************
	
	
	public Boutique() {
		super();
	}

	public Boutique(String nom) {
		super();
		this.nom = nom;
	}

	
	//3*****************************GETTERS AND SETTERS*******************************************************
	
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	
	
}
