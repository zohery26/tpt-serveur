package com.tpt.transversal.model;

import java.util.ArrayList;
import java.util.List;

public class DataHoraire {
	int idVaccinodrome = 0;
	List<HoraireVaccinodrome> horaire = new ArrayList<HoraireVaccinodrome>();
	
	public DataHoraire() {}

	public DataHoraire(int idVaccinodrome, List<HoraireVaccinodrome> horaire) {
		this.idVaccinodrome = idVaccinodrome;
		this.horaire = horaire;
	}

	public int getIdVaccinodrome() {
		return idVaccinodrome;
	}

	public void setIdVaccinodrome(int idVaccinodrome) {
		this.idVaccinodrome = idVaccinodrome;
	}

	public List<HoraireVaccinodrome> getHoraire() {
		return horaire;
	}

	public void setHoraire(List<HoraireVaccinodrome> horaire) {
		this.horaire = horaire;
	}
	
	
}
