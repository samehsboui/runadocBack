package com.app.runadoc.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

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

@Service
public class RendezVousServiceImpl implements RendezVousService {

	@Autowired
	private RendezVousRepository rendezVousRepository;

	@Autowired
	private EtablissementRepository etablissementRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private RendezVousService rendezVousService;

	@Override
	public List<RendezVous> getMyRendezVous(Long id) {
		return rendezVousRepository.getMyRendezVous(id);
	}

	@Override
	public void create(Etablissement etablissement, User user, RendezVous rendezVous) {
		rendezVous.setUser(user);
		rendezVous.setEtablissement(etablissement);
		rendezVousRepository.save(rendezVous);

	}

	@Override
	public ResponseEntity<ResponseMessage> deleteRendezVous(Long id) {
		Optional<RendezVous> rendezVous = rendezVousRepository.findById(id);
		if (!rendezVous.isPresent()) {
			return new ResponseEntity<>(new ResponseMessage("rendezVous doesn't exist"), HttpStatus.NOT_FOUND);
		} else {
			try {
				rendezVousRepository.delete(rendezVous.get());
				return new ResponseEntity<>(new ResponseMessage("rendezVous deleted successfully"), HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(new ResponseMessage("Error while deleting rendezVous"),
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}

	@Override
	public ResponseEntity<List<RendezVous>> getMyRendezVous() {
		Optional<User> user = userService
				.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		List<RendezVous> rendezVouss = new ArrayList<>();
		if (!user.isPresent()) {
			return new ResponseEntity<>(rendezVouss, HttpStatus.NOT_FOUND);
		}
		rendezVouss = rendezVousService.getMyRendezVous(user.get().getId());
		rendezVouss.forEach(rendezVous -> {
			rendezVous.getEtablissement().setRendezVous(null);
		});
		return new ResponseEntity<>(rendezVouss, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> createRendezVous(CreateRendezVousRequest createRendezVousRequest)
			throws ParseException {
		Optional<User> user = userService
				.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		SimpleDateFormat format = new SimpleDateFormat("HH:mm");
		Date date1 = format.parse(createRendezVousRequest.getHeureStart());
		Date date2 = format.parse(createRendezVousRequest.getHeureEnd());
		long difference = date2.getTime() - date1.getTime();
		int hours = (int) ((difference / (1000 * 60 * 60)) % 24);

		if (hours < 1 || date1.getTime() >= date2.getTime()) {
			return new ResponseEntity<>(
					"Le créneau minimum est d’une heure et la date début doit être inférieur à la date fin",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (!user.isPresent()) {
			return new ResponseEntity<>(new ResponseMessage(StringsConstants.USER_NOT_EXIST), HttpStatus.NOT_FOUND);
		}

		/*
		 * if ("Extérieur".equals(createReservationRequest.getTerrain().getType())) {
		 * YahooWeatherService service = new YahooWeatherService(); Channel channel =
		 * service.getForecast("2502265", DegreeUnit.CELSIUS);
		 * System.out.println(channel.getDescription()); }
		 */
		try {
			RendezVous reservation = new RendezVous(createRendezVousRequest.getPrice(),
					createRendezVousRequest.getHeureStart(), createRendezVousRequest.getHeureEnd(),
					createRendezVousRequest.getDateStart(), user.get(), createRendezVousRequest.getEtablissement());
			double price = 0;
			price = (8 - 8 * 0.05) * (hours - 1) + 8;
			reservation.setPrice(price);
			rendezVousService.create(createRendezVousRequest.getEtablissement(), user.get(), reservation);
			Etablissement etablissement = createRendezVousRequest.getEtablissement();
			List<RendezVous> foundedRendezVous = rendezVousRepository.findRendezVousByEtablissementBy(etablissement.getId());
			etablissement.setNbrePersonneReserve(foundedRendezVous.size());
			if (etablissement.getNbrePersonneReserve() > 8) {
				etablissement.setDisponible(false);
			}
			etablissementRepository.save(etablissement);
			return new ResponseEntity<>(new ResponseMessage("RendezVous added successfully"), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new ResponseMessage("Error during creating RendezVous"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
