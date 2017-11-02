package mfmst;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Driver {
	
	public static void main(String[] args) {
		Driver driver = new Driver();
		
		Graph g;
		StringBuilder builder = new StringBuilder();
		ArrayList<File> testFiles = driver.getFiles();
		for (File file : testFiles) {
			g = driver.buildGraphFromFile(file.getPath());		
			
			long start = System.currentTimeMillis();
			Tree MFMST = g.findMFMST(PartitionType.RANDOM);		// 3 type: LIGHT, HEAVY, RANDOM
			long end = System.currentTimeMillis();
			
			builder.append(file.getName()).append("\n--------------\n");
			builder.append("MFMST \t\t= ").append(MFMST); 
			builder.append("\nm(MFMST)\t= ").append(g.getMirror(MFMST)); 
			builder.append("\nw(MFMST))\t= ").append(MFMST.getWeight()); 
			builder.append("\nw(m(MFMST))\t= ").append(g.getMirror(MFMST).getWeight()); 
			builder.append("\n#Edges \t\t= ").append(MFMST.getEdges().size()); 
			builder.append("\nTime(ms)\t= ").append((end-start)).append("\n\n"); 

			System.out.println(builder.toString());
			builder.setLength(0); 	// clear the builder string
		}		
	}
	
	public ArrayList<File> getFiles() {
		ArrayList<File> files = new ArrayList<>();
		for (File file : new File("MirrorFriendlyMinimumSpanningTrees/custom_test_files").listFiles()) {
			files.add(file);
		}
		return files;
	}
	
	private Graph buildGraphFromFile(String filePath) {
		Graph g = new Graph();
		try {
			BufferedReader in = new  BufferedReader(new FileReader(filePath));	
			
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
	
}
