package mfmst;

import java.util.HashSet;
import java.util.LinkedList;

public class Vertex {
	int name;		// 1, 2, 3 ..
	
	HashSet<Edge> edges = new HashSet<Edge>();
	LinkedList<Vertex> neighbors = new LinkedList<Vertex>();
	HashSet<Vertex> componentSet = new HashSet<Vertex>();
	
	public Vertex(int i) {
		this.name = i;
		this.componentSet.add(this);
	}

	public void addEdge(Edge e) {
		this.edges.add(e);
		Vertex neighbor = e.getSource().equals(this) ? e.getTarget() : e.getSource();
		neighbors.add(neighbor);
	}
	
	public void removeEdge(Edge e) {
		this.edges.remove(e);
	}
	
	public HashSet<Edge> getEdges() {
		return edges;
	}

	public LinkedList<Vertex> getNeighbors() {
		return neighbors;
	}

	public void setComponentSet(HashSet<Vertex> set) {
		this.componentSet = set;
		
	}

	public HashSet<Vertex> getComponentSet() {
		return componentSet;
	}
	
	@Override
	public String toString() {
		return ""+name;
	}
}
