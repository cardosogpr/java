package io.altar.jseproject.controllers;

import java.security.spec.PSSParameterSpec;
import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import io.altar.jseproject.model.Product;
import io.altar.jseproject.services.ProductService;

@Path("products")
public class ProductController {
	
	ProductService PS = new ProductService();
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
	public Response addProduct(Product p) {
	    long newProductId = PS.addEntity(p);
	    String message = "O Produto com o ID " + newProductId + " foi criado.";
	    

	    return Response.status(Response.Status.CREATED)
	                   .entity(message)
	                   .build();
	}
	
	@PUT
	@Path("edit/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response editProduct(Product entity) {
	   PS.editEntity(entity); 
	   
	   return Response.status(Response.Status.OK)
               .entity("Foi editado com sucesso!")
               .build();
	}
	
    @DELETE
    @Path("delete/{id}")
    public Response deleteProduct(@PathParam("id") long id) {
        PS.removeEntity(id);
        
        return Response.status(Response.Status.OK)
                .entity("Foi apagado com sucesso!")
                .build();
    }
    
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Product> getAll() {
        return PS.getAll();
    }
    
 // Método para obter um produto por ID
    @GET
    @Path("view/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductById(@PathParam("id") long id) {
        // Busca o produto pelo ID
        Product product = PS.getEntity(id);
        
        // Se o produto não for encontrado, devolve um 404
        if (product == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        
        // Se o produto for encontrado, devolve-o com o código 200 OK
        return Response.ok(product).build();
    }
}
