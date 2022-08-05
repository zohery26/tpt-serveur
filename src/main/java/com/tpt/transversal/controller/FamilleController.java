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

import com.tpt.transversal.model.Docs;
import com.tpt.transversal.model.Famille;
import com.tpt.transversal.service.FamilleService;

@RestController
@RequestMapping(value = "/rdvvaksiny/famille")
@CrossOrigin(origins = {"*"})
@Component
public class FamilleController {
	
	@Autowired
	FamilleService familleService;
	
	@PostMapping(value = "/insertion-famille", consumes={"application/json"})
	public String insertionFamille(@RequestBody Famille famille) throws Exception {
		return familleService.insertionFamille(famille);
	}
	@PostMapping(value = "/update", consumes={"application/json"})
	public String update(@RequestBody Famille famille) throws Exception {
		return familleService.updateFamille(famille);
	}
	@GetMapping(value = "/delete/{idfamille}")
	public String delete(@PathVariable int idfamille){
		return familleService.deleteFamille(idfamille);
	}
	@GetMapping(value = "/list/{limit}/{page}")
	public Docs<Famille> list(@PathVariable int limit,@PathVariable int page){
		return familleService.getPageableFamille(limit,page);
	}
	
}
