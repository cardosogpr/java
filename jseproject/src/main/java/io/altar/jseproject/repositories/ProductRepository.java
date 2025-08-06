package io.altar.jseproject.repositories;

import java.nio.channels.ClosedByInterruptException;
import java.security.PublicKey;
import java.util.List;
import org.omg.DynamicAny.DynValueBox;

import io.altar.jseproject.model.Product;
import io.altar.jseproject.model.Shelf;
import io.altar.jseproject.repositories.interfaces.ProductInterface;

//apenas serve para herdar a classe
public class ProductRepository extends EntityRepository<Product> implements ProductInterface {
	
private static final ProductRepository INSTANCE = new ProductRepository();
private ShelfRepository shelfRepository = ShelfRepository.getInstance();

public static ProductRepository getInstance() {
	return INSTANCE;
  }

private ProductRepository() {
}
	@Override
	public List<Long> getShelfIdsByProductId(long productId) {
		
		return null;
	}
	 public void removeEntity(long id) {
	        // Encontra e remove a prateleira do ID de todos os produtos
		 for (Shelf shelf : shelfRepository.getAll()) {
		        if (shelf.getProductId() == id) { //Comparação direta com o tipo primitivo 'long'
		            shelf.setProductId(0); // Quebra a associação, usando '0' ou outro valor
		        }
	        }
	        
	        super.removeEntity(id);
 }
	 //faz a mesma coisa mas é só para 1 Produto apenas ter 1 Prateleira.
	 public void editEntity(long id) {
		 for (Shelf shelf : shelfRepository.getAll()) {
		        if (shelf.getProductId() == id) { //Comparação direta com o tipo primitivo 'long'
		            shelf.setProductId(0); // Quebra a associação, usando '0' ou outro valor
		        }
	        }
	        
	        super.removeEntity(id);
}
}




