/* WORD LADDER Main.java
 * EE422C Project 3 submission by
 * Michael Darden
 * mkd788
 * 76550
 * Michael Glasser
 * mg44735
 * 76550
 * Slip days used: <0>
 * Fall 2015
 */


package assignment3;

import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) {
		
		Scanner kb = new Scanner(System.in);
		
		while(true){
			String[] arr = kb.nextLine().split(" ");
			String start = arr[0];
			
			if(start.charAt(0) == '/'){
				switch (start) {		// Commands go here
				case "/quit": 
					return;
				default: 
					System.out.println("invalid command " + start);
					continue;
				}
			}
			
			String end = arr[1];

			if(notInDict(start) || notInDict(end)){
				System.out.println("no word ladder could be found between " + start + " and " + end + ".");
			}
			else{ 
				// call either DFS or BFS, do not call both unless you change the code to be able to print both
				//ArrayList<String> wordTree = getWordLadderDFS(start, end);
				ArrayList<String> wordTree = getWordLadderBFS(start, end);
				if(wordTree == null){
					System.out.println("no word ladder could be found between " + start + " and " + end + ".");
				}
				else{
					System.out.println("a " + wordTree.size() + "-rung word ladder exists between " + start + " and " + end + ".");
					for (String s: wordTree) {
						System.out.println("\t" + s.toLowerCase());
					}
				}
			}
		}
	}

	// if the word is not found in the dictionary then it is an invalid word
	public static boolean notInDict(String word){
		Set<String> dict = makeDictionary();
		
		word = word.toUpperCase();
		
		if(dict.contains(word)){
			return false;
		}
		
		return true;
	}
	
	public static ArrayList<String> getWordLadderDFS(String start, String end) {
		
		// Create data structures
		Set<String> dict = makeDictionary();
		Set<String> visited = new HashSet<String>();
		ArrayList<String> wordTree = new ArrayList<String>();
		
		// Initialize wordTree and visited with the starting word
		wordTree.add(start.toUpperCase());
		visited.add(start.toUpperCase());		
		
		if (dfs(start.toUpperCase(), end.toUpperCase(), dict, visited, wordTree))
			return wordTree;					// Ladder found
		else 
			return null;						// No ladder found
	}	
	
	public static boolean dfs(String start, String end, Set<String> dict, Set<String> visited, ArrayList<String> wordTree) {
		
		if (start.equals(end))			// Terminating Case - ladder to end has been found
			return true;
		
		else for (String next: dict) 											// Iterate through the dictionary
				if(next.length() == start.length() && !visited.contains(next)){	// Check for valid comparison
					int matchCount = 0;
					for(int i = 0; i < start.length(); i++) {		// Check number of matching characters
						if(start.charAt(i) == next.charAt(i)) {
							matchCount++;						
						}
					}
					if(matchCount == next.length() - 1) {			// Check if the neighbor is one-off
						visited.add(next);							// Delete neighbor from dictionary
						wordTree.add(next);							// Add neighbor to wordTree as a leaf
						if (dfs(next, end, dict, visited, wordTree))// Recursive call - dfs from newly added neighbor leaf
							return true;
						else
							wordTree.remove(wordTree.size() - 1);	// Remove neighbor from array list
					}
				}	
		return false;
	}
	
    public static ArrayList<String> getWordLadderBFS(String start, String end) {
		
		Set<String> dict = makeDictionary();
		Queue<Node> q = new LinkedList<Node>();
		ArrayList<String> wordTree = new ArrayList<String>();
		
		q.add(new Node(start.toUpperCase(), null));
		while(!(q.isEmpty())){
			Node head = q.remove();
			if ( (head.getWord()).equals(end.toUpperCase()) ){
				while(head.getPrevious() != null){
					wordTree.add(0, head.getWord());
					head = head.getPrevious();
				}
				wordTree.add(0, head.getWord());

				return wordTree;
			}
			else{
				dict.remove(head.getWord());
				for(String next : dict){
					if(next.length() == head.getWord().length()){
						int matchCount = 0;
						for(int i = 0; i < start.length(); i++){
							if( (head.getWord()).charAt(i) == next.charAt(i) ){
								matchCount++;
							}
						}
						if(matchCount == next.length() - 1){
							q.add(new Node(next, head));
						}
					}	
				}
			}
		}
		return null; // replace this line later with real return
	}
    
    // the dictionary is being created with the file five_letter_words.txt
    // I used this address to find it, make sure you update the address so you can use it on your computer
	public static Set<String>  makeDictionary () {
		Set<String> words = new HashSet<String>();
		Scanner infile = null;
		try {
			infile = new Scanner (new File("/Users/Koger/Documents/workspace/EE422C/Assignments/assignment3/five_letter_words.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("Dictionary File not Found!");
			e.printStackTrace();
			System.exit(1);
		}
		while (infile.hasNext()) {
			words.add(infile.next().toUpperCase());
		}
		return words;
	}
}
