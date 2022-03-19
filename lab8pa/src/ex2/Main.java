package ex2;

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
		int[] idx;
		int[] low;
		int timp = 0;
		ArrayList<Integer> sol = new ArrayList<>();

		@SuppressWarnings("unchecked")
		ArrayList<Integer> adj[] = new ArrayList[NMAX];
		private int time = 0;;

		private void readInput() {
			try {
				Scanner sc = new Scanner(new BufferedReader(new FileReader(INPUT_FILE)));
				n = sc.nextInt();
				m = sc.nextInt();

				idx = new int[n + 1];
				for (int i = 1; i <= n; i++) {
					idx[i] = -1;
					low[i] = -1;
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

		private void writeOutput(ArrayList<Integer> result) {
			try {
				PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE)));
				for (int node : result) {
					pw.printf("%d ", node);
				}
				pw.printf("\n");
				pw.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

//v parinte lui u
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

		void DFSCV(int v, int x) {
			int radacina = x;
			idx[v] = timp;
			low[v] = timp;
			timp++;
			ArrayList<Integer> copil = new ArrayList<>();
			for (int i = 0; i < adj[v].size() - 1; i++) {
				int u = adj[v].get(i);

				if (!parinte(u, v)) {
					if (idx[u] < 0) {
						copil.add(u);
						DFSCV(u, v);
						low[v] = Math.min(low[v], low[u]);
					}
					low[v] = Math.min(low[v], low[u]);

				}

			}

			if (radacina == x) {
				if (copil.size() >= 2)
					sol.add(x);

			} else {
				for (int i = 0; i < copil.size() - 1; i++)
					if (low[copil.get(i)] >= idx[v]) {
						sol.add(v);
						break;
					}
			}
		}

		void APUtil(int u, boolean visited[], int disc[], int low[], int parent[], boolean ap[]) {

			// Count of children in DFS Tree
			int children = 0;

			// Mark the current node as visited
			visited[u] = true;

			// Initialize discovery time and low value
			disc[u] = low[u] = ++time;

			// Go through all vertices aadjacent to this
			Iterator<Integer> i = adj[u].iterator();
			while (i.hasNext()) {
				int v = i.next(); // v is current adjacent of u

				// If v is not visited yet, then make it a child of u
				// in DFS tree and recur for it
				if (!visited[v]) {
					children++;
					parent[v] = u;
					APUtil(v, visited, disc, low, parent, ap);

					// Check if the subtree rooted with v has a connection to
					// one of the ancestors of u
					low[u] = Math.min(low[u], low[v]);

					// u is an articulation point in following cases

					// (1) u is root of DFS tree and has two or more chilren.
					if (parent[u] == -1 && children > 1)
						ap[u] = true;

					// (2) If u is not root and low value of one of its child
					// is more than discovery value of u.
					if (parent[u] != -1 && low[v] >= disc[u])
						ap[u] = true;
				}

				// Update low value of u for parent function calls.
				else if (v != parent[u])
					low[u] = Math.min(low[u], disc[v]);
			}
		}

		// The function to do DFS traversal. It uses recursive function APUtil()
		void AP() {
			// Mark all the vertices as not visited
			boolean visited[] = new boolean[n + 1];
			int disc[] = new int[n + 1];
			int low[] = new int[n + 1];
			int parent[] = new int[n + 1];
			boolean ap[] = new boolean[n + 1]; // To store articulation points

			// Initialize parent and visited, and ap(articulation point)
			// arrays
			for (int i = 1; i < n + 1; i++) {
				parent[i] = -1;
				visited[i] = false;
				ap[i] = false;
			}

			// Call the recursive helper function to find articulation
			// points in DFS tree rooted with vertex 'i'
			for (int i = 1; i < n + 1; i++)
				if (visited[i] == false)
					APUtil(i, visited, disc, low, parent, ap);

			// Now ap[] contains articulation points, print them
			for (int i = 1; i < n + 1; i++)
				if (ap[i] == true)
					//System.out.print(i + " ");
					sol.add(i);
		}

		private ArrayList<Integer> getResult() {
			// TODO: Gasiti nodurile critice ale grafului neorientat stocat cu liste
			// de adiacenta in adj.
			/*
			 * timp = 0; for (int i = 1; i <= n; i++) { if (idx[i] < 0) DFSCV(i, i); }
			 */
AP();
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
