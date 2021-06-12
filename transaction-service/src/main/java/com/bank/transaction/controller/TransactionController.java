package com.bank.transaction.controller;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bank.transaction.domain.Transaction;
import com.bank.transaction.service.TransactionService;

@RestController
@RequestMapping("/transaction")
@Validated
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
	
	@PostMapping("/doTrx")
	public Transaction doTransaction(
			@NotBlank(message = "Source account number is required") @RequestParam("source") String source,
			@NotBlank(message = "Destination account number is required") @RequestParam("destination") String destination,
			@NotNull(message = "Amount cannnot be empty") @RequestParam("amount") Float amount,
			@NotBlank(message = "Type of transaction is required") @RequestParam("type") String type) {
		return service.doTransaction(source, destination, type, amount);
	}
}
