package com.bank.account.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bank.account.domain.Account;
import com.bank.account.exception.AccountNotFoundException;

@Service
public interface AccountService {

	public Account getAccountById(Integer accId);
	
	public List<Account> getAllAccounts();
	
	public Account createAccount(Account account);
	
	public Account updateAccount(Integer id, String branchName, String type, boolean active);
	
	public void deleteAccount(Integer id);
	
	public Float changeBalance(String source, String destination, Float amount) throws AccountNotFoundException;
}
