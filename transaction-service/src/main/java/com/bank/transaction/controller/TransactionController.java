package com.bank.transaction.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.transaction.domain.Transaction;
import com.bank.transaction.service.impl.TransactionService;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

	@Autowired
	private TransactionService service;
	
	@GetMapping(value = "/allBySource/{account}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Transaction> getAllSourceTransactions(@PathVariable("account") String source) {
		return service.getTransactionBySource(source);
	}
	
	@GetMapping(value = "/allByDestination/{account}", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Transaction> getAllDestinationTransactions(@PathVariable("account") String source) {
		return service.getTransactionByDestination(source);
	}
}
