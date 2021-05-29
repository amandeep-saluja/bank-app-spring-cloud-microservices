package com.bank.account.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.account.domain.Account;
import com.bank.account.service.AccountService;

@RestController
@RequestMapping("/account")
public class AccountController {

	@Autowired
	private AccountService service;
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, value = "{accId}")
	public Account fetchAccount(@PathVariable(name = "accId") Integer accountId) {
		return service.getAccountById(accountId);
	}
	
	@GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Account> getAllAccounts() {
		return service.getAllAccounts();
	}
	
}
