package io.altar.jseproject.textinterface.states;

import java.util.Collection;

import io.altar.jseproject.model.Product;
import io.altar.jseproject.repositories.ProductRepository;
import io.altar.jseproject.services.ProductService;

public class DeleteProducts extends State {
	private ProductService PS = new ProductService();
	
	public int on() {
		
Collection<Product> allProducts = PS.getAll();
		
		if(allProducts.isEmpty()) {
			System.out.println("Não há produtos");
		} else {
			for (Product product : allProducts) {
				System.out.println(product.toString()); 
			}
		}
		
		System.out.println("---------------------------------");
		long removeProduct = sc.getLong("Qual produto desejas remover?");
		PS.removeEntity(removeProduct);
		
		return 1;
	}
}
