package com.bank.transaction.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.transaction.entity.TransactionEntity;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Integer>{

	List<TransactionEntity> findBySource(String source);
	
	List<TransactionEntity> findByDestination(String destination);
}
