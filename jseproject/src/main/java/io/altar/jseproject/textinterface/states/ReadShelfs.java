package io.altar.jseproject.textinterface.states;

import java.util.Collection;

import io.altar.jseproject.model.Product;
import io.altar.jseproject.model.Shelf;
import io.altar.jseproject.repositories.ShelfRepository;
import io.altar.jseproject.services.ShelfServices;

public class ReadShelfs extends State{
	private ShelfServices SS = new ShelfServices();
	public int on() {
       System.out.println("------ Lista de Prateleiras ------");
		
		Collection<Shelf> allShelfs = SS.getAll();
		
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
