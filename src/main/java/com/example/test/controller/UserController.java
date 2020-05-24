package com.example.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.test.domain.User;
import com.example.test.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/add")
	public ResponseEntity<String> userAdd(@RequestBody User user) throws Exception {

		if (StringUtils.isEmpty(user.getName()) && StringUtils.isEmpty(user.getPassword())) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<String>(userService.userAdd(user), HttpStatus.CREATED);

	}

	@RequestMapping("/add1")
	public ResponseEntity<String> userAdd1() throws Exception {

		User user = new User("Tom", "123");
		if (StringUtils.isEmpty(user.getName()) && StringUtils.isEmpty(user.getPassword())) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<String>(userService.userAdd(user), HttpStatus.CREATED);

	}
}
