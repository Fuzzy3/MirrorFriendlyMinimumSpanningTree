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
		return partition(new Partition(new HashSet<Edge>(), new HashSet<Edge>()));
	}

	public ArrayList<Partition> partition(Partition parentParition) {
		ArrayList<Partition> partitions = new ArrayList<Partition>();
		HashSet<Edge> included = new HashSet<Edge>(parentParition.getIncluded());
		HashSet<Edge> excluded = new HashSet<Edge>(parentParition.getExcluded());
		
		Edge prev = null;
		for (Edge e : edges) {
			if (included.contains(e) || excluded.contains(e)) continue;
			if (prev != null) {
				included.add(prev);
				excluded.remove(prev);
			}
			excluded.add(e);
			partitions.add(new Partition(new HashSet<Edge>(included), new HashSet<Edge>(excluded)));
			prev = e;
		}
		return partitions;
	}
}
