package com.whatsapi.restful.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    
	@GetMapping("/user")
	String users() {
		return "Here we can send back some users";
	}
}