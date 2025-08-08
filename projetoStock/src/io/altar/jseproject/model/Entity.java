package io.altar.jseproject.model;

import utils.DisallowModificationAttributeException; // Certifique-se de que o caminho 'utils' está correto

public abstract class Entity { // É boa prática ser 'abstract'
	
	protected long id = 0L; // O id é inicializado como 0L para indicar "não atribuído"

	public long getId() {
		return id;
	}

	public void setId(long id) {
		// Condição para lançar exceção:
		// Se o ID já foi definido (ou seja, não é 0L)
		// E o novo ID que está a ser passado é DIFERENTE do ID atual
		if (this.id != 0L && this.id != id) { 
			throw new DisallowModificationAttributeException("ID cannot be modified once set to a different value.");
		}
		
		// Condição para definir o ID PELA PRIMEIRA VEZ:
		// Se o ID atual da entidade ainda não foi definido (é 0L)
		// E o novo ID que está a ser passado é um ID válido (positivo)
		if (this.id == 0L && id > 0) { 
			this.id = id;
		}
		// Se this.id não é 0L E this.id == id, não faz nada (isto é para atualizações, onde o ID não muda)
		// Se this.id é 0L mas o 'id' passado é <= 0, também não faz nada (não atribui um ID inválido)
	}
}