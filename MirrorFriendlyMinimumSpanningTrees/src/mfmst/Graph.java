package mfmst;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;

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
		for (int i = 0; i < t.getEdges().size(); i++) {
			mirror.addEdge(edges.get(edges.size()-1-i));
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
		
		
		
		
		while (!pq.isEmpty()) {
			Edge e = pq.poll();
			Vertex v1 = e.getSource();
			Vertex v2 = e.getTarget();
			HashSet<Vertex> setV1 = v1.getComponentSet();
			HashSet<Vertex> setV2 = v2.getComponentSet();
			if (Collections.disjoint(setV1, setV2)) {
				/* merge the sets */
				mergeSets(setV1, setV2);
				MST.addEdge(e);
			}
		}
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

	public Tree getNextMST() {
		// TODO Auto-generated method stub
		return null;
	}
}