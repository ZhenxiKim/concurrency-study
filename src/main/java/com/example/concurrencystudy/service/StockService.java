package com.example.concurrencystudy.service;

import org.springframework.stereotype.Service;

import com.example.concurrencystudy.domain.StockEntity;
import com.example.concurrencystudy.repository.StockRepository;

/**
 * @author jhkim
 * @since 2023/01/06
 *
 */
@Service
public class StockService {
	private final StockRepository stockRepository;

	public StockService(StockRepository stockRepository) {
		this.stockRepository = stockRepository;
	}

	public void decreaseStock(Long id, Long quantity) throws Exception {
		StockEntity stockEntity = stockRepository.findById(id).orElseThrow(Exception::new);
		stockEntity.decrease(quantity);
		stockRepository.saveAndFlush(stockEntity);
	}
}
