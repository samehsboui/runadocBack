package com.app.runadoc.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.runadoc.dto.response.JwtResponse;
import com.app.runadoc.dto.response.ResponseMessage;
import com.app.runadoc.dto.response.UserDto;
import com.app.runadoc.dto.response.UserResponse;
import com.app.runadoc.model.User;
import com.app.runadoc.repository.UserRepository;
import com.app.runadoc.service.UserService;
import com.app.runadoc.utils.StringsConstants;

@CrossOrigin(origins = StringsConstants.FRONT_BASE_URL, maxAge = 3600)
@RestController
@RequestMapping(StringsConstants.USER_URL)
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;

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
	
	@GetMapping(value="/allUsers")
		public List<User> find(){
		
			List<User> users = userRepository.findAll();
			return users;
//			List<UserDto> res= new ArrayList<>();
//			users.forEach(user -> {
//				StringBuilder sbString = new StringBuilder("");
//		        user.getEtablissements().forEach(etb -> {
//		        	sbString.append(etb.getName());
//		        });		            
//				UserDto userDto = new UserDto(user.getFullName(), user.getEmail(), user.getAddress(), 
//						user.getSpecialty(), user.getTel(), user.getRoles(), sbString.toString(), user.getRendezvous());
//				res.add(userDto);
//			});
//			return res;
		} 

	@DeleteMapping(value = "/delete/{id}")
	@PreAuthorize("hasRole('USER')")
	public Boolean deleteRendezVous(@PathVariable Long id) {
		try {
			userRepository.deleteById(id);
			return true;
			
		} catch (Exception e) {
			return false;
		}
	}
}
