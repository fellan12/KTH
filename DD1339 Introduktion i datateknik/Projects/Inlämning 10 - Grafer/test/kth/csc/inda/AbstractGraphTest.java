package kth.csc.inda;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

import junit.framework.TestCase;

/**
 * @author Stefan Nilsson
 * @version 2013-01-01
 */
public abstract class AbstractGraphTest extends TestCase {
	protected Graph g0, g1, g5;

	/**
	 * This method should instantiate g0, g1, and g5 to be five graphs with no
	 * edges and 0, 1, and 5 vertices.
	 */
	protected abstract void createEmptyGraphs();

	/**
	 * Sets up the test fixture. Called before every test case method.
	 */
	@Override
	protected void setUp() {
		createEmptyGraphs();
		g1.addBi(0, 0);
		g5.addBi(0, 1);
		g5.add(2, 3, 1);
	}

	/**
	 * Tears down the test fixture. Called after every test case method.
	 */
	@Override
	protected void tearDown() {
		g0 = null;
		g1 = null;
		g5 = null;
	}

	public void testToString() {
		g5.remove(0, 1);
		String g0s = "{}";
		String g1s = "{(0,0)}";
		String g5s1 = "{(1,0), (2,3,1)}";  							//{(0,1), (1,0), (2,3,1)} ???
		String g5s2 = "{(2,3,1), (1,0)}";
		String g5s3 = "{(1,0), (2,2,1), (2,3,1)}";					//Extra tillägg
		String g5s4 = "{(1,0), (2,1,1), (2,2,1), (2,3,1)}";			//Extra tillägg
		System.out.println(g0.toString());
		assertTrue(g0.toString().equals(g0s));
		System.out.println(g1.toString());
		System.out.println(g1s);
		assertTrue(g1.toString().equals(g1s));
		System.out.println(g5.toString());
		System.out.println(g5s1);
		assertTrue(g5.toString().equals(g5s1) || g5.toString().equals(g5s2));
		
		//Extra tillägg
		g5.add(2, 2, 1);
		System.out.println(g5.toString());
		System.out.println(g5s3);
		assertTrue(g5.toString().equals(g5s3));
		//Extra tillägg
		g5.add(2, 1, 1);
		System.out.println(g5.toString());
		System.out.println(g5s4);
		assertTrue(g5.toString().equals(g5s4));
	}

	public void testNumVertices() {
		assertEquals(0, g0.numVertices());
		assertEquals(1, g1.numVertices());
		assertEquals(5, g5.numVertices());
	}

	public void testNumEdges() {
		assertEquals(0, g0.numEdges());
		assertEquals(1, g1.numEdges());
		assertEquals(3, g5.numEdges());
		g5.remove(1, 2);
		assertEquals(3, g5.numEdges());
		g5.add(0, 1);
		assertEquals(3, g5.numEdges());
		g5.add(1, 2);
		assertEquals(4, g5.numEdges());
		g5.remove(0, 1);
		assertEquals(3, g5.numEdges());
		g5.remove(0, 1);
		assertEquals(3, g5.numEdges());
	}

	public void testEdgeCost() {
		assertEquals(-1, g1.cost(0, 0));
		assertEquals(-1, g5.cost(0, 1));
		assertEquals(1, g5.cost(2, 3));
		assertEquals(-1, g5.cost(1, 2));
		g1.addBi(0, 0, 2);
		assertEquals(2, g1.cost(0, 0));
		g1.remove(0, 0);
		g5.remove(0, 1);
		g5.remove(2, 3);
		assertEquals(-1, g1.cost(0, 0));
		assertEquals(-1, g5.cost(0, 1));
		assertEquals(-1, g5.cost(2, 3));
		g1.remove(0, 0);
		g5.remove(0, 1);
		g5.remove(2, 3);
		assertEquals(-1, g1.cost(0, 0));
		assertEquals(-1, g5.cost(0, 1));
		assertEquals(-1, g5.cost(2, 3));
		g5.addBi(1, 2, 3);
		assertEquals(3, g5.cost(1, 2));
		assertEquals(3, g5.cost(2, 1));
	}

	public void testDegree() {
		assertEquals(1, g1.degree(0));
		g1.remove(0, 0);
		assertEquals(0, g1.degree(0));
		assertEquals(0, g5.degree(4));
		assertEquals(1, g5.degree(0));
		assertEquals(1, g5.degree(1));
		g5.add(1, 2);
		assertEquals(1, g5.degree(0));
		assertEquals(2, g5.degree(1));
		g5.remove(0, 1);
		assertEquals(0, g5.degree(0));
		assertEquals(2, g5.degree(1));
		g5.remove(0, 1);
		assertEquals(0, g5.degree(0));
		assertEquals(2, g5.degree(1));
		g5.removeBi(0, 1);
		assertEquals(0, g5.degree(0));
		assertEquals(1, g5.degree(1));
		try {
			g0.degree(0);
			fail();
		} catch (IllegalArgumentException e) {
		}
		try {
			g1.degree(-1);
			fail();
		} catch (IllegalArgumentException e) {
		}
		try {
			g1.degree(1);
			fail();
		} catch (IllegalArgumentException e) {
		}
	}

	public void testNeighbors() {
		VertexIterator pi = g1.neighbors(0);
		assertTrue(pi.hasNext());
		assertEquals(0, pi.next());
		pi = g5.neighbors(0);
		assertEquals(1, pi.next());
		assertFalse(pi.hasNext());
		g5.add(0, 0);
		g5.add(0, 1);
		g5.add(0, 2);
		g5.add(0, 3);
		pi = g5.neighbors(0);
		Set<Integer> s = new HashSet<Integer>();
		for (int i = 0; i < 4; i++) {
//			System.out.println(i);
			s.add(pi.next());
//			System.out.println(s);
		}
		assertEquals(4, s.size());
		for (int i = 0; i < 4; i++) {
			assertTrue(s.contains(i));
		}
		assertFalse(pi.hasNext());
		try {
			pi.next();
			fail();
		} catch (NoSuchElementException e) {
		}
		pi = g5.neighbors(4);
		assertFalse(pi.hasNext());
		try {
			pi.next();
			fail();
		} catch (NoSuchElementException e) {
		}
	}

	public void testHasEdge() {
		assertTrue(g1.hasEdge(0, 0));
		assertFalse(g5.hasEdge(0, 0));
		assertFalse(g5.hasEdge(4, 0));
		assertTrue(g5.hasEdge(0, 1));
		assertTrue(g5.hasEdge(1, 0));
		assertFalse(g5.hasEdge(1, 2));
		g5.add(1, 2);
		assertTrue(g5.hasEdge(1, 2));
	}
}
