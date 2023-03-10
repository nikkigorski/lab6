package lab;

import java.util.ArrayList;

/**
 * BinarySearchTree.java
 * @author abbasjabor Seth Tedder Nikki Gorski
 * @version 10 March 2023
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
	
	public BinarySearchTree() {
		root = null;
	}
	
	/**
	 * Author: Seth Tedder
	 * Adds an item to the tree.  Calls helper method.  See helper method for order.
	 * @param item
	 * @return
	 */
	public boolean add(E item) {
		return add(root, item);
	}
	/**
	 * Author: Seth Tedder
	 * Helper method for add().  The order of this method is O(N).  In the worst case scenario, a tree of height N
	 * will traverse all elements and add at the end, carry out a total of N+1 steps.  Remove the constant, and you 
	 * are left with O(N).
	 * @param localRoot
	 * @param item
	 * @return
	 */
	private boolean add(Node<E> localRoot, E item) {
		if (localRoot == null) {
			root = new Node<E>(item);
			return true;
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
	// If remove is successful, deleteCheck will be set to the value of the item.
	private E deleteCheck;
	/**
	 * Author: Seth Tedder
	 * Removes an item from the tree.  Calls helper method.  See helper method for order.
	 * @param item
	 * @return
	 */
	public E remove(E item) {
		deleteCheck = null;
		root = remove(root, item);
		return deleteCheck;
	}
	/**
	 * Author: Seth Tedder
	 * Helper method for remove().  The order of this method is O(N).  There are two worst cases.  One where the tree is a chain and the item to be removed in at the bottom of it,
	 * leading to every item being touched upon in order to reach the target.  And there's a second where findLargestChild needs to be called.  Whatever this method hasn't already
	 * traversed, findLargestChild will touch upon, leading to every element being traversed.  That means the order is O(N).  
	 * @param localRoot
	 * @param item
	 * @return
	 */
	private Node<E> remove(Node<E> localRoot, E item) {
		if (localRoot == null) {
			return null;
		}
		int compResult = item.compareTo(localRoot.data);
		if (compResult > 0) {
			localRoot.right = remove(localRoot.right, item);
			return localRoot;
		} else if (compResult < 0) {
			localRoot.left = remove(localRoot.left, item);
			return localRoot;
		} else {
			deleteCheck = localRoot.data;
			if (localRoot.left == null) {
				return localRoot.right;
			} else if (localRoot.right == null) {
				return localRoot.left;
			} else {
				if (localRoot.left.right == null) {
					localRoot.data = localRoot.left.data;
					localRoot.left = localRoot.left.left;
					return localRoot;
				} else {
					localRoot.data = findLargestChild(localRoot.left);
					return localRoot;
				}
			}
		}
	}
	/**
	 * Author: Seth Tedder
	 * Helper method for the remove() helper method.  This method's order is O(N). In the worst case scenario where there are only nodes to the right,
	 * it will run until a null node is found which would be N-2 in duration.  Remove the constant, and you are left with an order of O(N).   
	 * @param localRoot
	 * @return
	 */
	private E findLargestChild(Node<E> localRoot) {
		if (localRoot.right.right == null) {
			E item = localRoot.right.data;
			localRoot.right = localRoot.right.left;
			return item;
		} else {
			return findLargestChild(localRoot.right);
		}
	}
	/**
	 * Author: Seth Tedder
	 * Finds an item in the tree.  Calls helper method.  See helper method for order.
	 * @param item
	 * @return
	 */
	public boolean find(E item) {
		return find(root, item);
	}
	/**
	 * Author: Seth Tedder
	 * The order is O(N).  In the worst case, the item is the very last to be touched upon before the program ends, where all items are in a chain.
	 * If all items are traversed, then the order is O(N).  
	 * @param localRoot
	 * @param item
	 * @return
	 */
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
	/**
	 * Author: Seth Tedder
	 * Finds an item's parent in the tree.  Calls helper method.  See helper method for order.
	 * @param item
	 * @return
	 */
	public E getParent(E item) {
		return getParent(root, item, root);
	}
	/**
	 * Author: Seth Tedder
	 * The order is O(N).  Just like with the others, the worst case would involve traversing all items in the tree where the tree's height is equal to the
	 * number of nodes.  In that case, the order is O(N).  
	 * @param localRoot
	 * @param item
	 * @param parent
	 * @return
	 */
	private E getParent(Node<E> localRoot, E item, Node<E> parent) {
		if (localRoot == null || (localRoot == root && localRoot.data == item)) {
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
	/**
	 * Author: Seth Tedder
	 * Finds an item's descendants in the tree.  Calls helper method. The order is O(N).  findNode() has a run time of N, same with buildArray.  Add those together and you get 2N.
	 * Remove the constant and the order is O(N).  
	 * @param item
	 * @return
	 */
	public ArrayList<E> getAllDescendant(E item) {
		Node<E> itemNode = findNode(root, item);
		return buildArray(itemNode);
	}
	/**
	 * Author: Seth Tedder
	 * Helper method for getAllDescendant().
	 * This must traverse all items that are the descendants of the given node, adding them to an ArrayList.  The order is O(N).
	 * @param localRoot
	 * @return
	 */
	private ArrayList<E> buildArray(Node<E> localRoot) {
		ArrayList<E> list = new ArrayList<E>();
		if (localRoot == null) {
			return null;
		}
		if (localRoot.left != null) {
			list.add(localRoot.left.data);
			list.addAll(buildArray(localRoot.left));
		}
		if (localRoot.right != null) {
			list.add(localRoot.right.data);
			list.addAll(buildArray(localRoot.right));
		}
		return list;
	}
	/**
	 * Author: Seth Tedder
	 * Helper method for getAllDescendant().  
	 * Acts similarly to find, though this method returns the node of the given item.  
	 * The order is O(N).  In the worst case, the item is the very last to be touched upon before the program ends, where all items are in a chain.
	 * If all items are traversed, then the order is O(N).  
	 * @param localRoot
	 * @param item
	 * @return
	 */
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
	
	/**
	 * 
	 * Main method.  Used for testing
	 * @param args
	 */
	public static void main (String[] args) {
		//Tests for Seth Tedder's methods
		BinarySearchTree test = new BinarySearchTree();
		
		test.add(7);
		System.out.println("Add/Find " + test.find(7));
		
		test.add(4);
		System.out.println("Add/Find " + test.find(4));
		
		test.add(18);
		System.out.println("Add/Find 18: " + test.find(18));
		
		test.add(17);
		System.out.println("Get Parent 17: " + test.getParent(17));
		
		ArrayList list = test.getAllDescendant(7);
		for (int i = 0; i < list.size(); i++) {
			System.out.println("Get All Descendants of 7: (index " + i + ") " + list.get(i));
		}
		
		System.out.println(test.remove(18) + " Remove.  Should be false: " + test.find(18));
		System.out.println("Check to see if children(17) are retained: " + test.find(17));
		System.out.println("Get Parent 17 (post deletion of parent): " + test.getParent(17));
		
		ArrayList list1 = test.getAllDescendant(7);
		for (int i = 0; list1 != null && i < list1.size(); i++) {
			System.out.println("Get All Descendants of 7 (post deletion of 18): (index " + i + ") " + list1.get(i));
		}
		System.out.println(test.find(18));
	}
}