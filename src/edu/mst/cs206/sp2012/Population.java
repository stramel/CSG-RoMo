package edu.mst.cs206.sp2012;

import java.util.Vector;

public class Population {
	private int maxNumberOfRulesPerSolution;
	private int maxNumberOfSolutions;
	private String[] availableMetrics;
	private OpenSourceProject sampleProject;
	private Vector<Individual> individuals;
	private Individual bestSolution;

	public Population(int maxNumberOfSolutions, int maxNumberOfRulesPerSolution, String[] availableMetrics) {
		this.maxNumberOfRulesPerSolution = maxNumberOfRulesPerSolution;
		this.maxNumberOfSolutions = maxNumberOfSolutions;
		this.availableMetrics = availableMetrics;
		this.individuals = new Vector<Individual>(maxNumberOfSolutions);
	}

	public Population() {
		this.individuals = new Vector<Individual>();
	}

	public void setSampleProject(OpenSourceProject sampleProject) {
		this.sampleProject = sampleProject;
	}

	public void initialize() {
		// Initalize bestSolution to null
		bestSolution = null;
		
		// Initialize all individuals in the population.
		for (int i = 0; i < maxNumberOfSolutions; i++) {
			Individual individual = new Individual(sampleProject, availableMetrics, maxNumberOfRulesPerSolution);
			this.individuals.add(individual);
		}
	}

	public void purgeAndRenew() {
		// Remove all current individuals
		this.individuals.clear();
		
		// Re-initialize this object
		this.initialize();
	}

	public void evaluateSolutions() {
		float bestFitnessValue = (float) -0.1, fitnessValue;
		Individual currentIndividual;

		for (int i = 0; i < this.individuals.size(); i++) {
			currentIndividual = this.individuals.get(i);

			currentIndividual.GenerateSummary();
			fitnessValue = currentIndividual.FitnessFunction();
			
			// TODO check that the bestFitnessValue is supposed to be the smallest one
			if (fitnessValue > bestFitnessValue) {
				bestFitnessValue = fitnessValue;
				this.bestSolution = currentIndividual;
			}
		}
	}

	public Individual getBestSolution() {
		return bestSolution;
	}

	public void add(Individual individual) {
		this.individuals.add(individual);
	}
}