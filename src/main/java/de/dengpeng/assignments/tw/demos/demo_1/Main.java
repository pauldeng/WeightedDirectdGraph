/*
 * This is an sample application of JGraphT SimpleDirectedWeightedGraph found in this address below:
 * http://stackoverflow.com/questions/20242961/getting-weighted-graphs-to-work-in-jgrapht
 */
package de.dengpeng.assignments.tw.demos.demo_1;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		int x;
		Scanner sc = new Scanner(System.in);
		MyGraph my = new MyGraph();
		System.out.println("Please enter the number of flights: ");
		int no_of_ver = sc.nextInt();

		for (int i = 1; i <= no_of_ver; i++) {
			System.out.println("Please enter the flight destination for flight " + i + ":");
			my.addVertex(sc.next());
		}

		do {
			System.out.println("Enter the edges");
			String e1 = sc.next();
			String e2 = sc.next();
			my.addEdge(e1, e2);

			System.out.println("Please enter a price for this edge:");
			my.setEdgeWeight(sc.next());
			System.out.println("Continue... Yes:1 ********** No:0");
			x = sc.nextInt();
		} while (x == 1);

		System.out.println("Graph\n" + my.getGraph().toString());
		System.out.println("\n\n**********Spanning Tree*********");
		my.getSpanningTree();
		System.out.println("\nSpanning Tree Cost");
		my.getSpanningTreeCost();

	}

}
