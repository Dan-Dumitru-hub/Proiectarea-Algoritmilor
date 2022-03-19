package tm2pa;

//package tm2pa;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// A directed graph using 
// adjacency list representation 
public class P11 {

	public static final String INPUT_FILE = "3.in";
	public static final String OUTPUT_FILE = "p1.out";
	public static final int NMAX = 50005;

	static int n;
	static int m;
	static int k;
	static int cautare[];
	static int permutari[];
	static List<List<Integer>> posibilities=new ArrayList<>();
	static int s = 0;

	// No. of vertices in graph
	private int v;

	// adjacency list
	private ArrayList<Integer>[] adjList;

	// Constructor
	public P11(int vertices) {

		// initialise vertex count
		this.v = vertices;

		// initialise adjacency list
		initAdjList();
	}

	// utility method to initialise
	// adjacency list
	@SuppressWarnings("unchecked")
	private void initAdjList() {
		adjList = new ArrayList[v];

		for (int i = 0; i < v; i++) {
			adjList[i] = new ArrayList<>();
		}
	}

	// add edge from u to v
	public void addEdge(int u, int v) {
		// Add v to u's list.
		adjList[u].add(v);
	}

	void removeNode(int u) {
		//for (int i = 0; i < adjList[u].size(); i++)
			adjList[u]=null;

		for (int i = 0; i < adjList.length; i++)
			if (adjList[i].contains(u)) {
				adjList[i].set(u, null);
			}

	}

	// Prints all paths from
	// 's' to 'd'
	public void printAllPaths(int s, int d) {
		boolean[] isVisited = new boolean[v];
		ArrayList<Integer> pathList = new ArrayList<>();

		// add source to path[]
		pathList.add(s);

		// Call recursive utility
		
		printAllPathsUtil(s, d, isVisited, pathList);
		
		
	

	}

	private void printAllPathsUtil(Integer u, Integer d, boolean[] isVisited, List<Integer> localPathList) {

		// Mark the current node
		
		
		isVisited[u] = true;

		if (u.equals(d)) {
			// System.out.println(localPathList);

			// if match found then no need to traverse more till depth
			isVisited[u] = false;
			posibilities.add(localPathList);
			return ;
		}

		// Recur for all the vertices
		// adjacent to current vertex
		for (Integer i : adjList[u]) {
			if (!isVisited[i]) {
				// store current node
				// in path[]
				localPathList.add(i);
				printAllPathsUtil(i, d, isVisited, localPathList);

				// remove current node
				// in path[]
				localPathList.remove(i);
			}
		}

		// Mark the current node
		isVisited[u] = false;
		
			
		return ;
	}
	
	
	void suma() {
		int max=0;
		ArrayList<Integer> x=new ArrayList<>();
		x.add(1);
		 
		for (int i = 0; i < cautare.length; i++) {
			posibilities=new ArrayList<>();
			printAllPaths(1, cautare[i]);
			//System.out.println(posibilities);
			if(posibilities.lastIndexOf(x)== posibilities.size()-1) {
				continue;
			}
			
			for(int k=0;k<posibilities.size();k++)
			for(int j=max;j<permutari.length;j++)
			if(posibilities.get(k).contains(permutari[j])){
				removeNode(j);
				if(max <j)
				max=j;
			}
			
			s+=max;
		
		
		}
	}

	// Driver program
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new BufferedReader(new FileReader(INPUT_FILE)));
		// P11 g1 = new P11(4);
		n = sc.nextInt();
		P11 g = new P11(n + 1);

		m = sc.nextInt();
		k = sc.nextInt();

		cautare = new int[k];
		permutari = new int[n - 1];

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

			g.addEdge(x, y);
			g.addEdge(y, x);
		}
		sc.close();
		// Create a sample graph/ System.out.println("Following are all different paths from "+s+" to "+d);
		g.suma();
		BufferedWriter bw = new BufferedWriter(new FileWriter(OUTPUT_FILE));
		StringBuilder sb = new StringBuilder();

		sb.append(s);

		bw.write(sb.toString());
		bw.close();
	}
}