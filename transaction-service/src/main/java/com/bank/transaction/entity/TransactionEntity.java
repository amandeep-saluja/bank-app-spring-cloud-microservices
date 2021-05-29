package com.bank.transaction.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.bank.transaction.domain.TransactionStatus;
import com.bank.transaction.domain.Transaction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "transaction_table")
@NoArgsConstructor
@AllArgsConstructor
public class TransactionEntity {

	@Id
	@GeneratedValue
	private long id;

	@Column(nullable = false, length = 10)
	private String source;

	@Column(nullable = false, length = 10)
	private String destination;

	@Column(nullable = false, length = 50, name = "transaction_type")
	private String type;

	@Column(nullable = false, name = "time_stamp")
	@Temporal(TemporalType.TIMESTAMP)
	private Date timeStamp;

	@Enumerated(EnumType.STRING)
	private TransactionStatus status;

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
