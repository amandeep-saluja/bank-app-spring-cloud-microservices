package com.bank.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.bank.domain.Customer;
import com.bank.domain.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "customer")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class CustomerEntity extends User {

	@Id
	private long id;

	@Column(nullable = false, length = 50)
	private String name;

	@Column(nullable = false, length = 50)
	private String password;

	@Column(nullable = false, name = "date_of_birth")
	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;

	@Column(nullable = false, length = 10, name = "phone_no")
	private String phoneNo;

	@Column(nullable = false, length = 12, name = "aadhar_id")
	private String aadharId;

	@Column(nullable = false, length = 50, name = "email_id")
	private String emailId;

	@Column(nullable = false, length = 50)
	private String address;

	@Column(nullable = false)
	private Character gender;

	@Column(nullable = false, name = "joining_date")
	@Temporal(TemporalType.DATE)
	private Date joiningDate;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "account_id")
	private AccountEntity accountEntity;

	/**
	 * Method to prepare customer object from customer entity
	 * 
	 * @param customerEntity
	 * @return customer object
	 */
	public static Customer prepareDTO(CustomerEntity customerEntity) {
		Customer customer = new Customer(customerEntity.getId(), customerEntity.getName(), customerEntity.getPassword(),
				customerEntity.getDateOfBirth(), customerEntity.getPhoneNo(), customerEntity.getAadharId(),
				customerEntity.getEmailId(), customerEntity.getAddress(), customerEntity.getGender(),
				customerEntity.getJoiningDate(), AccountEntity.prepareDto(customerEntity.getAccountEntity()));
		return customer;
	}

	/**
	 * Method to prepare list of customer object from list of customer entity
	 * 
	 * @param customerEntities
	 * @return list of customers
	 */
	public static List<Customer> prepEntityList(List<CustomerEntity> customerEntities) {
		List<Customer> customers = new ArrayList<>();
		for (CustomerEntity entity : customerEntities) {
			customers.add(prepareDTO(entity));
		}
		return customers;
	}
}
