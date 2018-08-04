package org.winwin.config;

import java.util.HashMap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.MapSessionRepository;
import org.springframework.session.config.annotation.web.http.EnableSpringHttpSession;

@Configuration
@EnableSpringHttpSession
class HttpSessionConfig {

	@Bean
	public MapSessionRepository sessionRepository() {
		return new MapSessionRepository(new HashMap<>());
	}

}