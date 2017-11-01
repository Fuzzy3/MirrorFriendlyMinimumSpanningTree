package mfmst;

import java.util.HashSet;

public class Vertex {

	HashSet<Edge> edges = new HashSet<Edge>();
	
	public Vertex() {
		
	}
	
	public void addEdge(Edge e) {
		this.edges.add(e);
	}
	
	public void removeEdge(Edge e) {
		this.edges.remove(e);
	}
	
	public HashSet<Edge> getEdges() {
		return edges;
	}
}
