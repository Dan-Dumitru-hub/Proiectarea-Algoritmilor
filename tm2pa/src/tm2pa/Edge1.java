package tm2pa;



public class Edge1 implements Comparable<Edge1> {
	public int node;
	public double cost;

	Edge1(int _node, double _cost) {
		node = _node;
		cost = _cost;
	}

	public int compareTo(Edge1 rhs) {
		return Double.compare(cost, rhs.cost);
	}
}