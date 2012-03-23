package edu.mst.cs206.sp2012;

import java.util.Random;
import java.util.Vector;

/**This class will build an Individual*/
public class Individual {
	private Summary generatedSummary;
	private OpenSourceProject project;
	private Vector<Rule> rulesList = new Vector<Rule>();
	private float precision;
	private float recall;
	private boolean fitnessCalculated;
	
	/**public Individual(OpenSourceProject project, String[] availableMetrics, int maxNumberOfRules)
	 * 
	 * This constructor will create a random number of Rules, an array of maxMetricsThresholds for the availableMetrics length,
	 * and a Vector of type Element named elements set equal to the projects elements.
	 * 
	 * The nested for loop will:build a max number for each metric to generate the rules in better bounds.
	 * 
	 * The last for loop will create the rules for this specific Individual.
	 * 
	 * @param project of type OpenSourceProject
	 * @param availableMetrics of type String[]
	 * @param maxNumberOfRules of type int
	 */
	public Individual(OpenSourceProject project, String[] availableMetrics, int maxNumberOfRules) {
		this.project = project;
		this.generatedSummary = new Summary();

		final int rulesToCreate = new Random().nextInt(maxNumberOfRules) + 1;
		Integer[] maxMetricsThresholds = new Integer[availableMetrics.length];
		Vector<Element> elements = project.getElements();
		this.generatedSummary = new Summary();
		
		// Find the max of each metric
		for (int i = 0; i < maxMetricsThresholds.length; i++) {
			maxMetricsThresholds[i] = 0;
			
			// Loop over all elements available for the given open source project
			for (int j = 0; j < elements.size(); j++) {
				
				final boolean availableMetricExistsInMetricResults = elements.get(j).MetricExists(availableMetrics[i]);
				if (availableMetricExistsInMetricResults)
				{
					final boolean actualMetricResultIsGreaterThanCurrentMetricMax = elements.get(j).MetricValue(availableMetrics[i]).compareTo(maxMetricsThresholds[i]) > 0;
					if (actualMetricResultIsGreaterThanCurrentMetricMax) {
						// If the availableMetric exists in the metricResults and the 
						//  value of the metric result exceeds the current maximum value
						//  for the given metric, then update the maximum value of the given
						//  metric.
						maxMetricsThresholds[i] = elements.get(j).MetricValue(availableMetrics[i]);
					}
				}
			}
		}
		
		// Create the rules for this individual
		for (int i = 0; i < rulesToCreate; i++) {
			rulesList.add(new Rule(availableMetrics, maxMetricsThresholds));
		}
	}
  
	/** public void GenerateSummary()
	 * This function will add Elements to the generatedSummary based on the evaluate function of the Rule.java class.
	 */
	public void GenerateSummary() {
		Vector<Element> elements = project.getElements();
		this.fitnessCalculated = false;
		
		for (int i = 0; i < elements.size(); i++) {
			for (int j = 0; j < rulesList.size(); j++) {
				if (rulesList.get(j).evaluate(elements.get(i))) {
					// add this element to the generated summary
					this.generatedSummary.addElement(elements.get(i));
					break;
				}
			}
		}
	}
	
	/** public int FitnessFunction()
	 * This function will run the FitnessFuntion on both Summaries looking for a better Summary to create.  
	 * @return the value of the FitnessFunction.
	 */
	public float FitnessFunction() {
		if (!this.fitnessCalculated) {
			if (generatedSummary.getSummarySize() == 0) {
				this.recall = 0;
				this.precision = 0;
			} else {
				float intersectionValue = project.getSummary().Intersection(generatedSummary);
				this.recall = intersectionValue / generatedSummary.getSummarySize();
				this.precision = intersectionValue / project.getSummary().getSummarySize();
			}
			
			this.fitnessCalculated = true;
		}
		
		return (this.recall + this.precision) / 2;		
	}
	
	/**public float getPrecision()
	 * This function will return the precision.
	 * @return precision
	 */
	public float getPrecision() {
		if (!this.fitnessCalculated) {
			this.FitnessFunction();
		}
		
		return this.precision;
	}
	
	/**public float getRecall()
	 * This function will return the FitnessFuntion
	 * @return FitnessFunction
	 */
	public float getRecall() {
		if (!this.fitnessCalculated) {
			this.FitnessFunction();
		}
		
		return this.recall;
	}
	
	/**public Vector<Rule> getRules()
	 * Returns the rulesList
	 * @return rulesList
	 */
	public Vector<Rule> getRules() {
		return this.rulesList;
	}
}

