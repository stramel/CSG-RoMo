package edu.mst.cs206.sp2012;

import java.util.Vector;

public class Population {

	private int maxNumberOfRulesPerSolution;
	private int maxNumberOfSolutions;
	private Vector<Individual> individuals;
	private String[] availableMetrics;
	private OpenSourceProject sampleProject;

	public Population(int maxNumberOfSolutions, int maxNumberOfRulesPerSolution) {
		System.out.println("Initializing the population.");
		System.out.println("Population: there may only be " + maxNumberOfSolutions + " individuals in this population.");
		System.out.println("Population: each individual can only have " + maxNumberOfRulesPerSolution + " rules.");
		
		this.maxNumberOfRulesPerSolution = maxNumberOfRulesPerSolution;
		this.maxNumberOfSolutions = maxNumberOfRulesPerSolution;
		this.individuals = new Vector<Individual>(maxNumberOfSolutions);
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
		System.out.println("Setting available metrics to use: " + availableMetrics.toString());
		this.availableMetrics = availableMetrics;
	}

	public void initialize() {
		// Initialize all individuals in the population.
		for (int i=0; i<maxNumberOfRulesPerSolution; i++)
		{
			Individual newIndividual = new Individual(maxNumberOfRulesPerSolution, sampleProject);
			newIndividual.setAvailableMetrics(availableMetrics);
			this.individuals.add(newIndividual);
		}
	}

	public void setSampleProject(OpenSourceProject sampleProject) {
		// TODO Auto-generated method stub
		this.sampleProject = sampleProject;
	}
}