package io.altar.jseproject.DTOs;

import java.util.List;

public class ViewProductDTO {
	
	  private long id;
	    private List<Long> shelvesIds;
	    private String nameString;
	    private double discount;
	    private double iva;
	    private float pvp;
	    
		public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
		}
		public List<Long> getShelvesIds() {
			return shelvesIds;
		}
		public void setShelvesIds(List<Long> shelvesIds) {
			this.shelvesIds = shelvesIds;
		}
		public String getNameString() {
			return nameString;
		}
		public void setNameString(String nameString) {
			this.nameString = nameString;
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
		
		public ViewProductDTO(long id, List<Long> shelvesIds, String nameString, double discount, double iva,
				float pvp) {
			super();
			this.id = id;
			this.shelvesIds = shelvesIds;
			this.nameString = nameString;
			this.discount = discount;
			this.iva = iva;
			this.pvp = pvp;
		}
		
		public ViewProductDTO() {
			super();
			// TODO Auto-generated constructor stub
		}
		
}
