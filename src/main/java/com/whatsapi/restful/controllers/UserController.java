package com.whatsapi.restful.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {
	@GetMapping("/")
	String users() {
		return "Invalid API call with no user information/actions specified";
	}

    @GetMapping('/{id}')
}