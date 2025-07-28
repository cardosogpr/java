package io.altar.jseproject.test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.altar.jseproject.model.Product;


public class test {
	
	@Test
	@DisplayName("Test set product id")
	void testSetProductId() {
		Product p = new Product();
		assertEquals(-1, p.getId(), "Defaul ID should be -1");
		assertDoesNotThrow(() -> {
			p.setId(1);
		});
	}
	
	@Test
	@DisplayName("Test set product id with invalid id")
	void testSetProductIdWithInvalidId() {
		Product p = new Product();
		assertThrows(RuntimeException.class, () -> {
			p.setId(-2);
		});
	}

	@Test
	void testSetProductIdAfterValidId() {
		Product p = new Product();
		p.setId(1);
		assertThrows(RuntimeException.class, () -> {
			p.setId(2);
		});
		assertThrows(RuntimeException.class, () -> {
			p.setId(-2);
		});
	}

}
