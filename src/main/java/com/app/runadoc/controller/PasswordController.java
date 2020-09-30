package com.app.runadoc.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.runadoc.dto.request.auth.PasswordUpdateRequest;
import com.app.runadoc.dto.response.ResponseMessage;
import com.app.runadoc.model.User;
import com.app.runadoc.service.UserService;
import com.app.runadoc.utils.StringsConstants;

import io.swagger.annotations.Api;

@CrossOrigin(origins = StringsConstants.FRONT_BASE_URL, maxAge = 3600)
@Api("Password Controller")
@RestController
@RequestMapping(StringsConstants.PASSWORD_URL)
public class PasswordController {

	@Autowired
	private UserService userService;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@PostMapping(value = "/update", produces = "application/json")
	@PreAuthorize("hasRole('USER')")
	@Transactional
	public ResponseEntity<ResponseMessage> updatePassword(@Valid @RequestBody PasswordUpdateRequest passwordUpdateDto) {
		if (!passwordUpdateDto.getNewPassword().equals((passwordUpdateDto).getConfirmNewPassword())) {
			return new ResponseEntity<>(new ResponseMessage("Confirm Password is wrong"), HttpStatus.BAD_REQUEST);
		}
		try {
			Optional<User> user = userService
					.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
			if (!user.isPresent()) {
				return new ResponseEntity<>(new ResponseMessage(StringsConstants.USER_NOT_EXIST),
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
			if (!passwordEncoder.matches(passwordUpdateDto.getOldPassword(), user.get().getPassword())) {
				return new ResponseEntity<>(new ResponseMessage("Old password is incorrect"),
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
			String newPassword = passwordEncoder.encode(passwordUpdateDto.getNewPassword());
			userService.updatePassword(newPassword, user.get());
			return new ResponseEntity<>(new ResponseMessage("Password updated successfully"), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new ResponseMessage("Error while updating password"),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
