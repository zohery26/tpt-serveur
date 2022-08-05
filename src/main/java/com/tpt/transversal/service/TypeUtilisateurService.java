package com.tpt.transversal.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tpt.transversal.Repository.TypeUtilisateurRep;
import com.tpt.transversal.model.TypeUtilisateur;

@Service
public class TypeUtilisateurService {
	
	@Autowired
	private TypeUtilisateurRep rep;
	
	public List<TypeUtilisateur> getAll() {
		try {
			return rep.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<TypeUtilisateur>();
		}
		
	}
}
