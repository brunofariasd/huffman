package teste;
import java.io.*;
import operacoes.Node;
import operacoes.Heap;
import operacoes.listaSimb;


public class huffTeste {
	
	public static String codeBin(String texto){

		//String codigo = "";
		String textBin = "";
		int j;
		
		for(int i=0;i<texto.length();i++){
			j = (int)(texto.charAt(i));
			textBin+= Integer.toBinaryString(j);
		}
		
		return textBin;
	}
	
	static listaSimb constroiTabela(Node vetor, String code, listaSimb lista) {
		
		//A funcao ira receber uma lista que guardara todos os codigos e os simbolos correspondentes
		//Alem disso ela, tambem ira receber o primeiro no do heap que e o no raiz.
		
		//Quando chegar em uma folha e ira adicionar o simbolo e seu codigo correspondente a lista
		if(vetor.getLeft() == null && vetor.getRight() == null) {
			
			if(lista == null) {
				
				lista = new listaSimb();
			
			}else {
				
				lista = lista.inserir(lista, vetor.getCh(), code);
				
			}
			
		}
		
		//Caso tenha filho a esquerda, adiciona '0' ao codigo do simbolo e continua percorrendo
		if(vetor.getLeft() != null) {
			
			lista = constroiTabela(vetor.getLeft(), code+"0", lista);
		}
		
		//Caso tenha filho a direita, adiciona '1' ao codigo do simbolo e continua percorrendo		
		if(vetor.getRight() != null) {
			
			lista = constroiTabela(vetor.getRight(), code+"1", lista);
		}
		
		return lista;
		
	}
	
	private static BufferedReader reader;

	public static void main(String[] args) throws IOException{
		
		
		//vetor que armazenara as frequencias de acordo com o valor do char na tabela ASCII
		//e variavel que armazenara o tamanho do alfabeto
		int charFreqs[] = new int[256];
		int i, alphabetSize = 0;
		for ( i = 0; i<256; i++)
			 charFreqs[i] = 0;

		
		FileReader arq = new FileReader("teste.txt");
		reader = new BufferedReader(arq);	
		String linha;
        String s = "";
        
        //Concatena todas as linhas do arquivo em uma string soh
        while ((linha = reader.readLine()) != null) {
            s = s + linha;
        }
        arq.close();
		reader.close();
		
		//Calcula Frequencia de cada char
		for (char c : new String(s).toCharArray())	
             charFreqs[c]++;
		
		//Verifica qual o tamanho do alfabeto	
		//Cria um vetor de NO e o inicializa com sua frequencia e char
		Node vetor[] = new Node[256];
		int aux = 0;
		for ( i = 0; i < 256; i++){
			
			if (charFreqs[i] != 0){
				char j = (char)i;
				vetor[aux++] = new Node( j, charFreqs[i], null, null);
				alphabetSize++;
		}}
		
		System.out.println("Texto a ser comprimido pelo algoritmo de Huffman: \n"+s);
		
		//Criacao do heap ( heap de MIN ) que recebe o vetor de no
		Heap heap1 = new Heap(vetor);
		
		//Ordena e Exibi o vetor em ordem decrescente
		heap1.heapsort(heap1.getH(), alphabetSize);
		System.out.println("\nVetor ordenado pelo Heapsort ( ordem decrescente ), com alfabeto ( do texto ) de tamanho: "+alphabetSize);
		heap1.imprimir(alphabetSize, heap1.getH());
	
		System.out.println("Passo a passo da construcao da arvore, seguindo o conceito do algoritmo de retirar os 2 ultimos nos do heap");
		System.out.println("Entao se cria um novo no com char ( ? ) e frequencia que sera a soma dos nos retirados ( nos com menor freq)\n");
		System.out.println("========================================================================================================\n");
		
		//Ajuste do alfabeto
		//enquanto não existir apenas 1 elemento no heap continuara somando os nos e adicionando o novo no no heap
		int tamHeap = alphabetSize-1;		
		while(tamHeap > 0) {
				
			char newChar = (char) (heap1.getChar(tamHeap) + heap1.getChar(tamHeap-1));
			
			//O novo no recebe um char ( ? ), a soma das frequencias dos 2 ultimos no do heap ( menor valor ).
			//Alem disso o no de menor valor sera seu filho esq. e o no de maior valor o filho dir.
			Node novo = new Node(newChar, heap1.getFreq(tamHeap) + heap1.getFreq(tamHeap-1), heap1.getNode(tamHeap), heap1.getNode(tamHeap-1));
			
			
			System.out.println("Novo no criado com os seguintes dados: ");
			System.out.println("NovoChar: "+novo.getCh()+" | Freq: "+novo.getFreq()+" | FilhoEsq (char): "
							   +novo.getLeft().getCh()+" | FilhoDir (char): "+novo.getRight().getCh()+"\n");
			
			//tamanho do heap e atualizado, sempre que retira os 2 ultimos nos e cria um novo
			tamHeap = heap1.inserir(heap1.getH(), tamHeap-2, novo);
			
			//Ordena e Exibi o vetor em ordem decrescente
			System.out.println("Exibicao do Heap ordenado com o novo no inserido !");
			heap1.heapsort(heap1.getH(), tamHeap+1);
			heap1.imprimir(tamHeap+1, heap1.getH());
		}
        
		
		System.out.println("========================================================================================================");
		System.out.println("\nExibicao dos texos, e da tabela de simbolos com seus respectivos codigos !\n");
		
		
		
		System.out.println("Texto normal: "+s+"\n");
		System.out.println("Texto em binario: "+codeBin(s)+"\n");
		System.out.print("Texto comprimido: ");
				
		//criacao de uma lista que tera todos os simbolos e seus respectivos codigos
		listaSimb lista = new listaSimb();	
		lista = constroiTabela(heap1.getNode(0),"",lista);
		
		lista.impHuff(lista, s);
		System.out.println("");
		lista.imprimir(lista);

	}
}




















