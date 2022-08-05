package com.tpt.transversal.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tpt.transversal.Repository.HoraireVaccinodromeRep;
import com.tpt.transversal.autre.Json;
import com.tpt.transversal.model.DataHoraire;
import com.tpt.transversal.model.HoraireVaccinodrome;

@Service
public class HoraireVaccinodromeService {
	
	@Autowired
	HoraireVaccinodromeRep horaireVaccinodromeRep;
	
	public List<HoraireVaccinodrome> createHoraire(){
		List<HoraireVaccinodrome> horaire = new ArrayList<HoraireVaccinodrome>();
		for (int i = 0; i < 7; i++) {
			horaire.add(new HoraireVaccinodrome(0,0,i,0,0,0,0,0));
		}
		return horaire;
	}
	public List<HoraireVaccinodrome> getHoraireVaccinodromeByIdVaccinodrome(int idVaccinodrome) {
		if(idVaccinodrome>0) {
			try {
				List<HoraireVaccinodrome> horaireVaccinodrome = horaireVaccinodromeRep.findByIdVaccinodrome(idVaccinodrome),horaire = new ArrayList<HoraireVaccinodrome>();
				int size = horaireVaccinodrome.size();
				for (int i = 0; i < 7; i++) {
					boolean test = false;
					for (int a = 0; a < size; a++) {
						if(horaireVaccinodrome.get(a).getJour()== i && horaireVaccinodrome.get(a).getStatus()==1 && !test) {
							horaire.add(horaireVaccinodrome.get(a));test= true;
						}
					}
					if(!test) {
						horaire.add(new HoraireVaccinodrome(0,idVaccinodrome,i,0,0,0,0,0));
					}
				}
				return horaire;
			} catch (Exception e) {
				e.printStackTrace();
				return createHoraire();
			}
		}
		return createHoraire();
	}

	public boolean getVerificationHoraireVaccinodrome(HoraireVaccinodrome horaireVaccinodrome) {// Verification valeur horaire vaccinodrome
		if(horaireVaccinodrome.getJour()>=0 && horaireVaccinodrome.getJour()<7 && horaireVaccinodrome.getMatinDebut()>0 && horaireVaccinodrome.getMatinFin()>0
		    && horaireVaccinodrome.getMatinDebut()<horaireVaccinodrome.getMatinFin() && horaireVaccinodrome.getMidiDebut()>0 && horaireVaccinodrome.getMidiFin()>0
		    && horaireVaccinodrome.getMidiDebut() < horaireVaccinodrome.getMidiFin()) {
			return true;
		}
		return false;
	}
	public boolean getVerificationListHoraireVaccinodrome(List<HoraireVaccinodrome> horaireVaccinodromes) {// Verification list horaire vaccinodrome
		int size = horaireVaccinodromes.size();
		if(size>0){
			for (int i = 0; i < size; i++) {
				if(!getVerificationHoraireVaccinodrome(horaireVaccinodromes.get(i))) {
					return false;
				}
			}
			return true;
		}
		return false;
	}
	public String updateHoraireVaccinodrome(DataHoraire dataHoraire) {
		Json json =new Json();int idVaccinodrome = dataHoraire.getIdVaccinodrome();List<HoraireVaccinodrome> horaire = dataHoraire.getHoraire();
		if(getVerificationListHoraireVaccinodrome(horaire) && idVaccinodrome>0) {
			try {
				List<HoraireVaccinodrome> list =  horaireVaccinodromeRep.findByIdVaccinodrome(idVaccinodrome);int size = list.size(),sizehoraire = horaire.size();
				for (int i = 0; i < sizehoraire; i++) {
					for (int a = 0; a < size; a++) {
						list.get(a).setStatus(0);
						if(list.get(a).getJour()==horaire.get(i).getJour()) {
							horaire.get(i).setIdhorairevaccinodrome(list.get(a).getIdhorairevaccinodrome());
						}
					}
					
				}
				horaireVaccinodromeRep.saveAll(list);
				horaireVaccinodromeRep.saveAll(horaire);
				json.put("status",200);
				json.put("message","Les horaires du vaccinodrome ont été mis à jour.");
				return json.toString();
			} catch (Exception e) {
				e.printStackTrace();
				json.put("status",400);
				json.put("message","Erreur : "+e.getMessage());
				return json.toString();
			}
		}
		json.put("status",400);
		json.put("message","Les informations sont incomplètes, merci de compléter tous les champs.");
		return json.toString();
	}
	
}
