package com.bank.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.domain.Customer;
import com.bank.domain.Employee;
import com.bank.domain.Login;
import com.bank.domain.User;
import com.bank.service.LoginService;
import com.bank.utility.UserNotFoundException;

@RestController
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private LoginService service;

	@GetMapping(produces = "application/json")
	public <T> ResponseEntity<T> login(@Valid @RequestBody Login credentials) throws UserNotFoundException {
		User user = service.loginUser(credentials);
		if(user instanceof Customer) {
			return (ResponseEntity<T>) ResponseEntity.ok(user); 
		}
		
		if(user instanceof Employee) {
			return (ResponseEntity<T>) ResponseEntity.ok(user);
		}
		throw new UserNotFoundException("User not found");
	}
}
