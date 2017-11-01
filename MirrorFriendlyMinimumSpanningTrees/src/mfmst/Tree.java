package mfmst;

import java.util.LinkedList;

public class Tree extends Graph {

	public Tree() {
		
	}
	
	public LinkedList<Edge> getMirror() {
		Tree mirror = new Tree();
		for (int i = 0; i < edges.size(); i++) {
			mirror.addEdge(edges.get(edges.size()-1-i));
		}
		return mirror.edges;
	}	
}
