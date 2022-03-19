package lab7pa;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Main {
	static class Task {
		public static final String INPUT_FILE = "2.in";
		public static final String OUTPUT_FILE = "out";
		public static final int NMAX = 100005; // 10^5

		int n;
		int m;

		@SuppressWarnings("unchecked")
		ArrayList<Integer> adj[] = new ArrayList[NMAX];

		private void readInput() {
			try {
				Scanner sc = new Scanner(new BufferedReader(new FileReader(INPUT_FILE)));
				n = sc.nextInt();
				m = sc.nextInt();

				for (int i = 1; i <= n; i++)
					adj[i] = new ArrayList<>();
				for (int i = 1; i <= m; i++) {
					int x, y;
					x = sc.nextInt();
					y = sc.nextInt();
					adj[x].add(y);
				}
				sc.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		private void writeOutput(LinkedList<Integer> linkedList) {
			try {
				PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(OUTPUT_FILE)));
				for (int i = 0; i < linkedList.size(); i++) {
					pw.printf("%d ", linkedList.get(i));
				}
				pw.printf("\n");
				pw.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		LinkedList<Integer> muchie(int b) {
			LinkedList<Integer> ok = new LinkedList<>();
			for (int i = 1; i <= n; i++)
				if (adj[i].contains(b))
					ok.add(i);
			return ok;
			// return b;
		}

		LinkedList<Integer> TopSort() {
			
			// int v = n;
			LinkedList<Integer> l = new LinkedList<Integer>();// lista care va contine elementele sortate
			LinkedList<Integer> s = new LinkedList<Integer>();
			// initializare S cu nodurile care nu au in-muchii
			
			
			for (int u = 1; u <= n; u++)

				if (muchie(u).isEmpty())
					s.add(u);
			
			System.out.println(s);
			while (!s.isEmpty()) { 
			//	System.out.println(s);// cat timp mai am noduri de prelucrat
				int u = s.poll(); // se scoate un nod din multimea S
				l.add(u);
				int j=0;// adaug U la lista finala
				System.out.println(adj[u]);
				//Iterator itr =  adj[u].iterator();
				for (Iterator<Integer> iterator = adj[u].iterator(); iterator.hasNext();)
				 {int v= iterator.next();
					//for(int j)
					System.out.println(adj[u]);
					
					iterator.remove();
					
					if (muchie(v).isEmpty())
						s.add(v); // adauga v la multimea S
				} // close foreach
			} // close while

			return l; // ordinea topologica
		}

		private LinkedList<Integer> getResult() {
			// TODO: Faceti sortarea topologica a grafului stocat cu liste de
			// adiacenta in adj.
			// *******
			// ATENTIE: nodurile sunt indexate de la 1 la n.
			// *******
			return TopSort();

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
