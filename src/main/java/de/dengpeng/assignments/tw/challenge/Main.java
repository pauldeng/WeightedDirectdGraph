package de.dengpeng.assignments.tw.challenge;

import java.util.List;

import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

public class Main {

	public static void main(String[] args) {
		SimpleDirectedWeightedGraph<String, DefaultWeightedEdge> graph = new SimpleDirectedWeightedGraph<String, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		graph.addVertex("A");
		graph.addVertex("B");
		graph.addVertex("C");
		graph.addVertex("D");
		graph.addVertex("E");

		DefaultWeightedEdge e1 = graph.addEdge("A", "B");
		graph.setEdgeWeight(e1, 5);

		DefaultWeightedEdge e2 = graph.addEdge("B", "C");
		graph.setEdgeWeight(e2, 4);

		DefaultWeightedEdge e3 = graph.addEdge("C", "D");
		graph.setEdgeWeight(e3, 8);

		DefaultWeightedEdge e4 = graph.addEdge("D", "C");
		graph.setEdgeWeight(e4, 8);

		DefaultWeightedEdge e5 = graph.addEdge("D", "E");
		graph.setEdgeWeight(e5, 6);

		DefaultWeightedEdge e6 = graph.addEdge("A", "D");
		graph.setEdgeWeight(e6, 5);

		DefaultWeightedEdge e7 = graph.addEdge("C", "E");
		graph.setEdgeWeight(e7, 2);

		DefaultWeightedEdge e8 = graph.addEdge("E", "B");
		graph.setEdgeWeight(e8, 3);

		DefaultWeightedEdge e9 = graph.addEdge("A", "E");
		graph.setEdgeWeight(e9, 7);

		System.out.println("Shortest path from A to C:");
		List<DefaultWeightedEdge> shortest_path = DijkstraShortestPath.findPathBetween(graph, "A", "C");
		System.out.println(shortest_path);

	}

}
