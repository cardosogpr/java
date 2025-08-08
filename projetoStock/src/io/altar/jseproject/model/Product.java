package io.altar.jseproject.model;

import java.util.ArrayList;
import java.util.List;

public class Product extends Entity {

	private List<Long> shelvesIds = new ArrayList<Long>();
	private String nameString;
	private double discount;
	private double iva;
	private float pvp;
	
	
	public Product() {}
	
	public Product(String nameString, double discount, double iva, float pvp) {
		this.nameString = nameString;
		this.discount = discount;
		this.iva = iva;
		this.pvp = pvp;
	}

	public Product(List<Long> shelvesIds, String nameString , double discount, double iva, float pvp) {
		this.shelvesIds = shelvesIds;
		this.nameString = nameString;
		this.discount = discount;
		this.iva = iva;
		this.pvp = pvp;
	}
	
	public List<Long> getShelvesIds() {
		return shelvesIds;
	}
	
	public void setShelvesIds(List<Long> shelvesIds) {
		this.shelvesIds = shelvesIds;
	}
	
	public void addShelfId(long shelfId) {
		this.shelvesIds.add(shelfId);
	}
	
	public void removeShelfId(long shelfId) {
		this.shelvesIds.remove(shelfId);
	}
	
	public double getDiscount() {
		return discount;
	}
	
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	
	public double getIva() {
		return iva;
	}
	public void setIva(double iva) {
		this.iva = iva;
	}
	public float getPvp() {
		return pvp;
	}
	
	public void setPvp(float pvp) {
		this.pvp = pvp;
	}

	@Override
	public String toString() {
		return "Product [productName=" + nameString + ", shelvesIds=" + shelvesIds + ", discount=" + discount + ", iva=" + iva + ", pvp="
				+ pvp + "]";
	}

	public String getNameString() {
		return nameString;
	}

	public void setNameString(String nameString) {
		this.nameString = nameString;
	}
	
}