package io.altar.jseproject.textinterface.states;

import java.util.Collection;

import io.altar.jseproject.model.Product;
import io.altar.jseproject.repositories.ProductRepository;

public class DeleteProducts extends State {
	private ProductRepository DBP = ProductRepository.getInstance();
	public int on() {
		
Collection<Product> allProducts = DBP.getAll();
		
		if(allProducts.isEmpty()) {
			System.out.println("Não há produtos");
		} else {
			for (Product product : allProducts) {
				System.out.println(product.toString()); 
			}
		}
		
		System.out.println("---------------------------------");
		long removeProduct = sc.getLong("Qual produto desejas remover?");
		DBP.removeEntity(removeProduct);
		
		return 1;
	}
}
