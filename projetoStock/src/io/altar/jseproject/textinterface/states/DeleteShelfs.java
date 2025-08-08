package io.altar.jseproject.textinterface.states;

import java.util.Collection;

import io.altar.jseproject.model.Shelf;
import io.altar.jseproject.repositories.ShelfRepository;
import io.altar.jseproject.textinterface.states.ReadShelfs;

public class DeleteShelfs extends State{
	private ShelfRepository DBS = ShelfRepository.getInstance();
	public int on() {
		 System.out.println("------ Lista de Prateleiras ------");
			
			Collection<Shelf> allShelfs = DBS.getAll();
			
			if(allShelfs.isEmpty()) {
				System.out.println("Não há prateleiras");
			} else {
				for (Shelf shelf : allShelfs) {
					System.out.println(shelf.toString()); 
				}
			}
			
			System.out.println("---------------------------------");
			long removeShelf = sc.getLong("Qual prateleira desejas remover?");
			DBS.removeEntity(removeShelf);
			
		return 1;
	}
}
