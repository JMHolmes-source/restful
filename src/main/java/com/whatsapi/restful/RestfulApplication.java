package com.whatsapi.restful;


import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;

@RestController
@SpringBootApplication
public class RestfulApplication {

	@RequestMapping("/test")
	String test() {
		return "The API is up and running";
	}

	public static void main(String[] args) {
		SpringApplication.run(RestfulApplication.class, args);
	}
}