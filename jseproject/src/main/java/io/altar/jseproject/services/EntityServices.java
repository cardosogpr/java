package io.altar.jseproject.services;

import io.altar.jseproject.model.Entity;
import io.altar.jseproject.repositories.EntityRepository;

public abstract class EntityServices <T extends EntityRepository<U>, U extends Entity> {	
	T repository;

}
