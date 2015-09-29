package de.dengpeng.assignements.tw.challenge.jbehave;

import org.jbehave.core.annotations.AfterStories;
import org.jbehave.core.annotations.Alias;
import org.jbehave.core.annotations.BeforeStories;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import de.dengpeng.assignments.tw.challenge.TWGraph;

public class TrainsSteps {
	static TWGraph graph;
	int distance = 0;
	int numStops = 0;
	int numOfRoutes = 0;
	int commuteTime = 0;
	
	@BeforeStories
	public void beforeStories(){
		String graphDef = "AB5,BC4,CD8,DC8,DE6,AD5,CE2,EB3,AE7";
		graph = new TWGraph(graphDef.split(","));
	}
	
	@Given("a train commute map")
	public void givenTrainCommuteMap() {
	}
 
	@When("I follow the route $value")
	public void whenIFollowTheRoute(@Named("value") String value) throws Exception {
		distance = graph.distanceOf(value);
		
	}
 
	@Then("the route distance is $value")
	public void thenDistanceShouldBe(@Named("value") int value) {
		if (value != distance)
			throw new RuntimeException("distance is " + distance + ", but should be " + value);
	}
	
	@When("starting at $start and ending at $end with $operator $num stops")
	public void whenCalculatePossibleTrips(@Named("start") String start, @Named("end") String end, @Named("operator") String op, @Named("num") int num) {
		if(op.equalsIgnoreCase("maximum")){
			op = "<=";
		}else if(op.equalsIgnoreCase("exactly")){
			op = "==";
		}else{
			throw new RuntimeException("Unknown operator");
		}
		numStops = graph.numbOfTripsWithStops(start, end, op, num);
	}
	
	@When("starting at station $start and ending at $end")
	public void whenCalculateShortestPath(@Named("start") String start, @Named("end") String end) {
		distance = graph.shortestBetween("A", "C");
	}
	
	@When("starting at $start and ending at $end with a distance of $operator $value")
	public void whenCalculatePossiblePaths(@Named("start") String start, @Named("end") String end, @Named("operator") String op, @Named("value") int num) {
		if(op.equalsIgnoreCase("less than")){
			op = "<";
		}else{
			throw new RuntimeException("Unknown operator");
		}
		numOfRoutes = graph.numOfTripsWithDistance(start, end, op, num);
	}
	
	@When("travel the route $route")
	public void whenCalculateCommuteTime(@Named("route") String route) {
		commuteTime = graph.calculateTime(route);
	}
	
	@Then("the length of the shortest route is $value")
	public void thenShortestRouteShouldBe(@Named("value") int value) {
		if (value != distance)
			throw new RuntimeException("distance is " + distance + ", but should be " + value);
	}
	
	@Then("the commute time is $value minutes")
	public void thenCommuteTimeShouldBe(@Named("value") int value) {
		if (value != commuteTime)
			throw new RuntimeException("commuteTime is " + commuteTime + ", but should be " + value);
	}
	
	@Then("the number of different routes are $value")
	public void thenNumberOfRoutesShouldBe(@Named("value") int value) {
		if (value != numOfRoutes)
			throw new RuntimeException("numOfRoutes is " + numOfRoutes + ", but should be " + value);
	}
	
	@Then("the number of trips are $value")
	public void thenNumberOfTripsShouldBe(@Named("value") int value) {
		if (value != numStops)
			throw new RuntimeException("numStops is " + numStops + ", but should be " + value);
	}
	
	@AfterStories
	public void afterStories() {
	}

}
