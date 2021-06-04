package com.bank.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.user.domain.Login;
import com.bank.user.domain.User;
import com.bank.user.entity.CustomerEntity;
import com.bank.user.entity.EmployeeEntity;
import com.bank.user.exception.UserNotFoundException;
import com.bank.user.repository.CustomerRepository;
import com.bank.user.repository.EmployeeRepository;
import com.bank.user.service.LoginService;

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