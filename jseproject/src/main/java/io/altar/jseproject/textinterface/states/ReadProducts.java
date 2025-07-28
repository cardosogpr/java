package io.altar.jseproject.textinterface.states;

import io.altar.jseproject.model.Product;

import java.util.Collection;
import io.altar.jseproject.repositories.ProductRepository;
import io.altar.jseproject.services.ProductService;

public class ReadProducts extends State {
	
	private ProductService PS = new ProductService();
@Override
	public int on() {
	
		System.out.println("------ Lista de Produtos ------");
		
		Collection<Product> allProducts = PS.getAll();
		
		if(allProducts.isEmpty()) {
			System.out.println("Não há produtos");
		} else {
			for (Product product : allProducts) {
				System.out.println(product.toString()); 
			}
		}
		
		System.out.println("---------------------------------");
		
		return 1;
	}
}
