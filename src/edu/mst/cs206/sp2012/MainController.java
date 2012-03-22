package edu.mst.cs206.sp2012;

import javax.naming.InvalidNameException;

/**This class is our core library, operating as a service to be called by any given user interface*/
public class MainController {
	private final int numberOfIterations;
	private final int maxNumberOfSolutions;
	private final int maxNumberOfRulesPerSolution;
	private final String urlToSampleSummary;
	private final String urlToMetricsResults;
	private Population bestSolutions;
	private String[] availableMetrics;
	
	/**public MainController(int numberOfIterations, int numberOfFinalSolutions, int maxNumberOfRulesPerSolution, String urlToSampleSummaries, String urlToMetricsResults)
	 * 
	 * This constructor will initialize the MainController with the appropriate parameters, as specified by the initialization list.
	 * 
	 * availableMetrics is currently a hard-coded list of metric IDs that will be utilized by this project.
	 * 
	 * 
	 * @param numberOfIterations of type int
	 * @param numberOfFinalSolutions of type int
	 * @param maxNumberOfRulesPerSolution of type int
	 * @param urlToSampleSummaries of type String
	 * @param urlToMetricsResults of type String
	 */
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
	
	/** public void run()
	 * This function will begin the search for a set of rules that will create a summary table.
	 * 
	 * The population is first initialized with the appropriate parameters (maxNumberOfSolutions, maxNumberOfRulesPerSolution, and availableMetrics).
	 * 
	 * The sample project information is initialized as well, and passed to the population.
	 * 
	 * Looping through all iterations, all unevaluated individuals in the population are evaluated. 
	 * The best solution in the population is then stored in the Main Controller's population of
	 * bestSolutions. The population is purged and renewed for the next iteration.
	 */
	public void run() throws InvalidNameException {		
		Population currentPopulation = new Population(maxNumberOfSolutions, maxNumberOfRulesPerSolution, availableMetrics);
		currentPopulation.setSampleProject(new OpenSourceProject(urlToMetricsResults, urlToSampleSummary, availableMetrics));
		currentPopulation.initialize();
		
		for (int i = 0; i < numberOfIterations; i++) {
			currentPopulation.evaluateSolutions();
			bestSolutions.add(currentPopulation.getBestSolution());
			currentPopulation.purgeAndRenew();
		}
	}
	
	/** public Individual getBestSolution()
	 * This function will grab the best solution of the Main Controller's population of best solutions.
	 */
	public Individual getBestSolution() {
		// Output the best solutions that are stored in ctrl.bestSolutions;
		return bestSolutions.getBestSolution();
	}
}