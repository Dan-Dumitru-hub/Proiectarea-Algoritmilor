package lab10pa;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.*; 
public class Main {
	 static class Task {
		public static final String INPUT_FILE = "in";
		public static final String OUTPUT_FILE = "out";
		public static final int NMAX = 200005;

		int n;
		int m;
		int[] parent;
		int[] size;
		int cost;

		public class Edge1 {
			public int node1;
			public int node2;
			public int cost;

			Edge1(int _node1, int _node2, int _cost) {
				node1 = _node1;
				node2 = _node2;
				cost = _cost;
			}
		}

		class Edge implements Comparable<Edge> {
			int src, dest, weight;

			// Comparator function used for sorting edges
			// based on their weight
			public int compareTo(Edge compareEdge) {
				return this.weight - compareEdge.weight;
			}
		};

		class subset {
			int parent, rank;
		};

		int V, E; // V-> no. of vertices & E->no.of edges
		Edge edge[];

		@SuppressWarnings("unchecked")
		ArrayList<Edge1> edges = new ArrayList<>();
/*
		Task(int v, int e) {
			V = v;
			E = e;
			edge = new Edge[E];
			for (int i = 0; i < e; ++i)
				edge[i] = new Edge();
		}
*/
		private void readInput() {
			try {
				Scanner sc = new Scanner(new BufferedReader(new FileReader(INPUT_FILE)));
				n = sc.nextInt();
				m = sc.nextInt();
				 V = n; 
			        E = m; 
			        edge = new Edge[E]; 
			        for (int i=0; i<m; ++i) 
			            edge[i] = new Edge(); 

				for (int i = 1; i <= m; i++) {
					int x, y, w;
					x = sc.nextInt();
					y = sc.nextInt();
					w = sc.nextInt();
					edges.add(new Edge1(x, y, w));

				}
				parent = new int[n + 1];
				size = new int[n + 1];
				for (int i = 1; i <= n; i++) {
					parent[i] = i;
					size[i] = 1;
				}
				sc.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		private void writeOutput(int result) {
			try {
				PrintWriter pw = new PrintWriter(new File(OUTPUT_FILE));
				pw.printf("%d\n", result);
				pw.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		private int findRoot(int node) {
			if (node == parent[node]) {
				return node;
			}
			return parent[node] = findRoot(parent[node]);
		}

		private void mergeForests(int root1, int root2) {
			if (size[root1] <= size[root2]) {
				size[root2] += size[root1];
				parent[root1] = root2;
			} else {
				size[root1] += size[root2];
				parent[root2] = root1;
			}
		}
		
		
		 int find(subset subsets[], int i) 
		    { 
		        // find root and make root as parent of i (path compression) 
		        if (subsets[i].parent != i) 
		            subsets[i].parent = find(subsets, subsets[i].parent); 
		  
		        return subsets[i].parent; 
		    }
		 void Union(subset subsets[], int x, int y) 
		    { 
		        int xroot = find(subsets, x); 
		        int yroot = find(subsets, y); 
		  
		        // Attach smaller rank tree under root of high rank tree 
		        // (Union by Rank) 
		        if (subsets[xroot].rank < subsets[yroot].rank) 
		            subsets[xroot].parent = yroot; 
		        else if (subsets[xroot].rank > subsets[yroot].rank) 
		            subsets[yroot].parent = xroot; 
		  
		        // If ranks are same, then make one as root and increment 
		        // its rank by one 
		        else
		        { 
		            subsets[yroot].parent = xroot; 
		            subsets[xroot].rank++; 
		        } 
		    } 
		 void KruskalMST() 
		    { 
		        Edge result[] = new Edge[V];  // Tnis will store the resultant MST 
		        int e = 0;  // An index variable, used for result[] 
		        int i = 0;  // An index variable, used for sorted edges 
		        for (i=0; i<V; ++i) 
		            result[i] = new Edge(); 
		  
		        // Step 1:  Sort all the edges in non-decreasing order of their 
		        // weight.  If we are not allowed to change the given graph, we 
		        // can create a copy of array of edges 
		        Arrays.sort(edge); 
		  
		        // Allocate memory for creating V ssubsets 
		        subset subsets[] = new subset[V]; 
		        for(i=0; i<V; ++i) 
		            subsets[i]=new subset(); 
		  
		        // Create V subsets with single elements 
		        for (int v = 0; v < V; ++v) 
		        { 
		            subsets[v].parent = v; 
		            subsets[v].rank = 0; 
		        } 
		  
		        i = 0;  // Index used to pick next edge 
		  
		        // Number of edges to be taken is equal to V-1 
		        while (e < V - 1) 
		        { 
		            // Step 2: Pick the smallest edge. And increment  
		            // the index for next iteration 
		            Edge next_edge = new Edge(); 
		            next_edge = edge[i++]; 
		  
		            int x = find(subsets, next_edge.src); 
		            int y = find(subsets, next_edge.dest); 
		  
		            // If including this edge does't cause cycle, 
		            // include it in result and increment the index  
		            // of result for next edge 
		            if (x != y) 
		            { 
		                result[e++] = next_edge; 
		                Union(subsets, x, y); 
		            } 
		            // Else discard the next_edge 
		        } 
		  
		        // print the contents of result[] to display 
		        // the built MST /*
		        /*
		        System.out.println("Following are the edges in " +  
		                                     "the constructed MST"); */
		        for (i = 0; i < e; ++i) 
		          cost+= result[i].weight; 
		    } 

		private int getResult() {
			/*
			 * TODO: Calculati costul minim al unui arbore de acoperire folosind algoritmul
			 * lui Kruskal.
			 */
			KruskalMST();

			return cost;
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
