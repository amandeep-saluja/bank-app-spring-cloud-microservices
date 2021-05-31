package com.bank.user.service;

import java.util.List;

import com.bank.user.domain.Customer;
import com.bank.user.exception.UserNotFoundException;

public interface CustomerService {

	public Customer createCustomer(Customer customer);
	
	public Customer getCustomer(Integer id) throws UserNotFoundException;
	
	public Customer updateCustomer(Customer cust) throws UserNotFoundException;
	
	public void deleteCustomer(Integer id) throws UserNotFoundException;

	public List<Customer> getAllCustomers();
}
