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
		
		/*String start = kb.next();
		if(start.equals("hi")){
			System.out.println("you said hi lol");
			return;
		}
		String end = kb.nextLine();
		end = end.substring(1);*/
		
		String start = "";
		String end = "";
		
		while(!(start.equals("/quit"))){
			start = kb.next();
			
			if(start.charAt(0) == '/'){
				// this is where we could add more commands other than just /quit
				// but for now we will only check for /quit
				
				if(start.equals("/quit")){
					return;
				}
				else{
					System.out.println("invalid command " + start);
				}
			}
			
			end = kb.nextLine();
			end = end.substring(1);

			if(start.length() != 5 || end.length() != 5){
				System.out.println("wrong length   no word ladder could be found between " + start + " and " + end);
			}
			else if(invalidWord(start) || invalidWord(end)){
				System.out.println("invalid words   no word ladder could be found between " + start + " and " + end);
			}
			else if(notInDict(start) || notInDict(end)){
				System.out.println("not in dict   no word ladder could be found between " + start + " and " + end);
			}
			else{
				System.out.println("write the code to find the ladder yo"); // this is where we will add the BFS and DFS method calls
				return;
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
		
		// TODO some code
		Set<String> dict = makeDictionary();
		// TODO more code
		
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
