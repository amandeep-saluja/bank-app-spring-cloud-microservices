package com.bank.user.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {

	private Integer id;

	@NotBlank(message = "Account number cannot be empty")
	private String number;

	@NotBlank(message = "Branch location cannot be empty")
	private String branchLocation;

	@NotNull(message = "Account type cannot be empty")
	private AccountType type;

	@NotNull(message = "Interest Rate cannot be empty")
	private Double interestRate;

	@NotNull(message = "Account opening date cannot be empty")
	@PastOrPresent(message = "Account opeing date cannot be of future")
	private Date openingDate;

	@NotNull(message = "Account active cannot be empty")
	private Boolean active;

	@NotBlank(message = "Account number cannot be empty")
	@Size(max = 16, min = 16, message = "Enter valid credit card number")
	private String card;

	private Float balance;

	private List<Transaction> transactions = new ArrayList<>();
}
