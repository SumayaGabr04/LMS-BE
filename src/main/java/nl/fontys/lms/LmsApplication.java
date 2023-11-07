package nl.fontys.lms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"nl.fontys.lms.persistence",
		"nl.fontys.lms.persistence",
		"nl.fontys.lms.persistence.impl",
		"nl.fontys.lms.persistence.entity",
		"nl.fontys.lms.controller",
		"nl.fontys.lms.business",
		"nl.fontys.lms.business.user",
		"nl.fontys.lms.business.user.impl",
		"nl.fontys.lms.business.user.teacher",
		"nl.fontys.lms.business.user.teacher.impl",
		"nl.fontys.lms.domain",
		"nl.fontys.lms.domain.user",
		"nl.fontys.lms.business.exception"
		})
@EntityScan(basePackages = "nl.fontys.lms.persistence.entity")

public class LmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(LmsApplication.class, args);
	}

}
