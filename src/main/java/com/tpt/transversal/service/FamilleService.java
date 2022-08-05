package com.tpt.transversal.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.tpt.transversal.Repository.FamilleRep;
import com.tpt.transversal.autre.Json;
import com.tpt.transversal.autre.Utile;
import com.tpt.transversal.model.Docs;
import com.tpt.transversal.model.Famille;

@Service
public class FamilleService {
	
	@Autowired
	FamilleRep familleRep;
	Utile utile = new Utile();
	
	public boolean getVerifFamille(Famille famille) {
		if(famille.getIdUtilisateur()>0 && utile.verifString(famille.getNom()) && famille.getSexe()>=1 && famille.getSexe()<=2 && famille.getNaissance()!=null 
				&& utile.verifString(famille.getUrlPhoto()) && utile.verifString(famille.getUrlPhoto())) {
			return true;
		}
		return false;
	}
	
	public boolean getVerifDedoublementFamille(Famille famille) {
		try {
			List<Famille> list = familleRep.findByIdUtilisateurAndNom(famille.getIdUtilisateur(),famille.getNom()); int size = list.size();
			for (int i = 0; i < size; i++) {
				if(list.get(i).getNom().equalsIgnoreCase(famille.getNom()) && list.get(i).getIdUtilisateur()==famille.getIdUtilisateur() && list.get(i).getStatus()==1) {
					return true;
				}
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	public String insertionFamille(Famille famille) {
		Json json =new Json();
		if(getVerifFamille(famille)) {
			try {
				if(!getVerifDedoublementFamille(famille)) {
					famille.setStatus(1);
					familleRep.save(famille);
					json.put("status",200);
					json.put("message","ajout famille avec réussie.");
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
	public Docs<Famille> getPageableFamille(int limit,int pageNumber) {
		if(limit>0 && pageNumber>=0) {
			try {
				Page<Famille> page = familleRep.findByStatusSort(PageRequest.of(pageNumber, limit, Direction.ASC,"nom"));
				Docs<Famille> docs = new Docs<Famille>(page.getContent(),page.getNumberOfElements(),(int)page.getPageable().getOffset(),page.getPageable().getPageNumber(),page.getTotalPages(),1,page.hasPrevious(),page.hasNext(),page.getPageable().getPageNumber()-1,page.getPageable().getPageNumber()+1);
				return docs;
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		Docs<Famille> docs = new Docs<Famille>();docs.setDocs(new ArrayList<Famille>());
		return docs;
		
	}

	public String updateFamille(Famille famille) {
		Json json =new Json();
		if(famille.getIdFamille()>0 && getVerifFamille(famille)) {
			try {
				familleRep.save(famille);
				json.put("status",200);
				json.put("message","Les informations ont été mis à jour");
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

	public String deleteFamille(int idfamille) {
		Json json =new Json();
		if(idfamille>0) {
			Famille famille = familleRep.findByIdFamille(idfamille);
			if(famille!=null) {
				try {
					famille.setStatus(0);
					familleRep.save(famille);
					json.put("status",200);
					json.put("message","Les informations ont été supprimer");
					return json.toString();
				} catch (Exception e) {
					json.put("status",400);
					json.put("message","Erreur : "+e.getMessage());
					return json.toString();
				}
			}else {
				json.put("status",400);
				json.put("message","Les informations sont incomplètes.");
				return json.toString();
			}
		}
		json.put("status",400);
		json.put("message","Les informations sont incomplètes.");
		return json.toString();
	}
}
