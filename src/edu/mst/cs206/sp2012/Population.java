package edu.mst.cs206.sp2012;

import java.util.Vector;

/**This class will store all of the individuals and evaluate their fitness*/
public class Population {
	private int maxRulesPerSolution;
	private int maxSolutions;
	private String[] availableMetrics;
	private OpenSourceProject sampleProject;
	private Vector<Individual> individuals;
	private Individual bestSolution;

	/**public Population(int maxNumberOfSolutions, int maxNumberOfRulesPerSolution, String[] availableMetrics)
	 * Constructor for the Population class sets the parameters passed in by the user, and the metrics that
	 * will be used in the rules.
	 * @param maxNumberOfSolutions of type int
	 * @param maxNumberOfRulesPerSolution of type int
	 * @param availableMetrics of type String[]
	 */
	public Population(int maxNumberOfSolutions, int maxNumberOfRulesPerSolution, String[] availableMetrics) {
		this.maxRulesPerSolution = maxNumberOfRulesPerSolution;
		this.maxSolutions = maxNumberOfSolutions;
		this.availableMetrics = availableMetrics;
		this.individuals = new Vector<Individual>(maxNumberOfSolutions);
	}
    
	/** public Population()
	 * Constructor for the Population that initializes the Vector of individuals.
	 */
	public Population() {
		this.individuals = new Vector<Individual>();
	}

    /** public void setSampleProject(OpenSourceProject sampleProject)
     * Sets the classes sample project to the passed in OpenSourceProject.
     * @param sampleProject of type OpenSourceProject
     */
	public void setSampleProject(OpenSourceProject sampleProject) {
		this.sampleProject = sampleProject;
	}

	/**public void initialize()
	 * Initializes the bestSolution and all of the individuals that will be created, passing them 
	 * all of the necessary parameters. 
	 */
	public void initialize() {
		// Initialize bestSolution to null
		this.bestSolution = null;
		
		// Initialize all individuals in the population.
		for (int i = 0; i < maxSolutions; i++) {
			Individual individual = new Individual(sampleProject, availableMetrics, maxRulesPerSolution);
			this.individuals.add(individual);
		}
	}

	/**public void clearIndividuals()
	 * Clear the individuals and re-initialize them.
	 */
	public void clearIndividuals() {
		// Remove all current individuals
		this.individuals.clear();
		
		// Re-initialize this object
		this.initialize();
	}

	/**public void evaluateSolutions()
	 * Generates a summary for each of the individuals and then runs the fitness 
	 * function on each them storing the individual with the highest fitness value
	 * in the bestSolution variable.
	 */
	public void evaluateSolutions() {
		float bestFitnessValue = (float) -0.1, fitnessValue;
		Individual currentIndividual;

		for (int i = 0; i < this.individuals.size(); i++) {
			currentIndividual = this.individuals.get(i);

			currentIndividual.GenerateSummary();
			fitnessValue = currentIndividual.FitnessFunction();
						
			if (fitnessValue > bestFitnessValue) {
				bestFitnessValue = fitnessValue;
				this.bestSolution = currentIndividual;
			}
		}
	}

	/**public Individual getBestSolution()
	 * Returns the individual stored in bestSolution variable.
	 * @return
	 */
	public Individual getBestSolution() {
		if (this.bestSolution == null) {
			this.evaluateSolutions();
		}
		
		return this.bestSolution;
	}

	/**public void add(Individual individual)
	 * Adds an individual to the population.
	 * @param individual of type individual
	 */
	public void add(Individual individual) {
		this.individuals.add(individual);
	}
}