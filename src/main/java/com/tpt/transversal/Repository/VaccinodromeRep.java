package com.tpt.transversal.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tpt.transversal.model.Vaccinodrome;

@Repository
public interface VaccinodromeRep extends JpaRepository<Vaccinodrome, Integer> {
	
	@Query(nativeQuery=true,value="SELECT * FROM vaccinodrome  WHERE email LIKE ?1 and status=1 ")
	List<Vaccinodrome> findByEmail(String email);
	
	@Query(nativeQuery=true,value="SELECT * FROM vaccinodrome  WHERE idvaccinodrome = ?1 ")
	Vaccinodrome findByIdVaccinodrome(int idvaccinodrome);
	
	@Query(nativeQuery=true,value="SELECT * FROM vaccinodrome WHERE status = ?1 ")
	Page<Vaccinodrome> findByStatusSort(int status,Pageable pages);
	
	@Query(nativeQuery=true,value="SELECT * FROM vaccinodrome WHERE ( status = 1 or status = 11) ")
	Page<Vaccinodrome> findByNoStatusSort(Pageable pages);
	
	@Query(nativeQuery=true,value="SELECT * FROM vaccinodrome WHERE lower(nom_centre) LIKE %?1% and status = ?2 ")
	Page<Vaccinodrome> findByNomCentreAndStatusSort(String nomCentre,int status,Pageable pages);
	
	@Query(nativeQuery=true,value="SELECT * FROM vaccinodrome WHERE lower(nom_centre) LIKE %?1% and ( status = 1 or status = 11) ")
	Page<Vaccinodrome> findByNomCentreAndNoStatusSort(String nom,Pageable pages);
	
}
