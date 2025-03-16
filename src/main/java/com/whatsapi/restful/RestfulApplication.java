package com.whatsapi.restful;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class RestfulApplication {
	@RequestMapping("/")
	String home() {
		return "Welcome to the bus stop";
	}

	@RequestMapping("/test")
	String test() {
		return "Welcome to the test page";
	}


	public static void main(String[] args) {
		SpringApplication.run(RestfulApplication.class, args);
	}
}