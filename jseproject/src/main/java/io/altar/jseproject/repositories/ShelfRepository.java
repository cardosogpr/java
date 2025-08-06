package io.altar.jseproject.repositories;

import io.altar.jseproject.model.Product;
import io.altar.jseproject.model.Shelf;
import io.altar.jseproject.repositories.interfaces.ShelfInterface;

//apenas serve para herdar a classe
public class ShelfRepository extends EntityRepository<Shelf> implements ShelfInterface {
	
	private static final ShelfRepository INSTANCE = new ShelfRepository(); 
	private ProductRepository productRepository = ProductRepository.getInstance();

	public static ShelfRepository getInstance() {
		return INSTANCE;
	  }
	
	private ShelfRepository() {
		
	 }
	
	 public void removeEntity(long id) {
	        //Encontra e remove a prateleira do ID de todos os produtos
	        for (Product product : productRepository.getAll()) {
	            if (product.getShelvesIds() != null && product.getShelvesIds().contains(id)) {
	                product.setShelvesIds(null); // Quebra a associação
	            }
	        }
	        
	        super.removeEntity(id);
 }
	 public void editEntity(long id) {
		//faz a mesma coisa mas é só para 1 Prateleira apenas ter 1 Produto.
	        for (Product product : productRepository.getAll()) {
	            if (product.getShelvesIds() != null && product.getShelvesIds().contains(id)) {
	                product.setShelvesIds(null); // Quebra a associação
	            }
	        }
	        
	        super.removeEntity(id);
}
}
