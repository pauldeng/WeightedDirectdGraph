package de.dengpeng.assignments.tw.challenge;


import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


import org.jgrapht.alg.DijkstraShortestPath;
import org.jgrapht.ext.ComponentAttributeProvider;
import org.jgrapht.ext.DOTExporter;
import org.jgrapht.ext.IntegerNameProvider;
import org.jgrapht.ext.StringNameProvider;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import com.google.common.primitives.Ints;

public class TWGraph {
	
	private final SimpleDirectedWeightedGraph<String, DefaultWeightedEdge> graph;

	public TWGraph(String[] graphDef) {
		graph = new SimpleDirectedWeightedGraph<String, DefaultWeightedEdge>(DefaultWeightedEdge.class);
		
		HashMap<String, Boolean> verticiesMap = new HashMap<String, Boolean>();
		
		for(String edgeDef : graphDef){
			if(!verticiesMap.containsKey(edgeDef.charAt(0))){
				graph.addVertex(String.valueOf(edgeDef.charAt(0)));
			}
			if(!verticiesMap.containsKey(edgeDef.charAt(1))){
				graph.addVertex(String.valueOf(edgeDef.charAt(1)));
			}
			
			DefaultWeightedEdge edge = graph.addEdge(String.valueOf(edgeDef.charAt(0)), String.valueOf(edgeDef.charAt(1)));
			graph.setEdgeWeight(edge, Integer.valueOf(String.valueOf(edgeDef.charAt(2))));
			
		}
	}

	public int distanceOf(String route) throws Exception {
		int distance = 0;
		
		if(validate(route) == true){
			for(int i=0; i<route.length()-1;i++){
				
				DefaultWeightedEdge edge = graph.getEdge(String.valueOf(route.charAt(i)), String.valueOf(route.charAt(i+1)));
				if(edge == null){
//					System.out.println("NO SUCH ROUTE");
					throw new Exception("NO SUCH ROUTE");
//					break;
				}
				
				distance += graph.getEdgeWeight(edge);
				
			}
		}else{
			System.out.println("NO SUCH ROUTE");
		}
		
		return distance;
	}

	public int numbOfTripsWithStops(String fromVertex, String toVertex, String relationalOperator, int numOfStops) {
		int numOfTrips = 0;
		
		if(validate(fromVertex) && validate(toVertex) && fromVertex.length() == 1 && toVertex.length() == 1){
			
			numOfTrips = numbOfTripsWithStopsRecursively(fromVertex, toVertex, relationalOperator, numOfStops);
		} else {
			System.out.println("NO SUCH ROUTE");
		}
		
		return numOfTrips;
	}
	
	private int numbOfTripsWithStopsRecursively(String fromVertex, String toVertex, String relationalOperator, int numOfStops) {
		int numOfTrips = 0;
		
		numOfStops--;
		if (numOfStops < 0) {
			return numOfTrips;
		}
		
		Set<DefaultWeightedEdge> outgoingEdgesSet = graph.outgoingEdgesOf(fromVertex);
		
		for(DefaultWeightedEdge edge: outgoingEdgesSet){
//			System.out.println("Stop " + String.valueOf(numOfStops) + ": " + graph.getEdgeTarget(edge));
			
			if(toVertex.equals(graph.getEdgeTarget(edge))){
				
				switch (relationalOperator) {
				case "<":
					// System.out.println("Less than");
					if (numOfStops != 0) {
						numOfTrips++;
					}
					break;
				case "<=":
					// System.out.println("Less equal");
					numOfTrips++;
					break;
				case "==":
//					 System.out.println("equal");
					if (numOfStops == 0) {
						numOfTrips++;
					}
					break;
				default:
					System.out.println("Fault");
				}
			}
			
			numOfTrips += numbOfTripsWithStopsRecursively(graph.getEdgeTarget(edge), toVertex, relationalOperator, numOfStops);
		}

		return numOfTrips;
	}

	public int shortestBetween(String fromVertex, String toVertex) {
		int distance = 0;

		if (fromVertex.equals(toVertex)) {
			Set<DefaultWeightedEdge> outgoingEdgesSet = graph.outgoingEdgesOf(fromVertex);

			DefaultWeightedEdge[] outgoingEdges = outgoingEdgesSet.toArray(new DefaultWeightedEdge[outgoingEdgesSet.size()]);

			int[] edgesDistance = new int[outgoingEdges.length];

			for (int i = 0; i < outgoingEdges.length; i++) {
				edgesDistance[i] = (int) (shortestBetweenDifferentVertices(graph.getEdgeTarget(outgoingEdges[i]), toVertex) + graph.getEdgeWeight(outgoingEdges[i]));
			}
			
			distance = Ints.min(edgesDistance);		
		} else {
			distance = shortestBetweenDifferentVertices(fromVertex, toVertex);
		}

		return distance;
	}

	private int shortestBetweenDifferentVertices(String fromVertex, String toVertex) {
		int distance = 0;

		List<DefaultWeightedEdge> shortestPath = DijkstraShortestPath.findPathBetween(graph, fromVertex, toVertex);

		for (DefaultWeightedEdge edge : shortestPath) {
			distance += graph.getEdgeWeight(edge);
		}

		return distance;
	}

	public int numOfTripsWithDistance(String fromVertex, String toVertex, String relationalOperator, int distanceValue) {
		int numOfTrips = 0;
		
		if(validate(fromVertex) && validate(toVertex) && fromVertex.length() == 1 && toVertex.length() == 1){
//			numOfTrips = numOfTripsWithDistanceRecursively(fromVertex, toVertex, relationalOperator, distanceValue, "", 15);
			
			List<String> fiteredList = new ArrayList<String>();
			numOfTripsWithDistanceRecursively(fromVertex, toVertex, relationalOperator, distanceValue, fromVertex, 15, 0, fiteredList);
			
			numOfTrips = fiteredList.size();
		} else {
			System.out.println("NO SUCH ROUTE");
		}
		
		return numOfTrips;
	}

	private void numOfTripsWithDistanceRecursively(String fromVertex, String toVertex, String relationalOperator, int distanceValue, String previousePath, int maxStops, int distance, List<String> fiteredList) {
		
		if((distance < distanceValue) && (previousePath.lastIndexOf(toVertex) == previousePath.length()-1)){
			
			if(previousePath.length() > 1){
//				System.out.println(previousePath + ": " + distance);
				fiteredList.add(previousePath);
			}
		}
		
		maxStops--;
		
		if(maxStops >= 0){
			
			for(DefaultWeightedEdge edge: graph.outgoingEdgesOf(fromVertex)){
				
				numOfTripsWithDistanceRecursively(graph.getEdgeTarget(edge), toVertex, relationalOperator, distanceValue, previousePath + graph.getEdgeTarget(edge), maxStops, (int) (distance + graph.getEdgeWeight(edge)), fiteredList);
			}
		}
	}
	
	private boolean validate(String route) {
		boolean valid = true;
		
		for(char vertex : route.toCharArray()){
			if(!graph.vertexSet().contains(String.valueOf(vertex))){
				valid = false;
			}
		}
		return valid;
	}
	
	

	public void exportDOT(int lineNumber) throws IOException {
	    IntegerNameProvider<String> p1=new IntegerNameProvider<String>();
	    StringNameProvider<String> p2=new StringNameProvider<String>();
	    ComponentAttributeProvider<DefaultWeightedEdge> p4 =
	       new ComponentAttributeProvider<DefaultWeightedEdge>() {
	            public Map<String, String> getComponentAttributes(DefaultWeightedEdge e) {
	                Map<String, String> map =new LinkedHashMap<String, String>();
	                map.put("label", Integer.toString((int)graph.getEdgeWeight(e)));
	                return map;
	            }
	       };
	    DOTExporter<String, DefaultWeightedEdge> export=new DOTExporter<String, DefaultWeightedEdge>(p1, p2, null, null, p4);
	    try {
	        export.export(new FileWriter("graph_" + String.valueOf(lineNumber) + ".gv"), graph);
	    }catch (IOException ex){}
	}
}
