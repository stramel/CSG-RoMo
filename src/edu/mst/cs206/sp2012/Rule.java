package edu.mst.cs206.sp2012;

import java.util.Random;
import java.util.Vector;
import java.util.HashMap;
/**This Class will build a Rule for the Individual*/   

public class Rule {
	private HashMap<String, Integer> thresholds = new HashMap<String, Integer>();
	private Vector<Boolean> andBetween = new Vector<Boolean>();
	private static final Random randomizer = new Random();

	public Rule(String[] availableMetrics, Integer[] maxMetricsThresholds) {
		final int N = availableMetrics.length;
		
		for (int i=0; i<N; i++) {
			
			// Randomly determine if a metric will be added to the Rule.
			final boolean addMetric = randomizer.nextBoolean();
			if (addMetric){
				final int upperBound = maxMetricsThresholds[i];
				
				final int lowerBound = randomizer.nextInt(upperBound);
				final String metricKey = availableMetrics[i];
				thresholds.put(metricKey, lowerBound);
			}
		}
		
		for (int i = 1; i < thresholds.size(); i++) {			
			// Generate the logical operator for in-between the thresholds
			this.andBetween.add(randomizer.nextBoolean());
		}
	}
	
	/**public boolean evaluate(Element element)
	 * This function will check the string of ands to see if true and loop back to look for more.
	 * @param element of type Element
	 * @return evaluatesTrue
	 */
    public boolean evaluateElement(Element element) {
    	
    	// Build up a set of applicable metrics for the given element.
    	HashMap<String, Integer> applicableMetrics = new HashMap<String, Integer>();
    	Vector<String> applicableMetricIDs = new Vector<String>();
    	for (String metricID: this.thresholds.keySet()) {
	      final boolean metricAppliesToElement = element.MetricExists(metricID);
	      if (metricAppliesToElement)
	      {
	      	applicableMetrics.put(metricID, this.thresholds.get(metricID));
	        applicableMetricIDs.add(metricID);
	      }
      }
    	
    	// If no metrics apply to this element, then by default return false.
    	boolean noMetricsApplyToElement = (applicableMetricIDs.size() == 0);
    	if (noMetricsApplyToElement){
    		return false;
    	}
    	
    	// Initialize the evaluated rule with the first metric evaluated on the element.
    	final String firstMetricID = applicableMetricIDs.get(0);
    	boolean evaluatedRule = ( element.MetricValue(firstMetricID) >= thresholds.get(firstMetricID) );
    	
    	// With this initial evaluation, now proceed with evaluating the rest of the metrics in the Rule.
    	//  Make sure to skip the first metric, though. It was already evaluated.
    	for (int i=1; i < applicableMetricIDs.size(); i++) {
    		final String currentMetricID = applicableMetricIDs.get(i);
    		final boolean ruleEvaluatedOnCurrentMetric = ( element.MetricValue(currentMetricID) >= thresholds.get(currentMetricID) );
    		
    	  final boolean logicalSeparaterIsAnd = this.andBetween.get(i-1);
    	  if (logicalSeparaterIsAnd) {
    	  	evaluatedRule = evaluatedRule && ruleEvaluatedOnCurrentMetric;
    	  }
    	  else {
    	  	evaluatedRule = evaluatedRule || ruleEvaluatedOnCurrentMetric;
    	  }
    	}
    	
    	return evaluatedRule;
    }
    
    /**public HashMap<String, Integer> getThresholds()
     * Returns thresholds
     * @return thresholds
     */
    public HashMap<String, Integer> getThresholds() {		
    	return this.thresholds;
    }
    
    /**public Vector<Boolean> getAndBetweens()
     * Returns andBetween
     * @return andBetween
     */
    public Vector<Boolean> getAndBetweens() {
    	return this.andBetween;
    }
}
