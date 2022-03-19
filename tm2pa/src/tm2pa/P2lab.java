package tm2pa;

//package tm2pa;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class P2lab {
	static class Task {
		public static final String INPUT_FILE = "16.in";
		public static final String OUTPUT_FILE = "p2.out";
		public static final int NMAX = 50005;

		int n;
		int m;
		int source;
		int dest;

		private class Edge implements Comparable<Edge> {
			public int node;
			public int cost;

			Edge(int _node, int _cost) {
				node = _node;
				cost = _cost;
			}

			public int compareTo(Edge rhs) {
				return Integer.compare(cost, rhs.cost);
			}
		}

		@SuppressWarnings("unchecked")
		ArrayList<Edge> adj[];

		private void readInput() {
			try {
				//Scanner sc = new Scanner(new BufferedReader(new FileReader(INPUT_FILE)));
				BufferedReader reader = new BufferedReader(new FileReader(INPUT_FILE));
				String thisLine = reader.readLine();;
				//thisLine = reader.readLine();
				String s1[] = thisLine.split(" ");
				
				//String s3  [] = Files.readAllLines(INPUT_FILE);
				
				n = Integer.parseInt(s1[0]);
				m = Integer.parseInt(s1[1]);
				thisLine = reader.readLine();
				s1 = thisLine.split(" ");
				source = Integer.parseInt(s1[0]);

				dest = Integer.parseInt(s1[1]);
				adj = new ArrayList[n+1];
				for (int i = 1; i <= n; i++) {
					adj[i] = new ArrayList<>();
				}
				for (int i = 1; i <= m; i++) {

					thisLine = reader.readLine();
					String s2 []= thisLine.split(" ");

					int x, y, w;
					x = Integer.parseInt(s2[0]);
					y = Integer.parseInt(s2[1]);

					w = Integer.parseInt(s2[2]);
					adj[x].add(new Edge(y, w));
				}
				//sc.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		private void writeOutput(ArrayList<Integer> result) {
			try {
				BufferedWriter bw = new BufferedWriter(new FileWriter(OUTPUT_FILE));
				StringBuilder sb = new StringBuilder();

				sb.append(result.get(dest));

				bw.write(sb.toString());
				//bw.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
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
			ArrayList<Integer> d = new ArrayList<>();
			for (int i = 0; i <= n; i++)
				d.add(NMAX);

			dijkstra(source, d);

			return d;
		}

		private void dijkstra(int source, ArrayList<Integer> dist) {
			// Initializam distantele la NMAXinit

			// Folosim un priority queue de Edge, desi elementele noastre nu sunt tocmai
			// muchii.
			// Vom folosi field-ul de cost ca si distanta pana la nodul respectiv.
			// Observati ca am modificat clasa Edge ca sa implementeze interfata Comparable.
			PriorityQueue<Edge> pq = new PriorityQueue<>();

			// Inseram nodul de plecare in pq si ii setam distanta la 0.
			dist.set(source, 0);
			pq.add(new Edge(source, 0));

			// Cat timp inca avem noduri de procesat
			while (!pq.isEmpty()) {
				// Scoatem head-ul cozii
				int u = pq.peek().node;
				int d = pq.poll().cost;

				// In cazul in care un nod e introdus in coada cu mai multe distante (pentru ca
				// distanta pana la el se imbunatateste in timp), vrem sa procesam doar
				// versiunea sa cu distanta minima. Astfel, dam discard la intrarile din coada
				// care nu au distanta optima.
				if (d > dist.get(u) || u == dest)
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
					}
					if (e.node == n)
						break;
				}

			}

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
