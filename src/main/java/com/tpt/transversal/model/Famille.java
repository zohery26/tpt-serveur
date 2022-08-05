package com.tpt.transversal.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="famille")
public class Famille {
	@Id
   @GeneratedValue(
	    strategy = GenerationType.SEQUENCE,
	    generator = "famille-sequence"
	)
   @SequenceGenerator(
		name = "famille-sequence",
		sequenceName = "seq_famille",
		allocationSize = 1,
		initialValue = 2
	)
	@Column(name="idfamille")
	int idFamille;
	@Column(name="idutilisateur")
    int idUtilisateur;
	@Column(name="nom")
    String nom;
	@Column(name="sexe")
    int sexe;
	@Column(name="naissance")
    Date naissance;
	@Column(name="url_photo")
    String urlPhoto;
	@Column(name="positionfamilliale")
    String positionFamilliale;
	@Column(name="status")
    int status;
    
    public Famille() {}

	public Famille(int idFamille, int idUtilisateur, String nom, int sexe, Date naissance, String urlPhoto,String positionFamilliale, int status) {
		this.idFamille = idFamille;
		this.idUtilisateur = idUtilisateur;
		this.nom = nom;
		this.sexe = sexe;
		this.naissance = naissance;
		this.urlPhoto = urlPhoto;
		this.positionFamilliale = positionFamilliale;
		this.status = status;
	}

	public int getIdFamille() {
		return idFamille;
	}

	public void setIdFamille(int idFamille) {
		this.idFamille = idFamille;
	}

	public int getIdUtilisateur() {
		return idUtilisateur;
	}

	public void setIdUtilisateur(int idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getSexe() {
		return sexe;
	}

	public void setSexe(int sexe) {
		this.sexe = sexe;
	}

	public Date getNaissance() {
		return naissance;
	}

	public void setNaissance(Date naissance) {
		this.naissance = naissance;
	}

	public String getUrlPhoto() {
		return urlPhoto;
	}

	public void setUrlPhoto(String urlPhoto) {
		this.urlPhoto = urlPhoto;
	}

	public String getPositionFamilliale() {
		return positionFamilliale;
	}

	public void setPositionFamilliale(String positionFamilliale) {
		this.positionFamilliale = positionFamilliale;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	
    
}
