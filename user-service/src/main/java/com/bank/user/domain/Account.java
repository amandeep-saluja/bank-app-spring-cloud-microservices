package com.bank.user.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.CreditCardNumber;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {

	private long id;

	@NotBlank(message = "Account number cannot be empty")
	private String number;

	@NotBlank(message = "Branch location cannot be empty")
	private String branchLocation;

	@NotBlank(message = "Account type cannot be empty")
	private AccountType type;

	@NotBlank(message = "Interest Rate cannot be empty")
	private Double interestRate;

	@NotBlank(message = "Account opening date cannot be empty")
	private Date openingDate;

	@NotBlank(message = "Account active cannot be empty")
	private Boolean active;

	@NotBlank(message = "Account number cannot be empty")
	@CreditCardNumber(message = "Enter valid card number")
	private String card;

	private Float balance;

	private List<Transaction> transactions = new ArrayList<>();
}
