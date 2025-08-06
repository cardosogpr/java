package io.altar.jseproject.textinterface.states;

import java.util.Collection;

import io.altar.jseproject.model.Product;
import io.altar.jseproject.model.Shelf;
import utils.ScannerUtils;
import io.altar.jseproject.repositories.ShelfRepository;
import io.altar.jseproject.services.ProductService;
import io.altar.jseproject.services.ShelfServices;

public class UpdateShelfs extends State {
	private ShelfServices SS = new ShelfServices();
	private ProductService PS = new ProductService();
	public int on() {
		
		 System.out.println("------ Lista de Prateleiras ------");
			
			Collection<Shelf> allShelfs = SS.getAll();
					
			if(allShelfs.isEmpty()) {
				System.out.println("Não há prateleiras");
			} else {
				for (Shelf shelf : allShelfs) {
					System.out.println(shelf.toString()); 
				}
			}
			
			System.out.println("---------------------------------");
			long editShelfsId = sc.getLong("Qual prateleira desejas editar? (Insere o ID): "); // Mudado nome para clareza
			
			Shelf shelfToEdit = SS.getEntity(editShelfsId); // Mudado nome para clareza
			
			if(shelfToEdit != null) {
				
				String nameString = sc.getValue("Insira o nome (atual: "+ shelfToEdit.getNameString() + "):");
				shelfToEdit.setNameString(nameString); 

				int capacity = sc.getInt("Insira a capacidade (atual: " + shelfToEdit.getCapacity() + "):"); // Mostrar valor atual
				shelfToEdit.setCapacity(capacity);
				
				float dailyPrice = sc.getFloat("Insira o preço Diário (atual: " + shelfToEdit.getDailyPrice() + "):"); // Mostrar valor atual
	            shelfToEdit.setDailyPrice(dailyPrice);
				
				// A prateleira já foi atualizada com capacidade e preço diário.
				//Função para editar o ID do Produto
				
				System.out.println("Desejas editar o produto que está associado a esta prateleira? (s/n)");			
			    String resposta = sc.getValue().trim().toLowerCase();
			    
			    if (resposta.equals("s")) {
			        long newProductId = sc.getLong("Insira o ID do produto a associar (0 para remover associação):"); // Oferecer opção de remover
			        
			        if (newProductId == 0) {
			            // Se o utilizador inserir 0, desassocia o produto atual da prateleira
			            Product oldProduct = PS.getEntity(shelfToEdit.getProductId()); // Obter o produto antigo se houver
			            if (oldProduct != null) {
			                oldProduct.removeShelfId(shelfToEdit.getId()); // Remove o ID da prateleira do produto antigo
			                PS.editEntity(oldProduct); // Atualiza o produto antigo
			                System.out.println("Produto anterior desassociado da prateleira.");
			            }
			            shelfToEdit.setProductId(0); // Define productId da prateleira para 0
			            System.out.println("Associação de produto removida da prateleira.");
			        } else {
			            Product productToAssociate = PS.getEntity(newProductId);
			            
			            if (productToAssociate != null) {
			                // Remover associação antiga do produto (se existir)
			                Product oldProduct = PS.getEntity(shelfToEdit.getProductId());
			                if (oldProduct != null && oldProduct.getId() != newProductId) { // Se for um produto diferente
			                    oldProduct.removeShelfId(shelfToEdit.getId());
			                    PS.editEntity(oldProduct);
			                }

			                shelfToEdit.setProductId(newProductId);  
			                
			                // Adicionar o ID da prateleira ao novo produto (se ainda não estiver lá)
			                if (!productToAssociate.getShelvesIds().contains(shelfToEdit.getId())) {
			                    productToAssociate.addShelfId(shelfToEdit.getId());
			                    PS.editEntity(productToAssociate); // Usar editEntity para atualizar o produto
			                }
			                System.out.println("Produto com ID " + newProductId + " associado à prateleira.");
			            } else {
			                System.out.println("Aviso: Produto com ID " + newProductId + " não encontrado. A prateleira permanecerá com o produto anterior ou sem produto associado.");
			            }
			        }
			    }
			        			    
				//Notificação que foi atualizado
				SS.editEntity(shelfToEdit);
				
				System.out.println("Prateleira com ID " + shelfToEdit.getId() + " atualizada com sucesso.");
				System.out.println("Estado atual da prateleira: " + shelfToEdit.toString()); 

				return 1;
		    }
		    else {
		    	System.out.println("Oops... Não foi encontrada nenhuma prateleira com o ID " + editShelfsId + ".");
		    }
		    
			return 1; 
		}

	}