package com.tpt.transversal.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tpt.transversal.model.DataHoraire;
import com.tpt.transversal.model.Docs;
import com.tpt.transversal.model.HoraireVaccinodrome;
import com.tpt.transversal.model.Vaccinodrome;
import com.tpt.transversal.model.VaccinodromeAndHoraire;
import com.tpt.transversal.service.HoraireVaccinodromeService;
import com.tpt.transversal.service.VaccinodromeService;

@RestController
@RequestMapping(value = "/rdvvaksiny/vaccinodrome")
@CrossOrigin(origins = {"*"})
@Component
public class VaccinodromeController {
	
	@Autowired
	private VaccinodromeService vaccinodromeService;
	
	@Autowired
	private HoraireVaccinodromeService horaireVaccinodromeService;
	
	@PostMapping(value = "/inscription-vaccinodrome", consumes={"application/json"})
	public String inscriptionVaccinodrome(@RequestBody VaccinodromeAndHoraire vaccinodromeAndHoraire) throws Exception {
		return vaccinodromeService.inscriptionVaccinodrome(vaccinodromeAndHoraire);
	}
	@PostMapping(value = "/update-vaccinodrome", consumes={"application/json"})
	public String updateVaccinodrome(@RequestBody Vaccinodrome vaccinodrome) throws Exception {
		return vaccinodromeService.updateVaccinodrome(vaccinodrome);
	}
	@PostMapping(value = "/update-horaire-vaccinodrome", consumes={"application/json"})
	public String updateHHoraireVaccinodrome(@RequestBody DataHoraire dataHoraire) throws Exception {
		return horaireVaccinodromeService.updateHoraireVaccinodrome(dataHoraire);
	}
	@PostMapping(value = "/login-vaccinodrome")
	public String loginVaccinodrome(@RequestBody HashMap<String,String> data) throws Exception{
		return vaccinodromeService.loginVaccinodrome(data.get("email").toString(),data.get("mdp").toString(),data.get("status").toString());
	}
	@PostMapping(value = "/mot-de-passe-oublie")
	public String motDePasseOublie(@RequestBody Vaccinodrome vaccinodrome) throws Exception{
		return vaccinodromeService.motDePasseOublie(vaccinodrome);
	}
	@GetMapping(value = "/horaire-vaccinodrome/{idvaccinodrome}")
	public List<HoraireVaccinodrome> horaireVaccinodrome(@PathVariable int idvaccinodrome){
		return horaireVaccinodromeService.getHoraireVaccinodromeByIdVaccinodrome(idvaccinodrome);
	}
	@GetMapping(value = "/vaccinodrome-by-id/{idvaccinodrome}")
	public Vaccinodrome vaccinodromeById(@PathVariable int idvaccinodrome){
		return vaccinodromeService.getVaccinodromeByIdVaccinodrome(idvaccinodrome);
	}
	@GetMapping(value = "/list/{limit}/{page}/{status}")
	public Docs<Vaccinodrome> list(@PathVariable int limit,@PathVariable int page,@PathVariable int status){
		return vaccinodromeService.getPageableVaccinodrome(limit,page,status);
	}
	@GetMapping(value = "/list-search/{limit}/{page}/{status}/{libeller}")
	public Docs<Vaccinodrome> vaccinviewsearch(@PathVariable int limit,@PathVariable int page,@PathVariable String libeller,@PathVariable int status){
		return vaccinodromeService.getPageableUtilisateurSearch(limit,page,status,libeller);
	}
	@GetMapping(value = "/bloquer-vaccinodrome/{idvaccinodrome}")
	public String bloquerVaccinodrome(@PathVariable int idvaccinodrome){
		return vaccinodromeService.bloquerOrRestaurerVaccinodrome(idvaccinodrome,false);
	}
	@GetMapping(value = "/restaurer-vaccinodrome/{idvaccinodrome}")
	public String restaurerVaccinodrome(@PathVariable int idvaccinodrome){
		return vaccinodromeService.bloquerOrRestaurerVaccinodrome(idvaccinodrome,true);
	}
}
