package ex3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Main {
	static class Task {
		public static final String INPUT_FILE = "in";
		public static final String OUTPUT_FILE = "out";
		public static final int NMAX = 100005; // 10^5

		int n;
		int m;
		int timp;
		int[] idx;
		int[] low;
		int time = 0;
		static final int NIL = -1;
		ArrayList<Edge> sol = new ArrayList<>();
		@SuppressWarnings("unchecked")
		ArrayList<Integer> adj[] = new ArrayList[NMAX];

		class Edge {
			int x;
			int y;
			public Edge(int u, int v) {
				// TODO Auto-generated constructor stub
				this.x = u;
				this.y = v;

			}

			
		}

		private void readInput() {
			try {
				Scanner sc = new Scanner(new BufferedReader(new FileReader(INPUT_FILE)));
				n = sc.nextInt();
				m = sc.nextInt();
				low = new int[n + 1];
				idx = new int[n + 1];
				for (int i = 1; i < n + 1; i++) {
					low[i] = 0;
					idx[i] = 0;
				}

				for (int i = 1; i <= n; i++)
					adj[i] = new ArrayList<>();
				for (int i = 1; i <= m; i++) {
					int x, y;
					x = sc.nextInt();
					y = sc.nextInt();
					adj[x].add(y);
					adj[y].add(x);
				}
				sc.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		private void writeOutput(ArrayList<Edge> result) {
			try {
				PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE)));
				pw.printf("%d\n", result.size());
				for (Edge e : result) {
					pw.printf("%d %d\n", e.x, e.y);
				}
				pw.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		boolean parinte(int v, int u) {
			if (adj[v].contains((Integer) u))

				return true;
			else
				for (Iterator<Integer> iterator = adj[v].iterator(); iterator.hasNext();) {
					int t = iterator.next();

					if (parinte(t, u))
						return true;

				}
			return false;

		}

		void DFSB(int v, int parinte) {
			idx[v] = timp;
			low[v] = timp;
			timp = timp + 1;
			for (Iterator<Integer> iterator = adj[v].iterator(); iterator.hasNext();) {

				int u = iterator.next();
				if (parinte(v, u)) {

					if (idx[u] < 0) {
						DFSB(u, v);
						low[v] = Math.min(low[v], low[u]);
						if (low[u] > low[v])
							sol.add(new Edge(u, v));
						else
							low[v] = Math.min(low[v], idx[u]);
					}

				}

			}
		}

		void bridgeUtil(int u, boolean visited[], int disc[], int low[], int parent[]) {

			// Mark the current node as visited
			visited[u] = true;

			// Initialize discovery time and low value
			disc[u] = low[u] = ++time;

			// Go through all vertices aadjacent to this
			Iterator<Integer> i = adj[u].iterator();
			while (i.hasNext()) {
				int v = i.next(); // v is current adjacent of u

				// If v is not visited yet, then make it a child
				// of u in DFS tree and recur for it.
				// If v is not visited yet, then recur for it
				if (!visited[v]) {
					parent[v] = u;
					bridgeUtil(v, visited, disc, low, parent);

					// Check if the subtree rooted with v has a
					// connection to one of the ancestors of u
					low[u] = Math.min(low[u], low[v]);

					// If the lowest vertex reachable from subtree
					// under v is below u in DFS tree, then u-v is
					// a bridge
					if (low[v] > disc[u])
						sol.add(new Edge(u,v));
						//System.out.println(u + " " + v);
				}

				// Update low value of u for parent function calls.
				else if (v != parent[u])
					low[u] = Math.min(low[u], disc[v]);
			}
		}

// DFS based function to find all bridges. It uses recursive 
// function bridgeUtil() 
		void bridge() {
			// Mark all the vertices as not visited
			boolean visited[] = new boolean[n+1];
			int disc[] = new int[n+1];
			int low[] = new int[n+1];
			int parent[] = new int[n+1];

			// Initialize parent and visited, and ap(articulation point)
			// arrays
			for (int i = 1; i <= n; i++) {
				parent[i] = NIL;
				visited[i] = false;
			}

			// Call the recursive helper function to find Bridges
			// in DFS tree rooted with vertex 'i'
			for (int i = 1; i < n+1; i++)
				if (visited[i] == false)
					bridgeUtil(i, visited, disc, low, parent);
		}

		private ArrayList<Edge> getResult() {
			// TODO: Gasiti muchiile critice ale grafului neorientat stocat cu liste
			// de adiacenta in adj.
			/*
			 * for(int i=0;i<n;i++) DFSB(i+1,i+1);
			 */
			
			return sol;
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
