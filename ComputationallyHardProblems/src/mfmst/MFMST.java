package mfmst;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class MFMST {

	Graph g = new Graph();
	
	public static void main(String[] args) {
		MFMST mfmst = new MFMST();
		mfmst.init();
		mfmst.run();
				
	}
	
	private void init() {
		try {
			BufferedReader in = new  BufferedReader(new FileReader("C:\\Users\\Lukas\\Documents\\DTU\\Computationally Hard Problems\\Project\\test_files\\test01.uwg"));	
			
			int n = Integer.parseInt(in.readLine());
			
			for (int i = 0; i < n; i++) {
				g.addVertex(new Vertex());
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
	}
	
	private void run() {
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
