package com.bank.account.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bank.account.domain.Account;
import com.bank.account.exception.AccountNotFoundException;
import com.bank.account.service.AccountService;

@RestController
@RequestMapping("/account")
@Validated
public class AccountController {

	@Autowired
	private AccountService service;
	
	private static final Logger logger = LoggerFactory.getLogger(AccountController.class);
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "{accId}")
	public Account fetchAccount(@PathVariable(name = "accId") Integer accountId) {
		logger.info("Account Fetch API triggered with account id: {} ",accountId);
		return service.getAccountById(accountId);
	}
	
	@GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Account> getAllAccounts() {
		logger.info("Get all Accounts API triggered.");
		return service.getAllAccounts();
	}
	
	@PostMapping(value = "/add")
	public Account createAccount(@Valid @RequestBody Account account) {
		logger.info("New account created with account object: {}",account);
		return service.createAccount(account);
	}
	
	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Account updateAccount(@RequestParam("id") Integer id, @RequestParam("branch") String branchName,
			@RequestParam("type") String type, @RequestParam("active") boolean active) {
		Account account = service.updateAccount(id, branchName, type, active);
		logger.info("Account update requested with id: {} , branch: {} , type: {} and active: {}", id, branchName, type, active);
		logger.info("Updated account details: {} ",account);
		return account;
	}
	
	@DeleteMapping
	public ResponseEntity<HttpStatus> deleteAccount(Integer id) {
		service.deleteAccount(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping("/doTrx")
	public Float changeBalance(
			@NotBlank(message = "Source account number is required") @RequestParam("source") String source,
			@NotBlank(message = "Destination account number is required") @RequestParam("destination") String destination,
			@NotNull(message = "Amount cannnot be empty") @RequestParam("amount") Float amount)
			throws AccountNotFoundException {
		logger.info("Transaction initiated in Account: {}");
		logger.info("Sending {} INR from {} account to {} account", amount, source, destination);
		return service.changeBalance(source, destination, amount);
	}
	
}
