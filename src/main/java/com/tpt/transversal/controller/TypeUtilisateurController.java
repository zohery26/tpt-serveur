package com.tpt.transversal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tpt.transversal.model.TypeUtilisateur;
import com.tpt.transversal.service.TypeUtilisateurService;

@RestController
@RequestMapping(value = "/test")
@CrossOrigin(origins = {"*"})
@Component
public class TypeUtilisateurController {
	
	@Autowired
	TypeUtilisateurService typeUtilisateurService;
	
	
	@GetMapping(value = "/list")
	public List<TypeUtilisateur> liste(){
		return typeUtilisateurService.getAll();
	}
}
