package com.bank.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.user.domain.Customer;
import com.bank.user.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService service;
	
	@GetMapping(value = "/{custId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Customer getCustomer(@PathVariable("custId") Integer id) {
		
		return null;
	}
}
