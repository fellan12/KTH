/**
 * The test class for binary tree
 */
import static org.junit.Assert.*;

import org.junit.Test;
public class BinaryTreeTest {
	
	/**
	 * Test that the find method works as i should after description
	 * 
	 * should return false with null
	 */
	@Test
	public void testFind(){
		BinaryTree<String> tree = new BinaryTree<>();
		assertFalse(tree.find("hej"));		//should not find null
		
		tree.add("hej");
		assertTrue(tree.find("hej"));
		
		tree.add("då");
		assertTrue(tree.find("då"));
		assertTrue(tree.find("hej"));
		
		
		assertFalse(tree.find("fan"));
		
		tree.add("Fan!");
		assertTrue(tree.find("Fan!"));
		assertTrue(tree.find("då"));
		assertTrue(tree.find("hej"));
		
		
		assertFalse(tree.find(null));		//should not find null
	}
	
	/**
	 * Test that the add method works as i should after description
	 * 
	 * should give false with duplicates and null
	 */
	@Test
	public void testAdd(){
		BinaryTree<String> tree = new BinaryTree<>();
		
		assertTrue(tree.add("hej"));
		assertTrue(tree.add("då"));
		assertTrue(tree.add("din"));
		assertTrue(tree.add("fulle"));
		assertTrue(tree.add("fan"));
		assertFalse(tree.add("fan"));
		assertFalse(tree.add(null));
	}
	
	/**
	 * Test that the numberOfNodes method works as i should after description
	 */
	@Test
	public void testNumberOfNodes(){
		BinaryTree<String> tree = new BinaryTree<>();
		
		assertEquals(0, tree.numberOfNodes());
		
		tree.add("hej");
		assertEquals(1, tree.numberOfNodes());
		
		tree.add("på");
		assertEquals(2, tree.numberOfNodes());
		
		tree.add("dig");
		assertEquals(3, tree.numberOfNodes());
		
		tree.add("fan");
		assertEquals(4, tree.numberOfNodes());
		
		tree.add(null);
		assertEquals(4, tree.numberOfNodes());
		
		
	}
	
	/**
	 * Test that the depth method works as i should after description
	 */
	@Test
	public void testTreeDepth(){
		BinaryTree<Integer> tree = new BinaryTree<>();
		
		assertEquals(0, tree.depth());
		
		tree.add(100);
		assertEquals(1, tree.depth());
		
		tree.add(200);
		assertEquals(2, tree.depth());
		
		tree.add(50);
		assertEquals(2, tree.depth());
		
		tree.add(10);
		assertEquals(3, tree.depth());
		
		tree.add(75);
		assertEquals(3, tree.depth());
		
		tree.add(300);
		assertEquals(3, tree.depth());
		
		tree.add(150);
		assertEquals(3, tree.depth());
		
		
	}
	
	/**
	 * Test that the leaves method works as i should after description
	 */
	@Test
	public void testLeaves(){
		BinaryTree<String> tree = new BinaryTree<>();

		assertEquals(0, tree.leaves());
		
		tree.add("8");
		assertEquals(1, tree.leaves());					//one root = one leaf
		
		tree.add("9");
		assertEquals(1, tree.leaves());
		
		tree.add("5");
		assertEquals(2, tree.leaves());
		
		tree.add("2");
		assertEquals(2, tree.leaves());
		
		tree.add("6");
		assertEquals(3, tree.leaves());
	}
	
	/**
	 * Test that the ToString method works as i should after description
	 */
	@Test
	public void testToString(){
		BinaryTree<Integer> tree = new BinaryTree<>();
		
		tree.add(1);
		
		tree.add(2);
		
		tree.add(5);
		
		tree.add(19);
		
		tree.add(12);
		assertEquals("[1 2 5 12 19]", tree.toString());
		
		
	}
}
