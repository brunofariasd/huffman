package operacoes;

public class Heap {
	
	private Node h[];	
	
	public Heap(Node vetor[]) {
		
		this.setH(vetor);
	}
	
	public Node[] getH() {
		return h;
	}

	public void setH(Node[] h) {
		this.h = h;
	}

	public int piso(double n){
	 return (int) n;
	}
	
	public int getFreq(int i) {
		
		return this.h[i].getFreq();
	}
	
	public Node getNode(int i) {
		
		return this.h[i];
	}
	
	public char getChar(int i) {
		
		return this.h[i].getCh();
	}
	
	/* sobe o elemento i no heap */
	public void sobe(int i, Node heap[]){
	 
		int j; /* índice do pai de i */
		Node temp; /* variável usada na troca (swap) */

		j = piso((i - 1) / 2);

		/* se i tem pai e que seja menor que i */
		if (j >= 0 && heap[i].getFreq() < heap[j].getFreq()) {
			/* troca ambos */
			temp = heap[i];
			heap[i] = heap[j];
			heap[j] = temp;
			/* e continua subindo este elemento */
			sobe(j, heap);
		}
	}

	/* desce o elemento i no heap de tamanho n */
	public void desce(int i, Node heap[], int n){
	 
		int j; /* índice do filho de i */
		Node temp; /* variável usada na troca (swap) */

		j = 2 * i + 1;

		if (j < n) /* pra saber se é um índice válido */
		{
			if (j < n - 1) /* pra saber se tem mais */
			{
				/* pega o menor filho... */
				if (heap[j].getFreq() > heap[j + 1].getFreq())
					j++;
			}
			/* ..e compara com o pai */
			if (heap[j].getFreq() < heap[i].getFreq()) {
				/* troca ambos (swap) */
				temp = heap[i];
				heap[i] = heap[j];
				heap[j] = temp;
				/* e continua descendo o mesmo elemento */
				desce(j, heap, n);
			}
		}
	}

	public void constroiHeap(Node vetor[], int n){
	 
		int i;
		for(i = piso(n/2); i >= 0; i--){
			desce(i, vetor, n);
		}
	}
	
	/* função de ordenação */
	public void heapsort(Node vetor[], int n){
	 
		int i; /* contador */
		Node temp; /* usado na troca (swap) */
		int tamanhoDoHeap;

		tamanhoDoHeap = n;

		constroiHeap(vetor, n);

		for (i = n - 1; i > 0; i--) {
			/*
			 * coloca o primeiro (que é o menor, pela definição de heap) no final do vetor e
			 * decrementa o tamanho do heap
			 */
			temp = vetor[i];
			vetor[i] = vetor[0];
			vetor[0] = temp;

			tamanhoDoHeap--;

			desce(0, vetor, tamanhoDoHeap);
		}
	}

	
	public int inserir(Node h[], int tamHeap, Node novo){
		
			tamHeap++;
			h[tamHeap] = novo;
			sobe(tamHeap, h);
			
			return tamHeap;
	}
	
	
	public void imprimir(int n, Node vetor[]){
	 
		int i;
		String format = "|%1$-10s|%2$-12s|%3$-16s|\n";
		
		System.out.println();
		for (i = 0; i < n; i++)
			System.out.format(format, "Char: "+vetor[i].getCh(),"Freq: "+vetor[i].getFreq(),"Posicao: [ " + i + " ]");

		System.out.println();
	}


}