package lab10pap2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Main {
    static class Task {
        public static final String INPUT_FILE = "in";
        public static final String OUTPUT_FILE = "out";
        public static final int NMAX = 200005;

        int n;
        int m;
        int V;
        int cost;
        int graph[][];
        
        
        
        int weightArray[][];
        int visited[];
        int d[] ;
        int p[];
        int verticeCount, edgeCount;
        int nodeA, nodeB, weight;
        int current, total, mincost;

        public class Edge {
            public int node;
            public int cost;

            Edge(int _node, int _cost) {
                node = _node;
                cost = _cost;
            }
        }

        @SuppressWarnings("unchecked")
        ArrayList<Edge> adj[] = new ArrayList[NMAX];

        private void readInput() {
            try {
                Scanner sc = new Scanner(new BufferedReader(new FileReader(
                                INPUT_FILE)));
                n = sc.nextInt();
                V=n+1;
                m = sc.nextInt();
                graph=new int[V][m+1] ;
                verticeCount = n;
                edgeCount = m;
                weightArray = new int[n+1][n+1];
                 visited= new int [V];
                 d = new int[V];
                 p = new int[V];
                 for (int i = 1; i <= verticeCount; i++) {
                     for(int j = 1; j <= verticeCount; j++) {
                         weightArray[i][j] = 0;
                     }
                 }
                 for (int i = 1; i <= verticeCount; i++) {
                     p[i] = visited[i] = 0;
                     d[i] = 32767;
                  }
                

                for (int i = 1; i <= n; i++)
                    adj[i] = new ArrayList<>();
                for (int i = 1; i <= m; i++) {
                    int x, y, w;
                    x = sc.nextInt();
                    y = sc.nextInt();
                    w = sc.nextInt();
                    adj[x].add(new Edge(y, w));
                    adj[y].add(new Edge(x, w));
                    graph[x][y]=w;
                    graph[y][x]=w;
                    
                    nodeA=x;
                    nodeB=y;
                    weight=w;
                    weightArray[nodeA][nodeB] = weightArray[nodeB][nodeA] = weight;
                    
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
        }/*
        int minKey(int key[], Boolean mstSet[]) 
        { 
            // Initialize min value 
            int min = Integer.MAX_VALUE, min_index = -1; 
      
            for (int v = 0; v < V; v++) 
                if (mstSet[v] == false && key[v] < min) { 
                    min = key[v]; 
                    min_index = v; 
                } 
      
            return min_index; 
        } 
      
        // A utility function to print the constructed MST stored in 
        // parent[] 
        void printMST(int parent[], int graph[][]) 
        { 
            //System.out.println("Edge \tWeight"); 
            for (int i = 1; i < V; i++) 
                cost +=graph[i][parent[i]]; 
        } 
      
        // Function to construct and print MST for a graph represented 
        // using adjacency matrix representation 
        void primMST(int graph[][]) 
        { 
            // Array to store constructed MST 
            int parent[] = new int[V]; 
      
            // Key values used to pick minimum weight edge in cut 
            int key[] = new int[V]; 
      
            // To represent set of vertices not yet included in MST 
            Boolean mstSet[] = new Boolean[V]; 
      
            // Initialize all keys as INFINITE 
            for (int i = 0; i < V; i++) { 
                key[i] = Integer.MAX_VALUE; 
                mstSet[i] = false; 
            } 
      
            // Always include first 1st vertex in MST. 
            key[0] = 0; // Make key 0 so that this vertex is 
            // picked as first vertex 
            parent[0] = -1; // First node is always root of MST 
      
            // The MST will have V vertices 
            for (int count = 0; count < V - 1; count++) { 
                // Pick thd minimum key vertex from the set of vertices 
                // not yet included in MST 
                int u = minKey(key, mstSet); 
      
                // Add the picked vertex to the MST Set 
                mstSet[u] = true; 
      
                // Update key value and parent index of the adjacent 
                // vertices of the picked vertex. Consider only those 
                // vertices which are not yet included in MST 
                for (int v = 0; v < V; v++) 
      
                    // graph[u][v] is non zero only for adjacent vertices of m 
                    // mstSet[v] is false for vertices not yet included in MST 
                    // Update the key only if graph[u][v] is smaller than key[v] 
                    if (graph[u][v] != 0 && mstSet[v] == false && graph[u][v] < key[v]) { 
                        parent[v] = u; 
                        key[v] = graph[u][v]; 
                    } 
            } 
      
            // print the constructed MST 
            printMST(parent, graph); 
        } 
        
        
        
*/
        void func() {
        	current = 1;
            d[current] = 0;
            total = 1;
            visited[current] = 1;
            while( total != verticeCount) {
                for (int i = 1; i <= verticeCount; i++) {
                    if ( weightArray[current][i] != 0) {
                        if( visited[i] == 0) { 
                            if (d[i] > weightArray[current][i]) {
                                d[i] = weightArray[current][i];
                                p[i] = current;
                            }
                        }
                    }
                }
                mincost=32767;
                for (int i = 1 ; i <= verticeCount; i++) {
                    if (visited[i] == 0) {
                        if (d[i] < mincost) {
                            mincost = d[i];
                            current = i;
                        }
                    }
                }
                visited[current]=1;
                total++;
            }

            mincost=0;
            for(int i=1;i<=verticeCount;i++)
            mincost=mincost+d[i];

            
            cost=mincost;

           
        	
        }
        
        
        
        private int getResult() {
            /*
            TODO: Calculati costul minim al unui arbore de acoperire
            folosind algoritmul lui Prim.
            */
        	//primMST(graph);
        	func();
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
