package io.altar.jseproject.textinterface.states;

import io.altar.jseproject.model.Product;
import io.altar.jseproject.repositories.ProductRepository;
import io.altar.jseproject.services.ProductService;
import utils.ScannerUtils;

public class CreateProducts extends State {
	private ProductService PS = new ProductService();
	
	public int on() {
		
		System.out.println("Nome do Produto:");	
		String nameString = sc.getValue();
		double discount = sc.getDouble("Insira o desconto:");
		double iva = sc.getDouble("Insira o IVA:");
		float pvp = sc.getFloat("Insira o preço:");
		Product product = new Product(nameString, discount, iva, pvp);
		PS.addEntity(product);
				
		return 1;
	}
}
