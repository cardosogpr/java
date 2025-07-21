package io.altar.jseproject.model;

import java.util.List;
import java.security.PrivateKey;
import java.util.ArrayList;
import io.altar.jseproject.repositories.ProductRepository;

public class Shelf extends Entity { 

	private String shelfNameString;
    private int Capacidade;
    private Product product;
    private double precoAluguerDiario;

    public Shelf(String shelfNameString, long shelfId, int capacidade, Product product, double precoAluguerDiario) { 
        this.shelfNameString = shelfNameString;
    	this.setEntityId(shelfId); // Set the inherited entityId
        this.Capacidade = capacidade;
        this.product = product;
        this.precoAluguerDiario = precoAluguerDiario;
    }

    public Shelf() {
        super();
    }

    // getShelfId() and setShelfId() should now refer to getEntityId() and setEntityId() from Entity
    public Long getShelfId() { 
        return getEntityId();
    }

    public void setShelfId(Long shelfId) { 
        setEntityId(shelfId);
    }

    public int getCapacidade() {
        return Capacidade;
    }

    public void setCapacidade(int capacidade) {
        Capacidade = capacidade;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) { // <-- Recebe o objeto Product
        this.product = product; // A prateleira passa a ter uma referência ao objeto Product
        if (product != null) {
        	product.setShelfId(this.getEntityId());
        }
    }

    public double getPrecoAluguerDiario() {
        return precoAluguerDiario;
    }

    public void setPrecoAluguerDiario(double precoAluguerDiario) {
        this.precoAluguerDiario = precoAluguerDiario;
    }

     
	public String getShelfNameString() {
		return shelfNameString;
	}

	public void setShelfNameString(String shelfNameString) {
		this.shelfNameString = shelfNameString;
	}
	
    public Long getProductId() { 
        // Se a prateleira não tem um produto, retorna null para o ID do produto
        return (product != null) ? product.getEntityId() : null; 
    }

	@Override
	public String toString() {
		return "Shelf [shelfId=" + getEntityId() +", shelfNameString=" + shelfNameString + ", Capacidade=" + Capacidade + ", productId=" + getProductId()
				+ ", precoAluguerDiario=" + precoAluguerDiario + "]";
	}
}