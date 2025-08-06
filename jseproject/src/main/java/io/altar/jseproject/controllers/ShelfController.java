package io.altar.jseproject.controllers;

import javax.ws.rs.Produces;

import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import io.altar.jseproject.model.Product;
import io.altar.jseproject.model.Shelf;
import io.altar.jseproject.services.ProductService;
import io.altar.jseproject.services.ShelfServices;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("shelves")
public class ShelfController {
	
	ShelfServices SS = new ShelfServices();
	@Context
	protected UriInfo ctxInfo;

	@GET
	@Path("status")
	@Produces(MediaType.TEXT_PLAIN)
	public String status() {
		return ctxInfo.getRequestUri().toString() + " Is ok ";
	 }
	
	@POST
	@Path("add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public long addProduct(Shelf s) {
		return SS.addEntity(s);
	}
	
	@PUT
	@Path("edit/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public void editProduct(Shelf entity) {
	   SS.editEntity(entity); 
	}
	
    @DELETE
    @Path("delete/{id}")
    public void deleteProduct(@PathParam("id") long id) {
        SS.removeEntity(id);
    }
    
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Shelf> getAll() {
        return SS.getAll();
    }
    
 // Método para obter um produto por ID
    @GET
    @Path("view/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductById(@PathParam("id") long id) {
        // Busca o produto pelo ID
        Shelf shelf = SS.getEntity(id);
        
        // Se o produto não for encontrado, devolve um 404
        if (shelf == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        
        // Se o produto for encontrado, devolve-o com o código 200 OK
        return Response.ok(shelf).build();
    }
}
