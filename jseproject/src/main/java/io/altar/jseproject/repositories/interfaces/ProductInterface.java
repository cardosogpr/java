package io.altar.jseproject.repositories.interfaces;

import java.util.List;
import io.altar.jseproject.model.Product;

public interface ProductInterface extends EntityInterface<Product> {
	
	List<Long> getShelfIdsByProductId(long productId);
	
}
