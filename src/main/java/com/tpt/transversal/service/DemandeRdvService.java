package com.tpt.transversal.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tpt.transversal.Repository.DemandeRdvRep;
import com.tpt.transversal.Repository.PrescriptionRep;
import com.tpt.transversal.Repository.RdvFamilleRep;
import com.tpt.transversal.autre.Json;
import com.tpt.transversal.autre.Utile;
import com.tpt.transversal.model.DataDemandeRdv;
import com.tpt.transversal.model.DataVaccination;
import com.tpt.transversal.model.DemandeRdv;
import com.tpt.transversal.model.Prescription;
import com.tpt.transversal.model.RdvFamille;

@Service
public class DemandeRdvService {
	
	@Autowired
	DemandeRdvRep demandeRdvRep;
	@Autowired
	RdvFamilleRep rdvFamilleRep;
	@Autowired
	PrescriptionRep prescriptionRep;
	
	Utile utile = new Utile();

	public boolean getVerificationDemandeRdv(DemandeRdv demandeRdv) {
		if(utile.getDateNow().getTime()<=demandeRdv.getDateRdv().getTime() && demandeRdv.getFamille()>0 && demandeRdv.getFamille()<3 && demandeRdv.getIdInfovaccinuser()>0 && demandeRdv.getDose()>0) {
			return true;
		}
		return false;
	}
	public boolean getVerificationRdvFamille(List<RdvFamille> familles) {
		int size = familles.size();
		for (int i = 0; i < size; i++) {
			if(familles.get(i).getIdFamille()<0) {
				return false;
			}
		}
		return true;
	}
	public boolean getVerificationDedoublementDemandeRdv(DemandeRdv demandeRdv) {
		try {
			List<DemandeRdv> list = demandeRdvRep.findByiDinfoVaccinUserAndDaterdv(demandeRdv.getIdInfovaccinuser(),demandeRdv.getDateRdv());
			if(list.size()>0) {
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}
	public String deamndeDeRdv(DataDemandeRdv dataDemandeRdv) {
		Json json=new Json();
		DemandeRdv demandeRdv = dataDemandeRdv.getDemandeRdv();List<RdvFamille> rdvFamille =dataDemandeRdv.getFamille();
		if(getVerificationDemandeRdv(demandeRdv) && getVerificationRdvFamille(rdvFamille)) {
			try {
				if(!getVerificationDedoublementDemandeRdv(demandeRdv)) {
					demandeRdv.setFamille(rdvFamille.size());
					demandeRdv.setStatus(1);
					int idDemandeRdv = demandeRdvRep.save(demandeRdv).getIdDemandeRdv();
					int size = rdvFamille.size();
					for (int i = 0; i < size; i++) {
						rdvFamille.get(i).setIdDemandeRdv(idDemandeRdv);rdvFamille.get(i).setStatus(1);
					}
					rdvFamilleRep.saveAll(rdvFamille);
					json.put("status",200);
					json.put("message","La demande de rendez-vous a été envoyé.");
					return json.toString();
				}else {
					json.put("status",400);
					json.put("message","Cette information existe déjà.");
					return json.toString();
				}
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
	public boolean getVerifPrescription(List<Prescription> prescriptions) {
		int size = prescriptions.size();
		for (int i = 0; i < size; i++) {
			if(!utile.verifString(prescriptions.get(i).getTraitement())) {
				return false;
			}
		}
		return true;
	}
	@Transactional
	public String vaccinationPatient(DataVaccination dataVaccination) {
		Json json=new Json();List<Prescription> prescriptions = dataVaccination.getPrescription();
		if(dataVaccination.getQuantite()>0 && dataVaccination.getIdDemandeRdv()>0) {
			try {
				DemandeRdv demandeRdv = demandeRdvRep.findByIdDemandeRdv(dataVaccination.getIdDemandeRdv());
				if(demandeRdv!=null && getVerifPrescription(prescriptions)) {
					if(dataVaccination.isEtatProchainRDV()) {
						DemandeRdv prochainRdv = new DemandeRdv(0,demandeRdv.getIdInfovaccinuser(),demandeRdv.getDose()+1, dataVaccination.getDateProchainRDV(),demandeRdv.getFamille(),demandeRdv.getDescriptions(),2);
						demandeRdvRep.save(prochainRdv);
					}
					int size = prescriptions.size();
					for (int i = 0; i < size; i++) {
						prescriptions.get(i).setStatus(1);prescriptions.get(i).setIdDemandeRdv(demandeRdv.getIdDemandeRdv());
					}
					prescriptionRep.saveAll(prescriptions);
					demandeRdv.setStatus(5);
					demandeRdvRep.save(demandeRdv);
					json.put("status",200);
					json.put("message","Félicitation une nouvelle patient a été vaccinée.");
					return json.toString();
				}
				json.put("status",400);
				json.put("message","Les informations sont incomplètes.");
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
	public List<Prescription> getPrescription(int idDemandeRdv) {
		if(idDemandeRdv>0) {
			try {
				return prescriptionRep.findByIdDemandeRdv(idDemandeRdv);
			} catch (Exception e) {
				e.printStackTrace();
				return new ArrayList<Prescription>();
			}
		}
		return new ArrayList<Prescription>();
	}
}
