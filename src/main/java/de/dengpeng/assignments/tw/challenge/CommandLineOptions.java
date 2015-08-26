package de.dengpeng.assignments.tw.challenge;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class CommandLineOptions {

	private CommandLineParser parser;
	private CommandLine line;

	private String inputFilePath;

	public String getInputFilePath() {
		return inputFilePath;
	}

	public CommandLineOptions(String[] args) {
		// create the cli parser
		parser = new DefaultParser();

		// create Options object
		Options options = new Options();

		// add t option
		options.addOption("input", true, "Input file path");
		options.addOption("help", false, "How to use");

		try {
			line = parser.parse(options, args);

			if (line.hasOption("input")) {
				// print the value of block-size
				// System.out.println( line.getOptionValue( "input" ) );
				this.inputFilePath = line.getOptionValue("input");
			} else if (line.hasOption("help")) {
				// print the value of block-size
				HelpFormatter formatter = new HelpFormatter();
				formatter.printHelp("graphproblem", options);
			}
		} catch (ParseException exp) {
			// TODO Auto-generated catch block
			System.out.println("Unexpected exception:" + exp.getMessage());
		}
	}

}
