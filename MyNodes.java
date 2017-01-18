/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment;
import java.util.*;
/**
 *
 * @author Harnoor 
 * This class creates nodes for keywords. It adds new nodes if a keyword hasn't been encountered before and updates it's count if it has.
 */
public class MyNodes implements Comparable<Object> {

	private String value;
	private int size;
	private HashMap<String, MyNodes> children;
	
	public MyNodes(String val) {
		value = val;
		size = 1;
		children = new HashMap<String, MyNodes>();
	}
        public MyNodes() {
		value = "";
		size = 0;
		children = new HashMap<String, MyNodes>();
	}
	
	
	
	public MyNodes(String val, int s) {
		value = val;
		size = s;
		children = new HashMap<String, MyNodes>();
	}
	
	
	public MyNodes add(String child) {
		if (!children.containsKey(child))
			children.put(child, new MyNodes(child));
		else
			children.get(child).increment();
		
		return children.get(child);
	}
	
	
	public MyNodes get(String child) {
		return children.get(child);
	}
	
	
	public void increment() {
		size += 1;
	}
	
	
	public String getValue() {
		return value;
	}
	
	
	public int getSize() {
		return size;
	}
	
	
	public HashMap<String, MyNodes> getChildren() {
		return children;
	}
	
	
	public boolean hasChildren() {
		return children.size() != 0;
	}

	@Override
	public int compareTo(Object arg0) {
		MyNodes a = this;
		MyNodes b = (MyNodes) arg0;
		
		if (a.getSize() > b.getSize())
			return -1;
		else if (a.getSize() < b.getSize())
			return 1;
		else
			return 0;
	}
}