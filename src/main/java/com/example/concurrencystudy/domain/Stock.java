package com.example.concurrencystudy.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author jhkim
 * @since 2023/01/06
 *
 */
@Entity
public class Stock {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long productId;
	private Long quantity;

	public Stock() {
	}

	public Stock(Long productId, Long quantity) {
		this.productId = productId;
		this.quantity = quantity;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void decrease(Long quantity) {
		if (this.quantity - quantity < 0) {
			throw new RuntimeException("Quantity not exist!!");
		}
		this.quantity -= quantity;
	}
}
