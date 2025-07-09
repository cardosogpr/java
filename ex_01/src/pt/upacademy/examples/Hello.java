package pt.upacademy.examples;

import java.util.Scanner;
import java.util.Date;

public class Hello {

	public static void main(String[] args) {
		
	 System.out.println("Insira o seu nome!");
	 Scanner sc = new Scanner(System.in);
	 String nome = sc.nextLine();
	 
	 Date date = new Date();
	 
	 System.out.println("Insira a sua idade!");
	 int idade = sc.nextInt();
	 sc.nextLine();
	 
	 sc.close();
	 System.out.println(nome + ", " + idade + " anos ");
	 System.out.println("Data de quando foi gerado: " + date.toString());
	 

	}

}
