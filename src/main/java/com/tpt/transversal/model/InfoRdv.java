package com.tpt.transversal.model;

import java.util.ArrayList;
import java.util.List;

public class InfoRdv {
	DemandeRdv demandeRdv = new DemandeRdv();
	InfoVaccinUser infoVaccinUser =new InfoVaccinUser();
	Vaccin vaccin = new Vaccin();
	Vaccinodrome vaccinodrome = new Vaccinodrome();
	VaccinCentre vaccinCentre = new VaccinCentre();
	Utilisateur utilisateur = new Utilisateur();
	List<HoraireVaccinodrome> horaireVaccinodrome = new ArrayList<HoraireVaccinodrome>();
	List<Famille> famille = new ArrayList<Famille>();
	int status = 400;
	
	public InfoRdv() {}
	

	public InfoRdv(DemandeRdv demandeRdv, InfoVaccinUser infoVaccinUser, Vaccin vaccin, Vaccinodrome vaccinodrome,VaccinCentre vaccinCentre, Utilisateur utilisateur, List<HoraireVaccinodrome> horaireVaccinodrome,List<Famille> famille, int status) {
		this.demandeRdv = demandeRdv;
		this.infoVaccinUser = infoVaccinUser;
		this.vaccin = vaccin;
		this.vaccinodrome = vaccinodrome;
		this.vaccinCentre = vaccinCentre;
		this.utilisateur = utilisateur;
		this.horaireVaccinodrome = horaireVaccinodrome;
		this.famille = famille;
		this.status = status;
	}
	public DemandeRdv getDemandeRdv() {
		return demandeRdv;
	}

	public void setDemandeRdv(DemandeRdv demandeRdv) {
		this.demandeRdv = demandeRdv;
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

	public List<HoraireVaccinodrome> getHoraireVaccinodrome() {
		return horaireVaccinodrome;
	}


	public void setHoraireVaccinodrome(List<HoraireVaccinodrome> horaireVaccinodrome) {
		this.horaireVaccinodrome = horaireVaccinodrome;
	}
	
	
}
