package com.whatsapi.restful;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@EntityScan("com.*")
@EnableJpaRepositories("com.*")
public class RestfulApplication {

	@RequestMapping("/test")
	String test() {
		return "The API is up and running";
	}

	public static void main(String[] args) {
		SpringApplication.run(RestfulApplication.class, args);
	}
}