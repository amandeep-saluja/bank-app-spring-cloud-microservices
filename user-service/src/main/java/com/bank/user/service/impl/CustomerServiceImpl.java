package com.bank.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.bank.user.domain.Account;
import com.bank.user.domain.Customer;
import com.bank.user.entity.CustomerEntity;
import com.bank.user.repository.CustomerRepository;
import com.bank.user.service.CustomerService;

public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CustomerRepository repository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	private final String ACCOUNT_SERVICE_URL = "http://localhost:8001/account";

	@Override
	public Customer createCustomer(Customer customer) {
		
		ResponseEntity<Account> response = restTemplate.getForEntity(ACCOUNT_SERVICE_URL, Account.class);
		CustomerEntity entity = new CustomerEntity(repository.count() + 1, customer.getName(), customer.getPassword(),
				customer.getDateOfBirth(), customer.getPhoneNo(), customer.getAadharId(), customer.getEmailId(),
				customer.getAddress(), customer.getGender(), customer.getJoiningDate(), response.getBody().getId());
		return null;
	}

	@Override
	public Customer getCustomer(Integer id) {
		
		return null;
	}

	@Override
	public Customer updateCustomer(Integer id) {
		
		return null;
	}

	@Override
	public void deleteCustomer(Integer id) {
		
		
	}

}
