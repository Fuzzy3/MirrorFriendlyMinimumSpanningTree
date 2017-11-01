package mfmst;

public class Edge implements Comparable<Edge> {

	Vertex source, target;
	int weight;
	
	public Edge(Vertex source, Vertex target, int weight) {
		this.source = source;
		this.target = target;
		this.weight = weight;
	}
	
	public Vertex getSource() {
		return source;
	}
	
	public Vertex getTarget() {
		return target;
	}
	
	public int getWeight() {
		return weight;
	}

	@Override
	public int compareTo(Edge e) {
		return this.weight > e.getWeight() ? 1 : this.weight == e.getWeight() ? 0 : -1;
	}
	
	@Override
	public String toString() {
		return "{" + source + "," + target + "}";
	}
	
	
	
}
