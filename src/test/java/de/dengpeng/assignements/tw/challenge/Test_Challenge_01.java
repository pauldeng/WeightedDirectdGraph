package de.dengpeng.assignements.tw.challenge;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import de.dengpeng.assignments.tw.challenge.TWGraph;


public class Test_Challenge_01 {
	
	static TWGraph graph;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		String graphDef = "AB5,BC4,CD8,DC8,DE6,AD5,CE2,EB3,AE7";
		graph = new TWGraph(graphDef.split(","));
	}

	@Test
	public void testDistanceBetween_ABC() throws Exception {
		assertEquals(9, graph.distanceOf("ABC"));
	}

	@Test
	public void testDistanceBetween_AD() throws Exception {
		assertEquals(5, graph.distanceOf("AD"));
	}

	@Test
	public void testDistanceBetween_ADC() throws Exception  {
		assertEquals(13, graph.distanceOf("ADC"));
	}

	@Test
	public void testDistanceBetween_AEBCD() throws Exception  {
		assertEquals(22, graph.distanceOf("AEBCD"));
	}

	@Test(expected=Exception.class)
	public void testDistanceBetween_AED() throws Exception  {
		assertEquals(-1, graph.distanceOf("AED"));
	}

	@Test
	public void testNumStops_CC3() throws Exception {
		int numStops = graph.numbOfTripsWithStops("C", "C", "<=", 3);
		assertEquals(2, numStops);
	}

	@Test
	public void testNumStops_AC4() throws Exception {
		int numStops = graph.numbOfTripsWithStops("A", "C", "==", 4);
		assertEquals(3, numStops);
	}

	@Test
	public void testShortestRoute_AC() throws Exception {
		int shortestRoute = graph.shortestBetween("A", "C");
		assertEquals(9, shortestRoute);
	}

	@Test
	public void testShortestRoute_BB() throws Exception {
		int shortestRoute = graph.shortestBetween("B", "B");
		assertEquals(9, shortestRoute);
	}

	@Test
	public void numRoutesWithin_CC30() throws Exception {
		int numRoutesWithin = graph.numOfTripsWithDistance("C", "C", "<", 30);
		assertEquals(7, numRoutesWithin);
	}
}