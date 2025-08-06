package io.altar.jseproject.services;

import java.util.Collection;
import java.util.Set;

import io.altar.jseproject.model.Product;
import io.altar.jseproject.model.Shelf;
import io.altar.jseproject.repositories.ProductRepository;
import io.altar.jseproject.repositories.ShelfRepository;

public class ProductService extends EntityServices {

	private ProductRepository DBP = ProductRepository.getInstance();
	private ShelfRepository DBS = ShelfRepository.getInstance();
	
	
	public long addEntity(Product entity) { 
          return DBP.addEntity(entity);
    }

	public Set<Long> getAllIds() {
		return DBP.getAllIds();
	}
	
	public Collection<Product> getAll() {
		return DBP.getAll();
	}

	public Product getEntity(long id) {
        return DBP.getEntity(id);
	}

	public void editEntity(Product entity) {
		DBP.editEntity(entity);
	}

	public void removeEntity(long id) {
		DBP.removeEntity(id);
	}

	public boolean isEmpty() {
		return DBP.isEmpty();
	}
	
	public long size() {
		return DBP.size();
	}
}
