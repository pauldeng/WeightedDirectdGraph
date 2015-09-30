package de.dengpeng.assignements.tw.challenge.serenity.junit;

import org.junit.Test;
import org.junit.runner.RunWith;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;

@RunWith(SerenityRunner.class)
public class TrainMapTest {
	
    @Steps                                                                       
    CommuterSteps commuter;

    @Test
    public void distance_of_the_route_ABC() {
        // GIVEN
    	commuter.construct_commute_map();
        // WHEN
    	commuter.calculateDistanceofRoute("ABC");
        // THEN.
    	commuter.should_see_distance_value(9);
    }
    
    @Test
    public void distance_of_the_route_AD() {
        // GIVEN
    	commuter.construct_commute_map();
        // WHEN
    	commuter.calculateDistanceofRoute("AD");
        // THEN.
    	commuter.should_see_distance_value(5);
    }
    
    @Test
    public void distance_of_the_route_ADC() {
        // GIVEN
    	commuter.construct_commute_map();
        // WHEN
    	commuter.calculateDistanceofRoute("ADC");
        // THEN.
    	commuter.should_see_distance_value(13);
    }
    
    @Test
    public void distance_of_the_route_AEBCD() {
        // GIVEN
    	commuter.construct_commute_map();
        // WHEN
    	commuter.calculateDistanceofRoute("AEBCD");
        // THEN.
    	commuter.should_see_distance_value(22);
    }
    
    @Test
    public void distance_of_the_route_AED() {
        // GIVEN
    	commuter.construct_commute_map();
        // WHEN
    	commuter.calculateDistanceofRoute("AED");
        // THEN.
    	commuter.should_see_distance_value(-1);
    }
    
    @Test
    public void number_of_trips_between_C_and_C_with_maximum_3_stops() {
        // GIVEN
    	commuter.construct_commute_map();
        // WHEN
    	commuter.calculateNumberofTrips("C", "C", "<=", 3);
        // THEN.
    	commuter.should_see_number_of_trips_value(2);
    }
    
    @Test
    public void number_of_trips_between_A_and_C_with_exactly_4_stops() {
        // GIVEN
    	commuter.construct_commute_map();
        // WHEN
    	commuter.calculateNumberofTrips("A", "C", "==", 4);
        // THEN.
    	commuter.should_see_number_of_trips_value(3);
    }
    
    @Test
    public void length_of_shortest_route_AC() {
        // GIVEN
    	commuter.construct_commute_map();
        // WHEN
    	commuter.calculateLengthOfShortestRoute("A", "C");
        // THEN.
    	commuter.should_see_distance_value(9);
    }
    
    @Test
    public void length_of_shortest_route_BB() {
        // GIVEN
    	commuter.construct_commute_map();
        // WHEN
    	commuter.calculateLengthOfShortestRoute("B", "B");
        // THEN.
    	commuter.should_see_distance_value(9);
    }
    
    @Test
    public void number_of_different_routes_with_distance_less_than_30_betwee_CC() {
        // GIVEN
    	commuter.construct_commute_map();
        // WHEN
    	commuter.calculateLengthOfShortestRoute("C", "C", "<", 30);
        // THEN.
    	commuter.should_see_number_of_routes(7);
    }
    
    @Test
    public void commute_time_between_ABC() {
        // GIVEN
    	commuter.construct_commute_map();
        // WHEN
    	commuter.calculateCommuteTimeOnRoute("ABC");
        // THEN.
    	commuter.should_see_commute_time_in_minutes(11);
    }
}
