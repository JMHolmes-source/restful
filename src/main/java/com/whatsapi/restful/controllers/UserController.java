package com.whatsapi.restful.controllers;

import com.whatsapi.restful.repository.UserRepository;
import com.whatsapi.restful.util.JwtUtil;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.whatsapi.restful.models.User;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

	private UserRepository userRepository;

	@Autowired
    private JwtUtil jwtUtil;

	@Autowired
	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@GetMapping("/list")
	List<User> listUsers() {
		return userRepository.findAll();
	}

	@PostMapping("/create")
	void createUser(@RequestBody String body, @RequestHeader("Authorization") String authHeader) {
		JSONObject json = new JSONObject(body);
		String email = jwtUtil.extractEmail(authHeader.replace("Bearer ", ""));
		// System.out.println(email);
		// System.out.println(json.getString("username"));

		userRepository.createUserQuery(email, json.getString("username"));
	}
}