package org.winwin.controller;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class RandomNumberController {

	@ApiOperation(value="", notes="a simple api that secured behind aws cognito authenication")
	@GetMapping("/random-number")
	public Double getRandomNumber() {
		log.info("Service status controller");
		return Math.random();
	}

}
