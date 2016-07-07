package assignment3;

public class Node {
	private String word;
	private Node previous;
	
	public Node(String w, Node p) {
		word = w;
		previous = p;
	}
	
	public String getWord() {
		return word;
	}
	
	public Node getPrevious() {
		return previous;
	}

}
