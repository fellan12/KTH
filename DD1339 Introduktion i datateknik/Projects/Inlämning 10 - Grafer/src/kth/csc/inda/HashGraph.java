package kth.csc.inda;


import java.awt.List;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

import javax.swing.ViewportLayout;

/**
 * A graph with a fixed number of vertices implemented using adjacency maps.
 * Space complexity is &Theta;(n + m) where n is the number of vertices and m
 * the number of edges.
 * 
 * @author Felix De Silva
 * @date
 */
public class HashGraph implements Graph {
	/**
	 * The map edges[v] contains the key-value pair (w, c) if there is an edge
	 * from v to w; c is the cost assigned to this edge. The maps may be null
	 * and are allocated only when needed.
	 */
	private final Map<Integer, Integer>[] edges;
	private final static int INITIAL_MAP_SIZE = 4;

	/** Number of edges in the graph. */
	private int numEdges;

	/**
	 * Constructs a HashGraph with n vertices and no edges. Time complexity:
	 * O(n)
	 * 
	 * @throws IllegalArgumentException
	 *             if n < 0
	 */
	public HashGraph(int n) {
		if (n < 0)
			throw new IllegalArgumentException("n = " + n);

		// The array will contain only Map<Integer, Integer> instances created
		// in addEdge(). This is sufficient to ensure type safety.
		@SuppressWarnings("unchecked")
		Map<Integer, Integer>[] a = new HashMap[n];
		edges = a;
	}

	/**
	 * Add an edge without checking parameters.
	 */
	private void addEdge(int from, int to, int cost) {
		if (edges[from] == null)
			edges[from] = new HashMap<Integer, Integer>(INITIAL_MAP_SIZE);
		if (edges[from].put(to, cost) == null)
			numEdges++;
	}

	/**
	 * Remove an edge without checking parameters.
	 */
	private void removeEdge(int from, int to) {
		if (edges[from] != null && edges[from].containsKey(to)){
			edges[from].remove(to);
			numEdges--;
		}
	}

	/**
	 * Returns the number of vertices in this graph. 
	 * Time complexity: O(1).
	 * 
	 * @return the number of edges in this graph
	 */
	@Override
	public int numVertices() {
		return edges.length;
	}

	/**
	 * Returns the number of edges in this graph. 
	 * Time complexity: O(1).
	 * 
	 * @return the number of edges in this graph
	 */
	@Override
	public int numEdges() {
		return numEdges;
	}

	/**
	 * Returns the degree of vertex v.
	 * 
	 * @param v
	 *            vertex
	 * @return the degree of vertex v
	 * @throws IllegalArgumentException
	 *             if v is out of range
	 */
	@Override
	public int degree(int v) throws IllegalArgumentException {
		if (v < 0 || v >= numVertices()){
			throw new IllegalArgumentException("Out of range: v = " + v + ".");
		}else{
			if(edges != null && edges[v] != null){
				return edges[v].size();				//Use size now instead for iterating though keyset
			}else{
				return 0;
			}
		}
	}

	/**
	 * Returns an iterator of vertices adjacent to v.
	 * 
	 * @param v
	 *            vertex
	 * @return an iterator of vertices adjacent to v
	 * @throws IllegalArgumentException
	 *             if v is out of range
	 */
	@Override
	public VertexIterator neighbors(int v) {
		Map<Integer,Integer> neighbor = edges[v];

		if(neighbor == null) {
			return new VertexIterator() {
				@Override
				public boolean hasNext() {
					return false;
				}
				@Override
				public int next() throws NoSuchElementException {
					throw new NoSuchElementException();
				}
			};
		}

		final Iterator<Integer> current = neighbor.keySet().iterator();

		return new VertexIterator() {

			@Override
			public boolean hasNext() {
				return current.hasNext();
			}

			@Override
			public int next() throws NoSuchElementException {
				if(current.hasNext()) {
					return current.next();
				} else {
					throw new NoSuchElementException();
				}
			}
		};
	}

	/**
	 * Returns true if there is an edge from v to w.
	 * 
	 * @param v
	 *            vertex
	 * @param w
	 *            vertex
	 * @return true if there is an edge from v to w.
	 * @throws IllegalArgumentException
	 *             if v or w are out of range
	 */
	@Override
	public boolean hasEdge(int v, int w) throws IllegalArgumentException{
		if(0 <= v && v < edges.length){
			if(edges[v] != null && edges[v].containsKey(w)){
				return true;
			}else{
				return false;
			}
		}else{
			throw new IllegalArgumentException("v is out of range");
		}
	}

	/**
	 * Returns the edge cost if v and w are adjacent and an edge cost has been
	 * assigned, NO_COST otherwise.
	 * 
	 * @param v
	 *            vertex
	 * @param w
	 *            vertex
	 * @return edge cost if available, NO_COST otherwise
	 * @throws IllegalArgumentException
	 *             if v or w are out of range
	 */
	@Override
	public int cost(int v, int w) throws IllegalArgumentException {
		if(0 <= v && v < edges.length && 0 <= w && w < edges.length){
			if(hasEdge(v, w)){
				return edges[v].get(w);				//returns only the cost from v to w
			}else{
				return NO_COST;
			}
		}else{
			throw new IllegalArgumentException("v or w is out of range");
		}
	}

	/**
	 * Inserts a directed edge. (No edge cost is assigned.)
	 * 
	 * @param from
	 *            vertex
	 * @param to
	 *            vertex
	 * @throws IllegalArgumentException
	 *             if from or to are out of range
	 */
	@Override
	public void add(int from, int to) throws IllegalArgumentException {
		if((0<=from && from < edges.length) || (0<=to && to < edges.length)){
			addEdge(from, to, NO_COST);
		}else{
			throw new IllegalArgumentException("from is out of range");
		}
	}

	/**
	 * Inserts an edge with edge cost c.
	 * 
	 * @param c
	 *            edge cost, c >= 0
	 * @param from
	 *            vertex
	 * @param to
	 *            vertex
	 * @throws IllegalArgumentException
	 *             if from or to are out of range
	 * @throws IllegalArgumentException
	 *             if c < 0
	 */
	@Override
	public void add(int from, int to, int c) throws IllegalArgumentException{
		if((0<=from && from < edges.length) || (0<=to && to < edges.length) || c <= 0){
			addEdge(from, to, c);
		}else{
			throw new IllegalArgumentException("from or c is out of range");
		}
	}

	/**
	 * Inserts two edges between v and w. (No edge cost is assigned.)
	 * 
	 * @param v
	 *            vertex
	 * @param w
	 *            vertex
	 * @throws IllegalArgumentException
	 *             if v or w are out of range
	 */
	@Override
	public void addBi(int v, int w) throws IllegalArgumentException{
		if((0<=v && v < edges.length) || (0<=w && w < edges.length)){
			add(v, w, NO_COST);
			add(w, v, NO_COST);
		}else{
			throw new IllegalArgumentException("from is out of range");
		}
	}

	/**
	 * Inserts edges with edge cost c between v and w.
	 * 
	 * @param c
	 *            edge cost, c >= 0
	 * @param v
	 *            vertex
	 * @param w
	 *            vertex
	 * @throws IllegalArgumentException
	 *             if v or w are out of range
	 * @throws IllegalArgumentException
	 *             if c < 0
	 */
	@Override
	public void addBi(int v, int w, int c) throws IllegalArgumentException{
		if((0<=v && v < edges.length) || (0<=w && w < edges.length) || c < 0){
			add(v, w, c);
			add(w, v, c);
		}else{
			throw new IllegalArgumentException("from or c is out of range");
		}
	}

	/**
	 * Removes the edge.
	 * 
	 * @param from
	 *            vertex
	 * @param to
	 *            vertex
	 * @throws IllegalArgumentException
	 *             if from or to are out of range
	 */
	@Override
	public void remove(int from, int to) throws IllegalArgumentException {
		if((0<=from && from < edges.length) || (0<=to && to < edges.length)){
			removeEdge(from, to);
		}else{
			throw new IllegalArgumentException("from is out of range");
		}
	}

	/**
	 * Removes the edges between v and w.
	 * 
	 * @param v
	 *            vertex
	 * @param w
	 *            vertex
	 * @throws IllegalArgumentException
	 *             if v or w are out of range
	 */
	@Override
	public void removeBi(int v, int w) throws IllegalArgumentException{
		if((0<=v && v < edges.length) || (0<=w && w < edges.length)){
			if(edges[v].get(w) != null) 
				removeEdge(v, w);
			if (edges[w].get(v) != null)
				removeEdge(w, v);
		}else{
			throw new IllegalArgumentException("v or w is out of range");
		}
	}

	/**
	 * Returns a string representation of this graph.
	 * 
	 * @return a String representation of this graph
	 * 
	 */
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		boolean done = false;
		/*
		 * Iterates through the list
		 */
		for (int i = 0; i < edges.length; i++) {
			if (edges[i] != null) {
				/*
				 * Iterates thought the map
				 */
				for (int j : edges[i].keySet()) {
					if (edges[i].containsKey(j)) {
						if (edges[i].get(j) != NO_COST){
							s.append("(" + i + "," + j + "," + edges[i].get(j) + ")");
						}else{
							s.append("(" + i + "," + j + ")");
						}
						s.append(", ");
						done = true;

					}
				}
			}
		}
		/*
		 * Trim away the extra ", "
		 */
		if (done){
			s.setLength(s.length() - 2);
		}
		return "{" + s.toString() + "}";
	}
}
