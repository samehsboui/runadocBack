package com.app.runadoc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.runadoc.model.RendezVous;

@Repository
public interface RendezVousRepository extends JpaRepository<RendezVous, Long> {

	@Query(value = "SELECT * FROM rendezvous WHERE user_id = :userId", nativeQuery = true)
	List<RendezVous> getMyRendezVous(@Param("userId") Long userId);

	@Query(value = "SELECT * FROM rendezvous WHERE etablissement_id = :etablissementId", nativeQuery = true)
	List<RendezVous> findRendezVousByEtablissementBy(@Param("etablissementId") Long etablissementId);
}
