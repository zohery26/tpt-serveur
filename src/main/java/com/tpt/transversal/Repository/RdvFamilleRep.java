package com.tpt.transversal.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tpt.transversal.model.RdvFamille;

@Repository
public interface RdvFamilleRep extends JpaRepository<RdvFamille, Integer> {
	
	@Query(nativeQuery=true,value="select * from rdvfamille where iddemanderdv = ?1")
	List<RdvFamille> findByIdDemandeRdv(int iddemanderdv);
}
