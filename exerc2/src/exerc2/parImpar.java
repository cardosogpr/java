package exerc2;

import java.util.Iterator;
import java.util.Scanner;

public class parImpar {

	public static void main(String[] args) {
		
	    int number;
		
			Scanner myObj = new Scanner(System.in);
			System.out.println("Escreva um número");
			number = myObj.nextInt();
			
			for (int i = 0; i <= number; i++) {
				
				if (i % 3 == 0 && i % 5 == 0) {
					System.out.println("Fizzbuzz: " + i);
				} else if ( i % 3 == 0) {
					System.out.println("Fizz: " + i);
				}
				else if ( i % 3 == 0) {
					System.out.println("Buzz:" + i);
				} else {
					System.out.println("Number:" + i);
				}				
			}
			
	}

}
