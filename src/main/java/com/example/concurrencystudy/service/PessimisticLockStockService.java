package com.example.concurrencystudy.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.concurrencystudy.domain.Stock;
import com.example.concurrencystudy.repository.StockRepository;

/**
 * @author jhkim
 * @since 2023/01/07
 *
 */
@Service
public class PessimisticLockStockService {

	private StockRepository stockRepository;

	public PessimisticLockStockService(StockRepository stockRepository) {
		this.stockRepository = stockRepository;
	}

	@Transactional
	public void decreaseStock(Long id, Long quantity) {
		Stock stock = stockRepository.findByIdWithPessimisticLock(id);
		stock.decrease(quantity);
		stockRepository.saveAndFlush(stock);
	}
}
