package com.tpt.transversal.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tpt.transversal.Repository.HoraireVaccinodromeRep;
import com.tpt.transversal.Repository.VaccinodromeRep;
import com.tpt.transversal.autre.CryptoConfig;
import com.tpt.transversal.autre.Json;
import com.tpt.transversal.autre.Utile;
import com.tpt.transversal.model.CodeAuth;
import com.tpt.transversal.model.Docs;
import com.tpt.transversal.model.HoraireVaccinodrome;
import com.tpt.transversal.model.Vaccinodrome;
import com.tpt.transversal.model.VaccinodromeAndHoraire;

@Service
@Transactional
public class VaccinodromeService {
	
	@Autowired
	private VaccinodromeRep vaccinodromeRep;
	@Autowired
	private HoraireVaccinodromeRep horaireVaccinodromeRep;
	Utile utile =new Utile();
	CodeAuth codeAuth =new CodeAuth();
	
	public boolean getVerificationVaccinodrome(Vaccinodrome vaccinodrome) {//vérification valeur vaccinodrome
		if(utile.verifString(vaccinodrome.getIdFokontany()) && utile.verifString(vaccinodrome.getIdFokontany()) &&  utile.verifString(vaccinodrome.getLocalisation()) 
			&& utile.verifString(vaccinodrome.getNomCentre()) && utile.verifString(vaccinodrome.getEmail()) && utile.verifString(vaccinodrome.getTelephone()) 
			&& utile.verifString(vaccinodrome.getAdresse()) && utile.verifString(vaccinodrome.getUrlPhoto()) && utile.verifString(vaccinodrome.getMotDePasse()) ){
			return true;
		}
		return false;
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
	public boolean getVerificationDedoublementDansBase(String email) {// verification dedoublement d'information dans la base données
		List<Vaccinodrome> vaccinodromes = vaccinodromeRep.findByEmail("%"+email+"%");int size = vaccinodromes.size();
		for (int i = 0; i < size; i++) {
			if(vaccinodromes.get(i).getEmail().equalsIgnoreCase(email)) {
				return true;
			}
		}
		return false;
	}
	public String inscriptionVaccinodrome(VaccinodromeAndHoraire vaccinodromeAndHoraire) {
		Json json =new Json();
		Vaccinodrome vaccinodrome = vaccinodromeAndHoraire.getVaccinodrome(); List<HoraireVaccinodrome> horaireVaccinodromes=vaccinodromeAndHoraire.getHoraireVaccinodromes();
		if(getVerificationVaccinodrome(vaccinodrome) && getVerificationListHoraireVaccinodrome(horaireVaccinodromes)) {
			try {
				if(!getVerificationDedoublementDansBase(vaccinodrome.getEmail())) {
					vaccinodrome.setMotDePasse(CryptoConfig.generateSecurePassword(vaccinodrome.getMotDePasse(), CryptoConfig.DEFAULT_SALT));
					vaccinodrome.setStatus(1);vaccinodrome.setTelephone(utile.supprimerLesEspace(vaccinodrome.getTelephone()));
					int idvaccinodrome = vaccinodromeRep.save(vaccinodrome).getIdVaccinodrome();
					int size = horaireVaccinodromes.size();
					for (int i = 0; i < size; i++) {
						horaireVaccinodromes.get(i).setIdvaccinodrome(idvaccinodrome);
					}
					horaireVaccinodromeRep.saveAll(horaireVaccinodromes);
					json.put("status",200);
					json.put("message","inscription réussie.Pour accéder à votre compte, veuillez vous connecter à la page de connexion.");
					return json.toString();
				}else {
					json.put("status",400);
					json.put("message","inscription annulée, ce compte a déjà été créé.");
					return json.toString();
				}
			} catch (Exception e) {
				json.put("status",400);
				json.put("message","Erreur : "+e.getMessage());
				return json.toString();
			}
		}
		json.put("status",400);
		json.put("message","Les informations sont incomplètes, merci de compléter tous les champs.");
		return json.toString();
	}
	public String loginVaccinodrome(String email, String motDePasse, String status) {
		Json json =new Json();
		if(utile.verifString(email) && utile.verifString(motDePasse) && utile.verifString(status)) {
			int code = utile.setStringToInt(status);
			if(code==codeAuth.getVaccinodrome()) {
				List<Vaccinodrome> vaccinodromes = vaccinodromeRep.findByEmail("%"+email+"%");int size = vaccinodromes.size();
				String motdepasse = CryptoConfig.generateSecurePassword(motDePasse, CryptoConfig.DEFAULT_SALT);
				for (int i = 0; i < size; i++) {
					if(vaccinodromes.get(i).getEmail().equalsIgnoreCase(email)) {
						if(vaccinodromes.get(i).getMotDePasse().equalsIgnoreCase(motdepasse)) {
							json.put("token", utile.generateJwtByVaccinodrome(vaccinodromes.get(i),code,"rdvvaksiny_botosoamalandy_toavina_zo"));
							json.put("message","Connexion autorisée");
							json.put("status",200);
							return json.toString();
						}
					}
				}
				json.put("status",400);
				json.put("message","Ce compte n'existe pas");
				return json.toString();
			}else {
				json.put("status",400);
				json.put("message","Votre code d'authentification est invalide.");
				return json.toString();
			}
		}
		json.put("status",400);
		json.put("message","Il y a un champ vide, je vous en prie, remplissez-le.");
		return json.toString();
	}
	public String motDePasseOublie(Vaccinodrome vaccinodrome) {
		Json json =new Json();
		if(utile.verifString(vaccinodrome.getNomCentre()) && utile.verifString(vaccinodrome.getEmail()) && utile.verifString(vaccinodrome.getTelephone()) 
			&& utile.verifString(vaccinodrome.getMotDePasse())) {
			List<Vaccinodrome> vaccinodromes = vaccinodromeRep.findByEmail("%"+vaccinodrome.getEmail()+"%");int size = vaccinodromes.size();
			String motdepasse = CryptoConfig.generateSecurePassword(vaccinodrome.getMotDePasse(), CryptoConfig.DEFAULT_SALT);
			String telephone = utile.supprimerLesEspace(vaccinodrome.getTelephone());
			for (int i = 0; i < size; i++) {
				if(vaccinodromes.get(i).getEmail().equalsIgnoreCase(vaccinodrome.getEmail())) {
					if(vaccinodromes.get(i).getNomCentre().equalsIgnoreCase(vaccinodrome.getNomCentre()) && vaccinodromes.get(i).getTelephone().equalsIgnoreCase(telephone)) {
						try {
							Vaccinodrome newVaccinodrome = vaccinodromes.get(i); newVaccinodrome.setMotDePasse(motdepasse);
							vaccinodromeRep.save(newVaccinodrome);
							json.put("status",200);
							json.put("message","Le mot de passe a été mis à jour");
							return json.toString();
						} catch (Exception e) {
							json.put("status",400);
							json.put("message","Erreur : "+e.getMessage());
							return json.toString();
						}
					}else {
						json.put("status",400);
						json.put("message","Le nom ou le numéro de téléphone du centre de vaccination ne correspond pas à ceux qui figurent dans la base de données.");
						return json.toString();
					}
				}
			}
			json.put("status",400);
			json.put("message","Ce compte n'existe pas.");
			return json.toString();
		}
		json.put("status",400);
		json.put("message","Il y a un champ vide, je vous en prie, remplissez-le.");
		return json.toString();
	}
	public Vaccinodrome getVaccinodromeByIdVaccinodrome(int idvaccinodrome) {
		Vaccinodrome vaccinodromeTmp = new Vaccinodrome();vaccinodromeTmp.setIdVaccinodrome(0);
		if(idvaccinodrome>0) {
			try {
				Vaccinodrome vaccinodrome = vaccinodromeRep.findByIdVaccinodrome(idvaccinodrome);
				if(vaccinodrome!=null) {
					return vaccinodrome;
				}
				return vaccinodromeTmp;
			} catch (Exception e) {
				e.printStackTrace();
				return vaccinodromeTmp;
			}
		}
		return vaccinodromeTmp;
	}
	public String updateVaccinodrome(Vaccinodrome vaccinodrome) {
		Json json =new Json();
		if(getVerificationVaccinodrome(vaccinodrome) && vaccinodrome.getIdVaccinodrome()>0) {
			try {
				vaccinodromeRep.save(vaccinodrome);
				json.put("status",200);
				json.put("message","Les informations du vaccinodrome ont été mis à jour.");
				return json.toString();
			} catch (Exception e) {
				json.put("status",400);
				json.put("message","Erreur : "+e.getMessage());
				return json.toString();
			}
		}
		json.put("status",400);
		json.put("message","Les informations sont incomplètes, merci de compléter tous les champs.");
		return json.toString();
	}
	public Docs<Vaccinodrome> getPageableVaccinodrome(int limit, int pageNumber, int status) {
		if(limit>0 && pageNumber>=0) {
			try {
				if(status==22) {
					Page<Vaccinodrome> page = vaccinodromeRep.findByNoStatusSort(PageRequest.of(pageNumber, limit, Direction.ASC,"nom_centre"));
					Docs<Vaccinodrome> docs = new Docs<Vaccinodrome>(page.getContent(),page.getNumberOfElements(),(int)page.getPageable().getOffset(),page.getPageable().getPageNumber(),page.getTotalPages(),1,page.hasPrevious(),page.hasNext(),page.getPageable().getPageNumber()-1,page.getPageable().getPageNumber()+1);
					return docs;
				}else {
					Page<Vaccinodrome> page = vaccinodromeRep.findByStatusSort(status,PageRequest.of(pageNumber, limit, Direction.ASC,"nom_centre"));
					Docs<Vaccinodrome> docs = new Docs<Vaccinodrome>(page.getContent(),page.getNumberOfElements(),(int)page.getPageable().getOffset(),page.getPageable().getPageNumber(),page.getTotalPages(),1,page.hasPrevious(),page.hasNext(),page.getPageable().getPageNumber()-1,page.getPageable().getPageNumber()+1);
					return docs;
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}
		Docs<Vaccinodrome> docs = new Docs<Vaccinodrome>();docs.setDocs(new ArrayList<Vaccinodrome>());
		return docs;
	}
	public Docs<Vaccinodrome> getPageableUtilisateurSearch(int limit, int pageNumber, int status,String libeller) {
		if(limit>0 && pageNumber>=0) {
			try {
				if(status==22) {
					Page<Vaccinodrome> page = vaccinodromeRep.findByNomCentreAndNoStatusSort(libeller.toLowerCase(),PageRequest.of(pageNumber, limit, Direction.ASC,"nom_centre"));
					Docs<Vaccinodrome> docs = new Docs<Vaccinodrome>(page.getContent(),page.getNumberOfElements(),(int)page.getPageable().getOffset(),page.getPageable().getPageNumber(),page.getTotalPages(),1,page.hasPrevious(),page.hasNext(),page.getPageable().getPageNumber()-1,page.getPageable().getPageNumber()+1);
					return docs;
				}else {
					Page<Vaccinodrome> page = vaccinodromeRep.findByNomCentreAndStatusSort(libeller.toLowerCase(),status,PageRequest.of(pageNumber, limit, Direction.ASC,"nom_centre"));
					Docs<Vaccinodrome> docs = new Docs<Vaccinodrome>(page.getContent(),page.getNumberOfElements(),(int)page.getPageable().getOffset(),page.getPageable().getPageNumber(),page.getTotalPages(),1,page.hasPrevious(),page.hasNext(),page.getPageable().getPageNumber()-1,page.getPageable().getPageNumber()+1);
					return docs;
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}
		Docs<Vaccinodrome> docs = new Docs<Vaccinodrome>();docs.setDocs(new ArrayList<Vaccinodrome>());
		return docs;
	}
	public String bloquerOrRestaurerVaccinodrome(int idvaccinodrome,boolean restaurer) {
		Json json =new Json();
		if(idvaccinodrome>0) {
			try {
				Vaccinodrome vaccinodrome = vaccinodromeRep.findByIdVaccinodrome(idvaccinodrome);
				if(vaccinodrome!=null) {
					int status = 11;String message ="Le compte a été bloqué.";
					if(restaurer) { status = 1;message = "Le compte a été restauré.";}
					vaccinodrome.setStatus(status);vaccinodrome.setNomCentre(vaccinodrome.getNomCentre());
					vaccinodromeRep.save(vaccinodrome);
					json.put("status",200);
					json.put("message",message);
					return json.toString();
				}else {
					json.put("status",400);
					json.put("message","Les informations sont incomplètes.");
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
		json.put("message","Les informations sont incomplètes.");
		return json.toString();
	}

}
