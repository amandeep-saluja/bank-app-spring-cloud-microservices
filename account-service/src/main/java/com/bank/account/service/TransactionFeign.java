package com.bank.account.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.bank.account.domain.Transaction;

@FeignClient("TransactionService")
public interface TransactionFeign {

	@GetMapping("/transaction/allBySource/{accountNumber}")
	ResponseEntity<List<Transaction>> getTransactions(@PathVariable("accountNumber") String accountNumber); 
	
}
