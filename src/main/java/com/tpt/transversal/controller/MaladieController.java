package com.tpt.transversal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tpt.transversal.autre.Utile;
import com.tpt.transversal.model.Docs;
import com.tpt.transversal.model.Maladie;
import com.tpt.transversal.service.MaladieService;

@RestController
@RequestMapping(value = "/rdvvaksiny/maladie")
@CrossOrigin(origins = {"*"})
@Component
public class MaladieController {
	
	@Autowired
	MaladieService maladieService; 
	
	Utile utile = new Utile();
	
	@PostMapping(value = "/insertion-maladie", consumes={"application/json"})
	public String insertionMaladie(@RequestBody Maladie maladie) throws Exception {
		return maladieService.ajouterUneNouvelleMaladie(maladie);
	}
	@GetMapping(value = "/list/{limit}/{page}")
	public Docs<Maladie> liste(@PathVariable int limit,@PathVariable int page){
		return maladieService.getPageableMaladie(limit,page);
	}
	@GetMapping(value = "/search/{limit}/{page}/{libeller}")
	public Docs<Maladie> search(@PathVariable int limit,@PathVariable int page,@PathVariable String libeller){
		return maladieService.getPageableMaladieSearch(limit,page,libeller);
	}
	@GetMapping(value = "/listbyidvaccin/{idvaccin}/{limit}/{page}")
	public Docs<Maladie> listbyidvaccin(@PathVariable int idvaccin,@PathVariable int limit,@PathVariable int page){
		return maladieService.getPageableMaladieByIdVaccin(idvaccin,limit,page);
	}
	@GetMapping(value = "/delete-maladie/{idmaladie}")
	public String delete(@PathVariable int idmaladie){
		return maladieService.supprimerMaladie(idmaladie);
	}
	@GetMapping(value = "/avertissement-maladie/{idvaccin}")
	public String avertissementMaladie(@PathVariable int idvaccin){
		return maladieService.generaterWarningInInscriptionPatientByMaladie(idvaccin);
	}
	@GetMapping(value = "/allavertissement-maladie/{idvaccin}")
	public String allavertissementMaladie(@PathVariable int idvaccin){
		return maladieService.generatertextMaladieByMaladie(idvaccin);
	}
}
