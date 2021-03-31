package operacoes;

public class Node {
	   
	private char ch;   // usado só nas folhas
	private int freq;  // usado só para construção da trie
	private final Node left, right;
		
	
	   public Node(char ch, int freq, Node left, Node right) {
	       this.ch = ch;
	       this.freq = freq;
	       this.left = left;
	       this.right = right;
	   }

	   public boolean isLeaf() { 
	      return left == null && right == null; 
	   }

	   public int compareTo(Node that) { 
	      return this.freq - that.freq; 
	   }

	   public char getCh() {
		   return ch;
	   }

	   public void setCh(char ch) {
		   this.ch = ch;
	   }

	   public int getFreq() {
		   return freq;
	   }
	   
	   public void setFreq(int freq) {
		   this.freq = freq;
	   }
	   
	   public Node getLeft() {
		   return left;
	   }
	   
	   public Node getRight() {
		   return right;
	   }
}

