package mfmst;

public class Oracle {
	int trueB = 0;
	
	public Oracle(int b) {
		this.trueB = b;
	}
	public boolean ask(int guessB) {
		return guessB >= trueB;		
	}
	
//	public boolean doesSolutionExist(Graph G, int B) {
//		
//		// Find spanning trees in the graph
//		G.getSpanningTree();
//		
////		if (currentTreeWeight && currentMirrorTreeweight <= B) {
////			return true;
////		}
//	}
	
	
}
