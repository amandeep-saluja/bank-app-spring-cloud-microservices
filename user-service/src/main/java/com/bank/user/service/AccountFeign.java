package com.bank.user.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.bank.user.domain.Account;

@FeignClient("AccountService")
public interface AccountFeign {

	@PostMapping("/account/add")
	ResponseEntity<Account> addAccount(@RequestBody Account account);
	
	@PostMapping("/account/{id}")
	ResponseEntity<Account> getAccount(@PathVariable("id") Integer id);
}
