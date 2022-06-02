package controllers;

import java.util.ArrayList;
import java.util.HashMap;

import models.Symbol;

public class ListSymbolController {
	
	static public void writeListSymbols(ArrayList<Symbol> ListSymbols) {
		
		json.JSONArray jsArray = new json.JSONArray();
		for (Symbol l : ListSymbols) {
			jsArray.put(l.toJson());
		}
		ArchiverController.writeArchiver("src/data/ListSymbol.txt", jsArray);
		
		System.out.println("O arquivo com a tabela de simbolos foi criado com sucesso.");
	}
	
	static public String descompressiontSymbols(String binaryText) {
		
		HashMap<String, String> listSymbols = new HashMap<String, String>();
		
		String result = "";
		String treePath = "";
		
		String textoCompleto = ArchiverController.readArchiver("src/data/ListSymbol.txt");
		
		json.JSONArray jA = new json.JSONArray(textoCompleto);
		for (int i = 0; i < jA.length(); i++) {
			Symbol Symbol = new Symbol(jA.getJSONObject(i));
			listSymbols.put(Symbol.getCode(), Symbol.getSymbol());
		}
		
		
		for (int i = 0; i < binaryText.length(); i++) {
			
			treePath += binaryText.charAt(i);
			
			if(listSymbols.containsKey(treePath)) {
				
				result += listSymbols.get(treePath);
				treePath = "";
			}
		}
		
		return result;
	}

}