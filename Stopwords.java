/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment;

import static assignment.Assignment.tree;
import java.util.HashSet;
import java.util.Scanner;
import java.io.File;

/**
 *
 * @author Harnoor 
 * I made this class for data cleaning. It removes stop words and unnecessary punctuation marks.
 */
public class Stopwords {
    private HashSet<String> stopwords1 = new HashSet<String>();
    void addtotree(String s){
     tree=new Tree();
     String word="";
     String topic = "";
     String temp = "";
     String[] arr = s.split(" ");
     for (int i=0;i<arr.length;i++){
         word = arr[i];
         boolean stopword = isStopword(removepunctuation(word.toLowerCase()));
         boolean endPunc = (word.endsWith(".") || word.endsWith(",") || word.endsWith("!") || word.endsWith("?"));
         if (endPunc||stopword) {
                       if (endPunc){
			topic += removepunctuation(word) + " ";
			}
			if (topic != " " && topic != "") {
			String[] keywords = topic.trim().split(" ");
			tree.add(keywords);
			topic = "";
				}
         }
			else {
				topic += removepunctuation(word) + " ";
			}
           
     }
     
 
 }
    String removepunctuation(String s){
     
		String str = s.trim();
		
		str=str.replace("!", "");
		str=str.replace("?", "");
		str=str.replace("\\", "");
		str=str.replace("/", "");
		str=str.replace(",", "");
		str=str.replace("(", "");
		str=str.replace(")", "");
		str=str.replace("<", "");
		str=str.replace(">", "");
		str=str.replace("#", "");	
		str=str.replace("\"", "");
		str=str.replace("+", "");
		str=str.replace(":", "");
		str=str.replace("=", "");
		str=str.replace("--", "");
		str=str.replace("*", "");
		
		if (str.startsWith("'") || str.endsWith("'")){
			str=str.replace("'", "");
		}
		
		if (str.endsWith(".")){
			str=str.replace(".", "");
		}
		
		return str;						
	}
     private  boolean isStopword(String word){
            parseStopwords();
            return word.endsWith(",") || stopwords1.contains(word); 
	}
        private void parseStopwords() {
             
            Scanner sc = null;
            sc = new Scanner(getClass().getResourceAsStream("stopwords.txt"));
		
		while (sc.hasNextLine())
			stopwords1.add(sc.nextLine());
	}
}


 
  



