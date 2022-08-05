package com.tpt.transversal.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.tpt.transversal.Repository.InfoVaccinUserRep;
import com.tpt.transversal.Repository.TypeUtilisateurRep;
import com.tpt.transversal.Repository.UtilisateurRep;
import com.tpt.transversal.autre.CryptoConfig;
import com.tpt.transversal.autre.Json;
import com.tpt.transversal.autre.Utile;
import com.tpt.transversal.model.DataPatient;
import com.tpt.transversal.model.Docs;
import com.tpt.transversal.model.InfoVaccinUser;
import com.tpt.transversal.model.TypeUtilisateur;
import com.tpt.transversal.model.Utilisateur;

@Service
public class UtilisateurService {
	
	@Autowired
	private UtilisateurRep utilisateurRep;
	@Autowired
	private TypeUtilisateurRep typeUtilisateurRep;
	@Autowired
	private InfoVaccinUserRep infoVaccinUserRep;
	Utile utile= new Utile();
	
	public Utilisateur getUtilisateurById(int IdUtilisateur){
		try {
			return utilisateurRep.findByIdUtilisateur(IdUtilisateur);
		} catch (Exception e) {
			e.printStackTrace();
			return new Utilisateur(0,0,"",0,utile.getDateNow(),"","","","",utile.getDateNow(),1);
		}
	}
	public String loginUtilisateur(String telephone,String mdp,String status) {
		Json json = new Json();
		if(utile.verifString(telephone) && utile.verifString(mdp)) {
			String tel = utile.supprimerLesEspace(telephone);
			System.out.println("telephone : "+tel);
			List<Utilisateur> utilisateurs = utilisateurRep.findByTelephone(tel);int size = utilisateurs.size();
			System.out.println("size = "+size);
			String motdepasse = CryptoConfig.generateSecurePassword(mdp, CryptoConfig.DEFAULT_SALT);int code = utile.setStringToInt(status);
			for (int i = 0; i < size; i++) {
				if(utilisateurs.get(i).getTelephone().equalsIgnoreCase(tel)) {
					System.out.println("mdp = "+(utilisateurs.get(i).getMot_de_passe().equals(motdepasse)));
					if(utilisateurs.get(i).getMot_de_passe().equals(motdepasse)) {
						TypeUtilisateur typeUtilisateur = typeUtilisateurRep.findByIdtypeutilisateur(""+utilisateurs.get(i).getIdtypeutilisateur());
						if(typeUtilisateur!=null) {
							if(typeUtilisateur.getStatus()>0 && code==typeUtilisateur.getStatus()) {
								json.put("token", utile.generateJwtByUser(utilisateurs.get(i),typeUtilisateur.getStatus(),"rdvvaksiny_botosoamalandy_toavina_zo"));
								json.put("message","Connexion autorisée");
								json.put("status",200);
								return json.toString();
							}else {
								json.put("message","Votre code d'authentification est invalide.");
								json.put("status",400);
								return json.toString();
							}
						}
						json.put("message","Il y a une erreur d'information.");
						json.put("status",400);
						return json.toString();
					}
				}
			}
			json.put("message","Votre numéro de téléphone ne correspond à aucun des utilisateurs.");
			json.put("status",400);
			return json.toString();
		}
		json.put("message","Il y a un champ vide");
		json.put("status",400);
		return json.toString();
	}
	public boolean getVerifUtilisateur(Utilisateur utilisateur) {
		if(utile.verifString(utilisateur.getNom()) && utilisateur.getSexe()>=1 && utilisateur.getSexe()<=2 && utilisateur.getNaissance()!=null 
				&& utile.verifString(utilisateur.getEmail()) && utile.verifString(utilisateur.getTelephone()) && utile.verifString(utilisateur.getMot_de_passe()) 
				&& utile.verifString(utilisateur.getUrlPhoto())) {
			return true;
		}
		return false;
	}
	public boolean getVerifInfoVaccinUser(InfoVaccinUser infoVaccinUser) {
		if(infoVaccinUser.getIdVaccinCentre()>0 && infoVaccinUser.getIdVaccinodrome()>0 && infoVaccinUser.getIdVaccin()>0) {
			return true;
		}
		return false;
	}
	public boolean getVerifDedoublementUtilisateur(Utilisateur utilisateur) {
		try {
			String tel = utile.supprimerLesEspace(utilisateur.getTelephone());
			List<Utilisateur> list = utilisateurRep.findByEmailAndTelephone(utilisateur.getEmail(),tel); int size = list.size();
			for (int i = 0; i < size; i++) {
				if(list.get(i).getEmail().equalsIgnoreCase(utilisateur.getEmail()) && list.get(i).getTelephone().equalsIgnoreCase(tel) && list.get(i).getStatus()==1) {
					return true;
				}
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}
	public String inscriptionPatient(DataPatient dataPatient) {
		Json json =new Json();
		Utilisateur utilisateur = dataPatient.getUtilisateur();InfoVaccinUser infoVaccinUser = dataPatient.getInfoVaccinUser();
		if(getVerifInfoVaccinUser(infoVaccinUser) && getVerifUtilisateur(utilisateur)) {
			try {
				if(!getVerifDedoublementUtilisateur(utilisateur)) {
					String motdepasse = CryptoConfig.generateSecurePassword(utilisateur.getMot_de_passe(), CryptoConfig.DEFAULT_SALT);
					utilisateur.setDate_ajout(utile.getDateNow());utilisateur.setStatus(1);utilisateur.setIdtypeutilisateur(1);
					utilisateur.setTelephone(utile.supprimerLesEspace(utilisateur.getTelephone()));utilisateur.setMot_de_passe(motdepasse);
					int idUser = utilisateurRep.save(utilisateur).getIdutilisateur();
					System.out.println("iduser : "+idUser);
					infoVaccinUser.setIdUtilisateur(idUser);infoVaccinUser.setStatus(1);
					infoVaccinUserRep.save(infoVaccinUser);
					json.put("status",200);
					json.put("message","ajout d'une nouvelle patient avec réussie.");
					return json.toString();
				}else {
					json.put("status",400);
					json.put("message","Cette information existe déjà.");
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
	public String mdpOublie(Utilisateur utilisateur) {
		Json json =new Json();
		if(utile.verifString(utilisateur.getTelephone()) && utile.verifString(utilisateur.getEmail()) && utile.verifString(utilisateur.getMot_de_passe())) {
			String tel = utile.supprimerLesEspace(utilisateur.getTelephone());
			List<Utilisateur> list = utilisateurRep.findByTelephone(tel);int size = list.size();
			for (int i = 0; i < size; i++) {
				if(list.get(i).getTelephone().equalsIgnoreCase(tel) && list.get(i).getEmail().equalsIgnoreCase(utilisateur.getEmail())) {
					try {
						String motdepasse = CryptoConfig.generateSecurePassword(utilisateur.getMot_de_passe(), CryptoConfig.DEFAULT_SALT);
						Utilisateur newUtilisateur = list.get(i); newUtilisateur.setMot_de_passe(motdepasse);
						utilisateurRep.save(newUtilisateur);
						json.put("status",200);
						json.put("message","Le mot de passe a été mis à jour");
						return json.toString();
					} catch (Exception e) {
						json.put("status",400);
						json.put("message","Erreur : "+e.getMessage());
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
	public boolean getVerifDedoublementUpdateUtilisateur(Utilisateur utilisateur) {
		try {
			String tel = utile.supprimerLesEspace(utilisateur.getTelephone());
			List<Utilisateur> list = utilisateurRep.findByEmailAndTelephone(utilisateur.getEmail(),tel); int size = list.size();
			for (int i = 0; i < size; i++) {
				if(list.get(i).getEmail().equalsIgnoreCase(utilisateur.getEmail()) && list.get(i).getTelephone().equalsIgnoreCase(tel) && list.get(i).getStatus()==1 && list.get(i).getIdutilisateur()!=utilisateur.getIdutilisateur()) {
					return true;
				}
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}
	public String updatePatient(Utilisateur utilisateur) {
		Json json =new Json();
		if(utilisateur.getIdutilisateur()>0 && getVerifUtilisateur(utilisateur)) {
			if(!getVerifDedoublementUpdateUtilisateur(utilisateur)) {
				utilisateur.setStatus(1);
				utilisateurRep.save(utilisateur);
				json.put("status",200);
				json.put("message","Vos informations ont été actualisées");
				return json.toString();
			}else {
				json.put("status",400);
				json.put("message","Le numéro de téléphone existe déjà.");
				return json.toString();
			}
		}
		json.put("status",400);
		json.put("message","Les informations sont incomplètes, merci de compléter tous les champs.");
		return json.toString();
	}
	public Docs<Utilisateur> getPageableUtilisateur(int limit,int pageNumber,int idtypeutilisateur) {
		if(limit>0 && pageNumber>=0) {
			try {
				if(idtypeutilisateur==11) {
					Page<Utilisateur> page = utilisateurRep.findByStatusAndNoIdTypeUtilisateurSort(PageRequest.of(pageNumber, limit, Direction.ASC,"nom"));
					Docs<Utilisateur> docs = new Docs<Utilisateur>(page.getContent(),page.getNumberOfElements(),(int)page.getPageable().getOffset(),page.getPageable().getPageNumber(),page.getTotalPages(),1,page.hasPrevious(),page.hasNext(),page.getPageable().getPageNumber()-1,page.getPageable().getPageNumber()+1);
					return docs;
				}else {
					Page<Utilisateur> page = utilisateurRep.findByStatusSort(idtypeutilisateur,PageRequest.of(pageNumber, limit, Direction.ASC,"nom"));
					Docs<Utilisateur> docs = new Docs<Utilisateur>(page.getContent(),page.getNumberOfElements(),(int)page.getPageable().getOffset(),page.getPageable().getPageNumber(),page.getTotalPages(),1,page.hasPrevious(),page.hasNext(),page.getPageable().getPageNumber()-1,page.getPageable().getPageNumber()+1);
					return docs;
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}
		Docs<Utilisateur> docs = new Docs<Utilisateur>();docs.setDocs(new ArrayList<Utilisateur>());
		return docs;
	}
	public Docs<Utilisateur> getPageableUtilisateurSearch(int limit,int pageNumber, int idtypeutilisateur,String libeller) {
		if(limit>0 && pageNumber>=0) {
			try {
				if(idtypeutilisateur==11) {
					Page<Utilisateur> page = utilisateurRep.findByNomUtilisateurAndNoIdTypeUtilisateurSort(libeller.toLowerCase(),PageRequest.of(pageNumber, limit, Direction.ASC,"nom"));
					Docs<Utilisateur> docs = new Docs<Utilisateur>(page.getContent(),page.getNumberOfElements(),(int)page.getPageable().getOffset(),page.getPageable().getPageNumber(),page.getTotalPages(),1,page.hasPrevious(),page.hasNext(),page.getPageable().getPageNumber()-1,page.getPageable().getPageNumber()+1);
					return docs;
				}else {
					Page<Utilisateur> page = utilisateurRep.findByNomUtilisateurSort(libeller.toLowerCase(),idtypeutilisateur,PageRequest.of(pageNumber, limit, Direction.ASC,"nom"));
					Docs<Utilisateur> docs = new Docs<Utilisateur>(page.getContent(),page.getNumberOfElements(),(int)page.getPageable().getOffset(),page.getPageable().getPageNumber(),page.getTotalPages(),1,page.hasPrevious(),page.hasNext(),page.getPageable().getPageNumber()-1,page.getPageable().getPageNumber()+1);
					return docs;
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}
		Docs<Utilisateur> docs = new Docs<Utilisateur>();docs.setDocs(new ArrayList<Utilisateur>());
		return docs;
	}
	public String addAdministrateur(Utilisateur utilisateur) {
		Json json =new Json();
		if(getVerifUtilisateur(utilisateur)) {
			try {
				if(!getVerifDedoublementUtilisateur(utilisateur)) {
					String motdepasse = CryptoConfig.generateSecurePassword(utilisateur.getMot_de_passe(), CryptoConfig.DEFAULT_SALT);
					utilisateur.setDate_ajout(utile.getDateNow());utilisateur.setStatus(1);utilisateur.setIdtypeutilisateur(3);
					utilisateur.setTelephone(utile.supprimerLesEspace(utilisateur.getTelephone()));
					utilisateur.setMot_de_passe(motdepasse);
					utilisateurRep.save(utilisateur);
					json.put("status",200);
					json.put("message","ajout d'une nouvelle adminstrateur avec réussie.");
					return json.toString();
				}else {
					json.put("status",400);
					json.put("message","Cette information existe déjà.");
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
	public String bloquerOrRestaurerUtilisateur(int idutilisateur,boolean restaurer) {
		Json json =new Json();
		if(idutilisateur>0) {
			try {
				Utilisateur utilisateur = utilisateurRep.findByIdUtilisateurSansStatus(idutilisateur);
				if(utilisateur!=null) {
					int status = 11;String message ="Le compte a été bloqué.";
					if(restaurer) { status = 1;message = "Le compte a été restauré.";}
					utilisateur.setStatus(status);
					utilisateurRep.save(utilisateur);
					json.put("status",200);
					json.put("message",message);
					return json.toString();
				}else {
					json.put("status",400);
					json.put("message","Les informations sont incomplètes.");
					return json.toString();
				}
			} catch (Exception e) {
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
