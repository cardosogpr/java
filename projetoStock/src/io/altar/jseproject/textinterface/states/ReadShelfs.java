package io.altar.jseproject.textinterface.states;

import java.util.Collection;

import io.altar.jseproject.model.Product;
import io.altar.jseproject.model.Shelf;
import io.altar.jseproject.repositories.ShelfRepository;

public class ReadShelfs extends State{
	private ShelfRepository DBS = ShelfRepository.getInstance();
	public int on() {
       System.out.println("------ Lista de Prateleiras ------");
		
		Collection<Shelf> allShelfs = DBS.getAll();
		
		if(allShelfs.isEmpty()) {
			System.out.println("Não há produtos");
		} else {
			for (Shelf shelf : allShelfs) {
				System.out.println(shelf.toString()); 
			}
		}
		
		System.out.println("---------------------------------");
		
		return 1;
	}
}
