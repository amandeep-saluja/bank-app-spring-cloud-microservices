package com.bank.account.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bank.account.domain.Account;
import com.bank.account.domain.AccountType;
import com.bank.account.entity.AccountEntity;
import com.bank.account.exception.AccountNotFoundException;
import com.bank.account.repository.AccountRepository;
import com.bank.account.service.AccountService;

@Service
@RefreshScope(proxyMode = ScopedProxyMode.NO)
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private RestTemplate template;

	@Value("${transaction.port}")
	private String transactionServicePortNumber;

	@SuppressWarnings("unchecked")
	@Override
	public Account getAccountById(Integer accId) {
		Optional<AccountEntity> accountEntity = accountRepository.findById(accId);
		Account account = null;
		if (accountEntity.isPresent()) {
			account = AccountEntity.prepareDto(accountEntity.get());
			System.out.println("Transaction port: "+transactionServicePortNumber);
			String url = "http://localhost:" + transactionServicePortNumber
					+ "/transaction/allBySource/" + accountEntity.get().getNumber();
			HttpHeaders headers = template.headForHeaders(url);
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
			HttpEntity<HttpHeaders> entity = new HttpEntity<>(headers);
			@SuppressWarnings("rawtypes")
			ResponseEntity<List> transactions = template.exchange(url, HttpMethod.GET, entity, List.class);
			if (transactions.getStatusCode() != HttpStatus.NOT_FOUND) {
				account.setTransactions(transactions.getBody());
			}
		}
		return account;
	}

	@Override
	public List<Account> getAllAccounts() {
		return AccountEntity.prepareDtoList(accountRepository.findAll());
	}

	@Override
	public Account createAccount(Account account) {
		account.setId((int) (101 + accountRepository.count()));
		AccountEntity entity = accountRepository.save(Account.prepareEntity(account));
		return AccountEntity.prepareDto(entity);
	}

	@Override
	public Account updateAccount(Integer id, String branchName, String type, boolean active) {
		Optional<AccountEntity> account = accountRepository.findById(id);
		if (account.isPresent()) {
			if (!branchName.isBlank())
				account.get().setBranchLocation(branchName);
			if (!type.isBlank())
				account.get().setType(AccountType.valueOf(type));
			account.get().setActive(active);
			AccountEntity entity = accountRepository.save(account.get());
			return AccountEntity.prepareDto(entity);
		}
		return null;
	}

	@Override
	public void deleteAccount(Integer id) {
		Optional<AccountEntity> account = accountRepository.findById(id);
		if (account.isPresent()) {
			accountRepository.delete(account.get());
		}
	}

	@Override
	public Float changeBalance(String source, String destination, Float amount) throws AccountNotFoundException {
		Optional<AccountEntity> sourceAccount = Optional.of(accountRepository.findAccountByNumber(source));
		if (!sourceAccount.isPresent())
			throw new AccountNotFoundException("Source Account not found");

		if (!(sourceAccount.get().getBalance() > amount && sourceAccount.get().getActive()))
			return 0F;

		Optional<AccountEntity> destinationAccount = Optional.of(accountRepository.findAccountByNumber(destination));
		if (!destinationAccount.isPresent())
			throw new AccountNotFoundException("Destination Account not found");

		sourceAccount.get().setBalance(sourceAccount.get().getBalance() - amount);
		destinationAccount.get().setBalance(destinationAccount.get().getBalance() + amount);
		accountRepository.save(sourceAccount.get());
		accountRepository.save(destinationAccount.get());

		return amount;
	}

}