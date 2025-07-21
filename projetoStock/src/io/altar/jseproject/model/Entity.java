package io.altar.jseproject.model;

public class Entity { 
	
	private static Long countId = 1L; 
	private Long entityId; 

    public Entity() {
        this.entityId = countId++;
    }

    public Long getEntityId() { 
        return entityId;	
    }

    public void setEntityId(Long entityId) { 
        this.entityId = entityId;
    }
}