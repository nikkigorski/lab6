package lab;

import java.util.ArrayList;

/**
 * BinarySearchTree.java
 * @author abbasjabor Seth Tedder Nikki Gorski
 * @version 1 March 2023
 */
public class BinarySearchTree <E>{
	protected Node<E> root;
	
	//nested class for a tree node
	protected static class Node<E>{
		E data;
		Node <E> left; 
		Node <E> right;
		
		//Node constructor
		public Node (E data) {
			this.data = data;
			left = null;
			right = null; 
		}
		
	}
	//BinaryTree Constructor
	public BinarySearchTree() {
		root = null;
	}
	public ArrayList<E> bfs(){
		return null;
	
	}
	public boolean isIdentical(Node<E> anotherTree){
		return false;
		
	}
	public int numLeaves() {
		return 0;
		
	}
	public int numInternal() {
		return 0;
		
	}
	public void clear() {
		
	}
}