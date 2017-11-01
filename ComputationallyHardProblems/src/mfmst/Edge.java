package mfmst;

public class Edge {

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
	
	
	
}
