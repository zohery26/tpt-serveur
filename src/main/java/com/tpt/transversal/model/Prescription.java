package com.tpt.transversal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="prescription")
public class Prescription {
   @Id
   @GeneratedValue(
	    strategy = GenerationType.SEQUENCE,
	    generator = "prescription-sequence"
	)
   @SequenceGenerator(
		name = "prescription-sequence",
		sequenceName = "seq_prescription",
		allocationSize = 1,
		initialValue = 2
	)
    @Column(name="idprescription")
	int idPrescription = 0;
    @Column(name="iddemanderdv")
	int idDemandeRdv = 0;
    @Column(name="traitement")
    String traitement = "";
    @Column(name="description")
    String description = "";
    @Column(name="status")
    int status = 0;
    
    public Prescription() {}

	public Prescription(int idPrescription, int idDemandeRdv, String traitement, String description, int status) {
		this.idPrescription = idPrescription;
		this.idDemandeRdv = idDemandeRdv;
		this.traitement = traitement;
		this.description = description;
		this.status = status;
	}

	public int getIdPrescription() {
		return idPrescription;
	}

	public void setIdPrescription(int idPrescription) {
		this.idPrescription = idPrescription;
	}

	public int getIdDemandeRdv() {
		return idDemandeRdv;
	}

	public void setIdDemandeRdv(int idDemandeRdv) {
		this.idDemandeRdv = idDemandeRdv;
	}

	public String getTraitement() {
		return traitement;
	}

	public void setTraitement(String traitement) {
		this.traitement = traitement;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	
    
    
}
