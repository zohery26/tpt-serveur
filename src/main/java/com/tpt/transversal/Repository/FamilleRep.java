package com.tpt.transversal.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tpt.transversal.model.Famille;

@Repository
public interface FamilleRep extends JpaRepository<Famille, Integer> {

	@Query(nativeQuery=true,value="SELECT * FROM famille WHERE idutilisateur=?1 and lower(nom) LIKE %?2% and status = 1 ")
	List<Famille> findByIdUtilisateurAndNom(int idutilisateur, String nom);
	
	@Query(nativeQuery=true,value="SELECT * FROM famille WHERE idutilisateur=?1 and status = 1 ")
	List<Famille> findByIdUtilisateur(int idutilisateur);
	
	@Query(nativeQuery=true,value="select * from famille where idfamille in (select idfamille from rdvfamille where iddemanderdv = ?1)")
	List<Famille> findByIdDemandeRdv(int iddemanderdv);
	
	@Query(nativeQuery=true,value="SELECT * FROM famille WHERE status = 1 ")
	Page<Famille> findByStatusSort(Pageable pages);
	
	@Query(nativeQuery=true,value="SELECT * FROM famille WHERE idfamille = ?1 and status = 1 ")
	Famille findByIdFamille(int idfamille);
}
