package com.tpt.transversal.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.tpt.transversal.Repository.DemandeRdvRep;
import com.tpt.transversal.Repository.DemandeRdvViewRep;
import com.tpt.transversal.Repository.RdvFamilleRep;
import com.tpt.transversal.autre.Json;
import com.tpt.transversal.autre.Utile;
import com.tpt.transversal.model.DataRdvSearch;
import com.tpt.transversal.model.DemandeRdv;
import com.tpt.transversal.model.DemandeRdvView;
import com.tpt.transversal.model.Docs;
import com.tpt.transversal.model.RdvFamille;

@Service
public class DemandeRdvViewService {

	@Autowired
	DemandeRdvViewRep demandeRdvViewRep;
	
	@Autowired
	RdvFamilleRep rdvFamilleRep;
	
	@Autowired
	DemandeRdvRep demandeRdvRep;
	
	Utile utile = new Utile();
	
	public Docs<DemandeRdvView> getPageableDemandeRdvView(int limit,int pageNumber,int idUtilisateur,int statusRdv,boolean ordre) {
		if(limit>0 && pageNumber>=0) {
			try {
				PageRequest pageRequest = PageRequest.of(pageNumber, limit, Direction.ASC,"daterdv");
				if(!ordre) {pageRequest = PageRequest.of(pageNumber, limit, Direction.DESC,"daterdv");}
				Page<DemandeRdvView> page = demandeRdvViewRep.findByIdUtilisateurAndStatusRdvSort(idUtilisateur,statusRdv,pageRequest);
				Docs<DemandeRdvView> docs = new Docs<DemandeRdvView>(page.getContent(),page.getNumberOfElements(),(int)page.getPageable().getOffset(),page.getPageable().getPageNumber(),page.getTotalPages(),1,page.hasPrevious(),page.hasNext(),page.getPageable().getPageNumber()-1,page.getPageable().getPageNumber()+1);
				return docs;
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}
		Docs<DemandeRdvView> docs = new Docs<DemandeRdvView>();docs.setDocs(new ArrayList<DemandeRdvView>());
		return docs;
	}
	public PageRequest getPageRequest(Boolean order,String colone,int limite,int page) {
		if(!order) {
			return  PageRequest.of(page,limite, Direction.DESC,colone);
		}else {
			return PageRequest.of(page,limite, Direction.ASC,colone);
		}
	}
	public Docs<DemandeRdvView> searchlistDemandeRdv(DataRdvSearch dataRdvSearch) {
		if(utile.verifString(dataRdvSearch.getColone()) && utile.verifString(dataRdvSearch.getLibeller()) && dataRdvSearch.getStatus()>0 && dataRdvSearch.getIdUtilisateur()>0) {
			try {
				Page<DemandeRdvView> page = null; 
				if(dataRdvSearch.getColone().equalsIgnoreCase("dateRdv")) {
					long dateLibeller = utile.setStringToLong(dataRdvSearch.getLibeller());
					Date date=new Date(dateLibeller);
					page = demandeRdvViewRep.findByDateRdvAndStatusRdv(date, dataRdvSearch.getStatus(),dataRdvSearch.getIdUtilisateur(), getPageRequest(dataRdvSearch.isOrdre(),"daterdv", dataRdvSearch.getLimite(), dataRdvSearch.getPage()));
				}else if(dataRdvSearch.getColone().equalsIgnoreCase("nomVaccin")) {
					page = demandeRdvViewRep.findByNomVaccinAndStatusRdv(dataRdvSearch.getLibeller().toLowerCase(), dataRdvSearch.getStatus(),dataRdvSearch.getIdUtilisateur(), getPageRequest(dataRdvSearch.isOrdre(),"nom_vaccin", dataRdvSearch.getLimite(), dataRdvSearch.getPage()));
				}else {
					page = demandeRdvViewRep.findByNomVaccinodromeAnStatusRdv(dataRdvSearch.getLibeller().toLowerCase(), dataRdvSearch.getStatus(),dataRdvSearch.getIdUtilisateur(), getPageRequest(dataRdvSearch.isOrdre(),"nomvaccinodrome", dataRdvSearch.getLimite(), dataRdvSearch.getPage()));
				}
				Docs<DemandeRdvView> docs = new Docs<DemandeRdvView>(page.getContent(),page.getNumberOfElements(),(int)page.getPageable().getOffset(),page.getPageable().getPageNumber(),page.getTotalPages(),1,page.hasPrevious(),page.hasNext(),page.getPageable().getPageNumber()-1,page.getPageable().getPageNumber()+1);
				return docs;
			} catch (Exception e) {
				e.printStackTrace();
				Docs<DemandeRdvView> docs = new Docs<DemandeRdvView>();docs.setDocs(new ArrayList<DemandeRdvView>());
				return docs;
			}
		}else {
			Docs<DemandeRdvView> docs = new Docs<DemandeRdvView>();docs.setDocs(new ArrayList<DemandeRdvView>());
			return docs;
		}
	}
	public Docs<DemandeRdvView> searchlistDemandeRdvVaccinodrome(DataRdvSearch dataRdvSearch) {
		if(utile.verifString(dataRdvSearch.getColone()) && utile.verifString(dataRdvSearch.getLibeller()) && dataRdvSearch.getStatus()>0 && dataRdvSearch.getIdUtilisateur()>0) {
			try {
				Page<DemandeRdvView> page = null; 
				if(dataRdvSearch.getColone().equalsIgnoreCase("dateRdv")) {
					long dateLibeller = utile.setStringToLong(dataRdvSearch.getLibeller());
					Date date=new Date(dateLibeller);
					page = demandeRdvViewRep.findByIdVaccinodromeAndDateRdvAndStatusRdv(date, dataRdvSearch.getStatus(),dataRdvSearch.getIdUtilisateur(), getPageRequest(dataRdvSearch.isOrdre(),"daterdv", dataRdvSearch.getLimite(), dataRdvSearch.getPage()));
				}else if(dataRdvSearch.getColone().equalsIgnoreCase("nomVaccin")) {
					page = demandeRdvViewRep.findByIdVaccinodromeAndNomVaccinAndStatusRdv(dataRdvSearch.getLibeller().toLowerCase(), dataRdvSearch.getStatus(),dataRdvSearch.getIdUtilisateur(), getPageRequest(dataRdvSearch.isOrdre(),"nom_vaccin", dataRdvSearch.getLimite(), dataRdvSearch.getPage()));
				}else {
					page = demandeRdvViewRep.findByIdVaccinodromeAndNomPatientAnStatusRdv(dataRdvSearch.getLibeller().toLowerCase(), dataRdvSearch.getStatus(),dataRdvSearch.getIdUtilisateur(), getPageRequest(dataRdvSearch.isOrdre(),"nomvaccinodrome", dataRdvSearch.getLimite(), dataRdvSearch.getPage()));
				}
				Docs<DemandeRdvView> docs = new Docs<DemandeRdvView>(page.getContent(),page.getNumberOfElements(),(int)page.getPageable().getOffset(),page.getPageable().getPageNumber(),page.getTotalPages(),1,page.hasPrevious(),page.hasNext(),page.getPageable().getPageNumber()-1,page.getPageable().getPageNumber()+1);
				return docs;
			} catch (Exception e) {
				e.printStackTrace();
				Docs<DemandeRdvView> docs = new Docs<DemandeRdvView>();docs.setDocs(new ArrayList<DemandeRdvView>());
				return docs;
			}
		}else {
			Docs<DemandeRdvView> docs = new Docs<DemandeRdvView>();docs.setDocs(new ArrayList<DemandeRdvView>());
			return docs;
		}
	}
	public String deleteRdvStatusOne(int iddemanderdv,int etat) {
		Json json=new Json();
		if(iddemanderdv>0) {
			try {
				int status = 0;String message ="Suppression du rendez-vous avec succé.";
				if(etat==2) {status=3;message ="Demande d'annulation du rendez-vous envoyé au vaccinodrome concerné";}
				if(etat==3) {status=1;message ="Le rendez-vous est restauré.";}
				DemandeRdv demandeRdv = demandeRdvRep.findByIdDemandeRdv(iddemanderdv);
				List<RdvFamille> rdvFamille=rdvFamilleRep.findByIdDemandeRdv(iddemanderdv);
				int size = rdvFamille.size();
				for (int i = 0; i < size; i++) {
					rdvFamille.get(i).setStatus(status);
				}
				demandeRdv.setStatus(status);
				demandeRdvRep.save(demandeRdv);
				rdvFamilleRep.saveAll(rdvFamille);
				json.put("status",200);
				json.put("message",message);
				return json.toString();
			} catch (Exception e) {
				e.printStackTrace();
				json.put("status",400);
				json.put("message","Il y a une erreur d'information.");
				return json.toString();
			}
		}
		json.put("status",400);
		json.put("message","Les informations sont incomplètes");
		return json.toString();
	}
	public Docs<DemandeRdvView> getPageableDemandeRdvViewVaccinodrome(int limit, int pageNumber, int idvaccinodrome,int status, boolean ordre) {
		if(limit>0 && pageNumber>=0) {
			try {
				PageRequest pageRequest = PageRequest.of(pageNumber, limit, Direction.ASC,"daterdv");
				if(!ordre) {pageRequest = PageRequest.of(pageNumber, limit, Direction.DESC,"daterdv");}
				Page<DemandeRdvView> page = demandeRdvViewRep.findByIdVaccinodromeAndStatusRdvSort(idvaccinodrome,status,pageRequest);
				Docs<DemandeRdvView> docs = new Docs<DemandeRdvView>(page.getContent(),page.getNumberOfElements(),(int)page.getPageable().getOffset(),page.getPageable().getPageNumber(),page.getTotalPages(),1,page.hasPrevious(),page.hasNext(),page.getPageable().getPageNumber()-1,page.getPageable().getPageNumber()+1);
				return docs;
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}
		Docs<DemandeRdvView> docs = new Docs<DemandeRdvView>();docs.setDocs(new ArrayList<DemandeRdvView>());
		return docs;
	}
	public String statusDemandeRdv(int iddemanderdv, int status) {
		Json json=new Json();
		if(iddemanderdv>0) {
			try {
				String message ="";
				if(status==11) {
					message = "Le rendez-vous est refusé.";
				}
				if(status==22) {
					message = "La demande d'annulation a été envoyé.";
				}
				if(status==2) {
					message = "Confirmation du rendez-vous avec succé.";
				}
				DemandeRdv demandeRdv = demandeRdvRep.findByIdDemandeRdv(iddemanderdv);
				demandeRdv.setStatus(status);
				demandeRdvRep.save(demandeRdv);
				json.put("status",200);
				json.put("message",message);
				return json.toString();
			} catch (Exception e) {
				e.printStackTrace();
				json.put("status",400);
				json.put("message","Il y a une erreur d'information.");
				return json.toString();
			}
		}
		json.put("status",400);
		json.put("message","Les informations sont incomplètes");
		return json.toString();
	}
}
