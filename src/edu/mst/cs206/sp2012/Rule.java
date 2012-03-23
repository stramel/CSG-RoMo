package edu.mst.cs206.sp2012;

import java.util.Random;
import java.util.Vector;
import java.util.HashMap;
/**This Class will build a Rule for the Individual*/
/**public class Rule
 * This function will find metrics to remove for the values desired and add metrics to the values desired,
 * generates a threshold, saves that thresholds to a list, and generates the logical expressions.    
 */
public class Rule {
	private HashMap<String, Integer> thresholds = new HashMap<String, Integer>();
	private Vector<Boolean> andBetween = new Vector<Boolean>();

	public Rule(String[] availableMetrics, Integer[] maxMetricsThresholds) {		
		int thresholdsToCreate = new Random().nextInt(availableMetrics.length) + 1;
		Vector<String> metricsToUse = new Vector<String>();
		
		if (thresholdsToCreate > (availableMetrics.length / 2)) {
			// Remove metrics to hit the value wanted
			for (int i = 0; i < availableMetrics.length; i++) {
				metricsToUse.add(availableMetrics[i]);
			}
			
			for (int i = metricsToUse.size() - thresholdsToCreate; i > 0; i--) {
				int metricToRemove;
				
				do {
					metricToRemove = new Random().nextInt(availableMetrics.length);
				} while (!metricsToUse.remove(availableMetrics[metricToRemove]));
			}
		} else {
			// Add metrics to hit the value wanted
			for (int i = 0; i < thresholdsToCreate; i++) {
				int metricToAdd;
				
				do {
					metricToAdd = new Random().nextInt(availableMetrics.length);
				} while (metricsToUse.contains(availableMetrics[metricToAdd]) || !metricsToUse.add(availableMetrics[metricToAdd]));
			}
		}
		
		for (int i = 0; i < metricsToUse.size(); i++) {			
			// Generate the threshold
			int thresholdValue = new Random().nextInt(maxMetricsThresholds[i]);
			
			// Save this threshold to the list
			this.thresholds.put(metricsToUse.get(i), thresholdValue);
		}
		
		for (int i = 1; i < metricsToUse.size(); i++) {			
			// Generate the logical operator for in-between the thresholds
			this.andBetween.add(new Random().nextBoolean());
		}
	}
	/**public boolean evaluate(Element element)
	 * This function will check the string of ands to see if true and loop back to look for more.
	 * @param element of type Element
	 * @return evaluatesTrue
	 */
    public boolean evaluate(Element element) {
    	boolean evaluatesTrue = true;
    	String[] keys = thresholds.keySet().toArray(new String[0]);
    	int i = -1;
    	
    	do {
    		// Reset evaluatesTrue
    		evaluatesTrue = true;
    		
    		// Check the current string of ands to see if they are true
    		do {
    			i++; // Increment i for the next value in the chain
    			
    			// Check the value against this threshold
        		if (element.MetricExists(keys[i]) && element.MetricValue(keys[i]).compareTo(thresholds.get(keys[i])) < 0) {
        			evaluatesTrue = false;
        			break;
        		}
    		} while (i < this.andBetween.size() && this.andBetween.get(i)); // true here means to do another and
    		
    		// Fix for if the last loop broke before the end of an and chain
    		while (i < this.andBetween.size() && this.andBetween.get(i)) { i++; }
    	} while (!evaluatesTrue && i < this.andBetween.size());
    	
		return evaluatesTrue;
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
