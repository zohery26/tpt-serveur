package com.tpt.transversal.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tpt.transversal.model.Prescription;

@Repository
public interface PrescriptionRep extends JpaRepository<Prescription, Integer> {

	@Query(nativeQuery=true,value="select * from prescription where iddemanderdv = ?1 ")
	List<Prescription> findByIdDemandeRdv(int iddemanderdv);
}
