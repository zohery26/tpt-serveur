package com.tpt.transversal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="rdvfamille")
public class RdvFamille {
   @Id
   @GeneratedValue(
	    strategy = GenerationType.SEQUENCE,
	    generator = "rdvfamille-sequence"
	)
   @SequenceGenerator(
		name = "rdvfamille-sequence",
		sequenceName = "seq_rdvfamille",
		allocationSize = 1,
		initialValue = 2
	)
    @Column(name="idrdvfamille")
	int idRdvFamille;
    @Column(name="iddemanderdv")
	int idDemandeRdv;
    @Column(name="idfamille")
	int idFamille;
    @Column(name="status")
	int status;
    
    public RdvFamille() {}
	public RdvFamille(int idRdvFamille, int idDemandeRdv, int idFamille, int status) {
		this.idRdvFamille = idRdvFamille;
		this.idDemandeRdv = idDemandeRdv;
		this.idFamille = idFamille;
		this.status = status;
	}
	public int getIdRdvFamille() {
		return idRdvFamille;
	}
	public void setIdRdvFamille(int idRdvFamille) {
		this.idRdvFamille = idRdvFamille;
	}
	public int getIdDemandeRdv() {
		return idDemandeRdv;
	}
	public void setIdDemandeRdv(int idDemandeRdv) {
		this.idDemandeRdv = idDemandeRdv;
	}
	public int getIdFamille() {
		return idFamille;
	}
	public void setIdFamille(int idFamille) {
		this.idFamille = idFamille;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
    
}
