package com.bank.transaction.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("AccountService")
public interface AccountFeign {

	@PostMapping("/account/doTrx")
	ResponseEntity<Float> performTransaction(@RequestParam("source") String source,
			@RequestParam("destination") String destination, @RequestParam("amount") Float amount);
	
}
