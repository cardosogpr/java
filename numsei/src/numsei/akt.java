package numsei;

import java.util.Scanner;

public class akt {

	public static void main(String[] args) {
       
        Scanner scanner = new Scanner(System.in);

        System.out.println("Por favor, digite números ou dígitos separados por espaços (ex: 1 2 3 4 5 6 7 8 9):");
        String entradaDoUsuario = scanner.nextLine(); 

        System.out.println("\n---"); 

        System.out.println("Entrada original digitada: " + entradaDoUsuario);

        String[] partesNumericas = entradaDoUsuario.split("\\s+");
        StringBuilder stringUnida = new StringBuilder();

        for (String parte : partesNumericas) {
            stringUnida.append(parte);
        }

        String resultadoFinal = stringUnida.toString();

        System.out.println("Resultado final (números unidos em uma String): " + resultadoFinal);

        try {
            long numeroInteiroGrande = Long.parseLong(resultadoFinal);
            System.out.println("Resultado como um número 'long': " + numeroInteiroGrande);
        } catch (NumberFormatException e) {
            System.out.println("Atenção: A sequência unida não é um número válido ou é muito grande para um 'long'.");
        }
        
        scanner.close(); 
	}
}
		
		   
		

	

