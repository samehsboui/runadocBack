package com.app.runadoc.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.app.runadoc.model.Etablissement;

public interface EtablissementService {

	public ResponseEntity<List<Etablissement>> getAllUsers();

	public ResponseEntity<Etablissement> getEtablissementById(Long id);

}