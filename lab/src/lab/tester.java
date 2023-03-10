package lab;

public class tester {
	public static void main(String args[])
	{
	BinarySearchTree<String> a= new BinarySearchTree<String>();
	a.add("c");
	a.add("b");
	//System.out.print(a.toString());
	a.add("g");
	a.add("D");
	a.add("cat");
	a.add("Dog");
	a.add("catherine");
	
	
	System.out.println(a.getMax());
	
	
	//a.inOrder();
	//a.preOrder();
	}
}
