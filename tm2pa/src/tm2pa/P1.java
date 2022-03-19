package tm2pa;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class P1 {
	static class Task {
		public static final String INPUT_FILE = "1.in";
		public static final String OUTPUT_FILE = "p1.out";
		public static final int NMAX = 50005;

		int n;
		int m;
		int k;
		int cautare[];
		int permutari[];

		@SuppressWarnings("unchecked")
		ArrayList<Integer> adj[] = new ArrayList[NMAX];

		private void readInput() {
			try {
				Scanner sc = new Scanner(new BufferedReader(new FileReader(INPUT_FILE)));
				n = sc.nextInt();
				m = sc.nextInt();
				k = sc.nextInt();
				
				cautare = new int[k];
				permutari = new int[n - 1];
				for (int i = 1; i <= n; i++) {
					adj[i] = new ArrayList<>();
				}
				for (int i = 0; i < k; i++) {
					cautare[i] = sc.nextInt();
				}
				for (int i = 0; i < n - 1; i++) {
					permutari[i] = sc.nextInt();
				}

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

		private void writeOutput(int result) {
			try {
				BufferedWriter bw = new BufferedWriter(new FileWriter(OUTPUT_FILE));
				StringBuilder sb = new StringBuilder();

				sb.append(result);

				bw.write(sb.toString());
				bw.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		
		
		
		void delete() {
			
			
			
		}
		
		
		
		
		

		private int getResult() {
	

			return k - 1;
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
