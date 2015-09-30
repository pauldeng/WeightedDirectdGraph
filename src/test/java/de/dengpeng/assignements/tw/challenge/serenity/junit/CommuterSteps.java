package de.dengpeng.assignements.tw.challenge.serenity.junit;

import static org.junit.Assert.assertEquals;

import de.dengpeng.assignments.tw.challenge.TWGraph;
import net.thucydides.core.annotations.Step;

public class CommuterSteps {
	
	static TWGraph graph;
	int distance;
	int numTrips;
	int numRoutesWithin;
	int commuteTime;

	@Step
	public void construct_commute_map() {
		String graphDef = "AB5,BC4,CD8,DC8,DE6,AD5,CE2,EB3,AE7";
		graph = new TWGraph(graphDef.split(","));
	}

	@Step
	public void calculateDistanceofRoute(String route) {
		distance = graph.distanceOf(route);
	}

	@Step
	public void should_see_distance_value(int i) {
		assertEquals(i, distance);
	}

	@Step
	public void calculateNumberofTrips(String start, String end, String operator, int stops) {
		numTrips = graph.numbOfTripsWithStops(start, end, operator, stops);
	}

	@Step
	public void should_see_number_of_trips_value(int i) {
		assertEquals(i, numTrips);
		
	}

	@Step
	public void calculateLengthOfShortestRoute(String start, String end) {
		distance = graph.shortestBetween(start, end);
	}

	@Step
	public void calculateLengthOfShortestRoute(String start, String end, String operator, int distance) {
		numRoutesWithin = graph.numOfTripsWithDistance(start, end, operator, distance);
	}

	@Step
	public void should_see_number_of_routes(int i) {
		assertEquals(i, numRoutesWithin);
	}

	@Step
	public void calculateCommuteTimeOnRoute(String route) {
		commuteTime = graph.calculateTime("ABC");
	}

	public void should_see_commute_time_in_minutes(int minutes) {
		assertEquals(minutes, commuteTime);
	}
}
