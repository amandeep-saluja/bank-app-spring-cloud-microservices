package com.bank.account.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.bank.account.domain.Account;
import com.bank.account.domain.AccountType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "account")
@NoArgsConstructor
@AllArgsConstructor
public class AccountEntity {

	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(nullable = false, length = 10, name = "account_number")
	private String number;
	
	@Column(name = "branch_location", nullable = false, length = 50)
	private String branchLocation;
	
	@Column(nullable = false, length = 50, name = "account_type")
	@Enumerated(EnumType.STRING)
	private AccountType type;
	
	@Column(name = "interest_rate", nullable = false)
	private Double interestRate;
	
	@Column(name = "opening_date", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date openingDate;
	
	@Column(nullable = false, name = "is_active")
	private Boolean active;
	
	@Column(nullable = false, length = 16)
	private String card;
	
	@Column(nullable = false)
	private Float balance;
	
	/**
	 * Method to prepare Account DTO from account entity
	 * @param accountEntity
	 * @return account
	 */
	public static Account prepareDto(AccountEntity accountEntity) {
		Account account = new Account(accountEntity.getId(), accountEntity.getNumber(),
				accountEntity.getBranchLocation(), accountEntity.getType(), accountEntity.getInterestRate(),
				accountEntity.getOpeningDate(), accountEntity.getActive(), accountEntity.getCard(),
				accountEntity.getBalance(), new ArrayList<>());
		return account;
	}
	
	/**
	 * Method to prepare Account List from Account Entity list
	 * @param accountEntities
	 * @return list of accounts
	 */
	public static List<Account> prepareDtoList(List<AccountEntity> accountEntities) {
		List<Account> accounts = new ArrayList<>();
		for(AccountEntity entity: accountEntities) {
			accounts.add(prepareDto(entity));
		}
		return accounts;
	}
}
