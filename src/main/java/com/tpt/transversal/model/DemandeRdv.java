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
@Table(name="demanderdv")
public class DemandeRdv {
   @Id
   @GeneratedValue(
	    strategy = GenerationType.SEQUENCE,
	    generator = "demanderdv-sequence"
	)
   @SequenceGenerator(
		name = "demanderdv-sequence",
		sequenceName = "seq_demanderdv",
		allocationSize = 1,
		initialValue = 2
	)
    @Column(name="iddemanderdv")
	int idDemandeRdv;
	@Column(name="idinfovaccinuser")
    int idInfovaccinuser;
	@Column(name="dose")
	int dose;
    @Column(name="daterdv")
    Date dateRdv;
    @Column(name="famille")
    int famille;
    @Column(name="descriptions")
    String descriptions;
    @Column(name="status")
    int status;
    
    public DemandeRdv() {}

	public DemandeRdv(int idDemandeRdv, int idInfovaccinuser,int dose, Date dateRdv, int famille, String descriptions,int status) {
		this.idDemandeRdv = idDemandeRdv;
		this.idInfovaccinuser = idInfovaccinuser;
		this.dose = dose;
		this.dateRdv = dateRdv;
		this.famille = famille;
		this.descriptions = descriptions;
		this.status = status;
	}

	public int getIdDemandeRdv() {
		return idDemandeRdv;
	}

	public void setIdDemandeRdv(int idDemandeRdv) {
		this.idDemandeRdv = idDemandeRdv;
	}

	public int getIdInfovaccinuser() {
		return idInfovaccinuser;
	}

	public void setIdInfovaccinuser(int idInfovaccinuser) {
		this.idInfovaccinuser = idInfovaccinuser;
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

	public String getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
    
    
}
