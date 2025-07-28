package io.altar.jseproject.textinterface.states;

import java.util.Collection;

import io.altar.jseproject.model.Product;
import io.altar.jseproject.model.Shelf;
import utils.ScannerUtils;
import io.altar.jseproject.repositories.ShelfRepository;
import io.altar.jseproject.services.ShelfServices;

public class UpdateShelfs extends State {
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
			long editShelfs = sc.getLong("Qual prateleira desejas editar? (Insere o ID): ");
			
			Shelf shelftoEditShelf = SS.getEntity(editShelfs);
			
			if(shelftoEditShelf != null) {
				String nameString = sc.getValue();
				shelftoEditShelf.setNameString(nameString);
				int capacity = sc.getInt("Insira a capacidade:");
				shelftoEditShelf.setCapacity(capacity);
				float dailyPrice = sc.getFloat("Insira o preço Diário:");
                shelftoEditShelf.setDailyPrice(dailyPrice);
				SS.editEntity(shelftoEditShelf);
		    }
		    else {
		    	System.out.println("Oops... Não foi encontrado nenhum produto com o ID");
		    }
		    
			
		return 1;
	}
}
