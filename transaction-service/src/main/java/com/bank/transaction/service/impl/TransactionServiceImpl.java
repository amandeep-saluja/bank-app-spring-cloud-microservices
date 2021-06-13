package com.bank.transaction.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bank.transaction.domain.Transaction;
import com.bank.transaction.domain.TransactionStatus;
import com.bank.transaction.entity.TransactionEntity;
import com.bank.transaction.repository.TransactionRepository;
import com.bank.transaction.service.AccountFeign;
import com.bank.transaction.service.TransactionService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RefreshScope(proxyMode = ScopedProxyMode.NO)
public class TransactionServiceImpl implements TransactionService{

	@Autowired
	private TransactionRepository repository;
	
	@Autowired
	AccountFeign feign;
	
	@Value("${server.port}")
	private String currentPort;
	
	@Override
	public List<Transaction> getTransactionBySource(String source) {
		log.info("fetching transactions from port: {} for account: {}", currentPort, source);
		List<Transaction> transactions = TransactionEntity.prepareTransactionList(repository.findBySource(source));
		for(Transaction trx:transactions) {
			trx.setType(currentPort+" "+trx.getType());
		}
		return transactions;
	}

	@Override
	public List<Transaction> getTransactionByDestination(String destination) {
		return TransactionEntity.prepareTransactionList(repository.findByDestination(destination));
	}

	@Override
	public Transaction doTransaction(String source, String destination, String type, Float amount) {
		log.info("Transaction request raised by {}",source);
		Transaction transaction = new Transaction();
		transaction.setId(101 + repository.count());
		transaction.setSource(source);
		transaction.setDestination(destination);
		transaction.setStatus(TransactionStatus.STARTED);
		transaction.setTimeStamp(new Date());
		transaction.setType(type);
		transaction.setAmount(amount);
		
		log.info("Validating account details for starting transaction of amount {}", amount);
		
//		HttpHeaders headers = new HttpHeaders();
//		headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
//		HttpEntity<HttpHeaders> httpEntity = new HttpEntity<HttpHeaders>(headers);
//		
//		UriComponentsBuilder builder = UriComponentsBuilder
//				.fromHttpUrl(client.getInstances("ACCOUNTSERVICE").get(0).getUri().toString() + "/account/doTrx")
//				.queryParam("source", source)
//				.queryParam("destination", destination)
//				.queryParam("amount", amount);
		
		ResponseEntity<Float> txnAmount = feign.performTransaction(source, destination, amount);
		log.info("Response Body: "+txnAmount.getBody());
		
		if(txnAmount.getBody() != null && amount.equals(txnAmount.getBody())) {
			transaction.setStatus(TransactionStatus.COMPLETED);
			log.info("Transaction Completed successfully {}",transaction.toString());
		}
		else {
			transaction.setStatus(TransactionStatus.FAILED);
			log.info("Transaction failed {}", transaction.toString());
		}
		
		TransactionEntity txn = repository.save(Transaction.prepareTransactionEntity(transaction));
		return TransactionEntity.prepareTransaction(txn);
	}
}
