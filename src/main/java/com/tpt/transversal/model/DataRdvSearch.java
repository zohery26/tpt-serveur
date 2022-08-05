package com.tpt.transversal.model;

public class DataRdvSearch {
	int limite = 10;
	int page = 0;
	int idUtilisateur = 0;
    String libeller = "";
    String colone = "";
    boolean ordre = true;
    int status = 1;
    
    public DataRdvSearch() {}

	public DataRdvSearch(int limite, int page, int idUtilisateur, String libeller, String colone, boolean ordre, int status) {
		this.limite = limite;
		this.page = page;
		this.idUtilisateur = idUtilisateur;
		this.libeller = libeller;
		this.colone = colone;
		this.ordre = ordre;
		this.status = status;
	}

	public int getLimite() {
		return limite;
	}

	public void setLimite(int limite) {
		this.limite = limite;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getIdUtilisateur() {
		return idUtilisateur;
	}

	public void setIdUtilisateur(int idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	public String getLibeller() {
		return libeller;
	}

	public void setLibeller(String libeller) {
		this.libeller = libeller;
	}

	public String getColone() {
		return colone;
	}

	public void setColone(String colone) {
		this.colone = colone;
	}

	public boolean isOrdre() {
		return ordre;
	}

	public void setOrdre(boolean ordre) {
		this.ordre = ordre;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
