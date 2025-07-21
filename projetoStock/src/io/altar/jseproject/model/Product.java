package io.altar.jseproject.model;

import java.util.List;

import io.altar.jseproject.repositories.ProductRepository;
import io.altar.jseproject.repositories.ShelfRepository;
import java.util.ArrayList;

public class Product extends Entity { // Added 'extends Entity'

    // productId is now inherited from Entity as entityId, so it can be removed
    // private int productId; 
    private String productNameString;
    private Shelf shelf;
    private double preco;
    private double iva;
    private double precoFinal;
    private Long shelfId;


    public Product() {
        // super() is implicitly called, but can be added explicitly if needed
        super();
        // this.shelfId = shelfId; // This line seems incorrect as shelfId is not passed to constructor
    }

    public Product(Long productId, long shelfId, String productNameString, double preco, double iva, double precoFinal) { // Changed productId to Long
        super();
        this.setEntityId(productId); // Set the inherited entityId
        this.shelf = shelf;
        this.productNameString = productNameString;
        this.preco = preco;
        this.iva = iva;
        this.shelfId = shelfId;
        this.precoFinal = precoFinal;
        execPrecoFinal();
    }


    // getProductId() and setProductId() should now refer to getEntityId() and setEntityId() from Entity
    public Long getProductId() { // Changed return type to Long
        return getEntityId();
    }

    public void setProductId(Long productId) { // Changed parameter type to Long
        setEntityId(productId);
    }
    
    public Shelf getShelf() {
        return shelf;
    }

    public void setShelf(Shelf shelf) { // <-- Recebe o objeto Product
        this.shelf = shelf;
    }

    public Long getShelfId() {
    	return (shelf != null) ? shelf.getEntityId() : null; 
    }

    public void setShelfId(Long shelfId) {
        if (shelfId != null) {
        	this.shelf = ShelfRepository.getInstance().getById(shelfId);
        } else {
        	this.shelf = null;
        }
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
        execPrecoFinal();
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
        execPrecoFinal();
    }

    public double getPrecoFinal() {
        return precoFinal;
    }

    public void setPrecoFinal(double precoFinal) {
        this.precoFinal = precoFinal;
    }

    private void execPrecoFinal() {
        this.precoFinal = this.preco  * (1 + this.iva);
    }

	public String getProductNameString() {
		return productNameString;
	}

	public void setProductNameString(String productNameString) {
		this.productNameString = productNameString;
	}

	@Override
	public String toString() {
		return "Product [productId="+ getEntityId()+", shelfId=" + getShelfId() + ", productNameString=" + productNameString + ", preco=" + preco
				+ ", iva=" + iva + ", precoFinal=" + precoFinal + "]";
	}
}