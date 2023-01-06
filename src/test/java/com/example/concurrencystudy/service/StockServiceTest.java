package com.example.concurrencystudy.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.concurrencystudy.domain.Stock;
import com.example.concurrencystudy.repository.StockRepository;

/**
 * @author jhkim
 * @since 2023/01/06
 *
 */
@SpringBootTest
class StockServiceTest {

	@Autowired
	private StockService stockService;

	@Autowired
	private StockRepository stockRepository;

	@BeforeEach
	public void before() {
		Stock stock = new Stock(1L,100L);
		stockRepository.saveAndFlush(stock);
	}

	@AfterEach
	public void after() {
		stockRepository.deleteAll();
	}

	@Test
	public void stock_decrease() throws Exception {
		stockService.decreaseStock(1L,1L);
		Stock stock = stockRepository.findById(1L).orElseThrow(Exception::new);
		assertEquals(99,stock.getQuantity());
	}

}