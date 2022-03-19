package lab9pa;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Main {
	static class Task {
		public static final String INPUT_FILE = "6.in";
		public static final String OUTPUT_FILE = "out";
		public static final int NMAX = 50005;

		int n;
		int m;
		int source;
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
		private int V = n; // Number of vertices
		List<List<Edge>> adj1=new ArrayList<>();

		@SuppressWarnings("unchecked")
		ArrayList<Edge> adj[] = new ArrayList[NMAX];

		private void readInput() {
			try {
				Scanner sc = new Scanner(new BufferedReader(new FileReader(INPUT_FILE)));
				n = sc.nextInt();
				m = sc.nextInt();
				source = sc.nextInt();
				V = n;
				pq = new PriorityQueue<Edge>(V, new Edge()); 
				dist=new int[n+1];
				adj1.add(new ArrayList<Edge>());
				for (int i = 1; i <= n; i++) {
					adj[i] = new ArrayList<>();
					adj1.add(new ArrayList<Edge>());
				}

				for (int i = 1; i <= m; i++) {
					int x, y, w;
					x = sc.nextInt();
					y = sc.nextInt();
					w = sc.nextInt();
					adj[x].add(new Edge(y, w));
					adj1.get(x).add(new Edge(y, w));
				}
				sc.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		private void writeOutput(ArrayList<Integer> result) {
			try {
				BufferedWriter bw = new BufferedWriter(new FileWriter(OUTPUT_FILE));
				StringBuilder sb = new StringBuilder();
				for (int i = 1; i <= n; i++) {
					sb.append(result.get(i)).append(' ');
				}
				sb.append('\n');
				bw.write(sb.toString());
				bw.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		public void dijkstra(int src) {
			// this.adj1 = adj;

			for (int i = 0; i <= V; i++)
				dist[i] = -1;

			// Add source node to the priority queue
			pq.add(new Edge(src, 0));

			// Distance to the source is 0
			dist[src] = 0;
			while (!pq.isEmpty()) {

				// remove the minimum distance node
				// from the priority queue
				int u = pq.remove().node;

				// adding the node whose distance is
				// finalized
				settled.add(u);

				e_Neighbours(u);
			}
		}

		private void e_Neighbours(int u) {
			int edgeDistance = -1;
			int newDistance = -1;

			// All the neighbors of v
			for (int i = 0; i < adj[u].size(); i++) {
				Edge v = adj[u].get(i);

				// If current node hasn't already been processed
				if (!settled.contains(v.node)) {
					edgeDistance = v.cost;
					newDistance = dist[u] + edgeDistance;

					// If new distance is cheaper in cost
					if (newDistance < dist[v.node] || dist[v.node]==-1)
						dist[v.node] = newDistance;

					// Add the current node to the queue
					pq.add(new Edge(v.node, dist[v.node]));
				}
			}
		}

		private ArrayList<Integer> getResult() {
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

			// for (int i = 0; i <= n; i++)

			///d.add(0);

			dijkstra(source);
			 
			 for (int i = 0; i < dist.length; i++) {
		            d.add(dist[i]); 
		    } 
			return d;
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
