package org.winwin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.winwin.lib.AuthEncoder;
import org.winwin.model.SDG;
import org.winwin.model.SPI;
import org.winwin.repository.SDGRepository;
import org.winwin.repository.SPIRepository;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class DomainValueController {
	
	@Autowired
	private SPIRepository spiRepository;

	@Autowired
	private SDGRepository sdgRepository;

	@GetMapping("/domain/spi")
	public List<SPI> getSPI(@RequestHeader(AuthEncoder.AUTH_HEADER) String authValue) {
		Long userId = AuthEncoder.validateAuthHeader(authValue);
		List<SPI> values = spiRepository.findAll();
		return values;
	}

	@GetMapping("/domain/sdg")
	public List<SDG> getSDG(@RequestHeader(AuthEncoder.AUTH_HEADER) String authValue) {
		Long userId = AuthEncoder.validateAuthHeader(authValue);
		List<SDG> values = sdgRepository.findAll();
		return values;
	}

}
