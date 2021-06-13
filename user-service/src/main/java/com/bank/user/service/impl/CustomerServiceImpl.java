package com.bank.user.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bank.user.domain.Account;
import com.bank.user.domain.Customer;
import com.bank.user.entity.CustomerEntity;
import com.bank.user.exception.UserNotFoundException;
import com.bank.user.repository.CustomerRepository;
import com.bank.user.service.AccountFeign;
import com.bank.user.service.CustomerService;

@Service
@RefreshScope(proxyMode = ScopedProxyMode.NO)
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository repository;

	@Autowired
	AccountFeign feign;

	@Override
	public Customer createCustomer(Customer customer) {
		ResponseEntity<Account> response = feign.addAccount(customer.getAccount());
		
		CustomerEntity customerEntity = new CustomerEntity((int) (repository.count() + 101), customer.getName(),
				customer.getPassword(), customer.getDateOfBirth(), customer.getPhoneNo(), customer.getAadharId(),
				customer.getEmailId(), customer.getAddress(), customer.getGender(), customer.getJoiningDate(),
				response.getBody().getId());
		return CustomerEntity.prepareDTO(repository.save(customerEntity));
	}

	@Override
	public Customer getCustomer(Integer id) throws UserNotFoundException {
		Optional<CustomerEntity> optional = repository.findById(id);
		if (optional.isEmpty())
			throw new UserNotFoundException("Customer not found");

		Account account = getAccountById(optional.get().getAccountId());
		CustomerEntity customerEntity = optional.get();
		Customer customer = CustomerEntity.prepareDTO(customerEntity);
		customer.setAccount(account);
		return customer;
	}

	@Override
	public Customer updateCustomer(Customer cust) throws UserNotFoundException {
		Optional<CustomerEntity> optional = repository.findById(cust.getId());

		if (optional.isEmpty())
			throw new UserNotFoundException("Customer not found");
		CustomerEntity entity = optional.get();

		if (entity.getAddress() != null)
			entity.setAddress(cust.getAddress());
		if (entity.getEmailId() != null)
			entity.setEmailId(cust.getEmailId());
		if (entity.getName() != null)
			entity.setName(cust.getName());
		if (entity.getPhoneNo() != null)
			entity.setPhoneNo(cust.getPhoneNo());
		if (entity.getPassword() != null)
			entity.setPassword(cust.getPassword());

		CustomerEntity updatedEntity = repository.save(entity);
		Customer customer = CustomerEntity.prepareDTO(updatedEntity);
		Account account = getAccountById(customer.getAccount().getId());
		customer.setAccount(account);
		return customer;
	}

	@Override
	public void deleteCustomer(Integer id) throws UserNotFoundException {
		Optional<CustomerEntity> opt = repository.findById(id);
		opt.ifPresent(value -> repository.delete(opt.get()));
	}

	@Override
	public List<Customer> getAllCustomers() {
		return CustomerEntity.prepEntityList(repository.findAll());
	}

	public Account getAccountById(Integer integer) {
		ResponseEntity<Account> response = feign.getAccount(integer);
		return response.getBody();
	}

}
