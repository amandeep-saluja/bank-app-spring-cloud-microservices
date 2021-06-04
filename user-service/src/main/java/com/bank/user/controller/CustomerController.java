package com.bank.user.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.user.domain.Customer;
import com.bank.user.domain.UserType;
import com.bank.user.exception.UserNotFoundException;
import com.bank.user.service.CustomerService;
import com.bank.user.utility.ResponseObject;

@RestController
@RequestMapping("/customer")
@Validated
public class CustomerController {

	@Autowired
	private CustomerService service;
	
	@GetMapping(value = "/{custId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseObject> getCustomer(@NotNull @PathVariable("custId") Integer id) throws UserNotFoundException {
		ResponseObject obj = new ResponseObject();
		obj.setData(service.getCustomer(id));
		obj.setMessage("Found Customers successfully");
		obj.setSuccess(true);
		obj.setUserType(UserType.CUSTOMER.toString());
		return ResponseEntity.ok(obj);
	}
	
	@PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Customer createCustomer(@Valid @RequestBody Customer customer) {
		return service.createCustomer(customer);
	}
	
	@PutMapping
	public Customer updateCustomer(@RequestBody Customer cust) throws UserNotFoundException {
		return service.updateCustomer(cust);
	}
	
	@DeleteMapping("/{custId}")
	public ResponseEntity<ResponseObject> deleteCustomer(@NotNull @PathVariable("custId") Integer id) throws UserNotFoundException {
		service.deleteCustomer(id);
		ResponseObject obj = new ResponseObject();
		obj.setData(id);
		obj.setMessage("Deleted Customers successfully");
		obj.setSuccess(true);
		obj.setUserType(UserType.CUSTOMER.toString());
		return ResponseEntity.ok(obj);
	}
	
	@GetMapping("/all")
	public ResponseEntity<ResponseObject> getAllCustomers() {
		ResponseObject object = new ResponseObject();
		object.setData(service.getAllCustomers());
		object.setMessage("Found Customers");
		object.setSuccess(true);
		object.setUserType(UserType.CUSTOMER.toString());
		return ResponseEntity.ok(object);
	}
}
