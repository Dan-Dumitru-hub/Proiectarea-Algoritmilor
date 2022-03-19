package tm2pa;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

class P31 {

	static int N;
	static int M;
	static double E;
	static double[][] matrice;
	static BufferedWriter bw;
	static StringBuilder sb = new StringBuilder();
	static StringBuilder sb1 = new StringBuilder();

	private static void dijkstra(double[][] matrice, int sursa) throws IOException {
		int nVertices = N + 1;

		double[] distante = new double[nVertices];

		boolean[] visited = new boolean[nVertices];

		for (int i = 0; i < nVertices; i++) {
			distante[i] = Integer.MAX_VALUE;
			visited[i] = false;
		}

		distante[sursa] = 0;

		int[] parents = new int[nVertices];

		parents[sursa] = -1;

		for (int j = 1; j < nVertices; j++) {

			int nod = 0;
			double dist = Integer.MAX_VALUE;
			for (int i = 0; i < nVertices; i++) {
				if (!visited[i] && distante[i] < dist) {
					nod = i;
					dist = distante[i];
				}
			}

			visited[nod] = true;

			for (int i = 0; i < nVertices; i++) {
				double val = matrice[nod][i];

				if (val > 0 && ((dist + val) < distante[i])) {
					parents[i] = nod;
					distante[i] = dist + val;
				}
			}
		}

		printSolution(sursa, distante, parents);
	}
	
	 

	private static void printSolution(int sursa, double[] distances, int[] parents) throws IOException {
		int nVertices = distances.length;

		getenergie(N, parents);
		sb.append(E);
		sb.append("\n");

		bw.write(sb.toString());

		getmuchii(N, parents);
		//sb1.reverse();
		//String a;
		//a=reverses(sb1.toString());
		

		bw.write(sb1.toString());
		bw.close();

	}

	private static void getenergie(int sursa, int[] parents) {

		if (parents[sursa] == -1) {
			return;
		}
		E = E * (1 - matrice[parents[sursa]][sursa] / 100);

		getenergie(parents[sursa], parents);
	}

	private static void getmuchii(int sursa, int[] parents) {

		if (sursa == -1) {
			return;
		}
		
		sb1.insert(0, sursa + " ");

		getmuchii(parents[sursa], parents);
	}

	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(new BufferedReader(new FileReader("8.in")));
		N = sc.nextInt();
		M = sc.nextInt();
		E = sc.nextDouble();
		bw = new BufferedWriter(new FileWriter("p3.out"));
		matrice = new double[N + 1][N + 1];
		for (int i = 1; i <= M; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			double w = sc.nextDouble();
			matrice[x][y] = w;
		}
		sc.close();

		dijkstra(matrice, 1);

	}
}
