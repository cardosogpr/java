package io.altar.jseproject.textinterface.states;

import java.util.Collection;

import io.altar.jseproject.model.Shelf;
import io.altar.jseproject.repositories.ShelfRepository;
import io.altar.jseproject.services.ShelfServices;
import io.altar.jseproject.textinterface.states.ReadShelfs;

public class DeleteShelfs extends State {
	
	private ShelfServices SS = new ShelfServices();
	public int on() {
		 System.out.println("------ Lista de Prateleiras ------");
			
			Collection<Shelf> allShelfs = SS.getAll();
			
			if(allShelfs.isEmpty()) {
				System.out.println("Não há prateleiras");
			} else {
				for (Shelf shelf : allShelfs) {
					System.out.println(shelf.toString()); 
				}
			}
			
			System.out.println("---------------------------------");
			long removeShelf = sc.getLong("Qual prateleira desejas remover?");
			SS.removeEntity(removeShelf);
			
		return 1;
	}
}
