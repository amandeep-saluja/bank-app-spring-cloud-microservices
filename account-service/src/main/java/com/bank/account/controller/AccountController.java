package com.bank.account.controller;

import java.util.Collections;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
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
import org.springframework.web.client.RestTemplate;

import com.bank.account.domain.Account;
import com.bank.account.exception.AccountNotFoundException;
import com.bank.account.service.AccountService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/account")
@Validated
@Slf4j
//@RibbonClient(name = "accountRibbon", configuration = RibbonConfig.class)
public class AccountController {

	@Autowired
	private AccountService service;
	
	@Autowired
	private RestTemplate template;
	
//	@Autowired
//	DiscoveryClient client;

	@Value("${transaction.port}")
	private String transactionServicePortNumber;
	
	@SuppressWarnings("unchecked")
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "{accId}")
	public Account fetchAccount(@PathVariable(name = "accId") Integer accountId) {
		log.info("Fetching Account details with account id: {} ", accountId);
		
		Account account = service.getAccountById(accountId);
		
		log.info("Fetching transactions of account: {}", account.getNumber());
//		Used to get instances and hit them using unbalanced rest template
//		List<ServiceInstance> instances = client.getInstances("TransactionService");
//		ServiceInstance instance = instances.get(0);
//		String trxUrl = instance.getUri().toString();
//		log.info("Found trx service url: {} from Eureka", trxUrl);;
		
		@SuppressWarnings("rawtypes")
		ResponseEntity<List> transactions = template.getForEntity(
				"http://TransactionService" + "/transaction/allBySource/" + account.getNumber(), List.class);
		log.info("Received response: {}", transactions.toString());
		if (transactions.getStatusCode() != HttpStatus.NOT_FOUND) {
			account.setTransactions(transactions.getBody());
		}
		return account;
	}
	
	@GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Account> getAllAccounts() {
		log.info("Get all Accounts API triggered.");
		return service.getAllAccounts();
	}
	
	@PostMapping(value = "/add")
	public Account createAccount(@Valid @RequestBody Account account) {
		log.info("New account created with account object: {}",account);
		return service.createAccount(account);
	}
	
	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Account updateAccount(@RequestParam("id") Integer id, @RequestParam("branch") String branchName,
			@RequestParam("type") String type, @RequestParam("active") boolean active) {
		Account account = service.updateAccount(id, branchName, type, active);
		log.info("Account update requested with id: {} , branch: {} , type: {} and active: {}", id, branchName, type, active);
		log.info("Updated account details: {} ",account);
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
		log.info("Transaction initiated in Account: {}");
		log.info("Sending {} INR from {} account to {} account", amount, source, destination);
		return service.changeBalance(source, destination, amount);
	}
	
}
