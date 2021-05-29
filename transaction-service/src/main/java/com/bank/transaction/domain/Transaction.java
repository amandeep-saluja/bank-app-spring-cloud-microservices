package com.bank.transaction.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;

import com.bank.transaction.entity.TransactionEntity;

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

	/**
	 * Method to prepare Transaction entity from transaction DTO
	 * 
	 * @param transaction
	 * @return transaction entity
	 */
	public static TransactionEntity prepareTransactionEntity(Transaction transaction) {
		TransactionEntity entity = new TransactionEntity(transaction.getId(), transaction.getSource(),
				transaction.getDestination(), transaction.getType(), transaction.getTimeStamp(),
				transaction.getStatus());
		return entity;
	}

	/**
	 * Method to prepare list of transaction entity from list of transaction DTOs
	 * 
	 * @param transactions
	 * @return list of transaction
	 */
	public static List<TransactionEntity> prepareTransactionEntities(List<Transaction> transactions) {
		List<TransactionEntity> transactionEntities = new ArrayList<>();
		for (Transaction transaction : transactions) {
			transactionEntities.add(prepareTransactionEntity(transaction));
		}
		return transactionEntities;
	}
}
