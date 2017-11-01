package mfmst;

import java.util.LinkedList;

public class Graph {	
	LinkedList<Edge> edges = new LinkedList<Edge>();
	LinkedList<Vertex> vertices = new LinkedList<Vertex>();
	
	public Graph() {
		
	}

	public void addEdge(Edge e) {
		edges.add(e);
		e.getSource().addEdge(e);
		e.getTarget().addEdge(e);
	}
	
	public void removeEdge(Edge e) {
		edges.remove(e);
		e.getSource().removeEdge(e);
		e.getTarget().removeEdge(e);
	}

	public void addVertex(Vertex v) {
		vertices.add(v);
	}
	
	public void removeVertex(Vertex v) {
		vertices.remove(v);
	}
	
	public LinkedList<Edge> getEdges() {
		return edges;
	}
	
	public LinkedList<Vertex> getVertices() {
		return vertices;
	}

	public LinkedList<Edge> getSpanningTree() {
		return edges;		
	}
}