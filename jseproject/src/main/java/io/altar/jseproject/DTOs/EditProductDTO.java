package io.altar.jseproject.DTOs;

import java.util.List;

public class EditProductDTO {
	
    private Long id; 
    private List<Long> shelvesIds;
    private String nameString;
    private double discount;
    private double iva;
    private float pvp;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
}
