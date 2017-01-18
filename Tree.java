/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment;
import java.util.*;
/**
 *
 * @author Harnoor This class adds keywords phrase which is split into individual keywords which are stored in arrays. 
 */
public class Tree {
    
	private HashMap<String, MyNodes> tree;
	
	public Tree() {
		tree = new HashMap<String, MyNodes>();
	}
	
	
	/* * Adds the specified keyword string to the tree. If keyword already exists then increments the Node's size */
	 
	public void add(String keyword) {
		if (!tree.containsKey(keyword))
			tree.put(keyword, new MyNodes(keyword));
		else {
			get(keyword).increment();
		}
	}
	
	
	public void add(String[] keywords) {
		
		int index = 1;
		for (String keyword : keywords) {
			add(keyword);
			addHelper(keywords, index, tree.get(keyword));
			index++;
		}
	}
	
	/*
	 * Recursive helper function for add method
	 */
	private void addHelper(String[] keywords, int index, MyNodes parent) {
		if (index != keywords.length) {
			parent.add(keywords[index]);
			addHelper(keywords, index + 1, parent.get(keywords[index]));
		}
	}
	
	
	/* * Returns the MyNodes associated with the string 'keyword'*/
	 
	public MyNodes get(String keyword) {
		return tree.get(keyword);
	}
	
	/*
	 * Returns a Set of all keys in the tree HashMap
	 */
	public Set<String> getKeyset() {
		return tree.keySet();
	}
	
	/*
	 * Returns a HashMap containing each keyword phrase starting with string 'str' and their respective size
	 */
	public HashMap<String, Integer> getKeywords(String str) {
		return getKeywords(get(str));
	}
	
	
	private HashMap<String, Integer> map = null;
	private HashMap<String, Integer> getKeywords(MyNodes startNode) {
		
		map = new HashMap<String, Integer>();
		getKeywordsHelper(startNode, startNode.getValue());
		
		return map;
	}
	
	/*
	 * Recursive helper function for getKeywords(WordNde startNode)
	 */
	private String getKeywordsHelper(MyNodes node, String keywords) {
		
		if (node.hasChildren()) {
			HashMap<String, MyNodes> ch = node.getChildren();
			for (String child : ch.keySet()) {
				getKeywordsHelper(ch.get(child), keywords + " " + child);
			}
		}
		
		map.put(keywords, node.getSize());
		
		return keywords.trim();
	}
	
	
	 /*  Returns a HashMap containing every possible keyword in the tree and their respective size */
	 
	public HashMap<String, Integer> getAll() {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		for (String startKey : getKeyset()) {
			HashMap<String, Integer> keywords = getKeywords(startKey);
			for (String key : keywords.keySet()) {
				map.put(key, keywords.get(key));
			}
		}
		return map;
	}
		
}

