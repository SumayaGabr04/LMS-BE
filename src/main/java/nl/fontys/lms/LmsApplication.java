package nl.fontys.lms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
		"nl.fontys.lms",
		"nl.fontys.lms.persistence",
		"nl.fontys.lms.persistence",
		"nl.fontys.lms.persistence.entity",
		"nl.fontys.lms.controller",
		"nl.fontys.lms.business",
		"nl.fontys.lms.business.user",
		"nl.fontys.lms.business.user.impl",
		"nl.fontys.lms.business.material",
		"nl.fontys.lms.business.material.impl",
		"nl.fontys.lms.domain",
		"nl.fontys.lms.domain.user",
		"nl.fontys.lms.business.exception",
		"nl.fontys.lms.configuration.security.token.impl",
		"nl.fontys.lms.configuration.security.auth"
		})
@EntityScan(basePackages = "nl.fontys.lms.persistence.entity")

public class LmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(LmsApplication.class, args);
	}

}
