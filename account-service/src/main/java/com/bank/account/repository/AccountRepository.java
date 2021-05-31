package com.bank.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bank.account.entity.AccountEntity;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Integer>{

	@Query("select acc from AccountEntity acc where acc.number=?1")
	AccountEntity findAccountByNumber(String accountNumber);
}
