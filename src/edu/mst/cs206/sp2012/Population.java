package edu.mst.cs206.sp2012;

public class Population {

	private int maxNumberOfRulesPerSolution;
	private int maxNumberOfSolutions;

	public Population(int maxNumberOfSolutions, int maxNumberOfRulesPerSolution) {
		this.maxNumberOfRulesPerSolution = maxNumberOfRulesPerSolution;
		this.maxNumberOfSolutions = maxNumberOfRulesPerSolution;
	}

	public Population(int numberOfFinalSolutions) {
		this.maxNumberOfRulesPerSolution = 0;
		this.maxNumberOfSolutions = numberOfFinalSolutions;
	}

	public void purgeAndRenew() {
		System.out.println("Purge the abomonations of our generation, and replace with them new candidates who must prove their worthiness.");
	}

	public Object getBestSolution() {
		// TODO Auto-generated method stub
		return null;
	}

	public void push(Object solution) {
		// TODO Auto-generated method stub
		System.out.println("Adding a new solution to the population.");
	}

	public void evaluateSolutions() {
		// TODO Auto-generated method stub
		System.out.println("Evaluating all solutions against the criterium.");
	}

	public void setSampleSummary(Summary sampleSummary) {
		// TODO Auto-generated method stub
		System.out.println("Setting the sample summary to evaluate an individual's quality against.");
	}

	public void setAvailableMetrics(String[] availableMetrics) {
		// TODO Auto-generated method stub
		System.out.println("Setting available metrics to use");
	}

}