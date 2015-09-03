package de.dengpeng.assignments.tw.challenge;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * The Class CommandLineOptions.
 * 
 * This class helps parsing command line arguments 
 */
public class CommandLineOptions {

	/** The command line parser. */
	private CommandLineParser parser;
	
	/** The command line. */
	private CommandLine line;

	/** The input file path. */
	private String inputFilePath;

	/**
	 * Gets the input file path.
	 *
	 * @return the input file path
	 */
	public String getInputFilePath() {
		return inputFilePath;
	}

	/**
	 * Instantiates a new command line options.
	 *
	 * @param args the command line arguments
	 */
	public CommandLineOptions(String[] args) {
		// create the cli parser
		parser = new DefaultParser();

		// create Options object
		Options options = new Options();

		// add options
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
			System.out.println("Unexpected exception:" + exp.getMessage());
		}
	}

}
