package com.tpt.transversal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tpt.transversal.model.InfoVaccinUser;
import com.tpt.transversal.service.InfoVaccinUserService;

@RestController
@RequestMapping(value = "/rdvvaksiny/infovaccinuser")
@CrossOrigin(origins = {"*"})
@Component
public class InfoVaccinUserController {

	@Autowired
	InfoVaccinUserService infoVaccinUserService;
	
	@PostMapping(value = "/insertion-infovaccinuser", consumes={"application/json"})
	public String insertionInfovaccinuser(@RequestBody InfoVaccinUser infoVaccinUser) throws Exception {
		return infoVaccinUserService.insertionInfoVaccinUser(infoVaccinUser);
	}
}
