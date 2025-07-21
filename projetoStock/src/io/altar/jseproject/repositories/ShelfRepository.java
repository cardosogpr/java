package io.altar.jseproject.repositories;

import io.altar.jseproject.model.Product;
import io.altar.jseproject.model.Shelf;

//apenas serve para herdar a classe
public class ShelfRepository extends EntityRepository<Shelf> {
	private static final ShelfRepository INSTANCE = new ShelfRepository(); 

	public static ShelfRepository getInstance() {
		return INSTANCE;
	  }
	private ShelfRepository() {
		
	 }
	public void addShelf(Shelf shelf) {
		System.out.println("Estás a adicionar a prateleira " + shelf.getShelfNameString());
		super.addEntity(shelf);
		System.out.println("A prateleira" + shelf.getShelfNameString() + "foi adicionada com sucesso");
	}
	public void removeShelf(Long shelfLong) {
		System.out.println("Estás a remover a prateleira " + shelfLong);
		super.remove(shelfLong);
		System.out.println("A prateleira com o ID" + shelfLong + "foi removido com sucesso");
	}
	public void editShelf(Shelf shelf) {
		if (shelf != null && shelf.getEntityId() != null) {
		System.out.println("Estás a editar a prateleira:" + shelf.getShelfNameString() + "com o ID" + shelf.getEntityId());
	    super.edit(shelf);
	    System.out.println("A prateleira com o ID:" + shelf.getEntityId() + "foi editado com sucesso");
		} else {
			System.err.println("Oops... Não foi possível editar a prateleira");
		}
	}
}
