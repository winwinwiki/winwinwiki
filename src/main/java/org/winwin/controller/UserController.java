package org.winwin.controller;

import java.util.Date;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	
	
	@GetMapping("/user/{id}")
	public ApplicationUser getUser(@Valid @PathVariable("id") long id) {
		Optional<ApplicationUser> result = auRepository.findById(id);
		return removeCriticalFields(result.orElse(null));
	}

	@GetMapping("/user")
	public ApplicationUser searchUser(@Param("userName") String userName, @Param("email") String email) {
		ApplicationUser result = null;
		if(! StringUtils.isEmpty(userName)) {
			result = auRepository.findByUsername(userName);
		} else if (! StringUtils.isEmpty(email)) {
			result = auRepository.findByEmail(email);
		}

		return removeCriticalFields(result);
	}
	
	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	public String deleteUser(@Valid @PathVariable("id") long id) {
		auRepository.deleteById(id);
		return SUCCESS;
	}

	@PostMapping("/user")
	public ApplicationUser createUser(@Valid @RequestBody ApplicationUser user) {
		ApplicationUser appUser = auRepository.findByUsername(user.getUsername());
		if (appUser == null) {
			user.setPassword(passwdEncoder.encode(user.getPassword()));
			user.setCreatedAt(new Date());
			user.setUpdatedAt(new Date());
			appUser = auRepository.save(user);
			return removeCriticalFields(appUser);
		}
		return null;
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
	public String updateUser(@Valid @PathVariable("id") long id, @Valid @RequestBody ApplicationUser user) {
		Optional<ApplicationUser> result = auRepository.findById(id);
		if( result.isPresent() ) {
			ApplicationUser userInDB = result.get();
			userInDB.setUpdatedAt(new Date());
			userInDB.setLocation(user.getLocation());
			userInDB.setRole(user.getRole());
			userInDB.setFirstname(user.getFirstname());
			userInDB.setLastname(user.getLastname());
			userInDB.setEmail(user.getEmail());
			userInDB.setPassword(passwdEncoder.encode(user.getPassword()));
			auRepository.save(userInDB);
			return SUCCESS;
		}else {
			return FAILURE;
		}
	}

	@PostMapping("/password/update")
	public String updatePassword(@Valid @RequestBody ApplicationUser user) {
		ApplicationUser appUser = auRepository.findByUsername(user.getUsername());
		if (appUser != null) {
			appUser.setPassword(passwdEncoder.encode(user.getPassword()));
			appUser.setUpdatedAt(new Date());
			auRepository.save(user);
			return SUCCESS;
		} else {
			log.error("User not found :" + user.getEmail());
			return FAILURE;
		}
	}
	
	private ApplicationUser removeCriticalFields(ApplicationUser user) {
		if( user != null ) {
			user.setPassword("*****");
		}
		return user;
	}

}
