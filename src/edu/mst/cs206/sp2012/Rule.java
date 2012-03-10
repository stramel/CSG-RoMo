package edu.mst.cs206.sp2012;

import java.util.Random;
import java.util.Vector;
import java.util.HashMap;

public class Rule {
	private Vector<HashMap<String, Integer>> thresholds;

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
			HashMap<String,Integer> threshold = new HashMap<String, Integer>();
			threshold.put(metricsToUse.get(i), thresholdValue);
			thresholds.add(threshold);
		}		
	}
	
    public boolean evaluate(Element element) {
    	boolean evaluatesTrue = true;
    	
    	for (int i = 0; i < thresholds.size(); i++) {
    		HashMap<String, Integer> threshold = thresholds.get(i);
    		
    		if (element.getMetrics().get(threshold.get("metric")).compareTo(threshold.get("value")) < 0) {
    			evaluatesTrue = false;
    			break;
    		}
    	}
    	
		return evaluatesTrue;
    }
}
