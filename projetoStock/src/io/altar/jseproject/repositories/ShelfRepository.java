package io.altar.jseproject.repositories;

import io.altar.jseproject.model.Product;
import io.altar.jseproject.model.Shelf;
import io.altar.jseproject.repositories.interfaces.ShelfInterface;

//apenas serve para herdar a classe
public class ShelfRepository extends EntityRepository<Shelf> implements ShelfInterface {
	
	private static final ShelfRepository INSTANCE = new ShelfRepository(); 

	public static ShelfRepository getInstance() {
		return INSTANCE;
	  }
	
	private ShelfRepository() {
		
	 }
}
