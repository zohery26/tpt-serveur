package com.tpt.transversal.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tpt.transversal.model.HoraireVaccinodrome;


@Repository
public interface HoraireVaccinodromeRep extends JpaRepository<HoraireVaccinodrome, Integer>  {
	
	@Query(nativeQuery=true,value="SELECT * FROM horairevaccinodrome WHERE idvaccinodrome=?1 and status = 1 ")
	List<HoraireVaccinodrome> findByIdVaccinodrome(int idvaccinodrome);
	
	@Query(nativeQuery=true,value="SELECT * FROM horairevaccinodrome WHERE idvaccinodrome=?1")
	List<HoraireVaccinodrome> findByIdVaccinodromeSansStatus(int idvaccinodrome);
}
