package com.app.runadoc.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.runadoc.dto.response.JwtResponse;
import com.app.runadoc.dto.response.UserResponse;
import com.app.runadoc.model.User;
import com.app.runadoc.service.UserService;
import com.app.runadoc.utils.StringsConstants;

@CrossOrigin(origins = StringsConstants.FRONT_BASE_URL, maxAge = 3600)
@RestController
@RequestMapping(StringsConstants.USER_URL)
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping(value = "/getUserInfo")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<JwtResponse> getUserInfo(@RequestHeader(name = "Authorization") String token) {
		Optional<User> user = userService
				.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		JwtResponse jwtResponse = userService.getUserInfo(token, user);

		return ResponseEntity.ok(jwtResponse);
	}

	@GetMapping(value = "/all")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<List<UserResponse>> getAll(@RequestHeader(name = "Authorization") String token) {
		Optional<User> user = userService
				.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		List<User> users = userService.findAllWithoutConnectedUser(user.get().getId());
		List<UserResponse> userResponses = new ArrayList<>();
		users.forEach(u -> {
			UserResponse userResponse = new UserResponse(u.getId(), u.getFullName());
			userResponses.add(userResponse);
		});
		return ResponseEntity.ok(userResponses);
	}

}
