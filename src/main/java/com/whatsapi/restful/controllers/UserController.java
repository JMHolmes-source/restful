package com.whatsapi.restful.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.whatsapi.restful.models.User;

import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
class UserController {
	@GetMapping("/list")
	List<User> users() {
		List<User> users = new ArrayList<>();
		users.add(new User("yaosile", "joshua.holmes@bbd.co.za"));
		users.add(new User("greggyweggy", "gregory.conroy@bbd.co.za"));
		return users;
	}
}