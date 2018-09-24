package org.winwin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class PingController {
	
	private static String SUCCESS = "OK";

	@GetMapping("/ping")
	public String getServiceStatus() {
		log.info("Service status controller");
		return SUCCESS;
	}


}
