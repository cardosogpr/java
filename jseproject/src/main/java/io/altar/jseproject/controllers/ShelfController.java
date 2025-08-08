package io.altar.jseproject.controllers;

import javax.ws.rs.Produces;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import io.altar.jseproject.DTOs.CreateShelfDTO;
import io.altar.jseproject.DTOs.EditProductDTO;
import io.altar.jseproject.DTOs.EditShelfDTO;
import io.altar.jseproject.model.Product;
import io.altar.jseproject.model.Shelf;
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
	public Response addShelf(CreateShelfDTO shelfCriarDto) {
		try {
			// Validação para garantir que a capacidade é um valor positivo
            if (shelfCriarDto.getCapacity() <= 0) {
                return Response.status(Response.Status.BAD_REQUEST)
                               .entity("O valor da capacidade deve ser superior a zero.")
                               .build();
            }

            // Validação para garantir que o ID do produto não é zero ou negativo
            if (shelfCriarDto.getProductId() <= 0) {
                 return Response.status(Response.Status.BAD_REQUEST)
                               .entity("O ID do produto não pode ser zero ou negativo.")
                               .build();
            }
            
            // Validação para garantir que o preço diário é um valor positivo
            if (shelfCriarDto.getDailyPrice() <= 0) {
            	return Response.status(Response.Status.BAD_REQUEST)
                        .entity("O preço diário deve ser superior a zero.")
                        .build();
            }

            // Se as validações passarem, criar a nova prateleira
            // O construtor agora aceita diretamente o long productId
            Shelf novaPrateleira = new Shelf(
            		shelfCriarDto.getNameString(),
            		shelfCriarDto.getCapacity(),
            		shelfCriarDto.getProductId(),
            		shelfCriarDto.getDailyPrice()
            );
            
            SS.addEntity(novaPrateleira);

            return Response.status(Response.Status.CREATED)
                           .entity("Prateleira criada com sucesso!")
                           .build();

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("Ocorreu um erro ao criar a prateleira: " + e.getMessage())
                           .build();
        }
	}
	
	@PUT
	@Path("edit/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response editShelf(@PathParam("id") long id, EditShelfDTO shelfEditarDto) {
	       try {
	            // Tentar obter a prateleira existente pelo ID
	            Shelf shelfExistente = SS.getEntity(id);

	            // Se a prateleira não for encontrada, retornar 404
	            if (shelfExistente == null) {
	                return Response.status(Response.Status.NOT_FOUND)
	                               .entity("Prateleira com ID " + id + " não encontrada.")
	                               .build();
	            }

	            // Atualizar apenas os campos que foram modificados no DTO
	            if (shelfEditarDto.getCapacity() > 0) { // Assumindo que a capacidade deve ser um valor positivo
	                shelfExistente.setCapacity(shelfEditarDto.getCapacity());
	            } else if (shelfEditarDto.getCapacity() <= 0) {
	                 return Response.status(Response.Status.BAD_REQUEST)
                             .entity("O valor da capacidade não pode ser 0 ou negativo.")
                             .build();
	            }
	            
	            // A verificar se o DTO contém um único ID de produto para atualização
	            if (shelfEditarDto.getProductId() > 0) {
	                Long productId = shelfEditarDto.getProductId();
	                shelfExistente.setProductId(productId);
	            } else if (shelfEditarDto.getProductId() <= 0) {
	                 return Response.status(Response.Status.BAD_REQUEST)
                             .entity("O valor do ID do Produto não pode ser 0 ou negativo.")
                             .build();
	            }
	            
	            if (shelfEditarDto.getNameString() != null) {
		            shelfExistente.setNameString(shelfEditarDto.getNameString());
		        }

	            // Chamar o serviço para editar a prateleira
	            SS.editEntity(shelfExistente);

	            return Response.status(Response.Status.OK)
	                           .entity("Prateleira com ID " + id + " editada com sucesso!")
	                           .build();

	        } catch (Exception e) {
	            // Em caso de erro, retornar 500 Internal Server Error
	            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
	                           .entity("Ocorreu um erro ao editar a prateleira: " + e.getMessage())
	                           .build();
	        }
	       
	}	 	
	
    @DELETE
    @Path("delete/{id}")
    public Response deleteShelf(@PathParam("id") long id) {
        SS.removeEntity(id);
        
        return Response.status(Response.Status.OK)
                .entity("Foi apagado com sucesso!")
                .build();
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
    public Response getShelfById(@PathParam("id") long id) {
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


