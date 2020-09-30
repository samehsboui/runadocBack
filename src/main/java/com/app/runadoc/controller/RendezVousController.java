package com.app.runadoc.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.runadoc.dto.request.CreateRendezVousRequest;
import com.app.runadoc.dto.response.ResponseMessage;
import com.app.runadoc.model.Etablissement;
import com.app.runadoc.model.RendezVous;
import com.app.runadoc.model.User;
import com.app.runadoc.repository.RendezVousRepository;
import com.app.runadoc.repository.EtablissementRepository;
import com.app.runadoc.service.RendezVousService;
import com.app.runadoc.service.UserService;
import com.app.runadoc.utils.StringsConstants;

import io.swagger.annotations.Api;

@CrossOrigin(origins = StringsConstants.FRONT_BASE_URL, maxAge = 3600)
@Api("Reservation Controller")
@RestController
@RequestMapping(StringsConstants.RESERVATION_URL)
public class RendezVousController {

	@Autowired
	private RendezVousService rendezVousService;

	@PostMapping(value = "/create")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<?> createRendezVous(@Valid @RequestBody CreateRendezVousRequest createRendezVousRequest)
			throws ParseException, JAXBException, IOException {

		return rendezVousService.createRendezVous(createRendezVousRequest);

	}

	@GetMapping(value = "/my")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<List<RendezVous>> getMyRendezVous() {
		return rendezVousService.getMyRendezVous();
	}

	@DeleteMapping(value = "/delete/{id}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<ResponseMessage> deleteRendezVous(@PathVariable Long id) {

		return rendezVousService.deleteRendezVous(id);
	}

}
