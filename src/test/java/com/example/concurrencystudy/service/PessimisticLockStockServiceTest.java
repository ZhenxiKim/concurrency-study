package com.example.concurrencystudy.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.concurrencystudy.domain.Stock;
import com.example.concurrencystudy.repository.StockRepository;

/**
 * @author jhkim
 * @since 2023/01/07
 *
 */
@SpringBootTest
class PessimisticLockStockServiceTest {

	@Autowired
	private PessimisticLockStockService stockService;

	@Autowired
	private StockRepository stockRepository;

	@BeforeEach
	public void before() {
		Stock stock = new Stock(1L, 100L);
		stockRepository.saveAndFlush(stock);
	}

	@AfterEach
	public void after() {
		stockRepository.deleteAll();
	}

	@Test
	public void decrease_test() throws Exception {
		stockService.decreaseStock(1L, 1L);

		Stock stock = stockRepository.findById(1L).orElseThrow(Exception::new);
		// 100 - 1 = 99

		assertEquals(99, stock.getQuantity());
	}

	@Test
	public void 동시_100개_요청() throws Exception {
		int threadCount = 100;
		ExecutorService executorService = Executors.newFixedThreadPool(32);
		CountDownLatch countDownLatch = new CountDownLatch(threadCount);

		for (int i = 0; i < threadCount; i++) {
			executorService.submit(() -> {
				try {
					stockService.decreaseStock(1L, 1L);
				} catch (Exception e) {
					throw new RuntimeException(e);
				} finally {
					countDownLatch.countDown();
				}
			});
		}
		countDownLatch.await();
		Stock stock = stockRepository.findById(1L).orElseThrow(Exception::new);
		assertEquals(0L, stock.getQuantity());
	}
}