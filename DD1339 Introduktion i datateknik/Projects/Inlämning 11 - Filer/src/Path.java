import java.io.*;
import java.util.*;

/**
 * Programmet Path skriver ut den från FROM till TO, förutsatt att det finns en.
 * Finns det ingen väg mellan FROM och TO så skriv ett medelande ut.
 * 
 * Vägen som skrivt ut är det minimala vägen genom noder.
 * 
 * Programmet använder sig av Bredden Först Sökning (BFS) för att hitta den minska vägen.
 * 
 * @author Felix De Silva
 * @date 22 apr 2014
 */
public class Path {
	//============Instance Variables===============
	private static HashGraph graph;
	private static int numOfNodes = 0;


	public Path(){
		Scanner db = null;
		boolean gotNodes = false;

		try {
			db = new Scanner(new File("Text/Distances.txt"));
		} catch (FileNotFoundException e) {
			System.err.println("Text-filen hittades inte");
			System.exit(1);
		}

		//Iterates through txt-file and create/fill the graph
		while(db.hasNext()){
			Scanner line = new Scanner(db.nextLine());
			//Create the graph
			if(line.hasNextInt() && !gotNodes){
				numOfNodes = line.nextInt();
				gotNodes = true;
				graph = new HashGraph(numOfNodes);
			}

			//Fill the graph with edges
			if(line.hasNextInt()){
				String s = line.nextLine();
				String[] part = s.split("\\s+");
				int i = Integer.parseInt(part[0]);
				int j = Integer.parseInt(part[1]);
				int c = Integer.parseInt(part[2]);
				graph.addBi(i, j, c);
				//				System.out.println(graph.toString());

			}
			line.close();
		}
		db.close();

	}

	/**
	 * Finds the way with the minimum amount of nodes from one node to another.
	 * Uses BFS.
	 * 
	 * @param from Node to start from
	 * @param to Node to go to
	 * @return The way with minimum amount of nodes from from to to
	 */
	public static int[] bfs(int from, int to){
		if(graph == null) {
			throw new IllegalArgumentException("graph == null");
		}
		if((from > graph.numVertices()-1) || (from < 0)) {
			throw new IllegalArgumentException("node out of range: " + from);
		}
		if((to > graph.numVertices()) || (to < 0)) {
			throw new IllegalArgumentException("node out of range: " + to);
		}
		if(from == to){
			System.out.println("There is no connection between " + from + " and " + to);
		}

		HashSet<Integer> visited =  new HashSet<Integer>();
		Queue<Integer> q =new LinkedList<Integer>();

		visited.add(from);		//Adding from to visited set 
		q.add(from);			//Adding from to queue
		boolean check = false; 	//Preventing the iteration so continue

		int[] prev = new int[numOfNodes];
		Arrays.fill(prev, -1);

		while(!q.isEmpty() && !check){

			int current= (int) q.poll();

			for(VertexIterator it = graph.neighbors(current); it.hasNext();){
				int next = it.next();
				//				System.out.println(next + " " + current);
				if(!visited.contains(next)){
					prev[next] = current;
					visited.add(next);
					q.add(next);			
				}
			}
		}
		return prev;
	}

	public static void printNodes(int[] list, int from ,int to){
		StringBuilder s = new StringBuilder();
		int temp = to;
		while(temp != from){
			//The exception when list[element] = -1
			if(list[temp] == -1){
				break;
			}
			
			s.append(" >- " +list[temp]);
			temp = list[temp];

		}
		s.reverse();
		if(s.length() >= 1){
			s.append(to);
			System.out.println(s.toString());
		}
	}

	/**
	 */
	public static void main(String[] args){
		if(args.length != 2){
			throw new IllegalArgumentException("args is out of range");
		}

		new Path(); 		//Starting the constructor

		int from, to;

		//Converting FROM and TO to String
		from = Integer.parseInt(args[0]);
		to = Integer.parseInt(args[1]);

		int[] steps = bfs(from, to);
		printNodes(steps, from, to);


//		//Testing alla the possible combinations
//		for(int i = 0 ; i < numOfNodes; i++){
//			for(int j = 0; j < numOfNodes; j++){
//				if(i != j){
//					int[] steps = bfs(i, j);
//					printNodes(steps, i, j);
//				}
//			}
//		}

	}
}
