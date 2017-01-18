/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment;
import static assignment.Assignment.tree;
import java.util.*;
import java.io.*;
import java.net.*;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

/**
 *
 * @author Harnoor
 */
public class Assignment {
   static Tree tree;
   HashSet<String> stopwords;
    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) throws MalformedURLException {
        
        String s="";
        URL url1;
           if (args.length<1){
            System.out.println("Not enough arguments");
        }
        else if (args.length>=2){
            System.out.println("Too many arguments");
        }
        else if (args.length==1){
            s = args[0];
        }
      url1 = new URL(s);
      String ans= getURLcontent(url1);
      Stopwords stopwords = new Stopwords();
      stopwords.removepunctuation(ans);
      Assignment assignment = new Assignment();
      //System.out.println(ans);
     
      stopwords.addtotree(ans);
      PriorityQueue<MyNodes> pq = new PriorityQueue<MyNodes>();
      Scanner sc = new Scanner(System.in);
		
		// Default count threshold
     String input = "2";
		
		// Whether or not the user entered the close command
     boolean closed = false;
		
		
     while (!closed) {
		if (!closed) {
			System.out.println("Relevant topics:\n");
				// Get keyset of single-word keywords and all keywords stemming from each key in the keyset
                        for (String str : assignment.getKeyset()) {
			HashMap<String, Integer> keywords = assignment.get(str);
				for (String keyword : keywords.keySet()) {
						// Only add keyword to priority queue if the count is greater than the threshold
				    int threshold = Integer.parseInt(input);
						if (threshold < 2)
							threshold = 2;
						if (keywords.get(keyword) >= threshold) {
							pq.add(new MyNodes(keyword, keywords.get(keyword)));
						}
						}
				}
				
				// Print out keywords 
				while (!pq.isEmpty()) {
					MyNodes node = pq.remove();
					System.out.println(  " " +node.getValue());
				}
				System.out.println();
				
				// exit through console
				System.out.print("Press E to exit: ");
				input = sc.next();
				closed = (input.equals("e") || input.equals("E"));
				while (!closed ) {
					System.out.print("Press E to exit ");
					input = sc.next();
					closed = (input.equals("e") || input.equals("E"));
				}
				
				System.out.println();
			}
		}
     
		
	
       /*Document doc = Jsoup.parse(ans);// using JSoup to parse HTML//
       String ans1 = doc.text();
       String regex ="\\b(?:a|an|the|was|and|to|i)\\b\\s*";  // removing stopwords
       ans1.replaceAll(regex, "");
       System.out.println(ans1);*/
       
    }
    /* This method gets text from URL */
    private static String getURLcontent(URL url){
        
        URLConnection con = null;
        //tree = new Tree();
        try 
        {
            con=url.openConnection();
        }
        catch (IOException io){
            System.out.println("Could not open connection");
        }
        String encoding = con.getContentEncoding();
        if (encoding==null){
            encoding = "UTF-8";
        }
        BufferedReader br=null;
        try {
            br = new BufferedReader(new InputStreamReader(con.getInputStream(),encoding));
        }
        catch (UnsupportedEncodingException ue){
            System.err.println("Bad Encoding");
        }
        catch (IOException ioe){
            System.err.println("Could not read from URL");
        }
        StringBuilder sb = new StringBuilder(2500);
        try{
            String line;
            while ((line= br.readLine())!=null){
                sb.append(line);
                sb.append('\n');
            }
            
        }
        catch (IOException ioe){
            System.err.println("Could not read a line from URl:");
        }
        finally
        {
            try{
                br.close();
            }
            catch(IOException ioe){
                System.err.println("Couldn't cleanup buffered read");
            }
        }
       String ans = sb.toString();
       Document doc = Jsoup.parse(ans);// using JSoup to parse HTML//
       String ans1 = doc.text();
       String regex ="\\b(?:a|an|the|was|and|to|i)\\b\\s*";  // removing stopwords using regex. Not a good idea. Thus implemented through a text file.
       ans1.replaceAll(regex, "");
       return ans1;
    }
 
/* private static boolean isCandidateWord(String s){
		
		if(s.length()==1&&s.charAt(0)-'0'==9955){ // '?'
			return false;
		}
		
		boolean isEmpty=true;
		for(int i=0;i<s.length();i++){
			char c=s.charAt(i);
			if(c!=' '&&c-'0'!=112) isEmpty=false;
		}
		return !isEmpty;
	}*/
 
 public Set<String> getKeyset() {
		return tree.getKeyset();
	}
	
	// Returns a HashMap containing all keyword phrases starting with string 'str' and their respective count
	public HashMap<String, Integer> get(String str) {
		return tree.getKeywords(str);
	}
	
	// Returns a HashMap containing all keyword phrases and their respective count
	public HashMap<String, Integer> getAll() {
		return tree.getAll();
	}
 
       
        
        }
        


 
  

