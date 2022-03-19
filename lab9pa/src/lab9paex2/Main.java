package lab9paex2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
	static class Task {
		public static final String INPUT_FILE = "in";
		public static final String OUTPUT_FILE = "out";
		public static final int NMAX = 50005;

		int n;
		int m;
		int source;
		int[][] gr;
		ArrayList<Integer> d = new ArrayList<>();

		public class Edge1 {
			public int node;
			public int cost;

			Edge1(int _node, int _cost) {
				node = _node;
				cost = _cost;
			}
		}

		class Graph {

			class Edge {
				int src, dest, weight;

				Edge() {
					src = dest = weight = 0;
				}
			};

			int V, E;
			Edge edge[];

			Graph(int v, int e) {
				V = v;
				E = e;
				edge = new Edge[e];
				for (int i = 0; i < e; ++i)
					edge[i] = new Edge();
			}
		}

		@SuppressWarnings("unchecked")
		ArrayList<Edge1> adj[] = new ArrayList[NMAX];
		Edge1[] graph;

		private void readInput() {
			try {
				Scanner sc = new Scanner(new BufferedReader(new FileReader(INPUT_FILE)));
				n = sc.nextInt();
				m = sc.nextInt();

				graph = new Edge1[n + 1];
				source = sc.nextInt();
				for (int i = 1; i <= n; i++)
					adj[i] = new ArrayList<>();
				for (int i = 1; i <= m; i++) {
					int x, y, w;
					x = sc.nextInt();
					y = sc.nextInt();
					w = sc.nextInt();
					adj[x].add(new Edge1(y, w));
					// graph.edge[i].src = x;
					// graph.edge[i].dest = y;
					// graph.edge[i].weight = w;

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
				if (result.size() == 0) {
					sb.append("Ciclu negativ!\n");
				} else {
					for (int i = 1; i <= n; i++) {
						sb.append(result.get(i)).append(' ');
					}
					sb.append('\n');
				}
				bw.write(sb.toString());
				bw.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		int BellmanFord(int src) {
			int V = n, E = m;
			int dist[] = new int[V + 1];

			for (int i = 1; i < V + 1; i++)
				dist[i] = 9999;
			dist[src] = 0;

			for (int i = 1; i <= V; ++i) {
				for (int j = 0; j < adj[i].size(); ++j) {
					int u = i;
					int v = adj[i].get(j).node;
					int weight = adj[i].get(j).cost;
					if (dist[u] == -1 || dist[u] + weight < dist[v])
						dist[v] = dist[u] + weight;
				}
			}
			for (int i = 1; i <= V; ++i)
				for (int j = 0; j < adj[i].size(); ++j) {
					int u = i;
					int v = adj[i].get(j).node;
					int weight = adj[i].get(j).cost;
					if (dist[u] + weight < dist[v]) {

						return 0;
					}
				}
			d.add(0);
			for (int i = 1; i <= V; ++i)
				d.add(dist[i]);
			return 1;
		}

		private ArrayList<Integer> getResult() {
			// TODO: Gasiti distantele minime de la nodul source la celelalte noduri
			// folosind BellmanFord pe graful orientat cu n noduri, m arce stocat in
			// adj.
			// d[node] = costul minim / lungimea minima a unui drum de la source la
			// nodul node;
			// d[source] = 0;
			// d[node] = -1, daca nu se poate ajunge de la source la node.

			// Atentie:
			// O muchie este tinuta ca o pereche (nod adiacent, cost muchie):
			// adj[x].get(i).node = nodul adiacent lui x,
			// adj[x].get(i).cost = costul.

			// In cazul in care exista ciclu de cost negativ, returnati un vector gol:
			// return new ArrayList<Integer>();
			// BellmanFord(gr, n, m, 0);

			// BellmanFord(graph, 0);
			// bellman();

			BellmanFord(source);
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
