package tm2pa;

//package tm2pa;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class P3 {
	static class Task {
		public static final String INPUT_FILE = "5.in";
		public static final String OUTPUT_FILE = "p3.out";
		public static final int NMAX = 50005;
		public static final double DMAX = 50005;

		int n;
		int m;
		double e;
		int parents[];
		BufferedWriter bw ;
		StringBuilder sb = new StringBuilder();

		public class Edge implements Comparable<Edge> {
			public int node;
			public double cost;

			Edge(int _node, double _cost) {
				node = _node;
				cost = _cost;
			}

			public int compareTo(Edge rhs) {
				return Double.compare(cost, rhs.cost);
			}
		}

		@SuppressWarnings("unchecked")
		ArrayList<Edge> adj[] = new ArrayList[NMAX];
		

		private void readInput() {
			try {
				Scanner sc = new Scanner(new BufferedReader(new FileReader(INPUT_FILE)));
				n = sc.nextInt();
				m = sc.nextInt();
				e=sc.nextDouble();

				for (int i = 1; i <= n; i++) {
					adj[i] = new ArrayList<>();
				}
				for (int i = 1; i <= m; i++) {
					int x, y;
					double w;
					x = sc.nextInt();
					y = sc.nextInt();

					w = sc.nextDouble();
					adj[x].add(new Edge(y, w));
				}
				sc.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		private void writeOutput(ArrayList<Double> result) {
			try {bw = new BufferedWriter(new FileWriter(OUTPUT_FILE));
				
				
			writeOutput2( parents,n);
				
				sb.append(e);
				sb.append("\n");
				writeOutput1( parents,n);

				//bw.write(sb.toString());
				//bw.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		
		private void writeOutput1(int [] parents,int current) {
		
			

				// Base case : Source node has
				// been processed
				if (current == 0) {
					return;
				}
				//E=E*(1-adjacencyMatrix[currentVertex][parents[currentVertex]]/100);
			sb.append(current + " ");
			writeOutput1(parents, parents[current]);
			
			
		}
		private void writeOutput2(int [] parents,int current) {
			
			

			// Base case : Source node has
			// been processed
			if (current == 0 || current == -1) {
				return;
			}
			e=e*(1-adj[parents[current]].get(current).cost/100);
		//sb.append(current + " ");
		writeOutput2(parents, parents[current]);
		
		
	}

		private ArrayList<Double> getResult() {
			
			ArrayList<Double> d = new ArrayList<>();
			for (int i = 0; i <= n; i++)
				d.add(0.0);

			dijkstra(1, d);

			return d;
		}

		private void dijkstra(int source, ArrayList<Double> dist) {
			// Initializam distantele la NMAXinit
			for (int i = 1; i <= n; i++)
				dist.set(i, DMAX);
			
			parents=new int[n+1];
			parents[1] = -1;

			// Folosim un priority queue de Edge, desi elementele noastre nu sunt tocmai
			// muchii.
			// Vom folosi field-ul de cost ca si distanta pana la nodul respectiv.
			// Observati ca am modificat clasa Edge ca sa implementeze interfata Comparable.
			PriorityQueue<Edge> pq = new PriorityQueue<>();

			// Inseram nodul de plecare in pq si ii setam distanta la 0.
			dist.set(source, 0.0);
			pq.add(new Edge(source, 0));

			// Cat timp inca avem noduri de procesat
			while (!pq.isEmpty()) {
				// Scoatem head-ul cozii
				int u = pq.peek().node;
				double d = pq.poll().cost;

				// In cazul in care un nod e introdus in coada cu mai multe distante (pentru ca
				// distanta pana la el se imbunatateste in timp), vrem sa procesam doar
				// versiunea sa cu distanta minima. Astfel, dam discard la intrarile din coada
				// care nu au distanta optima.
				if (d > dist.get(u) || u == n)
					continue;

				// Parcurgem toti vecinii nodului
				for (Edge e : adj[u]) {
					if (e.node == source)
						continue;
					// Daca distanta se imbunatateste, actualizam distanta pana la vecin si
					// il adaugam in coada.
					if (d + e.cost < dist.get(e.node)) {
						dist.set(e.node, d + e.cost);
						pq.add(new Edge(e.node, dist.get(e.node)));
						parents[e.node]=u;
					}
				}

			}

		}

		public void solve() throws IOException {
			readInput();
			writeOutput(getResult());
			//writeOutput1(parents,1);
			bw.write(sb.toString());
			bw.close();
		}
	}

	public static void main(String[] args) throws IOException {
		new Task().solve();
		
		
		
	}
}


