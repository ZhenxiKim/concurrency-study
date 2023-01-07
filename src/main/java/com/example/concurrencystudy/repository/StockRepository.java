package com.example.concurrencystudy.repository;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import com.example.concurrencystudy.domain.Stock;

/**
 * @author jhkim
 * @since 2023/01/06
 *
 */
public interface StockRepository extends JpaRepository<Stock, Long> {

	@Lock(value = LockModeType.PESSIMISTIC_WRITE)
	@Query("select s from Stock s where s.id =: id")
	Stock findByIdWithPessimisticLock(Long id);
}
