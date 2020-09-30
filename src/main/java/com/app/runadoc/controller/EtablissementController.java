package com.app.runadoc.controller;

import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.runadoc.model.Etablissement;
import com.app.runadoc.model.RendezVous;
import com.app.runadoc.repository.RendezVousRepository;
import com.app.runadoc.repository.EtablissementRepository;
import com.app.runadoc.service.EtablissementService;
import com.app.runadoc.utils.StringsConstants;

import io.swagger.annotations.Api;

@CrossOrigin(origins = StringsConstants.FRONT_BASE_URL, maxAge = 3600)
@Api("Terrain Controller")
@RestController
@RequestMapping(StringsConstants.TERRAIN_URL)
public class EtablissementController {

	@Autowired
	private EtablissementRepository etablissementRepository;

	@Autowired
	private EtablissementService etablissementService;

	@GetMapping(value = "/all")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<List<Etablissement>> getAll() throws JAXBException, IOException {
		return etablissementService.getAllUsers();
	}

	@GetMapping(value = "/{id}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Etablissement> getEtablissementById(@PathVariable Long id) {
		return etablissementService.getEtablissementById(id);
	}

}
