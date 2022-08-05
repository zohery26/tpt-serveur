package com.tpt.transversal.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="demanderdvview")
public class DemandeRdvView {
	@Id
	@Column(name="iddemanderdv")
	int idDemandeRdv;
	@Column(name="dose")
	int dose;
	@Column(name="daterdv")
	Date dateRdv;
	@Column(name="famille")
	int famille;
	@Column(name="descriptionsrdv")
	String descriptionsRdv;
	@Column(name="statusrdv")
	int statusRdv;
	@Column(name="idinfovaccinuser")
	int idInfoVaccinUser;
	@Column(name="idutilisateur")
	int idUtilisateur;
	@Column(name="nompatient")
	String nomPatient;
	@Column(name="sexepatient")
	int sexePatient;
	@Column(name="photopatient")
	String photoPatient; 
	@Column(name="idvaccincentre")
	int idVaccinCentre;
	@Column(name="quantite")
	int quantite;
	@Column(name="nombre_dose")
	int nombreDose;
	@Column(name="age_minimum")
	int ageMinimum;
	@Column(name="idvaccinodrome")
	int idVaccinodrome;
	@Column(name="nomvaccinodrome")
	String nomVaccinodrome;
	@Column(name="imgvaccinodrome")
	String imgVaccinodrome;
	@Column(name="idvaccin")
	int idVaccin;
	@Column(name="nom_vaccin")
	String nomVaccin;
	@Column(name="imgvaccin")
	String imgVaccin;
	
	public DemandeRdvView() {}

	public DemandeRdvView(int idDemandeRdv, int dose, Date dateRdv, int famille, String descriptionsRdv,int statusRdv,int idInfoVaccinUser, int idUtilisateur, String nomPatient, int sexePatient, String photoPatient,
		int idVaccinCentre, int quantite, int nombreDose, int ageMinimum, int idVaccinodrome,String nomVaccinodrome, String imgVaccinodrome, int idVaccin, String nomVaccin, String imgVaccin) {
		this.idDemandeRdv = idDemandeRdv;
		this.dose = dose;
		this.dateRdv = dateRdv;
		this.famille = famille;
		this.descriptionsRdv = descriptionsRdv;
		this.statusRdv = statusRdv;
		this.idInfoVaccinUser = idInfoVaccinUser;
		this.idUtilisateur = idUtilisateur;
		this.nomPatient = nomPatient;
		this.sexePatient = sexePatient;
		this.photoPatient = photoPatient;
		this.idVaccinCentre = idVaccinCentre;
		this.quantite = quantite;
		this.nombreDose = nombreDose;
		this.ageMinimum = ageMinimum;
		this.idVaccinodrome = idVaccinodrome;
		this.nomVaccinodrome = nomVaccinodrome;
		this.imgVaccinodrome = imgVaccinodrome;
		this.idVaccin = idVaccin;
		this.nomVaccin = nomVaccin;
		this.imgVaccin = imgVaccin;
	}

	public int getIdDemandeRdv() {
		return idDemandeRdv;
	}

	public void setIdDemandeRdv(int idDemandeRdv) {
		this.idDemandeRdv = idDemandeRdv;
	}

	public int getDose() {
		return dose;
	}

	public void setDose(int dose) {
		this.dose = dose;
	}

	public Date getDateRdv() {
		return dateRdv;
	}

	public void setDateRdv(Date dateRdv) {
		this.dateRdv = dateRdv;
	}

	public int getFamille() {
		return famille;
	}

	public void setFamille(int famille) {
		this.famille = famille;
	}

	public String getDescriptionsRdv() {
		return descriptionsRdv;
	}

	public void setDescriptionsRdv(String descriptionsRdv) {
		this.descriptionsRdv = descriptionsRdv;
	}
	
	
	public int getStatusRdv() {
		return statusRdv;
	}

	public void setStatusRdv(int statusRdv) {
		this.statusRdv = statusRdv;
	}

	public int getIdInfoVaccinUser() {
		return idInfoVaccinUser;
	}

	public void setIdInfoVaccinUser(int idInfoVaccinUser) {
		this.idInfoVaccinUser = idInfoVaccinUser;
	}

	public int getIdUtilisateur() {
		return idUtilisateur;
	}

	public void setIdUtilisateur(int idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	public String getNomPatient() {
		return nomPatient;
	}

	public void setNomPatient(String nomPatient) {
		this.nomPatient = nomPatient;
	}

	public int getSexePatient() {
		return sexePatient;
	}

	public void setSexePatient(int sexePatient) {
		this.sexePatient = sexePatient;
	}

	public String getPhotoPatient() {
		return photoPatient;
	}

	public void setPhotoPatient(String photoPatient) {
		this.photoPatient = photoPatient;
	}

	public int getIdVaccinCentre() {
		return idVaccinCentre;
	}

	public void setIdVaccinCentre(int idVaccinCentre) {
		this.idVaccinCentre = idVaccinCentre;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public int getNombreDose() {
		return nombreDose;
	}

	public void setNombreDose(int nombreDose) {
		this.nombreDose = nombreDose;
	}

	public int getAgeMinimum() {
		return ageMinimum;
	}

	public void setAgeMinimum(int ageMinimum) {
		this.ageMinimum = ageMinimum;
	}

	public int getIdVaccinodrome() {
		return idVaccinodrome;
	}

	public void setIdVaccinodrome(int idVaccinodrome) {
		this.idVaccinodrome = idVaccinodrome;
	}

	public String getNomVaccinodrome() {
		return nomVaccinodrome;
	}

	public void setNomVaccinodrome(String nomVaccinodrome) {
		this.nomVaccinodrome = nomVaccinodrome;
	}

	public String getImgVaccinodrome() {
		return imgVaccinodrome;
	}

	public void setImgVaccinodrome(String imgVaccinodrome) {
		this.imgVaccinodrome = imgVaccinodrome;
	}

	public int getIdVaccin() {
		return idVaccin;
	}

	public void setIdVaccin(int idVaccin) {
		this.idVaccin = idVaccin;
	}

	public String getNomVaccin() {
		return nomVaccin;
	}

	public void setNomVaccin(String nomVaccin) {
		this.nomVaccin = nomVaccin;
	}

	public String getImgVaccin() {
		return imgVaccin;
	}

	public void setImgVaccin(String imgVaccin) {
		this.imgVaccin = imgVaccin;
	}
	
	
}
