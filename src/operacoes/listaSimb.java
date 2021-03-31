package operacoes;

public class listaSimb {
	
	char simbolo;
	String code;
	listaSimb prox;
	
	public listaSimb criar() {
		return null;
	}
	
	public listaSimb inserir(listaSimb l, char v, String code) {
		
		listaSimb novo = new listaSimb();
		novo.simbolo = v;
		novo.code = code;
		novo.prox = l;
		
		return novo;
		
	}
	
	public void imprimir(listaSimb l) {
		
		listaSimb p;
		String format = "|%1$-12s|%2$-15s|\n";
		
		for (p = l; p.code != null; p = p.prox) {
			
			System.out.format(format, "Simbolo: " + p.simbolo,
					"Code: " + p.code);
		}
		
	}
	
	public void impHuff(listaSimb l, String s) {
		
		listaSimb p;
		String aux = "";
		
		for(int i=0;i<s.length();i++){
			
			p = buscar(l, s.charAt(i));
			aux += p.code;
		}
		
		System.out.println(aux);
	}
	
	public listaSimb buscar(listaSimb l, char v) {
		
		listaSimb p;
		
		for (p = l; p!= null; p = p.prox) {
			
			if(p.simbolo == v) {
				return p;
			}
		
		}
		return null;
	}
	
	public listaSimb remover(listaSimb l, char v) {
		
		listaSimb anterior = null; // referência para elemento anterior
		listaSimb p = l; // referência para percorrer a lista
		
		while(p != null && p.simbolo != v) { // procura elemento na lista guardando o anterior
			anterior = p;
			p = p.prox;
		}
		
		// verificar se o elemento foi encontrado
		if (p == null) 
			return l; // se não for encontrado, retorna a lista original
		
		if(anterior == null) {
			
			l = p.prox; // remove o primeiro elemento
		
		}else {
			
			anterior.prox = p.prox; // remove elemento do meio da lista
		
		}
		
		p = null;
		return l;
	}
	
	public void liberar(listaSimb l) {
		
		listaSimb p = l;
		
		while(p != null) {
			
			listaSimb t = p.prox; // guarda referência para o próximo elemento
			p = null; // libera memória referenciada por p
			p = t; // faz p referenciar o próximo
		}
		
	}

}
