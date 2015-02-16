package kth.csc.inda;

import java.util.NoSuchElementException;

/**
 * A graph with a fixed number of vertices implemented using an adjacency
 * matrix. Space complexity is &Theta;(n<sup>2</sup>), where n is the number of
 * vertices.
 * 
 * @author Stefan Nilsson
 * @version 2013-01-01
 */
public class MatrixGraph implements Graph {
	/** Number of vertices in the graph. */
	private final int numVertices;

	/** Number of edges in the graph. */
	private int numEdges;

	/**
	 * Adjaceny matrix: adj[v][w] is EMPTY if v is not adjacent to w, otherwise
	 * adj[v][w] is NO_COST or the non-negative cost of the edge.
	 */
	private final int[][] adj;
	private final static int EMPTY = -2; // no edge

	/**
	 * Constructs a MatrixGraph with n vertices and no edges. Time complexity:
	 * O(n<sup>2</sup>)
	 * 
	 * @throws IllegalArgumentException
	 *             if n < 0
	 */
	public MatrixGraph(int n) {
		if (n < 0)
			throw new IllegalArgumentException("n = " + n);

		numVertices = n;
		adj = new int[n][n];
		for (int i = n - 1; i >= 0; i--) {
			int[] row = adj[i];
			for (int j = n - 1; j >= 0; j--)
				row[j] = EMPTY;
		}
	}

	/**
	 * {@inheritDoc Graph} Time complexity: O(1).
	 */
	@Override
	public int numVertices() {
		return numVertices;
	}

	/**
	 * {@inheritDoc Graph} Time complexity: O(1).
	 */
	@Override
	public int numEdges() {
		return numEdges;
	}

	/**
	 * {@inheritDoc Graph} Time complexity: O(n), where n is the number of
	 * vertices.
	 */
	@Override
	public int degree(int v) throws IllegalArgumentException {
		checkVertexParameter(v);

		int d = 0;
		int[] row = adj[v];
		for (int i = numVertices - 1; i >= 0; i--)
			if (row[i] != EMPTY)
				d++;
		return d;
	}

	/**
	 * {@inheritDoc Graph} Time complexity: O(n), where n is the number of
	 * vertices.
	 */
	@Override
	public VertexIterator neighbors(int v) throws IllegalArgumentException {
		checkVertexParameter(v);

		return new NeighborIterator(v);
	}

	private class NeighborIterator implements VertexIterator {
		int[] row;
		final int n;
		int nextPos = -1;

		NeighborIterator(int v) {
			row = adj[v];
			n = row.length;
			findNext();
		}

		private void findNext() {
			nextPos++;
			while (nextPos < n && row[nextPos] == EMPTY)
				nextPos++;
		}

		@Override
		public boolean hasNext() {
			return nextPos < n;
		}

		@Override
		public int next() {
			int pos = nextPos;
			if (pos < n) {
				findNext();
				return pos;
			}
			throw new NoSuchElementException(
					"This iterator has no more elements.");
		}
	}

	/**
	 * {@inheritDoc Graph} Time complexity: O(1).
	 */
	@Override
	public boolean hasEdge(int v, int w) throws IllegalArgumentException {
		checkVertexParameters(v, w);

		return adj[v][w] != EMPTY;
	}

	/**
	 * {@inheritDoc Graph} Time complexity: O(1).
	 */
	@Override
	public int cost(int v, int w) throws IllegalArgumentException {
		checkVertexParameters(v, w);

		return Math.max(NO_COST, adj[v][w]);
	}

	/**
	 * {@inheritDoc Graph} Time complexity: O(1).
	 */
	@Override
	public void add(int from, int to) throws IllegalArgumentException {
		checkVertexParameters(from, to);

		addEdge(from, to, NO_COST);
	}

	/**
	 * {@inheritDoc Graph} Time complexity: O(1).
	 */
	@Override
	public void add(int from, int to, int c) throws IllegalArgumentException {
		checkVertexParameters(from, to);
		checkNonNegativeCost(c);

		addEdge(from, to, c);
	}

	/**
	 * {@inheritDoc Graph} Time complexity: O(1).
	 */
	@Override
	public void addBi(int v, int w) throws IllegalArgumentException {
		checkVertexParameters(v, w);

		addEdge(v, w, NO_COST);
		if (v == w)
			return;
		addEdge(w, v, NO_COST);
	}

	/**
	 * {@inheritDoc Graph} Time complexity: O(1).
	 */
	@Override
	public void addBi(int v, int w, int c) throws IllegalArgumentException {
		checkVertexParameters(v, w);
		checkNonNegativeCost(c);

		addEdge(v, w, c);
		if (v == w)
			return;
		addEdge(w, v, c);
	}

	/**
	 * Add an edge without checking parameters.
	 */
	private void addEdge(int from, int to, int c) {
		int[] row = adj[from];
		if (row[to] == EMPTY)
			numEdges++;
		row[to] = c;
	}

	/**
	 * {@inheritDoc Graph} Time complexity: O(1).
	 */
	@Override
	public void remove(int from, int to) throws IllegalArgumentException {
		checkVertexParameters(from, to);

		removeEdge(from, to);
	}

	/**
	 * {@inheritDoc Graph} Time complexity: O(1).
	 */
	@Override
	public void removeBi(int v, int w) throws IllegalArgumentException {
		checkVertexParameters(v, w);

		removeEdge(v, w);
		if (v == w)
			return;
		removeEdge(w, v);
	}

	/**
	 * Remove an edge without checking parameters.
	 */
	private void removeEdge(int from, int to) {
		int[] row = adj[from];
		if (row[to] != EMPTY) {
			row[to] = EMPTY;
			numEdges--;
		}
	}

	/**
	 * Returns a string representation of this graph.
	 * 
	 * @return a String representation of this graph
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		for (int i = 0; i < numVertices; i++) {
			int[] row = adj[i];
			for (int j = 0; j < numVertices; j++) {
				int x = row[j];
				switch (x) {
				case EMPTY:
					break;
				case NO_COST:
					sb.append("(" + i + "," + j + "), ");
					break;
				default:
					sb.append("(" + i + "," + j + "," + x + "), ");
				}
			}
		}
		if (numEdges > 0)
			sb.setLength(sb.length() - 2); // Remove trailing ", "
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Checks a single vertex parameter v.
	 * 
	 * @throws IllegalArgumentException
	 *             if v is out of range
	 */
	private void checkVertexParameter(int v) {
		if (v < 0 || v >= numVertices)
			throw new IllegalArgumentException("Out of range: v = " + v + ".");
	}

	/**
	 * Checks two vertex parameters v and w.
	 * 
	 * @throws IllegalArgumentException
	 *             if v or w is out of range
	 */
	private void checkVertexParameters(int v, int w) {
		if (v < 0 || v >= numVertices || w < 0 || w >= numVertices)
			throw new IllegalArgumentException("Out of range: v = " + v
					+ ", w = " + w + ".");
	}

	/**
	 * Checks that the cost c is non-negative.
	 * 
	 * @throws IllegalArgumentException
	 *             if c < 0
	 */
	private void checkNonNegativeCost(int c) {
		if (c < 0)
			throw new IllegalArgumentException("Illegal cost: c = " + c + ".");
	}
}
