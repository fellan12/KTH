package kth.csc.inda;

/**
 * @author Stefan Nilsson
 * @version 2013-01-01
 */
public class HashGraphTest extends AbstractGraphTest {
	@Override
	protected void createEmptyGraphs() {
		g0 = new HashGraph(0);
		g1 = new HashGraph(1);
		g5 = new HashGraph(5);
	}

	public void testConstructor() {
		try {
			new HashGraph(-1);
			fail();
		} catch (IllegalArgumentException e) {
		}
	}
}
