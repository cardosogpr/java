package training;

import java.util.Scanner;

class train {

	public static void Main(String[] args) {
		
		double x, y, sum;
		
		Scanner myObj = new Scanner(System.in);
		System.out.println("Escreva um número");
		x = myObj.nextDouble();
		System.out.println("Escreva um número");
		y = myObj.nextDouble();
		
		sum = x + y;
		System.out.println("Resultado Final: " + sum);
		
		
	}
	
}
