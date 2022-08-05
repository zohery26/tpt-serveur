package com.tpt.transversal.controller;

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

import com.tpt.transversal.model.DataDemandeRdv;
import com.tpt.transversal.model.DataRdvSearch;
import com.tpt.transversal.model.DataVaccination;
import com.tpt.transversal.model.DemandeRdvView;
import com.tpt.transversal.model.Docs;
import com.tpt.transversal.model.Prescription;
import com.tpt.transversal.service.DemandeRdvService;
import com.tpt.transversal.service.DemandeRdvViewService;
import com.tpt.transversal.service.RdvFamilleService;

@RestController
@RequestMapping(value = "/rdvvaksiny/demanderdv")
@CrossOrigin(origins = {"*"})
@Component
public class DemandeRdvController {
	
	@Autowired
	DemandeRdvService demandeRdvService;
	@Autowired
	RdvFamilleService rdvFamilleService;
	@Autowired
	DemandeRdvViewService demandeRdvViewService;
	
	@PostMapping(value = "/demande-rdv-patient")
	public String updatePatient(@RequestBody DataDemandeRdv dataDemandeRdv) throws Exception{
		return demandeRdvService.deamndeDeRdv(dataDemandeRdv);
	}
	@PostMapping(value = "/patient-vacciner")
	public String patientVacciner(@RequestBody DataVaccination dataVaccination) throws Exception{
		return demandeRdvService.vaccinationPatient(dataVaccination);
	}
	@PostMapping(value = "/searchlist-demande-rdv")
	public Docs<DemandeRdvView> searchlistDemandeRdv(@RequestBody DataRdvSearch dataRdvSearch) throws Exception{
		return demandeRdvViewService.searchlistDemandeRdv(dataRdvSearch);
	}
	@PostMapping(value = "/search-list-demande-rdv-vaccinodrome")
	public Docs<DemandeRdvView> searchListDemandeRdvVaccinodrome(@RequestBody DataRdvSearch dataRdvSearch) throws Exception{
		return demandeRdvViewService.searchlistDemandeRdvVaccinodrome(dataRdvSearch);
	}
	@GetMapping(value = "/list-demande-rdv/{limit}/{page}/{idutilisateur}/{status}/{ordre}")
	public Docs<DemandeRdvView> listeview(@PathVariable int limit,@PathVariable int page,@PathVariable int idutilisateur,@PathVariable int status,@PathVariable boolean ordre){
		return demandeRdvViewService.getPageableDemandeRdvView(limit, page, idutilisateur, status,ordre);
	}
	@GetMapping(value = "/list-demande-rdv-vaccinodrome/{limit}/{page}/{idvaccinodrome}/{status}/{ordre}")
	public Docs<DemandeRdvView> listeviewVaccinodrome(@PathVariable int limit,@PathVariable int page,@PathVariable int idvaccinodrome,@PathVariable int status,@PathVariable boolean ordre){
		return demandeRdvViewService.getPageableDemandeRdvViewVaccinodrome(limit, page, idvaccinodrome, status,ordre);
	}
	@GetMapping(value = "/delete-demande-rdv-status-one/{iddemanderdv}")
	public String deleteRdv(@PathVariable int iddemanderdv){
		return demandeRdvViewService.deleteRdvStatusOne(iddemanderdv,1);
	}
	@GetMapping(value = "/status-demande-rdv/{iddemanderdv}/{status}")
	public String statusDemandeRdv(@PathVariable int iddemanderdv,@PathVariable int status){
		return demandeRdvViewService.statusDemandeRdv(iddemanderdv,status);
	}
	@GetMapping(value = "/delete-demande-rdv-status-two/{iddemanderdv}")
	public String deleteRdvTwo(@PathVariable int iddemanderdv){
		return demandeRdvViewService.deleteRdvStatusOne(iddemanderdv,2);
	}
	@GetMapping(value = "/restauration-demande-rdv/{iddemanderdv}")
	public String restaurationDemandeRdv(@PathVariable int iddemanderdv){
		return demandeRdvViewService.deleteRdvStatusOne(iddemanderdv,3);
	}
	@GetMapping(value = "/prescription/{iddemanderdv}")
	public List<Prescription> prescriptionById(@PathVariable int iddemanderdv){
		return demandeRdvService.getPrescription(iddemanderdv);
	}
}
