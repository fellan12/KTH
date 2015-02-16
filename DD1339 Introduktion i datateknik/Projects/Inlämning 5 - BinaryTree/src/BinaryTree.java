/**
 * The BinaryTree class
 * It defines how the binary tree works
 * 
 * @author Felix De Silva
 * @param <T>
 */
public class BinaryTree<T extends Comparable<T>>{
	private int numOfNodes;
	private Node root;

	/**
	 * The Node class that defines how a node lokks like
	 * 
	 * @author Felix De Silva
	 *
	 */
	public class Node{
		private Node leftSub;
		private Node rightSub;
		private T data;

		/**
		 * The construktor for Node
		 */
		public Node(T data){
			this.data = data;
		}

		/**
		 * Returns the right side of the node 
		 * 
		 * Tidskomplexitet: O(1)
		 * @return rightSub - the right branch
		 */
		public Node getRight(){
			return rightSub;
		}

		/**
		 * Returns the left side of the node 
		 * 
		 * Tidskomplexitet: O(1)
		 * @return leftSub - the left branch
		 */
		public Node getLeft(){
			return leftSub;
		}
	}

	/**
	 * The construktor for the BinaryTree
	 */
	public BinaryTree(){
		numOfNodes = 0;
		root = null;
	}

	/**
	 * Looks in the binary tree if the wanted data is there (Iterative)
	 * 
	 * Checks that the data value isnt null
	 * 
	 * Tidskomplexitet: O(n)
	 * @param data  the data value of the wanted found data
	 * @return true/false - depends on the outcome of the method
	 */
	public boolean find(T data){
		if (root == null ||data == null){
			return false;
		}
		Node node = root;
		while (true){
			if (node.data.compareTo(data) > 0) {
				if (node.rightSub == null){
					return false;
				} else {
					node = node.rightSub;
				}

			} else if (node.data.compareTo(data) == 0) {
				break;
			} else {
				if (node.leftSub == null){
					return false;
				} else {
					node = node.leftSub;
				}
			}
		}
		return true;
	}


	/**
	 * Adds a data value to a node (Iterative)
	 * 
	 * checks that duplicates and null is not valid input
	 * 
	 * Tidskomplexitet: O(log n)
	 * @param data - the data value of the wanted insertion data
	 * @return true/false - depends on the outcome of the method
	 */
	public boolean add(T data){
		if (data == null){
			return false;
		}
		if (root == null){
			root = new Node(data);
			numOfNodes++;
			return true;
		}
		Node node = root;
		while (true){
			if (node.data.compareTo(data) > 0) {
				if (node.rightSub == null){
					node.rightSub = new Node(data);
					numOfNodes++;
					break;
				} else {
					node = node.rightSub;
				}

			} else if (node.data.compareTo(data) == 0) {
				return false;
			} else {
				if (node.leftSub == null){
					node.leftSub = new Node(data);
					numOfNodes++;
					break;
				} else {
					node = node.leftSub;
				}
			}
		}
		return true;
	}

	/**
	 * Returns the number of nodes in the binary tree
	 * 
	 * Tidskomplexitet: O(1)
	 * @return numOfNodes - the number of nodes
	 */
	public int numberOfNodes(){
		return numOfNodes;
	}

	/**
	 * returns the depth of the binary tree using the method countLevels
	 * 
	 * Tidskomplexitet: O(N^2) due to countLevels
	 * @return number of levels
	 */
	public int depth(){
		if (root == null){
			return 0;
		}
		return countLevels(root);
	}

	/**
	 * Counts the levels in the binary tree for the depths method
	 * 
	 * Tidskomplexitet: O(N)
	 * @param node - the current node
	 * @return number of levels in the binary tree
	 */

	private int countLevels(Node node){
		if (node.getLeft() == null && node.getRight() != null){
			return 1 + countLevels(node.getRight());
		} else if (node.getLeft() != null && node.getRight() == null){
			return 1 + countLevels(node.getLeft());
		} else if (node.getLeft() != null && node.getRight() != null){
			return 1 + Math.max(countLevels(node.getRight()), countLevels(node.getLeft()));
		} else {
			return 1;
		}
	}

	/**
	 * Returns the number of leaves in the binary tree using countleaves method
	 * 
	 * Tidskomplexitet: O(N^2) due to countLeaves
	 * @return countleaves(root)
	 */
	public int leaves(){
		if (root == null){
			return 0;
		}

		return countleaves(root);
	}

	/**
	 * Counts the leaves in the binary tree for the leaves method
	 * 
	 * Tidskomplexitet: O(N)
	 * @param node
	 * @return the number of leaves in the binary tree
	 */
	private int countleaves(Node node){
		if (node.getLeft() == null && node.getRight() != null){
			return countLevels(node.getRight());
		} else if (node.getLeft() != null && node.getRight() == null){
			return countLevels(node.getLeft());
		} else if (node.getLeft() != null && node.getRight() != null){
			return countleaves(node.getLeft()) + countleaves(node.getRight());
		} else {
			return 1;
		}
	}

	/**
	 * Returns the String of the binary tree in ascending order
	 * 
	 * Tidskomplexitet: O(n) and that is due too gatherNode which is O(n)
	 * @return the String of the binary tree
	 */
	public String toString(){
		return "[" + gatherNode(root).trim() + "]";
	}

	/**
	 * Help method for ToString
	 * Builds the treeString using Stringbuilder
	 * 
	 * Tidskomplexitet O(n)
	 * @param node
	 * @return treeString - the String of the binary tree
	 */
	private String gatherNode(Node node){
		if (node == null){
			return "";
		}
		StringBuilder treeString = new StringBuilder();

		treeString.append(gatherNode(node.getRight()) + " ");
		treeString.append(node.data);
		treeString.append(gatherNode(node.getLeft()));

		return treeString.toString();

	}

}
