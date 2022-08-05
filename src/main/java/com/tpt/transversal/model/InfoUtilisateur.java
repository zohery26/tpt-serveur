package com.tpt.transversal.model;

import java.util.ArrayList;
import java.util.List;

public class InfoUtilisateur {
	InfoVaccinUser infoVaccinUser =new InfoVaccinUser();
	Vaccin vaccin = new Vaccin();
	Vaccinodrome vaccinodrome = new Vaccinodrome();
	VaccinCentre vaccinCentre = new VaccinCentre();
	Utilisateur utilisateur = new Utilisateur();
	List<Famille> famille = new ArrayList<Famille>();
	int status = 400;
	
	public InfoUtilisateur() {}
	
	public InfoUtilisateur(InfoVaccinUser infoVaccinUser, Vaccin vaccin, Vaccinodrome vaccinodrome,VaccinCentre vaccinCentre, Utilisateur utilisateur, List<Famille> famille, int status) {
		this.infoVaccinUser = infoVaccinUser;
		this.vaccin = vaccin;
		this.vaccinodrome = vaccinodrome;
		this.vaccinCentre = vaccinCentre;
		this.utilisateur = utilisateur;
		this.famille = famille;
		this.status = status;
	}

	public InfoVaccinUser getInfoVaccinUser() {
		return infoVaccinUser;
	}

	public void setInfoVaccinUser(InfoVaccinUser infoVaccinUser) {
		this.infoVaccinUser = infoVaccinUser;
	}

	public Vaccin getVaccin() {
		return vaccin;
	}

	public void setVaccin(Vaccin vaccin) {
		this.vaccin = vaccin;
	}

	public Vaccinodrome getVaccinodrome() {
		return vaccinodrome;
	}

	public void setVaccinodrome(Vaccinodrome vaccinodrome) {
		this.vaccinodrome = vaccinodrome;
	}

	public VaccinCentre getVaccinCentre() {
		return vaccinCentre;
	}

	public void setVaccinCentre(VaccinCentre vaccinCentre) {
		this.vaccinCentre = vaccinCentre;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public List<Famille> getFamille() {
		return famille;
	}

	public void setFamille(List<Famille> famille) {
		this.famille = famille;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
