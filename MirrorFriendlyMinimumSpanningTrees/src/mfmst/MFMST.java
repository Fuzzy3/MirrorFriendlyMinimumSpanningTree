package mfmst;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MFMST {
	
	public static void main(String[] args) {
		MFMST mfmst = new MFMST();
		Graph g = mfmst.buildGraphFromFile("test_files/test02.uwg");
//		mfmst.run(g);
		mfmst.findMFMST(g, 4);
				
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
//		System.out.println(MST);
//		System.out.println("MST \t\t= " + MST);
//		System.out.println("w(MST) \t\t= " + MST.getWeight());
//		Tree MirrorMST = G.getMirror(MST);
//		System.out.println("M(MST) \t\t= " + MirrorMST);
//		System.out.println("w(M(MST)) \t= " +MirrorMST.getWeight());

		
		ArrayList<Partition> partitions = MST.partition();
		for (Partition p : partitions) {
			System.out.println(p);
		}
		
		
//		LinkedList<Tree> msts = new LinkedList<Tree>();
//		while (MST.getWeight() <= B) {
////			System.out.println(MST);
//			msts.add(MST);
//			if (G.getMirror(MST).getWeight() <= B) return MST;
//			else {
//				List<Tree> partitions = new ArrayList<Tree>();
//				HashSet<Edge> mustExclude = new HashSet<Edge>();
//				Edge lastEdge = MST.getEdges().getLast();
//				mustExclude.add(lastEdge);
//				MST.removeEdge(lastEdge);
//				HashSet<Edge> mustInclude = new HashSet<Edge>(MST.getEdges());
//
//				while (!mustInclude.isEmpty()) {
//					partitions.add(G.getMST(mustInclude, mustExclude));
//					mustInclude.remove(MST.getEdges().getLast());
//				}
//
//				Collections.sort(partitions);
////				System.out.println("Partitions: " +partitions);
//			}
//		}
		
		return null;
	}
	
	private void run(Graph g) {
		Oracle oracle = new Oracle(4);
		
		int B = 0, minB = 0;
		
		for (Edge e : g.getEdges()) {
			B += e.getWeight();
		}
		
		int lowerBound = 0, upperBound = B;

		B = B/2;
		while (true) {
			if (oracle.ask(B)) {
				upperBound = B;
				B -= (B - lowerBound)/2;
			} else {
				lowerBound = B;
				B += (upperBound - B)/2;
			}
			
			if (lowerBound + 1 == upperBound) {
				minB = upperBound;
				break;
			}
		}
		
		System.out.println(minB);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
