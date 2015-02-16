/**
 * The test class TestLinkedList 
 * 
 * @author Felix De Silva
 * @version 2014-01-29
 */
public class TestLinkedList extends junit.framework.TestCase {
	/**
	 * Default constructor for test class test
	 */
	public TestLinkedList() {
	}
	
	/**
	 * Test if list with size 0 is true  (empty list)
	 */
	public void testSize0(){
		LinkedList<String> list = new LinkedList<String>();
		assertEquals(0, list.size());
	}
	
	public void testRemove2(){
		LinkedList<String> list = new LinkedList<String>();
		list.addFirst("hej");
		list.removeFirst2();
		assertTrue(list.isHealthy());
	}
	
	/**
	 * Test if size is 2 after removeFirst it used in a size 3 list
	 */
	public void testRemoveFirst2(){
		LinkedList<String> list = new LinkedList<String>();
		list.addFirst("hej");
		list.addLast("tjo");
		list.addFirst("kashi");
		list.removeFirst();
		assertEquals(2, list.size());

	}
	
	/**
	 * Test if the first element is changed after removeFirst it used
	 */
	public void testRemoveFirst(){
		LinkedList<String> list = new LinkedList<String>();
		list.addFirst("hej");
		list.addFirst("kashi");
		list.removeFirst();
		assertEquals("hej", list.getFirst());
	}
	
	/**
	 * Test if the last element is the same after removeFirst it used
	 */
	public void testRemoveFirst3(){
		LinkedList<String> list = new LinkedList<String>();
		list.addLast("hej");
		list.addLast("kashi");
		list.removeFirst();
		assertEquals("kashi", list.getLast());
	}
	
	/**
	 * Test if clear() work on a list with 3 elements
	 */
	public void testClear(){
		LinkedList<String> list = new LinkedList<String>();
		list.addFirst("hej");
		list.addLast("tjo");
		list.addFirst("kashi");
		list.clear();
		assertTrue(list.isEmpty());
	}
	
	/**
	 * Test if list with size 2 is true
	 */
	public void testSize2(){
		LinkedList<String> list = new LinkedList<String>();
		list.addFirst("hej");
		list.addFirst("tja");
		assertEquals(2, list.size());
	}

	/**
	 * Test if a empty list is empty
	 */
	public void testNewListIsEmpty() {
		LinkedList<String> list = new LinkedList<String>();
		assertTrue(list.isEmpty());
	}

	/**
	 * Test if a empty list is healty
	 */
	public void testNewListIsHealthy() {
		LinkedList<String> list = new LinkedList<String>();
		assertTrue(list.isHealthy());

	}

	/**
	 * Test if first element is last element in a one element list
	 */
	public void testAddGetLast() {
		LinkedList<String> list = new LinkedList<String>();
		list.addFirst("hej");
		assertEquals("hej", list.getLast());
	}

	/**
	 * Test if first element is the first element in a one element list
	 */
	public void testAddGetFirst() {
		LinkedList<String> list = new LinkedList<String>();
		list.addFirst("hej");
		assertEquals("hej", list.getFirst());
	}


	/**
	 * Test if last element is last element in a twoelement list
	 */
	public void testAddGetLast2() {
		LinkedList<String> list = new LinkedList<String>();
		list.addFirst("hej");
		list.addFirst("tjo");
		assertEquals("hej", list.getLast());
	}

	/**
	 * Test if first element is the first element in a two element list
	 */
	public void testAddGetFirst2() {
		LinkedList<String> list = new LinkedList<String>();
		list.addFirst("hej");
		list.addFirst("tjo");
		assertEquals("tjo", list.getFirst());
	}
	
	/**
	 * Test if a two element list is healthy
	 */
	public void testTwoElementsIsHealthy() {
		LinkedList<String> list = new LinkedList<String>();
		list.addFirst("hej");
		list.addFirst("tjo");
		assertTrue(list.isHealthy());
	}
	
	/**
	 * Test if get(int) works with index = 4 to return "tjo"
	 */
	public void testGetInt(){
		LinkedList<String> list = new LinkedList<String>();
		list.addFirst("hej");
		list.addFirst("tjo");
		list.addFirst("blö");
		list.addFirst("möö");
		list.addFirst("apa");
		list.addFirst("per");
		assertEquals("tjo", list.get(4));
	}
	
	/**
	 * Test if toString() work on a list with 3 elements 
	 */
	public void testToString(){
		LinkedList<String> list = new LinkedList<String>();
		list.addFirst("hej");
		list.addFirst("tjo");
		list.addFirst("blö");
		assertEquals("[blö, tjo, hej]", list.toString());
	}
}
