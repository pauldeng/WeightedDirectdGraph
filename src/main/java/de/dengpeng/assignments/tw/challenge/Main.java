package de.dengpeng.assignments.tw.challenge;

import java.io.FileReader;
import au.com.bytecode.opencsv.CSVReader;

public class Main {

	public static void main(String[] args) {
		// parse the command line parameters
		CommandLineOptions cmdLine = new CommandLineOptions(args);
		
		// default test data location
		String filePath = "./data/graphs_01.csv";
		
		// set the input file to user specified
		if(cmdLine.getInputFilePath() != null){
			filePath = cmdLine.getInputFilePath();
		}
		
		// read the graph definition from a CSV file
		try {
			CSVReader reader = new CSVReader(new FileReader(filePath));
			String[] nextLine;
			
			// the number of line in the CSV file
			int lineNumber = 0;
			while ((nextLine = reader.readNext()) != null) {
				
				// build the graph
				TWGraph graph = new TWGraph(nextLine);
				
				graph.exportDOT(lineNumber);
				lineNumber++;
				
				try {
					System.out.print("Output #01: ");
					System.out.println(graph.distanceOf("ABC"));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println("NO SUCH ROUTE");
				}
				
				
				try {
					System.out.print("Output #02: ");
					System.out.println(graph.distanceOf("AD"));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println("NO SUCH ROUTE");
				}

				try {
					System.out.print("Output #03: ");
					System.out.println(graph.distanceOf("ADC"));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println("NO SUCH ROUTE");
				}

				try {
					System.out.print("Output #04: ");
					System.out.println(graph.distanceOf("AEBCD"));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println("NO SUCH ROUTE");
				}

				try {
					System.out.print("Output #05: ");
					System.out.println(graph.distanceOf("AED"));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println("NO SUCH ROUTE");
				}
				
				System.out.print("Output #06: ");
				System.out.println(graph.numbOfTripsWithStops("C", "C", "<=", 3));
				
				
				System.out.print("Output #07: ");
				System.out.println(graph.numbOfTripsWithStops("A", "C", "==", 4));
				
				System.out.print("Output #08: ");
				System.out.println(graph.shortestBetween("A", "C"));
		
				System.out.print("Output #09: ");
				System.out.println(graph.shortestBetween("B", "B"));
				
				 System.out.print("Output #10: ");
				 System.out.println(graph.numOfTripsWithDistance("C", "C", "<", 30));
			}
			reader.close();
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
	}	
}
