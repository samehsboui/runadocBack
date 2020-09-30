package com.app.runadoc.service;

import java.text.ParseException;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.app.runadoc.dto.request.CreateRendezVousRequest;
import com.app.runadoc.dto.response.ResponseMessage;
import com.app.runadoc.model.Etablissement;
import com.app.runadoc.model.RendezVous;
import com.app.runadoc.model.User;

public interface RendezVousService {

	public List<RendezVous> getMyRendezVous(Long id);

	public ResponseEntity<ResponseMessage> deleteRendezVous(Long id);

	public ResponseEntity<List<RendezVous>> getMyRendezVous();

	public ResponseEntity<?> createRendezVous(CreateRendezVousRequest createRendezVousRequest) throws ParseException;

	public void create(Etablissement etablissement, User user, RendezVous rendezVous);

}
