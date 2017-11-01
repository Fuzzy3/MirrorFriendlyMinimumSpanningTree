package mfmst;

public class Oracle {
	int trueB = 0;
	
	public Oracle(int b) {
		this.trueB = b;
	}
	public boolean ask(int guessB) {
		return guessB >= trueB;		
	}
	
//	public boolean doesSolutionExist(int B) {
//		
//		
//		
//		if (currentTreeWieght and currentMirrorTreeweight <= B) {
//			return true;
//		}
//	}
}
