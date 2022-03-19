package tm2pa;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
import java.util.*;
/*
class MyScanner {
	BufferedReader br;
	StringTokenizer st;

	public MyScanner() throws FileNotFoundException {
		br = new BufferedReader(new FileReader("p2.in"));
	}

	String next() {
		while (st == null || !st.hasMoreElements()) {
			try {
				st = new StringTokenizer(br.readLine());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return st.nextToken();
	}

	int nextInt() {
		return Integer.parseInt(next());
	}

	long nextLong() {
		return Long.parseLong(next());
	}

	double nextDouble() {
		return Double.parseDouble(next());
	}

	String nextLine() {
		String str = "";
		try {
			str = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str;
	}
}


*/
import java.util.*;

//Data structure to store graph edges
private class Edge
{
	int source, dest, weight;

	public Edge(int source, int dest, int weight) {
		this.source = source;
		this.dest = dest;
		this.weight = weight;
	}
};

//Data structure to store heap nodes
class Node {
	int vertex, weight;

	public Node(int vertex, int weight) {
		this.vertex = vertex;
		this.weight = weight;
	}
};

//class to represent a graph object
class Graph
{
	// A List of Lists to represent an adjacency list
	List<List<Edge>> adjList = null;

	// Constructor
	Graph(List<Edge> edges, int N)
	{
		adjList = new ArrayList<>(N);

		for (int i = 0; i < N; i++) {
			adjList.add(i, new ArrayList<>());
		}

		// add edges to the undirected graph
		for (Edge edge: edges) {
			adjList.get(edge.source).add(edge);
		}
	}
}

class Main
{
	private static void getRoute(int prev[], int i, List<Integer> route)
	{
		if (i >= 0) {
			getRoute(prev, prev[i], route);
			route.add(i);
		}
	}

	// Run Dijkstra's algorithm on given graph
	public static void shortestPath(Graph graph, int source, int N)
	{
		// create min heap and push source node having distance 0
		PriorityQueue<Node> minHeap;
		minHeap = new PriorityQueue<>(Comparator.comparingInt(node -> node.weight));
		minHeap.add(new Node(source, 0));

		// set infinite distance from source to v initially
		List<Integer> dist = new ArrayList<>(Collections.nCopies(N, Integer.MAX_VALUE));

		// distance from source to itself is zero
		dist.set(source, 0);

		// boolean array to track vertices for which minimum
		// cost is already found
		boolean[] done = new boolean[N];
		done[source] = true;

		// stores predecessor of a vertex (to print path)
		int prev[] = new int[N];
		prev[source] = -1;

		List<Integer> route = new ArrayList<>();

		// run till minHeap is not empty
		while (!minHeap.isEmpty())
		{
			// Remove and return best vertex
			Node node = minHeap.poll();

			// get vertex number
			int u = node.vertex;

			// do for each neighbor v of u
			for (Edge edge: graph.adjList.get(u))
			{
				int v = edge.dest;
				int weight = edge.weight;

				// Relaxation step
				if (!done[v] && (dist.get(u) + weight) < dist.get(v))
				{
					dist.set(v, dist.get(u) + weight);
					prev[v] = u;
					minHeap.add(new Node(v, dist.get(v)));
				}
			}

			// marked vertex u as done so it will not get picked up again
			done[u] = true;
		}

		for (int i = 1; i < N; ++i)
		{
			if (i != source && dist.get(i) != Integer.MAX_VALUE) {
				getRoute(prev, i, route);
				System.out.printf("Path (%d -> %d): Minimum Cost = %d and Route is %s\n",
								source, i, dist.get(i), route);
				route.clear();
			}
		}
	}

	public static void main(String[] args)
	{
		// initialize edges as per above diagram
		// (u, v, w) triplet represent undirected edge from
		// vertex u to vertex v having weight w
		List<Edge> edges = Arrays.asList(
				new Edge(0, 1, 10), new Edge(0, 4, 3),
				new Edge(1, 2, 2), new Edge(1, 4, 4),
				new Edge(2, 3, 9), new Edge(3, 2, 7),
				new Edge(4, 1, 1), new Edge(4, 2, 8),
				new Edge(4, 3, 2)
		);

		// Set number of vertices in the graph
		final int N = 5;

		// construct graph
		Graph graph = new Graph(edges, N);

		int source = 0;
		shortestPath(graph, source, N);
	}
}





public class P2 {
	static class Task {
		public static final String INPUT_FILE = "2.in";
		public static final String OUTPUT_FILE = "p2.out";
		public static final int NMAX = 50005;

		int n;
		int m;
		int source;
		int dest;
		ArrayList<Integer> d = new ArrayList<>();

		public class Edge implements Comparator<Edge>{
			public int node;
			public int cost;

			public Edge (){}
			public Edge(int _node, int _cost) {
				node = _node;
				cost = _cost;
			}
			@Override
			 public int compare(Edge node1, Edge node2) 
			    { 
			        if (node1.cost < node2.cost) 
			            return -1; 
			        if (node1.cost > node2.cost) 
			            return 1; 
			        return 0; 
			    } 
		}

		private int dist[];
		private Set<Integer> settled=new HashSet<Integer>();
		private PriorityQueue<Edge> pq;
		
		

		@SuppressWarnings("unchecked")
		ArrayList<Edge> adj[] = new ArrayList[NMAX];

		private void readInput() {
			try {
				Scanner sc = new Scanner(new BufferedReader(new FileReader(INPUT_FILE)));
				n = sc.nextInt();
				m = sc.nextInt();
				source = sc.nextInt();
				dest=sc.nextInt();
				
				pq = new PriorityQueue<Edge>(n, new Edge()); 
				dist=new int[n+1];
				
				for (int i = 1; i <= n; i++) {
					adj[i] = new ArrayList<>();
					
				}

				for (int i = 1; i <= m; i++) {
					int x, y, w;
					x = sc.nextInt();
					y = sc.nextInt();
					w = sc.nextInt();
					adj[x].add(new Edge(y, w));
					
				}
				sc.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		private void writeOutput(int result) {
			try {
				BufferedWriter bw = new BufferedWriter(new FileWriter(OUTPUT_FILE));
				StringBuilder sb = new StringBuilder();
				
					sb.append(result);
				
				//sb.append('\n');
				bw.write(sb.toString());
				bw.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

	
		

		

		private int getResult() {
			// TODO: Gasiti distantele minime de la nodul source la celelalte noduri
			// folosind Dijkstra pe graful orientat cu n noduri, m arce stocat in adj.
			// d[node] = costul minim / lungimea minima a unui drum de la source la
			// nodul node;
			// d[source] = 0;
			// d[node] = -1, daca nu se poate ajunge de la source la node.
			// Atentie:
			// O muchie este tinuta ca o pereche (nod adiacent, cost muchie):
			// adj[x].get(i).node = nodul adiacent lui x,
			// adj[x].get(i).cost = costul.

			
	
			 
			 for (int i = 0; i < dist.length; i++) {
		            d.add(dist[i]); 
		    } 
			return d.get(dest);
		}

	public void solve() {
		readInput();
		 writeOutput(getResult());
	}

	}

	public static void main(String[] args) {
		new Task().solve();
	}
}
/*
 * import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class P2 {
    static class Task {
        public static final String INPUT_FILE = "p2.in";
        public static final String OUTPUT_FILE = "p2.out";
        public static final int NMAX = 50005;
        public static final int INF = (int) 1e9;

        int n;
        int m;
        int source;
        int dest;

        public class Edge {
            public int node;
            public int cost;

            Edge(int _node, int _cost) {
                node = _node;
                cost = _cost;
            }
        }

        @SuppressWarnings("unchecked")
        ArrayList<Edge> adj[] = new ArrayList[NMAX];

        private void readInput() {
            try {
                Scanner sc = new Scanner(new BufferedReader(new FileReader(
                                INPUT_FILE)));
                n = sc.nextInt();
                m = sc.nextInt();
                source = sc.nextInt();
                dest=sc.nextInt();

                for (int i = 1; i <= n; i++)
                    adj[i] = new ArrayList<>();
                for (int i = 1; i <= m; i++) {
                    int x, y, w;
                    x = sc.nextInt();
                    y = sc.nextInt();
                    w = sc.nextInt();
                    adj[x].add(new Edge(y, w));
                }
                sc.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        private void writeOutput(ArrayList<Integer> result) {
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter(
                                OUTPUT_FILE));
                StringBuilder sb = new StringBuilder();
                if (result.size() == 0) {
                    sb.append("Ciclu negativ!\n");
                } else {
                    
                        sb.append(result.get(dest));
                    
                    
                }
                bw.write(sb.toString());
                bw.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        private ArrayList<Integer> getResult() {
            // TODO: Gasiti distantele minime de la nodul source la celelalte noduri
            // folosind BellmanFord pe graful orientat cu n noduri, m arce stocat in
            // adj.
            //  d[node] = costul minim / lungimea minima a unui drum de la source la
            //  nodul node;
            //  d[source] = 0;
            //  d[node] = -1, daca nu se poate ajunge de la source la node.

            // Atentie:
            // O muchie este tinuta ca o pereche (nod adiacent, cost muchie):
            //  adj[x].get(i).node = nodul adiacent lui x,
            //  adj[x].get(i).cost = costul.

            // In cazul in care exista ciclu de cost negativ, returnati un vector gol:
            //  return new ArrayList<Integer>();
            ArrayList<Integer> d = new ArrayList<>();
            for (int i = 0; i <= n; i++)
                d.add(0);

            bellman(source, d);
                

            return d;
        }

        void  bellman(int source, ArrayList<Integer> d) {
            // Initializam distantele catre toate nodurile cu infinit
            for (int i = 1; i <= n; i++)
                d.set(i, INF);

            // Setez distanta pana la sursa la 0
            d.set(source, 0);

            // Relaxez toate muchiile de n - 1 ori
            for (int i = 1; i < n; i++) {
                relax_edges(d);
            }

            // Daca distanta inca mai poate fi imbunatatita, inseamna ca avem un
            // ciclu negativ.
            

            // Toate nodurile ce au distanta INF nu pot fi atinse din sursa, asa ca setam distantele
            // pe -1.
            
        }

        private boolean relax_edges(ArrayList<Integer> d) {
            boolean dist_updated = false;

            for (int u = 1; u <= n; u++) {
                for (Edge e : adj[u]) {
                    int v = e.node, cost = e.cost;
                    // (u, v, cost) este o muchie


                    if (d.get(u) + cost < d.get(v)) {
                        d.set(v, d.get(u) + cost);
                        dist_updated = true;
                    }
                }
            }

            return dist_updated;
        }

        public void solve() {
            readInput();
            writeOutput(getResult());
        }
    }

    public static void main(String[] args) {
        new Task().solve();
    }
}

 */

