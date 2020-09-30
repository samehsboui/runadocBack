package com.app.runadoc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.runadoc.model.Etablissement;

@Repository
public interface EtablissementRepository extends JpaRepository<Etablissement, Long> {

}
