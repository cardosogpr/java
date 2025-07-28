package io.altar.jseproject.textinterface.states;

import io.altar.jseproject.repositories.ShelfRepository;
import io.altar.jseproject.services.ShelfServices;

public class MenuShelfs extends State {
	private ShelfServices SS = new ShelfServices();
	
	@Override
	public int on() {
		SS.getAllIds().forEach(id -> System.out.println("ID da Prateleira: " + id));
		int [] options = new int [] { 1,5 };
		System.out.println("1)\t Criar Prateleira");
		if (!SS.isEmpty()) {
			options = new int[] { 1,2,3,4,5};
			System.out.println("2)\t Editar Prateleira");
			System.out.println("3)\t Consultar Prateleira");
			System.out.println("4)\t Remover Prateleira");
		}
		System.out.println("5)\t Voltar");
		
		return sc.getValidInt("Selecionar opção : ", options);
	}
}
