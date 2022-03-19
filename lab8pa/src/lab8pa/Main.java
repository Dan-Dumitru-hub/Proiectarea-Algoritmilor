package lab8pa;

import java.io.BufferedReader;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Main {
	static class Task {
		public static final String INPUT_FILE = "3.in";
		public static final String OUTPUT_FILE = "3-out";
		public static final int NMAX = 100005; // 10^5
		private int preCount;
		int n;
		int m;
		int[] low;
		Stack stack = new Stack();
		@SuppressWarnings("unchecked")
		ArrayList<Integer> adj[] = new ArrayList[NMAX];
		@SuppressWarnings("unchecked")
		ArrayList<Integer> adjt[] = new ArrayList[NMAX];
		boolean[] visited;
		int index = 0;
		int[] idx;
		int[] lowlink = new int[n + 1];
		ArrayList<ArrayList<Integer>> sol = new ArrayList<>();

		private void readInput() {
			try {
				Scanner sc = new Scanner(new BufferedReader(new FileReader(INPUT_FILE)));
				n = sc.nextInt();
				m = sc.nextInt();

				lowlink = new int[n + 1];
				idx = new int[n + 1];
				for (int i = 1; i <= n; i++) {
					idx[i] = -1;
				}
				for (int i = 1; i <= n; i++) {
					adj[i] = new ArrayList<>();
					adjt[i] = new ArrayList<>();
				}
				for (int i = 1; i <= m; i++) {
					int x, y;
					x = sc.nextInt();
					y = sc.nextInt();
					adj[x].add(y);
					adjt[y].add(x);
				}
				sc.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		private void writeOutput(ArrayList<ArrayList<Integer>> result) {
			try {
				PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE)));
				pw.printf("%d\n", result.size());
				for (ArrayList<Integer> ctc : result) {
					for (int nod : ctc) {
						pw.printf("%d ", nod);
					}
					pw.printf("\n");
				}
				pw.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}/*
			 * void DFS(int v) {
			 * 
			 * visited[v]="gri"; int n; Iterator<Integer> i =adj[v].iterator(); while
			 * (i.hasNext()) { n = i.next(); if (visited[n]=="alb") DFS(n); } stack.push(new
			 * Integer(v)); visited[v]="negru"; }
			 */
		/*
		 * tarjan(G, v) idx[v] = index lowlink[v] = index index = index + 1 push(S, v)
		 * 
		 * pentru (v, u) din E daca (idx[u] nu e definit) tarjan(G, u) lowlink[v] =
		 * min(lowlink[v], lowlink[u]) altfel daca (u e in S) lowlink[v] =
		 * min(lowlink[v], idx[u])
		 * 
		 * daca (lowlink[v] == idx[v]) // este v radacina unei CTC? print "O noua CTC: "
		 * repeat u = pop(S) print u until (u == v)
		 * 
		 */

		void tarjan(int v) {

			idx[v] = index;

			lowlink[v] = index + 1;
			index++;
			stack.push(new Integer(v));
			int n;
			Iterator<Integer> i = adj[v].iterator();
			while (i.hasNext()) {
				n = i.next();
				if (idx[n] < 0) {
					tarjan(n);
					lowlink[v] = Math.min(lowlink[v], lowlink[n]);
				} else {
					if (stack.contains(n))
						lowlink[v] = Math.min(lowlink[v], lowlink[n]);
				}

				if (lowlink[v] == idx[v]) {
					ArrayList<Integer> comp = new ArrayList<Integer>();
					int u = v;
					do {
						u = (int) stack.pop();
						comp.add(u);
					} while (u == v);
					sol.add(comp);
				}

			}

		}

		public ArrayList<ArrayList<Integer>> getSCComponents(List<Integer>[] graph) {
			// V = graph.length;
			// this.graph = graph;
			low = new int[n+1];
			visited = new boolean[n+1];
			stack = new Stack<Integer>();
			// sccComp = new ArrayList<>();

			for (int v = 1; v < n+1; v++)
				if (!visited[v])
					dfs(v);

			return sol;
		}

		public void dfs(int v) {
			try {
			low[v] = preCount++;
			visited[v] = true;
			stack.push(v);
			int min = low[v];
			for (int w : adj[v]) {
				if (!visited[w])
					dfs(w);
				if (low[w] < min)
					min = low[w];
			}
			if (min < low[v]) {
				low[v] = min;
				return;
			}
			ArrayList<Integer> component = new ArrayList<Integer>();
			int w;
			do {
				w = (int) stack.pop();
				component.add(w);
				low[w] = n;
			} while (w != v);
			sol.add(component);
		}
		catch(Exception e) {}}
		private ArrayList<ArrayList<Integer>> getResult() {
/*
			index = 0;
			for (int i = 1; i <= n; i++) {
				if (idx[i] < 0)
					tarjan(i);
			}
*/
			// TODO: Gasiti componentele tare conexe ale grafului orientat cu
			// n noduri, stocat in adj. Rezultatul se va returna sub forma
			// unui ArrayList, ale carui elemente sunt componentele tare conexe
			// detectate. Nodurile si componentele tare conexe pot fi puse in orice
			// ordine in arraylist.
			//
			// Atentie: graful transpus este stocat in adjt.

			return sol=getSCComponents(adj);
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
