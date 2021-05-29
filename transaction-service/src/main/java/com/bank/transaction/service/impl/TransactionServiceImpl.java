package com.bank.transaction.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.transaction.domain.Transaction;
import com.bank.transaction.entity.TransactionEntity;
import com.bank.transaction.repository.TransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService{

	@Autowired
	private TransactionRepository repository;
	
	@Override
	public List<Transaction> getTransactionBySource(String source) {
		return TransactionEntity.prepareTransactionList(repository.findBySource(source));
	}

	@Override
	public List<Transaction> getTransactionByDestination(String destination) {
		return TransactionEntity.prepareTransactionList(repository.findByDestination(destination));
	}

}
