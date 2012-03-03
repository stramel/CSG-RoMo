package edu.mst.cs206.sp2012;

public class MainController {
	
	private int numberOfIterators;
	private int maxNumberOfSolutions;
	private int maxNumberOfRulesPerSolution;
	private String urlToSampleSummaries;
	private String urlToMetricsResults;
	private Population bestSolutions;
	private String[] availableMetrics;
	
	public static void main(String[] args) {
		MainController ctrl = new MainController(0, 0, 0, "./", "./");
		
		System.out.println("Hard coded number of Iterations: " + ctrl.numberOfIterators);
		System.out.println("Hard coded number of Final Solutions: " + ctrl.maxNumberOfSolutions);
		System.out.println("Maximum number of Rules per Solution: " + ctrl.maxNumberOfRulesPerSolution);
		System.out.println("URL to Sample Summaries: " + ctrl.urlToSampleSummaries);
		System.out.println("URL to Metrics Results: " + ctrl.urlToMetricsResults);
		
		Population currentPopulation = new Population(ctrl.maxNumberOfSolutions, ctrl.maxNumberOfRulesPerSolution);
		
		Summary sampleSummary = new Summary(ctrl.urlToSampleSummaries);
		currentPopulation.setSampleSummary(sampleSummary);
		
		currentPopulation.setAvailableMetrics(ctrl.availableMetrics);
		//currentPopulation.setComputedMetrics(ctrl.precomputedMetrics);
		
		currentPopulation.initialize();
		for(int generation=0; generation < ctrl.numberOfIterators; generation++)
		{
			currentPopulation.evaluateSolutions();
			ctrl.bestSolutions.push(currentPopulation.getBestSolution());
			currentPopulation.purgeAndRenew();
		}
		
		// Output the best solution that is stored in ctrl.bestSolutions;
	}
	
	public MainController() {}
	
	public MainController(int numberOfIterations, int numberOfFinalSolutions,
			int maxNumberOfRulesPerSolution, String urlToSampleSummaries,
			String urlToMetricsResults)
	{
		this.numberOfIterators = numberOfIterations;
		this.maxNumberOfSolutions = numberOfFinalSolutions;
		this.maxNumberOfRulesPerSolution = maxNumberOfRulesPerSolution;
		this.urlToSampleSummaries = urlToSampleSummaries;
		this.urlToMetricsResults = urlToMetricsResults;
		this.bestSolutions = new Population(numberOfFinalSolutions);
		
		// Hard-coded metrics!
		this.availableMetrics = new String[6];
		this.availableMetrics[0] = "MLOC";	// Method Lines of Code
		this.availableMetrics[1] = "PAR";	// Number of Parameters
		this.availableMetrics[2] = "VG";	// McCabe Cyclomatic Complexity
		this.availableMetrics[3] = "NBD";	// Nested Block Depth
		this.availableMetrics[4] = "NSC";	// Number of Children
		this.availableMetrics[5] = "NOM";	// Number of Methods
	}
}