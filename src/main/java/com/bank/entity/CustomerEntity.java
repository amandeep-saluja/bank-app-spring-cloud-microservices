package com.bank.entity;

import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;

import com.bank.domain.Account;
import com.bank.domain.Customer;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerEntity {

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
	private AccountEntity accountEntity;

	/**
	 * Method to prepare customer object from customer entity
	 * @param customerEntity
	 * @return customer object
	 */
	public static Customer prepareDTO(CustomerEntity customerEntity) {
		Customer customer = new Customer(customerEntity.getId(), customerEntity.getName(),
				customerEntity.getDateOfBirth(), customerEntity.getPhoneNo(), customerEntity.getAadhar_id(),
				customerEntity.getEmailId(), customerEntity.getAddress(), customerEntity.getGender(),
				customerEntity.getJoiningDate(), AccountEntity.prepareDto(customerEntity.getAccountEntity()));
		return customer;
	}
	
	/**
	 * Method to prepare list of customer object from list of customer entity 
	 * @param customerEntities
	 * @return list of customers
	 */
	public static List<Customer> prepEntityList(List<CustomerEntity> customerEntities) {
		List<Customer> customers = new ArrayList<>();
		for(CustomerEntity entity: customerEntities) {
			customers.add(prepareDTO(entity));
		}
		return customers;
	}
}
