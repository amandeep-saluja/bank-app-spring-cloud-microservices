package com.bank.domain;

import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;

import com.bank.entity.AccountEntity;
import com.bank.entity.CustomerEntity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

	private String id;

	@NotBlank(message = "Name cannnot be empty")
	private String name;

	@Past(message = "Date of birth should be past")
	@NotBlank(message = "Date of birth cannnot be empty")
	private LocalDate dateOfBirth;

	@Pattern(regexp = "[0-9]{10}", message = "Phone number should be of 10 digits")
	@NotBlank(message = "Phone no cannnot be empty")
	private String phoneNo;

	@Pattern(regexp = "{0-9}{12}", message = "Invalid aadhar card number")
	@NotBlank(message = "Aadhar id cannot be blank")
	private String aadhar_id;

	@NotBlank(message = "Eamil id cannnot be empty")
	@Email(message = "Enter valid email id")
	private String emailId;

	@NotBlank(message = "Address cannnot be empty")
	private String address;

	@NotBlank(message = "Gender cannnot be empty")
	@Pattern(regexp = "(M|F){1}", message = "Gender value can only be M or F")
	private Character gender;

	@PastOrPresent(message = "Joining date can only be of past or present")
	@NotBlank(message = "Joining Date cannnot be empty")
	private LocalDate joiningDate;

	@NotBlank(message = "Account cannnot be empty")
	private Account account;

	/**
	 * Method to prepare customer entity from customer DTO.  
	 * @param customer
	 * @return customer entity
	 */
	public static CustomerEntity prepareEntity(Customer customer) {
		CustomerEntity customerEntity = new CustomerEntity(customer.getId(), customer.getName(),
				customer.getDateOfBirth(), customer.getPhoneNo(), customer.getAadhar_id(), customer.getEmailId(),
				customer.getAddress(), customer.getGender(), customer.getJoiningDate(),
				Account.prepareEntity(customer.getAccount()));
		return customerEntity;
	}
	
	/**
	 * Method to prepare list of customer entity from list of customer DTOs
	 * @param customers
	 * @return list of customer entity
	 */
	public static List<CustomerEntity> prepareEntityList(List<Customer> customers) {
		List<CustomerEntity> customerEntities = new ArrayList<>();
		for(Customer customer: customers) {
			customerEntities.add(prepareEntity(customer));
		}
		return customerEntities;
	}
}
