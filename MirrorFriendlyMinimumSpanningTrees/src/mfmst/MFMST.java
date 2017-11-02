package mfmst;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class MFMST {
	
	public static void main(String[] args) {
		MFMST mfmst = new MFMST();
		Graph g = mfmst.buildGraphFromFile("MirrorFriendlyMinimumSpanningTrees/custom_test_files/TestFile09.uwg");
		Tree tree = mfmst.findMFMST(g, Integer.MAX_VALUE);

//		long start1 = System.currentTimeMillis();
//		int B = Integer.MAX_VALUE;
//		int lowerBound = 0, upperBound = B, minB = B;
//		
//		Tree MFMST = null;
//		while (true) {
//			Tree t = mfmst.findMFMST(g, B);
//			if (t != null) {
//				MFMST = t;
//				upperBound = B;
//				B -= (B - lowerBound)/2;
//			} else {
//				lowerBound = B;
//				B += (upperBound - B)/2;
//			}
//			
//			if (lowerBound + 1 == upperBound) {
//				minB = upperBound;
//				break;
//			}
//		}
//		long end1 = System.currentTimeMillis();
//
//		System.out.println("MFMST \t\t= " + MFMST);
//		System.out.println("m(MFMST)\t= " + g.getMirror(MFMST));
//		System.out.println("w(MFMST)\t= " + MFMST.getWeight());
//		System.out.println("w(m(MFMST))\t= " + g.getMirror(MFMST).getWeight());
//		System.out.println("#Edges \t\t= " + MFMST.getEdges().size());
//		System.out.println("Time(ms)\t= " + (end1-start1));

		long start = System.currentTimeMillis();
		Tree smallestBTree = mfmst.reduceB(g,tree);
		long end = System.currentTimeMillis();

		System.out.println("MFMST \t\t= " + smallestBTree);
		System.out.println("m(MFMST)\t= " + g.getMirror(smallestBTree));
		System.out.println("w(MFMST)\t= " + smallestBTree.getWeight());
		System.out.println("w(m(MFMST))\t= " + g.getMirror(smallestBTree).getWeight());
		System.out.println("#Edges \t\t= " + smallestBTree.getEdges().size());
		System.out.println("Time(ms)\t= " + (end-start));
				
	}
	
	private Tree reduceB(Graph g, Tree t) {
		int treeWeight = t.getWeight();
		int mirrorWeight = g.getMirror(t).getWeight();
		int B = treeWeight > mirrorWeight ? treeWeight : mirrorWeight;
		Tree newT = this.findMFMST(g, B-1);
		
//		System.out.println("MFMST \t\t= " + t);
//		System.out.println("m(MFMST)\t= " + g.getMirror(t));
//		System.out.println("w(MFMST)\t= " + t.getWeight());
//		System.out.println("w(m(MFMST))\t= " + g.getMirror(t).getWeight());
//		System.out.println("#Edges \t\t= " + t.getEdges().size());
		
		return newT == null ? t : reduceB(g, newT);		
	}

	private Graph buildGraphFromFile(String localFilePath) {
		Graph g = new Graph();
		try {
			BufferedReader in = new  BufferedReader(new FileReader(localFilePath));	
			
			int n = Integer.parseInt(in.readLine());
			
			for (int i = 0; i < n; i++) {
				g.addVertex();
			}
			
			int m = Integer.parseInt(in.readLine());
	
			for (int i = 0; i < m; i++) {
				StringTokenizer st = new StringTokenizer(in.readLine());
				g.addEdge(new Edge(g.getVertices().get(Integer.parseInt(st.nextToken())-1), g.getVertices().get(Integer.parseInt(st.nextToken())-1), Integer.parseInt(st.nextToken())));
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return g;		
	}
	
	
	private Tree findMFMST(Graph G, int B) {
		Tree MST = G.getMST();
		if (MST.getWeight() <= B && G.getMirror(MST).getWeight() <= B) return MST;
		
		HashMap<Tree, Partition> partitionTrees = new HashMap<>();	// spanning trees in graph with weight <= B, not counting MST
		for (Partition p : MST.partition()) {
			Tree t = G.getMST(p);
			if (t != null && t.getWeight() <= B) {	
				if (G.getMirror(t).getWeight() <= B) return t;			
				partitionTrees.put(t, p);
			}
		}
		
		PriorityQueue<Tree> pq = new PriorityQueue<>(partitionTrees.keySet());
		while (!pq.isEmpty()) {
			Tree t = pq.poll();
			Partition p = partitionTrees.get(t);
			for (Partition subP : t.partition(p)) {
				Tree subPartitionTree = G.getMST(subP);
				if (subPartitionTree != null && subPartitionTree.getWeight() <= B) {
					if (G.getMirror(subPartitionTree).getWeight() <= B) return subPartitionTree;
					partitionTrees.put(subPartitionTree, subP);
					pq.offer(subPartitionTree);
				}
			}
		}
		
		return null;
	}
}
