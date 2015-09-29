Scenario: 01 The distance of the route A-B-C
Given a train commute map
When I follow the route ABC
Then the route distance is 9

Scenario: 02 The distance of the route A-D
Given a train commute map
When I follow the route AD
Then the route distance is 5

Scenario: 03 The distance of the route A-D-C
Given a train commute map
When I follow the route ADC
Then the route distance is 13

Scenario: 04 The distance of the route A-E-B-C-D
Given a train commute map
When I follow the route AEBCD
Then the route distance is 22

Scenario: 05 The distance of the route A-E-D
Given a train commute map
When I follow the route AED
Then the route distance is -1 (No Such Route)

Scenario: 06 The number of trips starting at C and ending at C with a maximum of 3 stops
Given a train commute map
When starting at C and ending at C with maximum 3 stops
Then the number of trips are 2

Scenario: 07 The number of trips starting at A and ending at C with exactly 4 stops
Given a train commute map
When starting at A and ending at C with exactly 4 stops
Then the number of trips are 3

Scenario: 08 The length of the shortest route (in terms of distance to travel) from A to C
Given a train commute map
When starting at station A and ending at C
Then the length of the shortest route is 9

Scenario: 09 The length of the shortest route (in terms of distance to travel) from B to B
Given a train commute map
When starting at station B and ending at B
Then the length of the shortest route is 9

Scenario: 10 The number of different routes from C to C with a distance of less than 30
Given a train commute map
When starting at C and ending at C with a distance of less than 30
Then the number of different routes are 7

Scenario: 11 The route commute time
Given a train commute map
When travel the route ABC
Then the commute time is 11 minutes