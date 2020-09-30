package com.app.runadoc.controller;

import java.util.Base64;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.runadoc.dto.request.auth.LoginRequest;
import com.app.runadoc.dto.request.auth.SignUpRequest;
import com.app.runadoc.dto.response.JwtResponse;
import com.app.runadoc.dto.response.ResponseMessage;
import com.app.runadoc.enumeration.RoleName;
import com.app.runadoc.model.Role;
import com.app.runadoc.model.User;
import com.app.runadoc.repository.RoleRepository;
import com.app.runadoc.repository.UserRepository;
import com.app.runadoc.security.jwt.JwtProvider;
import com.app.runadoc.service.UserService;
import com.app.runadoc.utils.StringsConstants;

@CrossOrigin(origins = StringsConstants.FRONT_BASE_URL, maxAge = 3600)
@RestController
@RequestMapping(StringsConstants.AUTH_URL)
public class AuthentificationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private JwtProvider jwtProvider;

	@PostMapping(value = "/signin", produces = StringsConstants.APPLICATION_JSON)
	public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		return autoLogin(loginRequest);
	}

	@PostMapping(value = "/signup", produces = StringsConstants.APPLICATION_JSON)
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return new ResponseEntity<>(new ResponseMessage(StringsConstants.USERNAME_TAKEN), HttpStatus.BAD_REQUEST);
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return new ResponseEntity<>(new ResponseMessage(StringsConstants.EMAIL_TAKEN), HttpStatus.BAD_REQUEST);
		}
		// Creating user's account
		StringBuilder stringBufferFullName = new StringBuilder();
		stringBufferFullName.append(signUpRequest.getFirstName());
		stringBufferFullName.append(" ");
		stringBufferFullName.append(signUpRequest.getLastName());
		String fullName = stringBufferFullName.toString();
		User user = new User(fullName, signUpRequest.getUsername(), signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()), new Date(), new Date());

		Set<Role> roles = new HashSet<>();
		roles.add(roleRepository.findByName(RoleName.valueOf("ROLE_USER")).get());
		user.setRoles(roles);
		userService.save(user);

		return autoLogin(new LoginRequest(signUpRequest.getUsername(), signUpRequest.getPassword()));
	}

	private ResponseEntity<JwtResponse> autoLogin(LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = jwtProvider.generateJwtToken(authentication);
		Optional<User> user = userRepository.findByUsername(loginRequest.getUsername());
		if (!user.isPresent()) {
			return new ResponseEntity<>(new JwtResponse(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		String image = null;

		if (user.get().getImage() != null) {
			image = Base64.getEncoder().encodeToString(user.get().getImage());
		}

		JwtResponse jwtResponse = new JwtResponse(jwt, user.get().getEmail(), user.get().getFullName(), image);

		return new ResponseEntity<>(jwtResponse, HttpStatus.OK);
	}

}