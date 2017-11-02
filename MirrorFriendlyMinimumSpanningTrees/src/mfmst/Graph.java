package mfmst;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Set;

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

	public void addVertex() {
		vertices.add(new Vertex(vertices.size()+1));
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
	
	public Tree getMirror(Tree t) {
		Tree mirror = new Tree();
		for (Edge e : t.getEdges()) {
			mirror.addEdge(edges.get(edges.size()-1-edges.indexOf(e)));
		}
		return mirror;
	}
	
	/**
	 * Kruskal's greedy algorithm for generating Minimum Spanning Tree (MST)
	 * O(|E|log|V|)
	 */
	public Tree getMST() {
		return this.getMST(new Partition(new HashSet<Edge>(), new HashSet<Edge>()));
	}
	
	public Tree getMST(Partition p) {
		Tree MST = new Tree();
		PriorityQueue<Edge> pq = new PriorityQueue<Edge>(edges);
		pq.removeAll(p.getExcluded());
		
		while (MST.getEdges().size() < vertices.size()-1) {
			Edge e = pq.poll();
			if (e == null) {
				MST = null;		// the partition removes at lease one edge to make tree not connected
				break;
			}
			Vertex src = e.getSource(), tar = e.getTarget();
			HashSet<Vertex> setSrc = src.getComponentSet();
			HashSet<Vertex> setTar = tar.getComponentSet();
			if (Collections.disjoint(setSrc, setTar) || p.getIncluded().contains(e)) {
				mergeSets(setSrc, setTar);
				MST.addEdge(e);
			}
		}
		if (vertices.get(0).getComponentSet().size() != vertices.size()) MST = null;	// not an MST if it doesn't cover all vertices
		for (Vertex v : vertices) {
			HashSet<Vertex> newSet = new HashSet<Vertex>();
			newSet.add(v);
			v.setComponentSet(newSet);
		}
		return MST;
	}
	
	public void mergeSets(HashSet<Vertex> setV1, HashSet<Vertex> setV2) {
		setV1.addAll(setV2);
		for (Vertex v: setV1) {
			v.setComponentSet(setV1);
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}