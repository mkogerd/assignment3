/* WORD LADDER Main.java
 * EE422C Project 3 submission by
 * Replace <...> with your actual data.
 * <Student1 Name>
 * <Student1 EID>
 * <Student1 5-digit Unique No.>
 * Michael Glasser
 * mg44735
 * 76550 ???
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
				// this is where we could add more commands other than just /quit
				// but for now we will only check for /quit
				
				if(start.equals("/quit")){
					return;
				}
				else{
					System.out.println("invalid command " + start);
					continue;
				}
			}
			
			String end = arr[1];
			
			

			/*if(start.length() == end.length()){
				System.out.println("wrong length   no word ladder could be found between " + start + " and " + end);
			}*/
			/*else if(invalidWord(start) || invalidWord(end)){
				System.out.println("invalid words   no word ladder could be found between " + start + " and " + end);
			}*/
			if(notInDict(start) || notInDict(end)){
				System.out.println("not in dict   no word ladder could be found between " + start + " and " + end);
			}
			else{ // call either DFS or BFS, do not call both unless you change the code to be able to print both
				// ArrayList<String> wordTree = getWordLadderDFS(start, end);
				ArrayList<String> wordTree = getWordLadderBFS(start, end);
				if(wordTree == null){
					System.out.println("no word ladder could be found between " + start + " and " + end);
				}
				else{
					System.out.println(wordTree);
				}
			}
		}
	}
	
	// if a word contains an invalid character. return true to indicate it is an invalid word
	public static boolean invalidWord(String word){
		for(int i=0; i<word.length(); i++){
			if( !( ('a' <= word.charAt(i) && word.charAt(i) <= 'z') || ('A' <= word.charAt(i) && word.charAt(i) <= 'Z') ) ){
				return true;
			}
		}
		
		return false;
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
		
		// TODO some code
		Set<String> dict = makeDictionary();
		// TODO more code
		
		return null; // replace this line later with real return
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
			infile = new Scanner (new File("C:\\Users\\Michael\\workspace\\Word_Tree\\src\\assignment3\\five_letter_words.txt"));
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
