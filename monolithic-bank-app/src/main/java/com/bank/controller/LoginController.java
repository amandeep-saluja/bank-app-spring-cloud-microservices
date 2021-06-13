package com.bank.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.domain.Customer;
import com.bank.domain.Employee;
import com.bank.domain.Login;
import com.bank.domain.User;
import com.bank.domain.UserType;
import com.bank.service.LoginService;
import com.bank.utility.ResponseObject;
import com.bank.utility.UserNotFoundException;

@RestController
@CrossOrigin
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private LoginService service;

	@PostMapping(produces = "application/json")
	public ResponseEntity<ResponseObject> login(@Valid @RequestBody Login credentials) throws UserNotFoundException {
		User user = service.loginUser(credentials);
		ResponseObject object = new ResponseObject();
		object.setData(user);
		if(user instanceof Customer) {
			 object.setUserType(UserType.CUSTOMER.name());
		}
		else if(user instanceof Employee) {
			object.setUserType(UserType.EMPLOYEE.name());
		}
		object.setSuccess(true);
		object.setMessage(environment.getProperty("login.success"));
		return ResponseEntity.ok(object);
	}
}
