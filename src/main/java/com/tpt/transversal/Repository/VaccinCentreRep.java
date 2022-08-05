package com.tpt.transversal.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tpt.transversal.model.VaccinCentre;

@Repository
public interface VaccinCentreRep extends JpaRepository<VaccinCentre, Integer> {
	
	@Query(nativeQuery=true,value="SELECT * FROM vaccincentre WHERE idvaccinodrome=?1 and idvaccin=?2")
	List<VaccinCentre> findByIdvaccinodromeAndIdvaccin(int idvaccinodrome,int idvaccin);
	
	@Query(nativeQuery=true,value="SELECT * FROM vaccincentre WHERE idvaccincentre=?1")
	VaccinCentre findByIdVaccinCentre(int idvaccincentre);
	
}
