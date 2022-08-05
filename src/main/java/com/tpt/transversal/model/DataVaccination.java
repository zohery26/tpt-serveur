package com.tpt.transversal.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.tpt.transversal.autre.Utile;

public class DataVaccination {
	int idDemandeRdv = 0;
    double quantite = 0;
    List<Prescription> prescription = new ArrayList<Prescription>(); 
    boolean etatProchainRDV = false;
    Date dateProchainRDV = (new Utile()).getDateNow();
    
    public DataVaccination() {}

	public DataVaccination(int idDemandeRdv, double quantite, List<Prescription> prescription, boolean etatProchainRDV,Date dateProchainRDV) {
		this.idDemandeRdv = idDemandeRdv;
		this.quantite = quantite;
		this.prescription = prescription;
		this.etatProchainRDV = etatProchainRDV;
		this.dateProchainRDV = dateProchainRDV;
	}

	public int getIdDemandeRdv() {
		return idDemandeRdv;
	}

	public void setIdDemandeRdv(int idDemandeRdv) {
		this.idDemandeRdv = idDemandeRdv;
	}

	public double getQuantite() {
		return quantite;
	}

	public void setQuantite(double quantite) {
		this.quantite = quantite;
	}

	public List<Prescription> getPrescription() {
		return prescription;
	}

	public void setPrescription(List<Prescription> prescription) {
		this.prescription = prescription;
	}

	public boolean isEtatProchainRDV() {
		return etatProchainRDV;
	}

	public void setEtatProchainRDV(boolean etatProchainRDV) {
		this.etatProchainRDV = etatProchainRDV;
	}

	public Date getDateProchainRDV() {
		return dateProchainRDV;
	}

	public void setDateProchainRDV(Date dateProchainRDV) {
		this.dateProchainRDV = dateProchainRDV;
	}
    
    
}
