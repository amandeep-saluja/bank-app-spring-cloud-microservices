package com.bank.user.service;

import com.bank.user.domain.Customer;

public interface CustomerService {

	public Customer createCustomer(Customer customer);
	
	public Customer getCustomer(Integer id);
	
	public Customer updateCustomer(Integer id);
	
	public void deleteCustomer(Integer id);
}
