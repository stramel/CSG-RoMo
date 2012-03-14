package edu.mst.cs206.sp2012;

import java.util.Random;
import java.util.Vector;
import java.util.HashMap;

public class Rule {
	private HashMap<String, Integer> thresholds = new HashMap<String, Integer>();

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
	}
	
    public boolean evaluate(Element element) {
    	boolean evaluatesTrue = true;
    	String[] keys = thresholds.keySet().toArray(new String[0]);
    	
    	for (int i = 0; i < keys.length; i++) {
    		if (element.MetricExists(keys[i]) && element.MetricValue(keys[i]).compareTo(thresholds.get(keys[i])) < 0) {
    			evaluatesTrue = false;
    			break;
    		}
    	}
    	
		return evaluatesTrue;
    }
    
    public HashMap<String, Integer> getThresholds() {
    	return this.thresholds;
    }
}
