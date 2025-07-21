package io.altar.jseproject.repositories;

import java.nio.channels.ClosedByInterruptException;
import java.security.Identity;
import java.security.PublicKey;

import org.omg.DynamicAny.DynValueBox;

import io.altar.jseproject.model.Product;

//apenas serve para herdar a classe
public class ProductRepository extends EntityRepository<Product>{
	
private static final ProductRepository INSTANCE = new ProductRepository(); 

public static ProductRepository getInstance() {
	return INSTANCE;
  }
private ProductRepository() {
	
 }

public void addProduct(Product product) {
	System.out.println("Estás a adicionar o produto " + product.getProductNameString());
	super.addEntity(product);
	System.out.println("O produto" + product.getProductNameString() + "foi adicionado com sucesso");
}

public void removeProduct(Long productId) {
	System.out.println("Estás a remover o produto" + productId);
	super.remove(productId);
	System.out.println("O produto com o ID" + productId + "foi removido com sucesso");	
 }

public void editProduct(Product product) {
	if (product != null && product.getEntityId() != null) {
	System.out.println("Estás a editar o produto:" + product.getProductNameString() + "com o ID:" + product.getEntityId());
	super.edit(product);	
	System.out.println("O produto com o ID" + product.getEntityId()+ "foi editado com sucesso");
	} else {
		System.err.println("Oops... Não foi possível editar o produto");
	}
	
 }
public Product getProductById(Long productId) {
    System.out.println("Buscando produto com ID: " + productId);
    return super.getById(productId); // <-- Usa o método getById da superclasse!
}
}


