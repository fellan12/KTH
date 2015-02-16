


/**
 * A graph with a fixed number of vertices. The vertices are numbered from 0 to
 * n-1, were n is the number of vertices in the graph. Edges may be added or
 * removed from the graph. An edge may have an optional non-negative cost.
 * 
 * @author Stefan Nilsson
 * @version 2013-01-01
 */

public interface Graph {
	/**
	 * An edge with no cost has this value.
	 */
	int NO_COST = -1;

	/**
	 * Returns the number of vertices in this graph.
	 * 
	 * @return the number of vertices in this graph
	 */
	int numVertices();

	/**
	 * Returns the number of edges in this graph.
	 * 
	 * @return the number of edges in this graph
	 */
	int numEdges();

	/**
	 * Returns the degree of vertex v.
	 * 
	 * @param v
	 *            vertex
	 * @return the degree of vertex v
	 * @throws IllegalArgumentException
	 *             if v is out of range
	 */
	int degree(int v) throws IllegalArgumentException;

	/**
	 * Returns an iterator of vertices adjacent to v.
	 * 
	 * @param v
	 *            vertex
	 * @return an iterator of vertices adjacent to v
	 * @throws IllegalArgumentException
	 *             if v is out of range
	 */
	VertexIterator neighbors(int v) throws IllegalArgumentException;

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
	boolean hasEdge(int v, int w) throws IllegalArgumentException;

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
	int cost(int v, int w) throws IllegalArgumentException;

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
	void add(int from, int to) throws IllegalArgumentException;

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
	void add(int from, int to, int c) throws IllegalArgumentException;

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
	void addBi(int v, int w) throws IllegalArgumentException;

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
	void addBi(int v, int w, int c) throws IllegalArgumentException;

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
	void remove(int from, int to) throws IllegalArgumentException;

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
	void removeBi(int v, int w) throws IllegalArgumentException;
}
