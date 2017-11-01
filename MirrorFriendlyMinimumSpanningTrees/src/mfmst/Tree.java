package mfmst;

import java.util.ArrayList;
import java.util.HashSet;

public class Tree extends Graph implements Comparable<Tree> {

	public Tree() {
		
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder().append("{ ");
		for (Edge e : edges) {
			builder.append(e);
			if (edges.getLast() != e) 
				builder.append(", ");
		}
		builder.append(" }");
		return builder.toString();
	}
	
	public int getWeight() {
		int weight = 0;
		for (Edge e : edges) {
			weight += e.getWeight();
		}
		return weight;
	}
	

	@Override
	public int compareTo(Tree t) {
		return this.getWeight() > t.getWeight() ? 1 : this.getWeight() == t.getWeight() ? 0 : -1;
	}

	public ArrayList<Partition> partition() {
		ArrayList<Partition> list = new ArrayList<Partition>();
		HashSet<Edge> included = new HashSet<Edge>();
		HashSet<Edge> excluded = new HashSet<Edge>();
		
		Edge prev = null;
		for (Edge e : edges) {
			if (prev != null) {
				included.add(prev);
				excluded.remove(prev);
			}
			excluded.add(e);
			list.add(new Partition(new HashSet<Edge>(included), new HashSet<Edge>(excluded)));
			prev = e;
		}
		return list;
	}
}
