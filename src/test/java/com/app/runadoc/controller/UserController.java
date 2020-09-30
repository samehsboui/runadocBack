package com.app.runadoc.controller;

import java.util.Collection;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.runadoc.controller.UserController;
import com.app.runadoc.dto.response.UserResponse;
import com.app.runadoc.model.User;
import com.app.runadoc.service.UserService;
import com.app.runadoc.utils.StringsConstants;

import ch.qos.logback.classic.Logger;

@Controller
@RequestMapping(StringsConstants.USER_URL)
public class UserController {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@GetMapping(value = "/all")
	public ResponseEntity<List<UserResponse>> getAllUsers() {
	
		Collection<User> users = userService.findAllWithoutConnectedUser((long) 11);
		logger.info("liste des utilisateurs : " + users.toString());
		return new ResponseEntity<List<UserResponse>>(HttpStatus.FOUND);
	}

}