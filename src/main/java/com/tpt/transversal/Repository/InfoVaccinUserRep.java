package com.tpt.transversal.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tpt.transversal.model.InfoVaccinUser;

@Repository
public interface InfoVaccinUserRep extends JpaRepository<InfoVaccinUser, Integer> {
	
	@Query(nativeQuery=true,value="SELECT * FROM infovaccinuser WHERE status = 1 and idutilisateur = ?1 ORDER BY idinfovaccinuser ASC")
	List<InfoVaccinUser> findByIdUtilisateurAndStatus(int idutilisateur);
	
	@Query(nativeQuery=true,value="select idinfovaccinuser from infovaccinuser where status = 1 and idutilisateur = ?1 and ROWNUM < 2 ORDER BY idinfovaccinuser DESC")
	InfoVaccinUser findByIdUtilisateurAndStatusAndRowNumOne(int idutilisateur);
	
	@Query(nativeQuery=true,value="SELECT * FROM infovaccinuser WHERE idinfovaccinuser = ?1 ")
	InfoVaccinUser findByidInfoVaccinUserAndStatus(int idinfovaccinuser);
	
	@Query(nativeQuery=true,value="select * from infovaccinuser where idutilisateur = ?1 and idvaccincentre = ?2 and idvaccinodrome = ?3 and idvaccin = ?4 and status=1")
	List<InfoVaccinUser> findByIdUtilisateurAndIdVaccinCentreAndIdVaccinodromeAndIdVaccin(int idutilisateur,int idvaccincentre,int idvaccinodrome,int idvaccin);

}
