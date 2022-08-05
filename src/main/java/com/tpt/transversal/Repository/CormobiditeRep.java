package com.tpt.transversal.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tpt.transversal.model.Cormobidite;

@Repository
public interface CormobiditeRep extends JpaRepository<Cormobidite, Integer>{

}
