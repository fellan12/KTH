package kth.csc.inda;

/**
 * Class for MyGraph there i create a graph and inserts random edges
 * searches for the biggest component and the amount of components.
 * 
 * @author Felix De Silva
 * @date 16 apr 2014
 */

import java.util.ArrayList;

public class MyGraph {
	private static HashGraph graph;

	public MyGraph(){
	}

	/**
	 * Create a graph
	 * @param n
	 */
	public static void createGraph(int n){
		graph = new HashGraph(n);
	}

	/**
	 * Inserts random edges in the graph
	 */
	public static void insertRandomEdges(){
		int from, to, cost, which;

		while(graph.numEdges() <= graph.numVertices()){
			from = (int) (Math.random()*graph.numVertices());
			to = (int) (Math.random()*graph.numVertices());
			cost = (int) (Math.random()*graph.numVertices() +1 );
			which = (int) (Math.random()*4);

			if(!graph.hasEdge(from, to)){
				switch(which){
				case 0:
					graph.add(from, to);
					break;
				case 1:
					graph.add(from, to, cost);
					break;
				case 2:
					graph.addBi(from, to);
					break;
				case 3:
					graph.addBi(from, to, cost);
					break;
				}
			}
		}
	}

	/**
	 * Searches through the graph to find biggest component and amount of components.
	 */
	public static void dfs() {
		boolean[] visited = new boolean[graph.numVertices()];

		ArrayList<Integer> components = new ArrayList<Integer>();
		int numComp = 0;

		for(int i = 0; i < graph.numVertices(); i++) {
			int[] depth = new int[1];
			depth[0] = 0;
			if(!visited[i]){
				dfsHelp(graph, i, visited, depth);
				numComp++;
				components.add(depth[0]);
			}
		}

		int deepest = 0;
		for(int i = 0; i < components.size(); i++){
			if(components.get(i) > deepest)
				deepest = components.get(i);
		}

		System.out.println("Amount of components: " +  numComp);
		System.out.println("Largest compenent: " + deepest);
	}

	/**
	 * help method to dfs.
	 * @param g:   The graph.
	 * @param v:   Where to start searching.
	 * @param visited:  An array to keep track of where it has already been.
	 * @param depth: An array that only contains one integer. The point here is 
	 *   that it should be passed by reference, which an int isn't. 
	 *   It keeps track of how deep the current component is.
	 */
	private static void dfsHelp(Graph g, int v, boolean[] visited, int[] depth) {
		if(visited[v]){
			return;
		}
		
		visited[v] = true;
		depth[0]++;

		for (VertexIterator it = g.neighbors(v); it.hasNext();){
			dfsHelp(g, it.next(), visited, depth);
		}
	}

	/**
	 * Mains method for the class
	 * 
	 * @param args
	 */
	public static void main(String[] args){
		createGraph(1000);
		insertRandomEdges();
		System.out.println("A graph with edges at " + graph.toString() + " was created");
		dfs();
	}
}
