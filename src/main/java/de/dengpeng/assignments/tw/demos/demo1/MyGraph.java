package de.dengpeng.assignments.tw.demos.demo1;

import org.jgrapht.alg.KruskalMinimumSpanningTree;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

public class MyGraph {
	private final SimpleDirectedWeightedGraph<String, DefaultWeightedEdge> g = new SimpleDirectedWeightedGraph<String, DefaultWeightedEdge>(
			DefaultWeightedEdge.class);
	static final double DEFAULT_EDGE_WEIGHT = 19;
	// DefaultWeightedEdge > (DefaultWeightedEdge.class);
	private DefaultWeightedEdge e1;

	public void addVertex(String name) {
		g.addVertex(name);
		// graph.addVertex(name);
	}

	public void addEdge(String v1, String v2) {
//		g.addEdge(v1, v2);
//		e1 = g.addEdge(v1, v2);
	    e1 =g.addEdge(v1, v2);
	    System.out.println("Edge added: " + e1.toString());
	}

	public void setEdgeWeight(String EDGE_WEIGHT) {
		g.setEdgeWeight(e1, Double.valueOf(EDGE_WEIGHT));
	}

	public SimpleDirectedWeightedGraph<String, DefaultWeightedEdge> getGraph() {
		return g;
	}

	/*
	 * public SimpleWeightedGraph<String,DefaultWeightedEdge> getGraph() {
	 * return graph; }
	 */

	public void getSpanningTree() {
		KruskalMinimumSpanningTree k = new KruskalMinimumSpanningTree(g);
		System.out.println(k.getEdgeSet().toString());
		// KruskalMinimumSpanningTree k1=new KruskalMinimumSpanningTree(graph);
		// System.out.println(k1.getEdgeSet().toString());
	}

	public void getSpanningTreeCost() {
		KruskalMinimumSpanningTree k = new KruskalMinimumSpanningTree(g);
		System.out.println(k.getSpanningTreeCost());
	}
}
