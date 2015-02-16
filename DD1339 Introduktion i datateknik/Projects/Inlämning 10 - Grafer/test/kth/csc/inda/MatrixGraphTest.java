package kth.csc.inda;

/**
 * @author Stefan Nilsson
 * @version 2013-01-01
 */
public class MatrixGraphTest extends AbstractGraphTest {
	@Override
	protected void createEmptyGraphs() {
		g0 = new MatrixGraph(0);
		g1 = new MatrixGraph(1);
		g5 = new MatrixGraph(5);
	}

	public void testConstructor() {
		try {
			new MatrixGraph(-1);
			fail();
		} catch (IllegalArgumentException e) {
		}
	}
}