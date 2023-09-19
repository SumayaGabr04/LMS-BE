package nl.fontys.lms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"nl.fontys.lms.persistence",
		"nl.fontys.lms.persistence",
		"nl.fontys.lms.persistence.impl",
		"nl.fontys.lms.persistence.entity",
		"nl.fontys.lms.controller",
		"nl.fontys.lms.business"})

public class LmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(LmsApplication.class, args);
	}

}
