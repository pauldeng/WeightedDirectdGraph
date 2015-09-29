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

/**
 * The Class TWGraph.
 * 
 * This class defines the commute graph based on the problem
 */
public class TWGraph {
	
	/** The graph. */
	private final SimpleDirectedWeightedGraph<String, DefaultWeightedEdge> graph;
	
	/** 
	 * The Constant transfer time of each train station.
	 * Start and destination station do not need this extra transfer time.
	 */
	public final static int EXTRA_TIME_PER_STATION =2;
	
	
	/** The Constant NO_SUCH_ROUTE. */
	public final static int NO_SUCH_ROUTE = -1;

	/**
	 * Instantiates a new commute graph.
	 *
	 * @param graphDef the commute graph
	 */
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

	/**
	 * Distance of given route
	 *
	 * @param route the given route
	 * @return the distance
	 */
	public int distanceOf(String route){
		int distance = 0;
		
		if(validate(route) == true){
			for(int i=0; i<route.length()-1;i++){
				
				DefaultWeightedEdge edge = graph.getEdge(String.valueOf(route.charAt(i)), String.valueOf(route.charAt(i+1)));
				if(edge == null){
//					System.out.println("NO SUCH ROUTE");
//					throw new Exception("NO SUCH ROUTE");
					distance = NO_SUCH_ROUTE;
					break;
				}
				
				distance += graph.getEdgeWeight(edge);
				
			}
		}else{
			System.out.println("NO SUCH ROUTE");
		}
		
		return distance;
	}

	/**
	 * Number of trips which meets the condition of given stops.
	 * 
	 * This is the interface method and it will actually call numbOfTripsWithStopsRecursively to compute.
	 *
	 * @param fromVertex the start station
	 * @param toVertex the destination station
	 * @param relationalOperator the relational operator, e.g. >, >=, <, <=, ==
	 * @param numOfStops the number of stops
	 * @return the number of possible trips which met the given stop condition
	 */
	public int numbOfTripsWithStops(String fromVertex, String toVertex, String relationalOperator, int numOfStops) {
		int numOfTrips = 0;
		
		if(validate(fromVertex) && validate(toVertex) && fromVertex.length() == 1 && toVertex.length() == 1){
			
			numOfTrips = numbOfTripsWithStopsRecursively(fromVertex, toVertex, relationalOperator, numOfStops);
		} else {
			System.out.println("NO SUCH ROUTE");
		}
		
		return numOfTrips;
	}
	
	/**
	 * Number of trips which meets the condition of given stops.
	 * 
	 * This is the implementation of numbOfTripsWithStops.
	 *
	 * @param fromVertex the start station
	 * @param toVertex the destination station
	 * @param relationalOperator the relational operator, e.g. >, >=, <, <=, ==
	 * @param numOfStops the number of stops
	 * @return the number of possible trips which met the given stop condition
	 */
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

	/**
	 * Shortest distance between 2 stations.
	 * 
	 * This method find the shortest distance between 2 stations. The 2 stations can be the same station (a loop).
	 *
	 * @param fromVertex the start station
	 * @param toVertex the destination station
	 * @return the distance of shortest route
	 */
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

	/**
	 * Shortest between 2 different stations.
	 * 
	 * This method find the shortest distance between 2 different stations. The 2 stations cannot be the same station.
	 *
	 * @param fromVertex the start station
	 * @param toVertex the destination station
	 * @return the distance of shortest route
	 */
	private int shortestBetweenDifferentVertices(String fromVertex, String toVertex) {
		int distance = 0;

		List<DefaultWeightedEdge> shortestPath = DijkstraShortestPath.findPathBetween(graph, fromVertex, toVertex);

		for (DefaultWeightedEdge edge : shortestPath) {
			distance += graph.getEdgeWeight(edge);
		}

		return distance;
	}

	/**
	 * Number of trips with given distance condition.
	 * 
	 * This is interface method and it will call numOfTripsWithDistanceRecursively to compute.
	 *
	 * @param fromVertex the start station
	 * @param toVertex the destination station
	 * @param relationalOperator the relational operator, e.g. >, >=, <, <=, ==
	 * @param distanceThreshold the distance threshold
	 * @return the number of trips which met the given distance condition
	 */
	public int numOfTripsWithDistance(String fromVertex, String toVertex, String relationalOperator, int distanceThreshold) {
		int numOfTrips = 0;
		
		if(validate(fromVertex) && validate(toVertex) && fromVertex.length() == 1 && toVertex.length() == 1){
//			numOfTrips = numOfTripsWithDistanceRecursively(fromVertex, toVertex, relationalOperator, distanceValue, "", 15);
			
			int[] weights = new int[graph.edgeSet().size()];
			int i=0;
			for(DefaultWeightedEdge edge : graph.edgeSet()){
				weights[i] = (int) graph.getEdgeWeight(edge);
				i++;
			}
			int minDistance = Ints.min(weights);
						
			List<String> fiteredList = new ArrayList<String>();
			numOfTripsWithDistanceRecursively(fromVertex, toVertex, relationalOperator, distanceThreshold, fromVertex, distanceThreshold/minDistance, 0, fiteredList);
			
			numOfTrips = fiteredList.size();
		} else {
			System.out.println("NO SUCH ROUTE");
		}
		
		return numOfTrips;
	}

	/**
	 * Number of trips with given distance condition.
	 * 
	 * This is implementation of method numOfTripsWithDistance.
	 *
	 * @param fromVertex the start station
	 * @param toVertex the destination station
	 * @param relationalOperator the relational operator, e.g. >, >=, <, <=, ==
	 * @param distanceThreshold the distance threshold
	 * @param previousePath the route path log
	 * @param maxStops the max stops allowed
	 * @param currentDistance the current distance
	 * @param filteredPathList the filtered route path list log
	 */
	private void numOfTripsWithDistanceRecursively(String fromVertex, String toVertex, String relationalOperator, int distanceThreshold, String previousePath, int maxStops, int currentDistance, List<String> filteredPathList) {
		
		if((currentDistance < distanceThreshold) && (previousePath.lastIndexOf(toVertex) == previousePath.length()-1)){
			
			if(previousePath.length() > 1){
//				System.out.println(previousePath + ": " + distance);
				filteredPathList.add(previousePath);
			}
		}
		
		maxStops--;
		
		if(maxStops >= 0){
			
			for(DefaultWeightedEdge edge: graph.outgoingEdgesOf(fromVertex)){
				
				numOfTripsWithDistanceRecursively(graph.getEdgeTarget(edge), toVertex, relationalOperator, distanceThreshold, previousePath + graph.getEdgeTarget(edge), maxStops, (int) (currentDistance + graph.getEdgeWeight(edge)), filteredPathList);
			}
		}
	}
	
	/**
	 * Validate the stations name
	 *
	 * @param route the route
	 * @return true, if all routes given are valid
	 */
	private boolean validate(String route) {
		boolean valid = true;
		
		for(char vertex : route.toCharArray()){
			if(!graph.vertexSet().contains(String.valueOf(vertex))){
				valid = false;
			}
		}
		return valid;
	}
	
	

	/**
	 * Export dot image file
	 *
	 * @param lineNumber the line number
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
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
	
	/**
	 * Calculate the commute time it takes with given route.
	 *
	 * @param route the given route
	 * @return the time in minutes
	 */
	public int calculateTime(String route){
		int mins = 0;
		
		mins = distanceOf(route);
		
		mins = numberOfStopsWithWaitTime(mins, route.length());
		// the start node and finish node do not require extra time
		
		return mins;
	}
	
	/**
	 * Number of stops with wait time.
	 *
	 * @param route the given route
	 * @param numberOfStations the number of stations
	 * @return the time in minutes
	 */
	private int numberOfStopsWithWaitTime(int route, int numberOfStations) {
		// TODO Auto-generated method stub
		// magic number 2 here are the start and end node
		return route = route + (numberOfStations - 2) * EXTRA_TIME_PER_STATION;
	}
}
