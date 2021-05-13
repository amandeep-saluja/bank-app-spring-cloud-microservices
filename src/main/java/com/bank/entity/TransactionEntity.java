package com.bank.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.bank.domain.Status;
import com.bank.domain.Transaction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "transaction")
@NoArgsConstructor
@AllArgsConstructor
public class TransactionEntity {

	@Id
	private String id;

	@Column(nullable = false, length = 50)
	private String source;

	@Column(nullable = false, length = 50)
	private String destination;

	@Column(nullable = false, length = 50)
	private String type;

	@Column(nullable = false, length = 50)
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDate timeStamp;

	@Enumerated(EnumType.STRING)
	private Status status;

	/**
	 * Method to prepare Transaction DTO from Transaction Entity
	 * @param transactionEntity
	 * @return transaction DTO
	 */
	public static Transaction prepareTransaction(TransactionEntity transactionEntity) {
		Transaction transaction = new Transaction(transactionEntity.getId(), transactionEntity.getSource(),
				transactionEntity.getDestination(), transactionEntity.getType(), transactionEntity.getTimeStamp(),
				transactionEntity.getStatus());
		return transaction;
	}

	/**
	 * Method to prepare list of Transaction DTOs from list of transaction entity  
	 * @param transactionEntities
	 * @return list of transaction DTO
	 */
	public static List<Transaction> prepareTransactionList(List<TransactionEntity> transactionEntities) {
		List<Transaction> transactions = new ArrayList<>();
		for(TransactionEntity transactionEntity: transactionEntities) {
			transactions.add(prepareTransaction(transactionEntity));
		}
		return transactions;
	}
}
