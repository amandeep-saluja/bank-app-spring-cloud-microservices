package com.bank.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.CreditCardNumber;

import com.bank.entity.AccountEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {

	private String id;

	@NotBlank(message = "Account number cannot be empty")
	private String number;

	@NotBlank(message = "Branch location cannot be empty")
	private String branchLocation;

	@NotBlank(message = "Account type cannot be empty")
	private AccountType type;

	@NotBlank(message = "Interest Rate cannot be empty")
	private Double interestRate;

	@NotBlank(message = "Account opening date cannot be empty")
	private LocalDate openingDate;

	@NotBlank(message = "Account active cannot be empty")
	private Boolean active;

	@NotBlank(message = "Account number cannot be empty")
	@CreditCardNumber(message = "Enter valid card number")
	private String card;

	private Float balance;

	private List<Transaction> transactions;

	/**
	 * Method to prepare Entity from DTO
	 * @param account
	 * @return accountEntity
	 */
	public static AccountEntity prepareEntity(Account account) {
		AccountEntity accountEntity = new AccountEntity(account.getId(), account.getNumber(),
				account.getBranchLocation(), account.getType(), account.getInterestRate(), account.getOpeningDate(),
				account.getActive(), account.getCard(), account.getBalance(),
				Transaction.prepareTransactionEntities(account.getTransactions()));
		return accountEntity;
	}

	/**
	 * Method to prepare account entity list from account DTO list
	 * @param accounts
	 * @return list of account entity
	 */
	public static List<AccountEntity> prepareEntitList(List<Account> accounts) {
		List<AccountEntity> accountEntities = new ArrayList<>();
		for(Account account: accounts) {
			accountEntities.add(prepareEntity(account));
		}
		return accountEntities;
	}
}
