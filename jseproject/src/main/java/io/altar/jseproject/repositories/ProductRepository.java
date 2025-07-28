package io.altar.jseproject.repositories;

import java.nio.channels.ClosedByInterruptException;
import java.security.PublicKey;
import java.util.List;
import org.omg.DynamicAny.DynValueBox;

import io.altar.jseproject.model.Product;
import io.altar.jseproject.repositories.interfaces.ProductInterface;

//apenas serve para herdar a classe
public class ProductRepository extends EntityRepository<Product> implements ProductInterface {
	
private static final ProductRepository INSTANCE = new ProductRepository(); 

public static ProductRepository getInstance() {
	return INSTANCE;
  }

private ProductRepository() {
}
	@Override
	public List<Long> getShelfIdsByProductId(long productId) {
		
		return null;
	}
}




