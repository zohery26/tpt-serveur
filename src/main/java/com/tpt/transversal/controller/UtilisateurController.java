package com.tpt.transversal.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tpt.transversal.model.DataPatient;
import com.tpt.transversal.model.Docs;
import com.tpt.transversal.model.InfoRdv;
import com.tpt.transversal.model.InfoUtilisateur;
import com.tpt.transversal.model.Utilisateur;
import com.tpt.transversal.service.InfoVaccinUserService;
import com.tpt.transversal.service.UtilisateurService;

@RestController
@RequestMapping(value = "/rdvvaksiny/utilisateur")
@CrossOrigin(origins = {"*"})
@Component
public class UtilisateurController {

	@Autowired
	private UtilisateurService utilisateurService;
	
	@Autowired
	private InfoVaccinUserService infoVaccinUserService;
	
	@GetMapping(value = "/byid/{idutilisateur}")
	public Utilisateur byid(@PathVariable int idutilisateur){
		return utilisateurService.getUtilisateurById(idutilisateur);
	}
	@GetMapping(value = "/info-vaccin-utilisateur/{idutilisateur}")
	public InfoUtilisateur infoVaccinUtilisateur(@PathVariable int idutilisateur){
		return infoVaccinUserService.getInfoVaccinUserByIdUtilisateur(idutilisateur);
	}
	@GetMapping(value = "/info-utilisateur/{iddemanderdv}")
	public InfoRdv infoUtilisateur(@PathVariable int iddemanderdv){
		return infoVaccinUserService.getInfoVaccinUserByIdDemandeRdv(iddemanderdv);
	}
	@PostMapping(value = "/update-patient")
	public String updatePatient(@RequestBody Utilisateur utilisateur) throws Exception{
		return utilisateurService.updatePatient(utilisateur);
	}
	@PostMapping(value = "/login-utilisateur")
	public String loginadmin(@RequestBody HashMap<String,String> data) throws Exception{
		return utilisateurService.loginUtilisateur(data.get("telephone").toString(), data.get("mot_de_passe").toString(),data.get("status").toString());
	}
	@PostMapping(value = "/administrateur-patient")
	public String administrateurPatient(@RequestBody Utilisateur utilisateur) throws Exception{
		return utilisateurService.addAdministrateur(utilisateur);
	}
	@PostMapping(value = "/inscription-patient", consumes={"application/json"})
	public String insertionVaccin(@RequestBody DataPatient DataPatient) throws Exception {
		return utilisateurService.inscriptionPatient(DataPatient);
	}
	@PostMapping(value = "/mot-de-passe-oublie", consumes={"application/json"})
	public String motDePasseOublie(@RequestBody Utilisateur utilisateur) throws Exception {
		return utilisateurService.mdpOublie(utilisateur);
	}
	@GetMapping(value = "/list/{limit}/{page}/{idtypeutilisateur}")
	public Docs<Utilisateur> list(@PathVariable int limit,@PathVariable int page,@PathVariable int idtypeutilisateur){
		return utilisateurService.getPageableUtilisateur(limit,page,idtypeutilisateur);
	}
	@GetMapping(value = "/list-search/{limit}/{page}/{idtypeutilisateur}/{libeller}")
	public Docs<Utilisateur> vaccinviewsearch(@PathVariable int limit,@PathVariable int page,@PathVariable String libeller,@PathVariable int idtypeutilisateur){
		return utilisateurService.getPageableUtilisateurSearch(limit,page,idtypeutilisateur,libeller);
	}
	@GetMapping(value = "/bloquer-utilisateur/{idutilisateur}")
	public String bloquerUtilisateur(@PathVariable int idutilisateur){
		return utilisateurService.bloquerOrRestaurerUtilisateur(idutilisateur,false);
	}
	@GetMapping(value = "/restaurer-utilisateur/{idutilisateur}")
	public String restaurerUtilisateur(@PathVariable int idutilisateur){
		return utilisateurService.bloquerOrRestaurerUtilisateur(idutilisateur,true);
	}
}
