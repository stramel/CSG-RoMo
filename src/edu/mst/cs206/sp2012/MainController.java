package edu.mst.cs206.sp2012;

import javax.naming.InvalidNameException;

public class MainController {
	private final int numberOfIterations;
	private final int maxNumberOfSolutions;
	private final int maxNumberOfRulesPerSolution;
	private final String urlToSampleSummary;
	private final String urlToMetricsResults;
	private Population bestSolutions;
	private String[] availableMetrics;
	
	public MainController(int numberOfIterations, int numberOfFinalSolutions,
			int maxNumberOfRulesPerSolution, String urlToSampleSummaries,
			String urlToMetricsResults)
	{
		this.numberOfIterations = numberOfIterations;
		this.maxNumberOfSolutions = numberOfFinalSolutions;
		this.maxNumberOfRulesPerSolution = maxNumberOfRulesPerSolution;
		this.urlToSampleSummary = urlToSampleSummaries;
		this.urlToMetricsResults = urlToMetricsResults;
		this.bestSolutions = new Population();
		
		// Hard-coded metrics!
		this.availableMetrics = new String[6];
		this.availableMetrics[0] = "MLOC";	// Method Lines of Code
		this.availableMetrics[1] = "PAR";	// Number of Parameters
		this.availableMetrics[2] = "VG";	// McCabe Cyclomatic Complexity
		this.availableMetrics[3] = "NBD";	// Nested Block Depth
		this.availableMetrics[4] = "NSC";	// Number of Children
		this.availableMetrics[5] = "NOM";	// Number of Methods
	}

	public void run() throws InvalidNameException {
		System.out.println("Hard coded number of Iterations: " + numberOfIterations);
		System.out.println("Hard coded number of Final Solutions: " + maxNumberOfSolutions);
		System.out.println("Maximum number of Rules per Solution: " + maxNumberOfRulesPerSolution);
		System.out.println("URL to Sample Summaries: " + urlToSampleSummary);
		System.out.println("URL to Metrics Results: " + urlToMetricsResults);

		
		Population currentPopulation = new Population(maxNumberOfSolutions, maxNumberOfRulesPerSolution, availableMetrics);
		currentPopulation.setSampleProject(new OpenSourceProject(urlToMetricsResults, urlToSampleSummary, availableMetrics));
		currentPopulation.initialize();
		
		for (int i = 0; i < numberOfIterations; i++) {
			currentPopulation.evaluateSolutions();
			bestSolutions.add(currentPopulation.getBestSolution());
			currentPopulation.purgeAndRenew();
		}
	}
	
	public Individual getBestSolution() {
		// Output the best solutions that are stored in ctrl.bestSolutions;
		return bestSolutions.getBestSolution();
	}
}