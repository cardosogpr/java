package io.altar.jseproject.services;

import java.util.Collection;
import java.util.Set;

import io.altar.jseproject.model.Shelf;
import io.altar.jseproject.repositories.ProductRepository;
import io.altar.jseproject.repositories.ShelfRepository;

public class ShelfServices extends EntityServices {
	
	private ShelfRepository DBS = ShelfRepository.getInstance();
	private ProductRepository DBP = ProductRepository.getInstance();
	
	public long addEntity(Shelf entity) { 
          return DBS.addEntity(entity);
    }

	public Set<Long> getAllIds() {
		return DBS.getAllIds();
	}
	
	public Collection<Shelf> getAll() {
		return DBS.getAll();
	}

	public Shelf getEntity(long id) {
        return DBS.getEntity(id);
	}

	public void editEntity(Shelf entity) {
		DBS.editEntity(entity);
	}

	public void removeEntity(long id) {
		DBS.removeEntity(id);
	}

	public boolean isEmpty() {
		return DBS.isEmpty();
	}
	
	public long size() {
		return DBS.size();
	}
	//mm cena q tá no CreateProducst ams alter para "SS"
}
