package com.bank.transaction.service.impl;

import java.util.List;

import com.bank.transaction.domain.Transaction;

public interface TransactionService {

	public List<Transaction> getTransactionBySource(String source);
	
	public List<Transaction> getTransactionByDestination(String destination);
	
	public Transaction doTransaction(String source, String destination, String type, Float amount);
}
