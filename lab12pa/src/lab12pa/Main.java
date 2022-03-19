package lab12pa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.*;

/**
 * Generic class to represent a regular pair (C++ style).
 * 
 * Use it for storing the indexes of a grid cell like this <row, col>.
 */
class Pair<T, U> {
	public T first;
	public U second;

	public Pair(T first, U second) {
		this.first = first;
		this.second = second;
	}

	@Override
	public boolean equals(Object other) {
		Pair<T, U> p = (Pair<T, U>) other;

		return this.first.equals(p.first) && this.second.equals(p.second);
	}

	@Override
	public int hashCode() {
		int result = this.first == null ? 0 : this.first.hashCode();
		result = result * 31 + (this.second == null ? 0 : this.second.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "<" + this.first + ", " + this.second + ">";
	}
}

public class Main {
	/**
	 * Generic method to print the path to a goal state.
	 *
	 * Each element in the path is a pair containing a matrix cell's row and column.
	 *
	 * @param path The path to a goal state.
	 */
	public static <T, U> void printPath(List<Pair<T, U>> path) {
		System.out.println(path.size() - 1);
		for (int i = path.size() - 1; i >= 0; --i) {
			System.out.println(path.get(i).first + " " + path.get(i).second);
		}
	}

	/**
	 * Method to implement the A* algorithm.
	 *
	 * @param M         Encoding of the grid.
	 * @param rowPacman Pacman's starting row.
	 * @param colPacman Pacman's starting column.
	 * @param rowFood   Food row.
	 * @param colFood   Fool column.
	 *
	 * @return The shortest path between Pacman and food, determined with A*. If no
	 *         such path exists, returns an empty path.
	 */
	public static List<Pair<Integer, Integer>> astar(List<List<Character>> M, int rowPacman, int colPacman, int rowFood,
			int colFood) {
		List<Pair<Integer, Integer>> path = new ArrayList<>();

		// TODO: Implement A*.
		
		Set<Pair<Integer,Integer>> explored = new HashSet<>();
		PriorityQueue<Pair<Integer,Integer>> queue = new PriorityQueue<>();
		Pair<Integer,Integer> source = new Pair(rowPacman,colPacman);
		queue.add(source);
		 //int source.g_scores = 0;
		
		 boolean found = false;
		
		 while ((!queue.isEmpty()) && (!found)) {
			 Pair<Integer,Integer> current = queue.poll();
			 
			  explored.add(current);
			  
			  if(current.first==rowFood && current.second==colFood){
                  found = true;
          }
			  
			  Pair<Integer,Integer> e = current;
			  Pair<Integer,Integer> est=  e.first--;
			  e.second--;
			  e.first++;
			  e.second++;
			  {
				  
				  Pair<Integer,Integer> child = e;
                  double cost = e.cost;
                  double temp_g_scores = current.g_scores + cost;
                  double temp_f_scores = temp_g_scores + child.h_scores;


                  /*if child node has been evaluated and 
                  the newer f_score is higher, skip*/
                  
                  if((explored.contains(child)) && 
                          (temp_f_scores >= child.f_scores)){
                          continue;
                  }

                  /*else if child node is not in queue or 
                  newer f_score is lower*/
                  
                  else if((!queue.contains(child)) || 
                          (temp_f_scores < child.f_scores)){

                          child.parent = current;
                          child.g_scores = temp_g_scores;
                          child.f_scores = temp_f_scores;

                          if(queue.contains(child)){
                                  queue.remove(child);
                          }

                          queue.add(child);

                  }

          }
 }

		return path;
	}/*
		 * 
		 * public static void AstarSearch(Node source, Node goal) {
		 * 
		 * Set<Node> explored = new HashSet<Node>();
		 * 
		 * PriorityQueue<Node> queue = new PriorityQueue<Node>(20, new
		 * Comparator<Node>() { // override compare method public int compare(Node i,
		 * Node j) { if (i.f_scores > j.f_scores) { return 1; }
		 * 
		 * else if (i.f_scores < j.f_scores) { return -1; }
		 * 
		 * else { return 0; } }
		 * 
		 * });
		 * 
		 * // cost from start source.g_scores = 0;
		 * 
		 * queue.add(source);
		 * 
		 * boolean found = false;
		 * 
		 * while ((!queue.isEmpty()) && (!found)) {
		 * 
		 * // the node in having the lowest f_score value Node current = queue.poll();
		 * 
		 * explored.add(current);
		 * 
		 * // goal found if (current.value.equals(goal.value)) { found = true; }
		 * 
		 * // check every child of current node for (Edge e : current.adjacencies) {
		 * Node child = e.target; double cost = e.cost; double temp_g_scores =
		 * current.g_scores + cost; double temp_f_scores = temp_g_scores +
		 * child.h_scores;
		 * 
		 * // // if child node has been evaluated and the newer f_score is higher, skip
		 * //
		 * 
		 * if ((explored.contains(child)) && (temp_f_scores >= child.f_scores)) {
		 * continue; }
		 * 
		 * // // else if child node is not in queue or newer f_score is lower //
		 * 
		 * else if ((!queue.contains(child)) || (temp_f_scores < child.f_scores)) {
		 * 
		 * child.parent = current; child.g_scores = temp_g_scores; child.f_scores =
		 * temp_f_scores;
		 * 
		 * if (queue.contains(child)) { queue.remove(child); }
		 * 
		 * queue.add(child);
		 * 
		 * }
		 * 
		 * }
		 * 
		 * }
		 * 
		 * }
		 */

	public static void main(String[] args) {
		List<List<Character>> M = new ArrayList<>();
		List<Pair<Integer, Integer>> path;
		int rowPacman, colPacman;
		int rowFood, colFood;
		int numRows, numCols;
		char c;

		Scanner s = new Scanner(System.in);

		rowPacman = s.nextInt();
		colPacman = s.nextInt();
		rowFood = s.nextInt();
		colFood = s.nextInt();
		numRows = s.nextInt();
		numCols = s.nextInt();

		M = new ArrayList<>();

		for (int i = 0; i < numRows; ++i) {
			List<Character> currentRow = new ArrayList<>();
			String nextRow = s.next();
			for (int j = 0; j < numCols; ++j) {
				currentRow.add(nextRow.charAt(j));
			}
			M.add(currentRow);
		}

		s.close();

		path = astar(M, rowPacman, colPacman, rowFood, colFood);
		printPath(path);
	}
}
