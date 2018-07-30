package org.winwin.request;

import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.winwin.model.AccountHolder;
import org.winwin.repository.AccountHolderRepository;

@RestController
public class GreetingController {


	private static final String template = "Hello, %s!";
	
	private final AtomicLong counter = new AtomicLong();

	@RequestMapping("/greeting")
	public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return new Greeting(counter.incrementAndGet(), String.format(template, name));
	}

	@Autowired
	private AccountHolderRepository ahRepository;

	@PostMapping("/addUser")
	public AccountHolder createQuestion(@Valid @RequestBody AccountHolder user) {
		user.setCreatedAt(new Date());
		user.setUpdatedAt(new Date());
		return ahRepository.save(user);
	}
}