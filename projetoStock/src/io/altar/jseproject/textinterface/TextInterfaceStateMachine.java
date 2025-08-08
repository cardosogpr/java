package io.altar.jseproject.textinterface;

import io.altar.jseproject.textinterface.states.*;
import io.altar.jseproject.textinterface.states.MenuInit;

public class TextInterfaceStateMachine {
	// 2. states
	private State[] states = { new MenuInit(), // State 0
			new MenuProducts(), // State 1
			new MenuShelfs(), // State 2
			new CreateShelfs(), // State 3
			new UpdateShelfs(), // State 4
			new ReadShelfs(), // State 5
			new DeleteShelfs(), // State 6
			new CreateProducts(), // State 7
			new UpdateProducts(), // State 8
			new ReadProducts(), // State 9
			new DeleteProducts() };// State 10
	// 4. transitions
	private int[][] transition = { 
			{ 1, 2 }, 
			{ 7, 8, 9, 10, 0 }, 
			{ 3, 4, 5, 6, 0 }, 
			{ 2 }, 
			{ 2 }, 
			{ 2 }, 
			{ 2 }, 
			{ 1 },
			{ 1 }, 
			{ 1 }, 
			{ 1 } 
			};
	// 3. current
	private int current = 0;

	// 5. All client requests are simply delegated to the current state object
	public void start() {
		
		while(true) {
			int option = states[current].on();
			if (current == 0 && option == 3) {
				System.out.println("Saida");
				break;
			}
			current = transition[current][option-1];
		}
	}

}