package com.example.concurrencystudy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.concurrencystudy.domain.Stock;

/**
 * @author jhkim
 * @since 2023/01/06
 *
 */
public interface StockRepository extends JpaRepository<Stock, Long> {


}
