package main;
import java.io.*;
import java.util.Scanner;

import controllers.ListSymbolController;


public class HuffDescompression {
	
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) throws IOException{
		
		System.out.println("========================================================================================================");
		System.out.println("\n Descompactador de HUFFMAN !\n");
		System.out.println("========================================================================================================");
		
				
		System.out.print("Digite o Texto em binario a ser Descompactado: ");
		String binaryText = sc.nextLine();
		
		
		System.out.println("\n\nTexto Descompactado: "+ListSymbolController.descompressiontSymbols(binaryText));
	}
}




















