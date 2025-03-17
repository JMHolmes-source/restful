package com.whatsapi.restful.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.whatsapi.restful.models.User;

import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
	@GetMapping("/user/list")
	List<User> users() {
		List<User> users = new ArrayList<>();
		users.add(new User("yaosile", "joshua.holmes@bbd.co.za"));
		users.add(new User("greggyweggy", "gregory.conroy@bbd.co.za"));
		return users;
	}
}