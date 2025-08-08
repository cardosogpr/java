package io.altar.jseproject.textinterface.states;

import java.awt.image.DataBufferShort;
import java.util.Collection;

import io.altar.jseproject.model.Product;
import io.altar.jseproject.model.Shelf;
import io.altar.jseproject.repositories.ProductRepository;


public class UpdateProducts extends State {
	
private ProductRepository DBP = ProductRepository.getInstance();
	
	public int on() {
		System.out.println("------ Lista de Produtos ------");
		
		Collection<Product> allProducts = DBP.getAll();
		
		if(allProducts.isEmpty()) {
			System.out.println("Não há produtos");
		} else {
			for (Product product : allProducts) {
				System.out.println(product.toString()); 
			}
		}
		
		System.out.println("---------------------------------");
		long editProduct = sc.getLong("Qual produto desejas editar? (Insere o ID): ");
		
	    Product productToEdit = DBP.getEntity(editProduct);
	    
	    if(productToEdit != null) {
			System.out.println("Nome do Produto:");	
			String nameString = sc.getValue();
			productToEdit.setNameString(nameString);
			double discount = sc.getDouble("Insira o desconto:");
			productToEdit.setDiscount(discount);
			double iva = sc.getDouble("Insira o IVA:");
			productToEdit.setIva(iva);
			float pvp = sc.getFloat("Insira o preço:");
			productToEdit.setPvp(pvp);	
			DBP.editEntity(productToEdit);
	    }
	    else {
	    	System.out.println("Oops... Não foi encontrado nenhum produto com o ID");
	    }
		return 1;
	}
}
