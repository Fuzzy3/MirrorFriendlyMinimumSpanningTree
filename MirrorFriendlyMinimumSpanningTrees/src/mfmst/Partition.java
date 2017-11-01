package mfmst;

import java.util.HashSet;

public class Partition {

	HashSet<Edge> included = new HashSet<Edge>();
	HashSet<Edge> excluded = new HashSet<Edge>();
	
	public Partition(HashSet<Edge> in, HashSet<Edge> ex) {
		this.included = in;
		this.excluded = ex;
	}
	
	public HashSet<Edge> getIncluded() {
		return included;
	}
	
	public HashSet<Edge> getExcluded() {
		return excluded;
	}
	
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder().append("{ ");
		for (Edge e : included) {
			builder.append(e);
			builder.append(" ");
		}
		for (Edge e : excluded) {
			builder.append("NOT").append(e);
			builder.append(" ");
		}
		
		builder.append(" }");
		return builder.toString();
	}
}
