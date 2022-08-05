package com.tpt.transversal.model;

import java.util.ArrayList;
import java.util.List;

public class DataDemandeRdv {
	DemandeRdv demandeRdv = new DemandeRdv();
	List<RdvFamille> famille = new ArrayList<RdvFamille>();
	
	public DataDemandeRdv() {}

	public DataDemandeRdv(DemandeRdv demandeRdv, List<RdvFamille> famille) {
		this.demandeRdv = demandeRdv;
		this.famille = famille;
	}

	public DemandeRdv getDemandeRdv() {
		return demandeRdv;
	}

	public void setDemandeRdv(DemandeRdv demandeRdv) {
		this.demandeRdv = demandeRdv;
	}

	public List<RdvFamille> getFamille() {
		return famille;
	}

	public void setFamille(List<RdvFamille> famille) {
		this.famille = famille;
	}
	
}
