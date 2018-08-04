package org.winwin.request;

import java.util.Date;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.winwin.model.ApplicationUser;
import org.winwin.repository.ApplicationUserRepository;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class UserController {

	private static String SUCCESS = "OK";
	private static String FAILURE = "ERROR";

	@Autowired
	PasswordEncoder passwdEncoder;

	@Autowired
	private ApplicationUserRepository auRepository;

	@PostMapping("/searchUser")
	public ApplicationUser getUser(@Valid @RequestBody ApplicationUser user) {
		if (user.getId() != null) {
			Optional<ApplicationUser> result = auRepository.findById(user.getId());
			result.orElse(null);
		}
		if (user.getUsername() != null) {
			return auRepository.findByUsername(user.getUsername());
		}
		return null;
	}

	@PutMapping("/createUser")
	public String createUser(@Valid @RequestBody ApplicationUser user) {
		user.setPassword(passwdEncoder.encode(user.getPassword()));
		user.setCreatedAt(new Date());
		user.setUpdatedAt(new Date());
		auRepository.save(user);
		return SUCCESS;
	}

	@PostMapping("/updatePassword")
	public String updatePassword(@Valid @RequestBody ApplicationUser user) {
		ApplicationUser appUser = auRepository.findByUsername(user.getUsername());
		if (appUser != null) {
			appUser.setPassword(passwdEncoder.encode(user.getPassword()));
			appUser.setUpdatedAt(new Date());
			auRepository.save(user);
			return SUCCESS;
		} else {
			log.error("User not found :" + user.getUsername());
			return FAILURE;
		}
	}

}
