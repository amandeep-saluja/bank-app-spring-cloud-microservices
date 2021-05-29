package com.bank.account.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bank.account.domain.Account;

@Service
public interface AccountService {

	public Account getAccountById(Integer accId);
	
	public List<Account> getAllAccounts();
}
