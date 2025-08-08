package io.altar.jseproject.repositories;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger; // Se estiver a usar o Logger noutro local, caso contrário, pode remover
import java.util.Collection;



import io.altar.jseproject.model.Entity; // Certifique-se que o caminho está correto

public abstract class EntityRepository<T extends Entity> {

	private Map<Long, T> map = new HashMap<Long, T>();

	private long currentID = 1L;

	public long addEntity(T entity) { // O retorno é 'long' como pediste
	    Long entityId = entity.getId(); 

	    if (entityId == null || entityId.equals(0L)) { // Se é NOVA entidade (sem ID ou ID 0)
	        entity.setId(currentID);   // Define o ID (ex: 1, 2, 3...)
	        map.put(entity.getId(), entity); // Adiciona ao mapa com o ID correto
	        return currentID++;        // Retorna o ID atribuído e INCREMENTA para o próximo
	    } else { // A entidade JÁ TEM um ID (atualização)
	        map.put(entityId, entity); 
	        return entityId; 
	    }

    }

	public Set<Long> getAllIds() {
		return map.keySet();
	}
	
	public Collection<T> getAll() {
		return map.values();
	}

	public T getEntity(long id) {
		T entity = map.get(id);
		return entity;
	}

	public void editEntity(T entity) {
		map.put(entity.getId(), entity);
	}

	public void removeEntity(long id) {
		map.remove(id);
	}

	public boolean isEmpty() {
		return (map.size() == 0) ? true : false;
	}
	
	public long size() {
		return map.size();
	}
}