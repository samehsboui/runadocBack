package com.app.runadoc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.app.runadoc.model.Etablissement;
import com.app.runadoc.model.RendezVous;
import com.app.runadoc.repository.RendezVousRepository;
import com.app.runadoc.repository.EtablissementRepository;
import com.app.runadoc.service.EtablissementService;

@Service
public class EtablissementServiceImpl implements EtablissementService {

	@Autowired
	private EtablissementRepository etablissementRepository;

	@Autowired
	private RendezVousRepository rendezVousRepository;

	@Override
	public ResponseEntity<List<Etablissement>> getAllUsers() {
		List<Etablissement> etablissements = etablissementRepository.findAll();
		etablissements.forEach(etablissement -> {
			etablissement.setRendezVous(null);
			List<RendezVous> foundedReservations = rendezVousRepository.findRendezVousByEtablissementBy(etablissement.getId());
			etablissement.setNbrePersonneReserve(foundedReservations.size());
		});

		return ResponseEntity.ok(etablissements);
	}

	@Override
	public ResponseEntity<Etablissement> getEtablissementById(Long id) {
		return new ResponseEntity<>(etablissementRepository.findById(id).get(), HttpStatus.OK);
	}
}
