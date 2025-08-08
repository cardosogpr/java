package io.altar.jseproject.repositories.interfaces;

import java.util.Map;

import io.altar.jseproject.model.Entity;
import io.altar.jseproject.model.Product;
import io.altar.jseproject.repositories.EntityRepository;
import utils.CRUD_Interface;

public abstract interface EntityInterface<T extends Entity> extends CRUD_Interface<T>{

	boolean isEmpty();
}
