package com.bank.account.domain;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

	private long id;

	@NotBlank(message = "Transaction source cannot be empty")
	private String source;

	@NotBlank(message = "Transaction Destination cannot be empty")
	private String destination;

	@NotBlank(message = "Transaction type cannot be empty")
	private String type;

	@NotBlank(message = "Transaction timestamp cannot be empty")
	private Date timeStamp;

	@NotBlank(message = "Transaction status cannot be empty")
	private TransactionStatus status;

}
