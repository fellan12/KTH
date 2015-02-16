/**
 * A test class for StringHash class
 * 
 * @author Felix De Silva
 * @date 11 feb 2014
 */
import static org.junit.Assert.*;
import org.junit.Test;
public class StringHashTest {

	/*
	 * Test if the add method works
	 * 
	 * Should return true is the string isn't in the list
	 * Should return false is the string is in the list
	 * Should return false if the string is empty "" or null
	 */
	@Test
	public void testAdd() {
		StringHash table = new StringHash(3);
		
		table.add("hejdå");
		
		assertEquals(true, table.add("hej"));
		assertEquals(true, table.add("tja"));
		assertEquals(true, table.add("blö"));
		assertEquals(false, table.add("hejdå"));
		assertEquals(false, table.add(""));
		assertEquals(false, table.add(null));
		
	}
	
	/*
	 * Test if the remove method works
	 * 
	 * Should return true is the string is in the list
	 * Should return false is the string isn't in the list	 
	 * Should return false if the string is empty "" or null
	 */
	@Test
	public void testRemove(){
		StringHash table = new StringHash(3);
		
		table.add("hej");
		table.add("tja");
		table.add("blö");
		
		
		assertEquals(true, table.remove("hej"));
		assertEquals(true, table.remove("tja"));
		assertEquals(true, table.remove("blö"));
		assertEquals(false, table.remove("hejdå"));
		assertEquals(false, table.remove(""));
		assertEquals(false, table.remove(null));
	}
	
	/*
	 * Test if the contains method works
	 * 
	 * Should return true is the string is in the list
	 * Should return false is the string isn't in the list
	 */
	@Test
	public void testcontains(){
		StringHash table = new StringHash(3);
		
		table.add("hej");
		table.add("tja");
		table.add("blö");
		
		
		assertEquals(true, table.contains("hej"));
		assertEquals(true, table.contains("tja"));
		assertEquals(true, table.contains("blö"));
		assertEquals(false, table.contains("hejdå"));
		assertEquals(false, table.remove(""));
		assertEquals(false, table.remove(null));
	}
	
	/*
	 * Test if a empty list behaves as it should
	 * 
	 * false if using the contains method on empty list
	 * false if using the remove method on empty list
	 * true if using the add method on empty list
	 * false if using the add method again after first add
	 */
	@Test
	public void testEmptyList(){
		StringHash table = new StringHash(3);
		assertFalse(table.contains("hej"));
		assertFalse(table.remove("hej"));
		assertTrue(table.add("hej"));
		assertFalse(table.add("hej"));
	}

}
