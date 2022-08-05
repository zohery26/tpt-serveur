package com.tpt.transversal.Repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tpt.transversal.model.DemandeRdv;

@Repository
public interface DemandeRdvRep extends JpaRepository<DemandeRdv, Integer> {
	
	@Query(nativeQuery=true,value="select * from demanderdv where idinfovaccinuser = ?1 and daterdv = ?2 and status = 1")
	List<DemandeRdv> findByiDinfoVaccinUserAndDaterdv(int idinfovaccinuser,Date daterdv);
	
	@Query(nativeQuery=true,value="select * from demanderdv where iddemanderdv = ?1")
	DemandeRdv findByIdDemandeRdv(int iddemanderdv);
	
}
