package mfmst;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class Graph {	
	protected LinkedList<Edge> edges = new LinkedList<Edge>();
	protected LinkedList<Vertex> vertices = new LinkedList<Vertex>();
	protected PartitionType partitionType = PartitionType.LIGHT;
	
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
	 * Modified Kruskal's greedy algorithm for generating Minimum Spanning Tree (MST) : O(|E|log|V|)
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
		// since the MFMST needs to run this algorithm multiple times
		// we need to reset the component sets to be ready for next iteration
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
	
	private Tree reduceB(Tree t) {
		int treeWeight = t.getWeight();
		int mirrorWeight = this.getMirror(t).getWeight();
		int B = treeWeight > mirrorWeight ? treeWeight : mirrorWeight;
		
		Tree MFST = this.findMFST(B-1);
		return MFST == null ? t : reduceB(MFST);		
	}
	
	public Tree findMFMST(PartitionType type) {
		this.partitionType = type;
		return this.reduceB(this.findMFST(Integer.MAX_VALUE));
	}
	
	private Tree findMFST(int B) {
		long start = System.currentTimeMillis();
		long end = start + 30*1000L;
		Tree MST = this.getMST();
		if (MST.getWeight() <= B && this.getMirror(MST).getWeight() <= B) return MST;
		
		HashMap<Tree, Partition> partitionTrees = new HashMap<>();	// spanning trees in graph with weight <= B, not counting MST
		for (Partition p : MST.partition()) {
			Tree t = this.getMST(p);
			if (t != null && t.getWeight() <= B) {	
				if (this.getMirror(t).getWeight() <= B) return t;			
				partitionTrees.put(t, p);
			}
		}
		
		/*
		 * RANDOM PARTITIONING 
		 */
//		PriorityQueue<Tree> pq = new PriorityQueue<>(partitionTrees.keySet());
//		while (!pq.isEmpty()) {
//			List<Object> trees = Arrays.asList(pq.toArray());
//			Collections.shuffle(trees);
//			Tree t = (Tree) trees.get(0);
//			pq.remove(t);
//			Partition p = partitionTrees.get(t);
//			for (Partition subP : t.partition(p)) {
//				Tree subPartitionTree = G.getMST(subP);
//				if (subPartitionTree != null && subPartitionTree.getWeight() <= B) {
//					if (G.getMirror(subPartitionTree).getWeight() <= B) return subPartitionTree;
//					partitionTrees.put(subPartitionTree, subP);
//					pq.offer(subPartitionTree);
//				}
//			}
//		}
		
		/*
		 * LIGHTEST FIRST PARTITIONING
		 */
		PriorityQueue<Tree> pq;
		if (partitionType == PartitionType.HEAVY){
			pq = new PriorityQueue<>(Collections.reverseOrder(new Comparator<Tree>() {
		    	public int compare(Tree t, Tree s) {
		    		return t.compareTo(s);
		    	}
		    }));
			pq.addAll(partitionTrees.keySet());
		}
		else
			 pq = new PriorityQueue<>(partitionTrees.keySet());
		
		while (!pq.isEmpty()) {
			if (System.currentTimeMillis() > end) {
				System.out.println("Time limit exceeded");
				break;
			}			
			Tree t;
			if (partitionType != PartitionType.RANDOM) t = pq.poll();
			else {
				List<Object> trees = Arrays.asList(pq.toArray());
				Collections.shuffle(trees);
				t = (Tree) trees.get(0);
				pq.remove(t);
			}
			Partition p = partitionTrees.get(t);
			for (Partition subP : t.partition(p)) {
				Tree subPartitionTree = this.getMST(subP);
				if (subPartitionTree != null && subPartitionTree.getWeight() <= B) {
					if (this.getMirror(subPartitionTree).getWeight() <= B) return subPartitionTree;
					partitionTrees.put(subPartitionTree, subP);
					pq.offer(subPartitionTree);
				}
			}
		}
		
		return null;
	}
}