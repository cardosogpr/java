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

import io.altar.jseproject.DTOs.CreateProductDTO;
import io.altar.jseproject.DTOs.EditProductDTO;
import io.altar.jseproject.DTOs.ViewProductDTO;
import io.altar.jseproject.model.Product;
import io.altar.jseproject.model.Shelf;
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
public Response addProduct(CreateProductDTO productDto) {
        
        // Validações para a criação de um novo produto
        if (productDto.getShelvesIds() != null && !productDto.getShelvesIds().isEmpty()) {
            for (Long shelfId : productDto.getShelvesIds()) {
                if (shelfId <= 0) {
                    return Response.status(Response.Status.BAD_REQUEST)
                           .entity("A lista de IDs de prateleiras não pode conter valores zero ou negativos.")
                           .build();
                }
            }
        }
        
        if (productDto.getDiscount() < 0) {
            return Response.status(Response.Status.BAD_REQUEST)
                           .entity("O valor do desconto não pode ser negativo.")
                           .build();
        }

        if (productDto.getIva() < 0) {
            return Response.status(Response.Status.BAD_REQUEST)
                           .entity("O valor do IVA não pode ser negativo.")
                           .build();
        }
        
        if (productDto.getPvp() <= 0) {
            return Response.status(Response.Status.BAD_REQUEST)
                           .entity("O valor do Preço de Venda ao Público (PVP) deve ser superior a zero.")
                           .build();
        }

		Product newProduct = new Product(
				productDto.getShelvesIds(),
		        productDto.getNameString(),
		        productDto.getDiscount(),
		        productDto.getIva(),
		        productDto.getPvp()
		        
	);
		PS.addEntity(newProduct);
		
	    String message = "O Produto " + newProduct + " foi criado.";
	    return Response.status(Response.Status.CREATED)
	                   .entity(message)
	                   .build();
	}
	
	@PUT
	@Path("edit/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response editProduct(@PathParam("id") long id, EditProductDTO produtoEditarDto) {
		 try {
		        Product produtoExistente = PS.getEntity(id);

		        // Verifica se o produto foi encontrado
		        if (produtoExistente == null) {
		            return Response.status(Response.Status.NOT_FOUND)
		                           .entity("Produto com ID " + id + " não encontrado.")
		                           .build();
		        }

		        // Atualizar apenas os que queres modificar e não todos

		        if (produtoEditarDto.getShelvesIds() != null && !produtoEditarDto.getShelvesIds().isEmpty()) {
	                // Validação para garantir que os IDs de prateleiras não são 0 ou negativos
	                for (Long shelfId : produtoEditarDto.getShelvesIds()) {
	                    if (shelfId <= 0) {
	                        return Response.status(Response.Status.BAD_REQUEST)
	                               .entity("A lista de IDs de prateleiras não pode conter valores zero ou negativos.")
	                               .build();
	                    }
	                }
	                produtoExistente.setShelvesIds(produtoEditarDto.getShelvesIds());
	            }

		        if (produtoEditarDto.getNameString() != null) {
		            produtoExistente.setNameString(produtoEditarDto.getNameString());
		        }

		        if (produtoEditarDto.getDiscount() > 0) { // Assume que o desconto deve ser maior que 0 para ser válido
		            produtoExistente.setDiscount(produtoEditarDto.getDiscount());
		        } else if (produtoEditarDto.getDiscount() < 0) {
	                 return Response.status(Response.Status.BAD_REQUEST)
                             .entity("O valor do desconto não pode ser negativo.")
                             .build();
	            }

		        if (produtoEditarDto.getIva() < 0) { // O IVA pode ser 0
		            produtoExistente.setIva(produtoEditarDto.getIva());
		        } else {
	                 return Response.status(Response.Status.BAD_REQUEST)
                             .entity("O valor do IVA não pode ser negativo.")
                             .build();
		        }
		        
		        if (produtoEditarDto.getPvp() > 0) {
		            produtoExistente.setPvp(produtoEditarDto.getPvp());
		        } else if (produtoEditarDto.getPvp() < 0) {
	                 return Response.status(Response.Status.BAD_REQUEST)
                             .entity("O valor do Preço não pode ser negativo.")
                             .build();
		        }

		   
		        PS.editEntity(produtoExistente);

		        return Response.status(Response.Status.OK)
		                .entity("Produto com ID " + id + " editado com sucesso!")
		                .build();
		    } catch (Exception e) {
		        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
		                       .entity("Ocorreu um erro ao editar o produto: " + e.getMessage())
		                       .build();
		    }
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
        
        ViewProductDTO produtoVerDTO = new ViewProductDTO();
        
        produtoVerDTO.setId(product.getId());
        produtoVerDTO.setShelvesIds(product.getShelvesIds());
        produtoVerDTO.setNameString(product.getNameString());
        produtoVerDTO.setDiscount(product.getDiscount());
        produtoVerDTO.setIva(product.getIva());
        produtoVerDTO.setPvp(product.getPvp());
        
        return Response.status(Response.Status.OK)
                .entity(produtoVerDTO)
                .build();
    }
}
