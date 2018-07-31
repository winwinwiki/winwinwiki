package org.winwin.restservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"org.winwin.config", "org.winwin.request"})
@EntityScan("org.winwin.model")
@EnableJpaRepositories("org.winwin.repository")
public class RestServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(RestServiceApplication.class, args);
	}
}
