package io.altar.jseproject.textinterface.states;

import io.altar.jseproject.model.Product;
import io.altar.jseproject.model.Shelf;
import io.altar.jseproject.repositories.ProductRepository;
import io.altar.jseproject.repositories.ShelfRepository;

public class CreateShelfs extends State {
	private ShelfRepository DBS = ShelfRepository.getInstance();
	private ProductRepository DBP = ProductRepository.getInstance();
	
	public int on() {
		System.out.println("Nome da Prateleira:");	
		String nameString = sc.getValue();
		int capacity = sc.getInt("Insira a capacidade:");
		float dailyPrice = sc.getFloat("Insira o preço Diário:");
		Shelf shelf = new Shelf(nameString, capacity, dailyPrice);
		System.out.println("Deseja associar um produto existente a esta prateleira? (s/n)");
	    String resposta = sc.getValue().trim().toLowerCase();
	    
	    Long associatedProductId = null;
	    
	    if (resposta.equals("s")) {
	        long productId = sc.getLong("Insira o ID do produto a associar:");
	        
	        Product productToAssociate = DBP.getEntity(productId);
	        
	        if (productToAssociate != null) {
	            shelf.setProductId(productId); 
	            associatedProductId = productId;
	            System.out.println("Produto com ID " + productId + " associado à prateleira.");
	        } else {
	            System.out.println("Aviso: Produto com ID " + productId + " não encontrado. A prateleira será criada sem produto associado.");
	        }
	    }
	        
	    
		DBS.addEntity(shelf);
		
		if (associatedProductId != null) { 
            Product productToUpdate = DBP.getEntity(associatedProductId);
            if (productToUpdate != null) {
                productToUpdate.addShelfId(shelf.getId()); 
                DBP.addEntity(productToUpdate);
                System.out.println("Produto com ID " + associatedProductId + " atualizado com o ID da Prateleira: " + shelf.getId());
            }
        }
				
		return 1;
	}
}
