package com.tpt.transversal.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tpt.transversal.model.Utilisateur;

@Repository
public interface UtilisateurRep extends JpaRepository<Utilisateur, Integer>{
	
	@Query(nativeQuery=true,value="SELECT * FROM utilisateur WHERE idutilisateur = ?1 and status=1 ")
	Utilisateur findByIdUtilisateur(int idutilisateur);
	
	@Query(nativeQuery=true,value="SELECT * FROM utilisateur WHERE idutilisateur = ?1 ")
	Utilisateur findByIdUtilisateurSansStatus(int idutilisateur);
	
	@Query(nativeQuery=true,value="SELECT * FROM utilisateur  WHERE telephone LIKE %?1% and status=1 ")
	List<Utilisateur> findByTelephone(String tel);
	
	@Query(nativeQuery=true,value="SELECT * FROM utilisateur WHERE lower(email) LIKE %?1% and telephone LIKE %?2% and status=1 ")
	List<Utilisateur> findByEmailAndTelephone(String email,String telephone);
	
	@Query(nativeQuery=true,value="SELECT * FROM utilisateur WHERE idtypeutilisateur=?1 and ( status = 1 or status = 11) ")
	Page<Utilisateur> findByStatusSort(int idtypeutilisateur,Pageable pages);
	
	@Query(nativeQuery=true,value="SELECT * FROM utilisateur WHERE ( status = 1 or status = 11) ")
	Page<Utilisateur> findByStatusAndNoIdTypeUtilisateurSort(Pageable pages);
	
	@Query(nativeQuery=true,value="SELECT * FROM utilisateur WHERE lower(nom) LIKE %?1% and idtypeutilisateur=?2 and ( status = 1 or status = 11) ")
	Page<Utilisateur> findByNomUtilisateurSort(String nom,int idtypeutilisateur,Pageable pages);
	
	@Query(nativeQuery=true,value="SELECT * FROM utilisateur WHERE lower(nom) LIKE %?1% and ( status = 1 or status = 11) ")
	Page<Utilisateur> findByNomUtilisateurAndNoIdTypeUtilisateurSort(String nom,Pageable pages);
	
}
