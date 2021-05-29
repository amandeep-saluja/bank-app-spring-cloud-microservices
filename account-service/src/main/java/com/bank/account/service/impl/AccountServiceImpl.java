package com.bank.account.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bank.account.domain.Account;
import com.bank.account.entity.AccountEntity;
import com.bank.account.repository.AccountRepository;
import com.bank.account.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService
{
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private RestTemplate template;
	
	@SuppressWarnings("unchecked")
	@Override
	public Account getAccountById(Integer accId) {
		Optional<AccountEntity> accountEntity = accountRepository.findById(accId);
		Account account = null;
		if(accountEntity.isPresent()) {
			account = AccountEntity.prepareDto(accountEntity.get());
			String url = "http://localhost:8002/transaction/allBySource/" + accountEntity.get().getNumber();
			HttpHeaders headers = template.headForHeaders(url);
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
			HttpEntity<HttpHeaders> entity = new HttpEntity<>(headers); 
			@SuppressWarnings("rawtypes")
			ResponseEntity<List> transactions =  template.exchange(url, HttpMethod.GET, entity, List.class);
			if(transactions.getStatusCode() != HttpStatus.NOT_FOUND) {
				account.setTransactions(transactions.getBody());
			}
		}
		return account;
	}
	
	@Override
	public List<Account> getAllAccounts() {
		return AccountEntity.prepareDtoList(accountRepository.findAll());
	}

}