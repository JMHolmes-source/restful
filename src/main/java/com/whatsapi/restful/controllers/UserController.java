package com.whatsapi.restful.controllers;

import com.whatsapi.restful.repository.UserRepository;
import com.whatsapi.restful.util.JwtUtil;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.whatsapi.restful.models.User;

import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	ResponseEntity<String> createUser(@RequestBody String body, @RequestHeader("Authorization") String authHeader) {
		JSONObject json = new JSONObject(body);
		String email = jwtUtil.extractEmail(authHeader.replace("Bearer ", ""));
		// System.out.println(email);
		// System.out.println(json.getString("username"));
		try {
			userRepository.createUserQuery(email, json.getString("username"));
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/check")
	Map<String, String> checkUser(@RequestHeader("Authorization") String authHeader) {
		String email = jwtUtil.extractEmail(authHeader.replace("Bearer ", ""));
		// return new JSONObject().put("username", userRepository.findUserExist(email));
		HashMap<String, String> map = new HashMap<>();
		map.put("username", userRepository.findUserExist(email));
		return map;
	}
}