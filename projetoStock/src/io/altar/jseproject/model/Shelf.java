package io.altar.jseproject.model;

import java.util.List;
import java.security.PrivateKey;
import java.util.ArrayList;
import io.altar.jseproject.repositories.ProductRepository;

public class Shelf extends Entity { 

	private String nameString;
	private int capacity;
	private long productId;
	private float dailyPrice;

	public Shelf() {
	}

	public Shelf(String nameString, int capacity, float dailyPrice) {
		this.nameString = nameString;
		this.capacity = capacity;
		this.dailyPrice = dailyPrice;
	}

	public Shelf(String nameString, int capacity, long productId, float dailyPrice) {
		this.nameString = nameString;
		this.capacity = capacity;
		this.productId = productId;
		this.dailyPrice = dailyPrice;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public float getDailyPrice() {
		return dailyPrice;
	}

	public void setDailyPrice(float dailyPrice) {
		this.dailyPrice = dailyPrice;
	}

	@Override
	public String toString() {
		return "Shelf [shelfName=" + nameString + ", capacity=" + capacity + ", productId=" + productId + ", dailyPrice=" + dailyPrice + "]";
	}

	public String getNameString() {
		return nameString;
	}

	public void setNameString(String nameString) {
		this.nameString = nameString;
	}
}