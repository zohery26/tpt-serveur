package com.tpt.transversal.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tpt.transversal.Repository.DemandeRdvRep;
import com.tpt.transversal.Repository.FamilleRep;
import com.tpt.transversal.Repository.HoraireVaccinodromeRep;
import com.tpt.transversal.Repository.InfoVaccinUserRep;
import com.tpt.transversal.Repository.MaladieRep;
import com.tpt.transversal.Repository.RdvFamilleRep;
import com.tpt.transversal.Repository.UtilisateurRep;
import com.tpt.transversal.Repository.VaccinCentreRep;
import com.tpt.transversal.Repository.VaccinRep;
import com.tpt.transversal.Repository.VaccinodromeRep;
import com.tpt.transversal.autre.Json;
import com.tpt.transversal.autre.Utile;
import com.tpt.transversal.model.DemandeRdv;
import com.tpt.transversal.model.Famille;
import com.tpt.transversal.model.HoraireVaccinodrome;
import com.tpt.transversal.model.InfoRdv;
import com.tpt.transversal.model.InfoUtilisateur;
import com.tpt.transversal.model.InfoVaccinUser;
import com.tpt.transversal.model.Maladie;
import com.tpt.transversal.model.Utilisateur;
import com.tpt.transversal.model.Vaccin;
import com.tpt.transversal.model.VaccinCentre;
import com.tpt.transversal.model.Vaccinodrome;

@Service
public class InfoVaccinUserService {

	@Autowired
	InfoVaccinUserRep infoVaccinUserRep;
	@Autowired
	VaccinCentreRep vaccinCentreRep;
	@Autowired
	VaccinRep vaccinRep;
	@Autowired
	VaccinodromeRep vaccinodromeRep;
	@Autowired
	UtilisateurRep utilisateurRep;
	@Autowired
	FamilleRep familleRep;
	@Autowired
	DemandeRdvRep demandeRdvRep;
	@Autowired
	RdvFamilleRep rdvFamilleRep;
	@Autowired
	HoraireVaccinodromeRep horaireVaccinodromeRep;
	@Autowired
	MaladieRep maladieRep;
	
	Utile utile =new Utile();
	
	public InfoUtilisateur getInfoVaccinUserByIdUtilisateur(int idutilisateur) {
		if(idutilisateur>0) {
			try {
				List<InfoVaccinUser> listInfoVaccinUsers = infoVaccinUserRep.findByIdUtilisateurAndStatus(idutilisateur);int size = listInfoVaccinUsers.size();
				if(size>0) {
					InfoVaccinUser infoVaccinUser = listInfoVaccinUsers.get(size-1);
					Vaccin vaccin=vaccinRep.findByIdVaccinAndNoStatus(infoVaccinUser.getIdVaccin());
					VaccinCentre vaccinCentre = vaccinCentreRep.findByIdVaccinCentre(infoVaccinUser.getIdVaccinCentre());
					Vaccinodrome vaccinodrome = vaccinodromeRep.findByIdVaccinodrome(infoVaccinUser.getIdVaccinodrome());
					Utilisateur utilisateur = utilisateurRep.findByIdUtilisateur(infoVaccinUser.getIdUtilisateur());
					List<Famille> famille = familleRep.findByIdUtilisateur(infoVaccinUser.getIdUtilisateur());
					if(vaccin!=null && vaccinCentre!=null && vaccinodrome!=null && utilisateur!=null) {
						return new InfoUtilisateur(infoVaccinUser,vaccin, vaccinodrome, vaccinCentre, utilisateur, famille, 200);
					}
				}
				return new InfoUtilisateur();
			} catch (Exception e) {
				e.printStackTrace();
				return new InfoUtilisateur();
			}
		}
		return new InfoUtilisateur();
	}
	public List<HoraireVaccinodrome> getHoraireVaccinodrome(List<HoraireVaccinodrome> horaireVaccinodrome) {
		List<HoraireVaccinodrome> horaire = new ArrayList<HoraireVaccinodrome>();
		int size = horaireVaccinodrome.size();
		for (int i = 0; i < 7; i++) {
			boolean test = false;
			for (int a = 0; a < size; a++) {
				if(horaireVaccinodrome.get(a).getJour()== i && horaireVaccinodrome.get(a).getStatus()==1 && !test) {
					horaire.add(horaireVaccinodrome.get(a));test= true;
				}
			}
			if(!test) {
				horaire.add(new HoraireVaccinodrome(0,0,i,0,0,0,0,0));
			}
		}
		return horaire;
	}
	public String generatertextMaladieByMaladie(int idvaccin) {
		String libeller = "";
		try {
			List<Maladie> maladies = maladieRep.findByIdVaccinAll(idvaccin);int size = maladies.size();
			for (int i = 0; i < size; i++) {
				libeller = libeller + " "+ maladies.get(i).getMaladie();
				if(i< (size-1)) {
					libeller = libeller + ", ";
				}
			}
			libeller = libeller+", ... ";
		} catch (Exception e) {
			e.printStackTrace();
			libeller = "";
		}
		return libeller;
	}
	public InfoRdv getInfoVaccinUserByIdDemandeRdv(int idDemandeRdv) {
		if(idDemandeRdv>0) {
			try {
				DemandeRdv demandeRdv = demandeRdvRep.findByIdDemandeRdv(idDemandeRdv);
				if(demandeRdv!=null) {
					InfoVaccinUser infoVaccinUser = infoVaccinUserRep.findByidInfoVaccinUserAndStatus(demandeRdv.getIdInfovaccinuser());
					if(infoVaccinUser!=null) {
						Vaccin vaccin=vaccinRep.findByIdVaccinAndNoStatus(infoVaccinUser.getIdVaccin());
						VaccinCentre vaccinCentre = vaccinCentreRep.findByIdVaccinCentre(infoVaccinUser.getIdVaccinCentre());
						Vaccinodrome vaccinodrome = vaccinodromeRep.findByIdVaccinodrome(infoVaccinUser.getIdVaccinodrome());
						Utilisateur utilisateur = utilisateurRep.findByIdUtilisateur(infoVaccinUser.getIdUtilisateur());
						List<Famille> famille = familleRep.findByIdDemandeRdv(idDemandeRdv);
						if(vaccin!=null && vaccinCentre!=null && vaccinodrome!=null && utilisateur!=null) {
							String libeller = generatertextMaladieByMaladie(vaccin.getIdVaccin());
							vaccinCentre.setDescriptions(libeller);
							List<HoraireVaccinodrome> horaireVaccinodrome = horaireVaccinodromeRep.findByIdVaccinodromeSansStatus(vaccinodrome.getIdVaccinodrome());
							return new InfoRdv(demandeRdv,infoVaccinUser,vaccin, vaccinodrome, vaccinCentre, utilisateur,getHoraireVaccinodrome(horaireVaccinodrome), famille, 200);
						}
					}
					
				}
				return new InfoRdv();
			} catch (Exception e) {
				e.printStackTrace();
				return new InfoRdv();
			}
		}
		return new InfoRdv();
	}
	public boolean getVerifInfoVaccinUser(InfoVaccinUser infoVaccinUser) {
		if(infoVaccinUser.getIdVaccinCentre()>0 && infoVaccinUser.getIdVaccinodrome()>0 && infoVaccinUser.getIdVaccin()>0 && infoVaccinUser.getIdUtilisateur()>0) {
			return true;
		}
		return false;
	}
	public boolean getVerifDedoublementInfoVaccinUser(InfoVaccinUser infoVaccinUser) {
		try {
			List<InfoVaccinUser> list = infoVaccinUserRep.findByIdUtilisateurAndIdVaccinCentreAndIdVaccinodromeAndIdVaccin(infoVaccinUser.getIdUtilisateur(),infoVaccinUser.getIdVaccinCentre(),infoVaccinUser.getIdVaccinodrome(),infoVaccinUser.getIdVaccin()); 
			int size = list.size();
			for (int i = 0; i < size; i++) {
				list.get(i).setStatus(0);
			}
			infoVaccinUserRep.saveAll(list);
			return false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	public String insertionInfoVaccinUser(InfoVaccinUser infoVaccinUser) {
		Json json =new Json();
		if(getVerifInfoVaccinUser(infoVaccinUser)) {
			try {
				if(!getVerifDedoublementInfoVaccinUser(infoVaccinUser)) {
					infoVaccinUser.setStatus(1);
					infoVaccinUserRep.save(infoVaccinUser);
					json.put("status",200);
					json.put("message","Votre vaccinodrome a été mis à jour.");
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
	
}
