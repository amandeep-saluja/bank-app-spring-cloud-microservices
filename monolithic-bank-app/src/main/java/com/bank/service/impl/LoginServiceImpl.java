package com.bank.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.domain.Login;
import com.bank.domain.User;
import com.bank.entity.CustomerEntity;
import com.bank.entity.EmployeeEntity;
import com.bank.repository.CustomerRepository;
import com.bank.repository.EmployeeRepository;
import com.bank.service.LoginService;
import com.bank.utility.UserNotFoundException;

@Service
public class LoginServiceImpl implements LoginService
{
	
	@Autowired
	private CustomerRepository customerRepo;
	
	@Autowired
	private EmployeeRepository employeeRepo;
	
	@Override
	public User loginUser(Login credentials) throws UserNotFoundException 
	{
		User user = null;
		if("CUSTOMER".equalsIgnoreCase(credentials.getUserRole())) 
		{
			user =  customerRepo.findCustomerByPhoneNo(credentials.getPhoneNo());
			
			if(user == null)
				throw new UserNotFoundException("User not found by phone no");
		} 
		else if("EMPLOYEE".equalsIgnoreCase(credentials.getUserRole())) 
		{
			user = employeeRepo.findEmployeeByPhoneNo(credentials.getPhoneNo());
			
			if(user == null)
				throw new UserNotFoundException("User not found by phone no");
		}
		
		if(user.getPassword().equals(credentials.getPassword())) 
		{
			if(user instanceof CustomerEntity)
				return CustomerEntity.prepareDTO((CustomerEntity) user);
			else
				return EmployeeEntity.prepareEmployeeDTO((EmployeeEntity) user);
		}
		return null;
	}

}