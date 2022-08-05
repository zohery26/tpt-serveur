package com.tpt.transversal.Repository;

import java.sql.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tpt.transversal.model.DemandeRdvView;

@Repository
public interface DemandeRdvViewRep extends JpaRepository<DemandeRdvView, Integer> {
	
	@Query(nativeQuery=true,value="select * from demanderdvview where idutilisateur=?1 and statusrdv = ?2")
	Page<DemandeRdvView> findByIdUtilisateurAndStatusRdvSort(int idutilisateur,int statusrdv,Pageable pages);
	
	@Query(nativeQuery=true,value="select * from demanderdvview where  lower(nomvaccinodrome) LIKE %?1% and statusrdv = ?2 and idutilisateur=?3")
	Page<DemandeRdvView> findByNomVaccinodromeAnStatusRdv(String nomvaccinodrome,int status,int idutilisateur,Pageable pages);
	
	@Query(nativeQuery=true,value="select * from demanderdvview where  lower(nom_vaccin) LIKE %?1% and statusrdv = ?2 and idutilisateur=?3")
	Page<DemandeRdvView> findByNomVaccinAndStatusRdv(String nom_vaccin,int status,int idutilisateur,Pageable pages);
	
	@Query(nativeQuery=true,value="select * from demanderdvview where  daterdv = ?1 and statusrdv = ?2 and idutilisateur=?3")
	Page<DemandeRdvView> findByDateRdvAndStatusRdv(Date daterdv,int status,int idutilisateur,Pageable pages);

	@Query(nativeQuery=true,value="select * from demanderdvview where idvaccinodrome=?1 and statusrdv = ?2")
	Page<DemandeRdvView> findByIdVaccinodromeAndStatusRdvSort(int idvaccinodrome,int statusrdv,Pageable pages);

	@Query(nativeQuery=true,value="select * from demanderdvview where  daterdv = ?1 and statusrdv = ?2 and idvaccinodrome=?3")
	Page<DemandeRdvView> findByIdVaccinodromeAndDateRdvAndStatusRdv(Date daterdv,int status,int idvaccinodrome,Pageable pages);

	@Query(nativeQuery=true,value="select * from demanderdvview where  lower(nom_vaccin) LIKE %?1% and statusrdv = ?2 and idvaccinodrome=?3")
	Page<DemandeRdvView> findByIdVaccinodromeAndNomVaccinAndStatusRdv(String nom_vaccin,int status,int idvaccinodrome,Pageable pages);

	@Query(nativeQuery=true,value="select * from demanderdvview where  lower(nompatient) LIKE %?1% and statusrdv = ?2 and idvaccinodrome=?3")
	Page<DemandeRdvView> findByIdVaccinodromeAndNomPatientAnStatusRdv(String nompatient,int status,int idvaccinodrome,Pageable pages);
}
