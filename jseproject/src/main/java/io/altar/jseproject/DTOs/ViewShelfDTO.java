package io.altar.jseproject.DTOs;

public class ViewShelfDTO {
	
	private Long id; 
	private String nameString;
	private int capacity;
	private long productId;
	private float dailyPrice;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNameString() {
		return nameString;
	}
	public void setNameString(String nameString) {
		this.nameString = nameString;
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
	
	public ViewShelfDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

}
