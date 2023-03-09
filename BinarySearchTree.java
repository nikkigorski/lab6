package lab;

import java.util.ArrayList;

/**
 * BinarySearchTree.java
 * @author abbasjabor Seth Tedder Nikki Gorski
 * @version 1 March 2023
 */
public class BinarySearchTree <E extends Comparable<E>>{
	protected Node<E> root;
	
	//nested class for a tree node
//	implements Comparable<Node<E>>
	protected static class Node<E extends Comparable<E>> {
		E data;
		Node <E> left; 
		Node <E> right;
		
		//Node constructor
		public Node (E data) {
			this.data = data;
			left = null;
			right = null; 
		}
		public int compareTo(E item) {
			return this.data.compareTo(item);
		}
	}
	//Seth Tedder's Methods
	public boolean add(E item) {
		return add(root, item);
	}
	private boolean add(Node<E> localRoot, E item) {
		if (localRoot == null) {
			return false;
		}
		int compResult = item.compareTo(localRoot.data);
		if (compResult < 0 && localRoot.left != null) {
			return add(localRoot.left, item);
		} else if (compResult < 0) {
			Node<E> newNode = new Node<E>(item);
			localRoot.left = newNode;
			return true;
		} else if (compResult > 0 && localRoot.right != null) {
			return add(localRoot.right, item);
		} else if (compResult > 0) {
			Node<E> newNode = new Node<E>(item);
			localRoot.right = newNode;
			return true;
		} else {
			Node<E> newNode = new Node<E>(item);
			localRoot.right = newNode;
			return true;
		}
	}
	
	public E remove(E item) {
		return remove(root, item);
	}
	private E remove(Node<E> localRoot, E item) {
		if (localRoot == null) {
			return null;
		}
		int compResult = item.compareTo(localRoot.data);
		if (compResult > 0) {
			return remove(localRoot.right, item);
		} else if (compResult < 0) {
			return remove(localRoot.left, item);
		} else {
			if (localRoot.left == null && localRoot.right == null) {
				E data = localRoot.data;
				localRoot = null;
				return data;
			} else if (localRoot.right != null) {
				localRoot.data = nextRight(localRoot);
				return remove(localRoot.right, item);
			} else {
				localRoot.data = nextLeft(localRoot);
				return remove(localRoot.left, item);
			}
		}
	}
	private E nextRight(Node<E> root){
        root = root.right;
        while(root.left != null){
            root = root.left;
        }
        return root.data;
    }
	private E nextLeft(Node<E> localRoot){
        root = root.left;
        while(root.right != null){
            root = root.right;
        }
        return root.data;
    }
	
	public boolean find(E item) {
		return find(root, item);
	}
	private boolean find(Node<E> localRoot, E item) {
		if (localRoot == null) {
			return false;
		}
		int compResult = item.compareTo(localRoot.data);
		if (compResult == 0) {
			return true;
		} else if (compResult < 0) {
			return find(localRoot.left, item);
		} else {
			return find(localRoot.right, item);
		}
	}
	
	public E getParent(E item) {
		return getParent(root, item, root);
	}
	private E getParent(Node<E> localRoot, E item, Node<E> parent) {
		if (localRoot == null) {
			return null;
		}
		int compResult = item.compareTo(localRoot.data);
		if (compResult == 0) {
			return parent.data;
		} else if (compResult < 0) {
			return getParent(localRoot.left, item, localRoot);
		} else {
			return getParent(localRoot.right, item, localRoot);
		}
	}
	
	public ArrayList<E> getAllDescendant(E item) {
		ArrayList<E> list = new ArrayList<E>();
		Node<E> itemNode = findNode(root, item);
		return getAllDescendant(itemNode, list);
	}
	private ArrayList<E> getAllDescendant(Node<E> localRoot, ArrayList<E> list) {
		if (localRoot == null) {
			return null;
		}
		if (localRoot.left != null) {
			return getAllDescendant(localRoot.left, list);
		} else if (localRoot.right != null) {
			return getAllDescendant(localRoot.right, list);
		} else {
			list.add(localRoot.data);
			return list;
		}
	}
	private Node<E> findNode(Node<E> localRoot, E item) {
		if (localRoot == null) {
			return null;
		}
		int compResult = item.compareTo(root.data);
		if (compResult == 0) {
			return localRoot;
		} else if (compResult < 0) {
			return findNode(localRoot.left, item);
		} else {
			return findNode(localRoot.right, item);
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
	public static void main (String[] args) {
		BinarySearchTree test = new BinarySearchTree();
		test.find(7);
	}
}