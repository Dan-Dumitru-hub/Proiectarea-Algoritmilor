package lab11pa;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;

public class Main {
	static class Task {
		public static final String INPUT_FILE = "in";
		public static final String OUTPUT_FILE = "out";
		public static final int NMAX = 1005;

		int n;
		int m;
		private boolean[] visited;
		 private int[] parent;
		 private long[][] flow;

		@SuppressWarnings("unchecked")
		ArrayList<Integer> adj[] = new ArrayList[NMAX];
		int C[][];

		private void readInput() {
			try {
				Scanner sc = new Scanner(new BufferedReader(new FileReader(
								INPUT_FILE)));
				n = sc.nextInt();
				m = sc.nextInt();
				visited = new boolean[n+1];
				parent=new int[n+1];
				flow = new long[n+1][n+1];
				C = new int[n + 1][n + 1];
				for (int i = 1; i <= n; i++) {
					adj[i] = new ArrayList<>();
				}
				for (int i = 1; i <= m; i++) {
					int x, y, z;
					x = sc.nextInt();
					y = sc.nextInt();
					z = sc.nextInt();
					adj[x].add(y);
					adj[y].add(x);
					C[x][y] += z;
					//C[y][x]=z;
				}
				sc.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		
		public int getMaxFlow(int s, int t) {
	        while (true) {
	            final Queue<Integer> Q = new ArrayDeque<Integer>();
	            Q.add(s);

	            for (int i = 0; i < this.n; ++i)
	                visited[i] = false;
	            visited[s] = true;

	            boolean check = false;
	            int current;
	            while (!Q.isEmpty()) {
	                current = Q.peek();
	                if (current == t) {
	                    check = true;
	                    break;
	                }
	                Q.remove();
	                for (int i = 0; i < n; ++i) {
	                    if (!visited[i] && C[current][i] > flow[current][i]) {
	                        visited[i] = true;
	                        Q.add(i);
	                        parent[i] = current;
	                    }
	                }
	            }
	            if (check == false) 
	                break;

	            long temp = C[parent[t]][t] - flow[parent[t]][t];
	            for (int i = t; i != s; i = parent[i])
	                temp = Math.min(temp, (C[parent[i]][i] - flow[parent[i]][i]));

	            for (int i = t; i != s; i = parent[i]) {
	                flow[parent[i]][i] += temp;
	                flow[i][parent[i]] -= temp;
	            }
	        }

	        int result = 0;
	        for (int i = 0; i < n; ++i)
	            result += flow[s][i];
	        return result;
	    }

		private void writeOutput(int result) {
			try {
				PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(
								OUTPUT_FILE)));
				pw.printf("%d\n", result);
				pw.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		private int getResult() {
			// TODO: Calculati fluxul maxim pe graful orientat dat.
			// Sursa este nodul 1.
			// Destinatia este nodul n.
			//
			// In adj este stocat graful neorientat obtinut dupa ce se elimina orientarea
			// arcelor, iar in C sunt stocate capacitatile arcelor.
			// De exemplu, un arc (x, y) de capacitate z va fi tinut astfel:
			// C[x][y] = z, adj[x] contine y, adj[y] contine x.
			


			int flow = getMaxFlow(1,n);
			return flow;
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
