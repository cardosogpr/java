package io.altar.jseproject.textinterface;

import java.nio.channels.ClosedByInterruptException;
import java.nio.channels.NonWritableChannelException;
import java.security.PublicKey;
import java.util.Scanner;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;
import javax.xml.soap.SOAPElementFactory;

import org.omg.CosNaming.NamingContextExtPackage.AddressHelper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List; // Importar List
import io.altar.jseproject.model.Shelf;
import io.altar.jseproject.repositories.ProductRepository;
import io.altar.jseproject.repositories.ShelfRepository;
import io.altar.jseproject.model.Product;

public class Application {

    private static Scanner scanner = new Scanner(System.in);
    
    private static List<Product> products = new ArrayList<>();
    private static List<Shelf> shelfs = new ArrayList<>();
    
    private static ProductRepository productRepository = ProductRepository.getInstance();
    private static ShelfRepository shelfRepository = ShelfRepository.getInstance();
    
    public static void main(String[] args) {
        menuPrincipal();
    }
    
    public static void menuPrincipal() {
        int chooseOption;

        do {
            System.out.println("Por favor, selecione uma das seguintes opções:");
            System.out.println("1) Listar produtos");
            System.out.println("2) Listar prateleiras");
            System.out.println("3) Sair");

            System.out.print("Sua opção: ");
            try {
                chooseOption = scanner.nextInt();
                scanner.nextLine();
                switch (chooseOption) {
                    case 1:
                    	listProducts();
                        break;
                    case 2:
                    	listShelfs();
                        break;
                    case 3:
                        break;
                    default:
                        System.out.print("Opção Inválida. Apenas é permitido 1, 2 ou 3");
                }
            } catch (java.util.InputMismatchException e) { // Catches non-integer input
                System.out.println("Entrada inválida. Por favor, insira um número.");
                scanner.nextLine(); // Consumes the invalid input
                chooseOption = 0; // Ensures the loop continues
            }
        } while (chooseOption != 3); // This completes your do-while loop
    }
    
    public static void listProducts() {
    	int chooseOptionProducts;
    	
    	do {
    		 System.out.println("Por favor, selecione uma das seguintes opções:");
             System.out.println("1) Criar novo produtos");
             System.out.println("2) Editar um produto existente");
             System.out.println("3) Consultar o detalhe de um produto");
             System.out.println("4) Remover um produto");
             System.out.println("5) Voltar ao ecrã anterior");

             System.out.print("Sua opção: ");
             try {
            	 chooseOptionProducts = scanner.nextInt();
                 scanner.nextLine();

                 switch (chooseOptionProducts) {
                     case 1:
                    	 Product novoProduct = createProduct();
                    	 if (novoProduct != null) {
                    		 products.add(novoProduct);
                    		 System.out.println("Produto ' " + novoProduct.getProductId() + " ' adicionado á lista ");
                    	 } else {
                    		 System.out.println("Oops. Parece que fizeste algo de errado");
                    	 }
                         break;
                     case 2:
                    	 editProduct();
                         break;
                     case 3:
                    	 listarProduct();
                         break;
                     case 4: 
                    	 removeProduct();
                    	 break;
                     case 5:
                    	 menuPrincipal();
                    	 break;              	 
                 }
             } catch (java.util.InputMismatchException e) { // Catches non-integer input
                 System.out.println("Entrada inválida. Por favor, insira um número.");
                 scanner.nextLine(); // Consumes the invalid input
                 chooseOptionProducts = 0; // Ensures the loop continues
             }
         } while (chooseOptionProducts != 5); // This completes your do-while loop
    }
    
public static void listShelfs() {
    	int chooseOptionShelfs;
    	
    	do {
    		 System.out.println("Por favor, selecione uma das seguintes opções:");
             System.out.println("1) Criar uma nova prateleira");
             System.out.println("2) Editar uma prateleira existente");
             System.out.println("3) Consultar o detalhe de uma prateleira");
             System.out.println("4) Remover uma prateleira");
             System.out.println("5) Voltar ao ecrã anterior");

             System.out.print("Sua opção: ");
             try {
            	 chooseOptionShelfs = scanner.nextInt();
                 scanner.nextLine();

                 switch (chooseOptionShelfs) {
                     case 1:
                    	 Shelf novaShelf = createShelf();
                    	 if (novaShelf != null) {
                    		 shelfs.add(novaShelf);
                    		 System.out.println("Prateleira '" + novaShelf.getShelfId() + "' adicionado á lista");
                    	 } else {
                    		 System.out.println("Oops. Parece que fizeste algo de errado!");
                    	 }
     
                         break;
                     case 2:
                    	 editShelf();
                         break;
                     case 3:
                    	 listarShelf();
                         break;
                     case 4:
                    	 removeShelf();
                    	 break;
                     case 5:
                    	 menuPrincipal();
                    	 break;              	 
                 }
             } catch (java.util.InputMismatchException e) { // Catches non-integer input
                 System.out.println("Entrada inválida. Por favor, insira um número.");
                 scanner.nextLine(); // Consumes the invalid input
                 chooseOptionShelfs = 0; // Ensures the loop continues
             }
         } while (chooseOptionShelfs != 5); // This completes your do-while loop   	
 }

//Provavelmente terei de remover :(
public static Product createProduct() {
	
	Product produto = new Product();
	long newProductId = productRepository.addEntity(produto);
	
	
	//System.out.println("ID do produto atribuído automaticamente:" + newProductId);
	
	System.out.println("Insira o nome:");
	while (!scanner.hasNextLine()) { 
		System.out.println("Esta entrada é inválida. Por favor, escreva uma string válida.");
		scanner.next();
		System.out.println("Insira o nome:"); 
	}
	String name = scanner.nextLine(); 
    produto.setProductNameString(name);
	
	System.out.println("Insira o preço do produto:");
	while (!scanner.hasNextDouble()) {
		System.out.println("Esta entrada é inválida. Por favor, escreva um número.");
		scanner.next();
		System.out.println("Insira o preço do produto:");
	}
	double preco = scanner.nextDouble();
	produto.setPreco(preco);
	scanner.nextLine();
	
	System.out.println("Insira o IVA do produto (0.23 para 23%)");
	while (!scanner.hasNextDouble()) {
		System.out.println("Esta entrada é inválida. Por favor, escreva um número.");
		scanner.next();
		System.out.println("Insira o IVA do produto (0.23 para 23%)");
	}
	
	double iva = scanner.nextDouble();
	produto.setIva(iva);
	scanner.nextLine();
		
  
 return produto;
 }

public static void listarProduct() {
	
Collection<Product> currentProducts = productRepository.getAll().values();

 if(currentProducts.isEmpty()) {
	 System.out.println("Nenhum produto foi criado até agora.");
 } else {
	 System.out.println("\n--- Lista de Produtos ---");
	 for (Product p : currentProducts) {
		 System.out.println(p);
	 }
 }
}
public static Shelf createShelf() {
	
	Shelf shelf = new Shelf();
	Product produto = new Product();
	
	long newShelfId = shelfRepository.addEntity(shelf);	
	
	System.out.println("Insira o nome:");
	while (!scanner.hasNextLine()) { 
		System.out.println("Esta entrada é inválida. Por favor, escreva uma string válida.");
		scanner.next();
		System.out.println("Insira o nome:"); 
	}
	String name = scanner.nextLine(); 
    shelf.setShelfNameString(name);
				
	System.out.println("Insira a capacidade:");
	while (!scanner.hasNextInt()) { 
		System.out.println("Esta entrada é inválida. Por favor, escreva um número inteiro válido.");
		scanner.next();
		System.out.println("Insira a capacidade:"); 
	}
	int capacidade = scanner.nextInt(); 
	shelf.setCapacidade(capacidade);
	scanner.nextLine();
		
	System.out.println("Insira o preço diário:");
	while (!scanner.hasNextDouble()) { 
		System.out.println("Esta entrada é inválida. Por favor, escreva um número inteiro válido.");
		scanner.next();
		System.out.println("Insira o preço diário:"); 
	}
	double precoDiario = scanner.nextDouble();
	shelf.setPrecoAluguerDiario(precoDiario);
	scanner.nextLine();
	
	//Sistema de adicionar produto á prateleira que estamos a criar.
	
	System.out.println("Deseja associar um produto existente a esta prateleira? (s/n)");
    String resposta = scanner.nextLine().trim().toLowerCase();

    if (resposta.equals("s")) { //se for sim, pede para inserir o ID
        System.out.println("Insira o ID do Produto a associar:");
        while (!scanner.hasNextLong()) {
            System.out.println("Esta entrada é inválida. Por favor, escreva um número inteiro válido."); //caso nao for um nº inteiro
            scanner.next();
            System.out.println("Insira o ID do Produto a associar:"); //pde novamente para inserir
        }
        long idProdutoParaAssociar = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha

        Product produtoEncontrado = ProductRepository.getInstance().getById(idProdutoParaAssociar); //vai conseguir captar o ID do Product

       if (produtoEncontrado != null) {
    	   
    	   shelf.setProduct(produtoEncontrado); //A prateleira agora "contém" este produto
           
    	   
    	   System.out.println("Produto ID " + idProdutoParaAssociar + " associado à prateleira ID " + newShelfId + " com sucesso!");
    	   
    	   ProductRepository.getInstance().edit(produtoEncontrado);
           ShelfRepository.getInstance().edit(shelf);
       } else {
           System.out.println("Produto com ID " + idProdutoParaAssociar + " não encontrado. Nenhum produto foi associado a esta prateleira.");
       }
   } else {
       System.out.println("Nenhum produto será associado a esta prateleira neste momento.");
   }
   return shelf;
}
  

public static void listarShelf() {
	
	Collection<Shelf> currentShelf = shelfRepository.getAll().values();

	 if(currentShelf.isEmpty()) {
		 System.out.println("Nenhum produto foi criado até agora.");
	 } else {
		 System.out.println("\n--- Lista de Produtos ---");
		 for (Shelf s : currentShelf) {
			 System.out.println(s);
		 }
	 }
	}


public static void removeShelf() {
	listarShelf();
	
	System.out.println("Tens a certeza que desejas remover esta prateleira? (s/n)");
    String resposta = scanner.nextLine().trim().toLowerCase(); 
    
    if (resposta.equals("s")) {  
	System.out.println("Insira um ID para remover:");
	while (!scanner.hasNextLong()) { 
		System.out.println("Esta entrada é inválida. Por favor, escreva um ID válido.");
		scanner.next();
		System.out.println("Insira um ID para remover:"); 
	}
	long deleteShelfID = scanner.nextLong(); 
	shelfRepository.remove(deleteShelfID);
	
    }
    
	System.out.println("\n--- Lista de Produtos ATUALIZADA ---");
	listarShelf();
	
	System.out.println("\nPressione Enter para voltar ao menu principal...");
    scanner.nextLine();
}

public static void removeProduct() {
	
	listarProduct();
	
	System.out.println("Tens a certeza que desejas remover este produto? (s/n)");
    String resposta = scanner.nextLine().trim().toLowerCase(); 
    
    if (resposta.equals("s")) {  
	System.out.println("Insira um ID para remover:");
	long deleteProductID;
	while (!scanner.hasNextLong()) { 
		System.out.println("Esta entrada é inválida. Por favor, escreva um ID válido.");
		scanner.next();
		System.out.println("Insira um ID para remover:"); 
	}
	deleteProductID = scanner.nextLong(); 	
	productRepository.remove(deleteProductID);
	  
    }
	System.out.println("\n--- Lista de Produtos ATUALIZADA ---");
	listarProduct();
	
	System.out.println("\nPressione Enter para voltar ao menu principal...");
    scanner.nextLine();
  }

public static void editProduct() {
	listarProduct();
		
	System.out.println("Insira um ID para editar:");
	long editProductID;
	while (!scanner.hasNextLong()) { 
		System.out.println("Esta entrada é inválida. Por favor, escreva um ID válido.");
		scanner.next();
		System.out.println("Insira um ID para editar:"); 
	}
	editProductID = scanner.nextLong(); 	
	scanner.nextLine();
	
	Product productToEdit = productRepository.getById(editProductID);
	
	if (productToEdit == null) {
		System.out.println("O produto com o ID " + editProductID + "não foi encontrado.");
	} else {
		System.out.println("Estás a editar neste momento o produto: " + productToEdit);
		
		System.out.println("Insira o novo nome do produto. (actual:" + productToEdit.getProductNameString()+ "):");
		String newNameString = scanner.nextLine();
		if (!newNameString.trim().isEmpty()) {
			productToEdit.setProductNameString(newNameString);
		}
				
		System.out.println("Insira o preço do produto. (actual:" + productToEdit.getPreco()+ "):");
		while (!scanner.hasNextDouble()) {
			 System.out.println("Entrada inválida. Por favor, insira um número para o preço:");
	         scanner.next();;
		}
		double newPreco = scanner.nextDouble();
		productToEdit.setPreco(newPreco);
		scanner.nextLine();
		
		System.out.println("Insira o IVA do produto. (actual:" + productToEdit.getIva()+ "):");
		while (!scanner.hasNextDouble()) {
			 System.out.println("Entrada inválida. Por favor, insira um número para o preço:");
	         scanner.next();;
		}
		double newIVA = scanner.nextDouble();
		productToEdit.setIva(newIVA);
		scanner.nextLine();
		
		
		productToEdit.setPrecoFinal(productToEdit.getPreco() * (1 + productToEdit.getIva()));
		
		productRepository.edit(productToEdit);
		
		System.out.println("\nProduto editado com sucesso!");
	    listarProduct();
	}
	
	
} 

public static void editShelf() {
 listarShelf();
 
	System.out.println("Insira um ID para editar:");
	long editShelfID;
	while (!scanner.hasNextLong()) { 
		System.out.println("Esta entrada é inválida. Por favor, escreva um ID válido.");
		scanner.next();
		System.out.println("Insira um ID para editar:"); 
	}
	editShelfID = scanner.nextLong();
	scanner.nextLine();
	
    Shelf shelftoEditShelf = shelfRepository.getById(editShelfID);
    
    if (shelftoEditShelf == null) {
    	System.out.println("A shelf com o ID " + editShelfID + "não foi encontrado.");
    } else {
    	System.out.println("Estás a editar neste momento o produto: " + shelftoEditShelf);
    	
    	System.out.println("Insira o novo nome do produto. (actual:" + shelftoEditShelf.getShelfNameString()+ "):");
		String newNameString = scanner.nextLine();
		if (!newNameString.trim().isEmpty()) {
			shelftoEditShelf.setShelfNameString(newNameString);
		}
    				
    	System.out.println("Insira a capacidade. (actual:" + shelftoEditShelf.getCapacidade()+ "):");
    	while (!scanner.hasNextInt()) { 
    		System.out.println("Esta entrada é inválida. Por favor, escreva um número inteiro válido.");
    		scanner.next();
    		System.out.println("Insira a capacidade:"); 
    	}
    	int capacidade = scanner.nextInt(); 
    	shelftoEditShelf.setCapacidade(capacidade);
    	scanner.nextLine();
    		
    	System.out.println("Insira o preço diário. (actual:" + shelftoEditShelf.getPrecoAluguerDiario()+ "):");
    	while (!scanner.hasNextDouble()) { 
    		System.out.println("Esta entrada é inválida. Por favor, escreva um número inteiro válido.");
    		scanner.next();
    		System.out.println("Insira o preço diário:"); 
    	}
    	Double newPrecoDiario = scanner.nextDouble();
    	shelftoEditShelf.setPrecoAluguerDiario(newPrecoDiario);
    	scanner.nextLine();
    	
    	//Sistema de mudar produto de prateleira
    	
    	System.out.println("Desejas mudar algum produto para esta prateleira? (s/n)");
        String resposta = scanner.nextLine().trim().toLowerCase();

        if (resposta.equals("s")) { //se for sim, pede para inserir o ID
            System.out.println("Insira o ID do Produto a associar:");
            while (!scanner.hasNextLong()) {
                System.out.println("Esta entrada é inválida. Por favor, escreva um número inteiro válido."); //caso nao for um nº inteiro
                scanner.next();
                System.out.println("Insira o ID do Produto a associar:"); //pde novamente para inserir
            }
            long newIdProdutoAssociar = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha

            Product produtoEncontrado = ProductRepository.getInstance().getById(newIdProdutoAssociar); //vai conseguir captar o ID do Product

           if (produtoEncontrado != null) {
        	   
        	   shelftoEditShelf.setProduct(produtoEncontrado); //A prateleira agora "contém" este produto
               
        	   
        	   System.out.println("Produto ID " + newIdProdutoAssociar + " associado à prateleira ID " + editShelfID + " com sucesso!");
        	   
        	   ProductRepository.getInstance().edit(produtoEncontrado);
               ShelfRepository.getInstance().edit(shelftoEditShelf);
           } else {
               System.out.println("Produto com ID " + newIdProdutoAssociar + " não encontrado. Nenhum produto foi associado a esta prateleira.");
           }
       } else {
           System.out.println("Nenhum produto será associado a esta prateleira neste momento.");
       }
       listarShelf();
    }
}
 
 
	
}
 


    
    	   


