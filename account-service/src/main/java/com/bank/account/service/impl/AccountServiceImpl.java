package com.bank.account.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.account.domain.Account;
import com.bank.account.domain.AccountType;
import com.bank.account.entity.AccountEntity;
import com.bank.account.exception.AccountNotFoundException;
import com.bank.account.repository.AccountRepository;
import com.bank.account.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository;

	@Override
	public Account getAccountById(Integer accId) {
		Optional<AccountEntity> accountEntity = accountRepository.findById(accId);
		Account account = null;
		if (accountEntity.isPresent()) {
			account = AccountEntity.prepareDto(accountEntity.get());
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