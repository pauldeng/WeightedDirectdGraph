package de.dengpeng.assignments.tw.challenge;

import java.io.FileReader;
import au.com.bytecode.opencsv.CSVReader;

// TODO: Auto-generated Javadoc
/**
 * The Class Main.
 */
public class Main {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
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
				
				int distance;
				
				System.out.print("Output #01: ");
				distance = graph.distanceOf("ABC");
				if(distance>=0){
					System.out.println(distance);
				}else{
					System.out.println("NO SUCH ROUTE");
				}
				
				System.out.print("Output #02: ");
				distance = graph.distanceOf("AD");
				if(distance>=0){
					System.out.println(distance);
				}else{
					System.out.println("NO SUCH ROUTE");
				}
				
				System.out.print("Output #03: ");
				distance = graph.distanceOf("ADC");
				if(distance>=0){
					System.out.println(distance);
				}else{
					System.out.println("NO SUCH ROUTE");
				}
				
				System.out.print("Output #04: ");
				distance = graph.distanceOf("AEBCD");
				if(distance>=0){
					System.out.println(distance);
				}else{
					System.out.println("NO SUCH ROUTE");
				}
				
				System.out.print("Output #05: ");
				distance = graph.distanceOf("AED");
				if(distance>=0){
					System.out.println(distance);
				}else{
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
			ex.printStackTrace();
		}
	}	
}
