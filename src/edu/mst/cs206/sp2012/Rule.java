package edu.mst.cs206.sp2012;

import java.util.Random;
import java.util.Vector;
import java.util.HashMap;

public class Rule {
	private Vector<HashMap<String, Float>> thresholds;

	public Rule(String[] availableMetrics) {
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
			float thresholdValue = Float.parseFloat("2");
			HashMap<String,Float> threshold = new HashMap<String,Float>();
			threshold.put(metricsToUse.get(i), thresholdValue);
			thresholds.add(threshold);
			

		    /*
			  // Method Lines of Code->In the hundreds possibly thousands
			  // Number of Parameters-> less than 10
			  // McCabe Cyclomatic Complexity-> less than 20
			  // Nested Block Depth-> less than 10
			  // Number of Children-> less than 10
			  // Number of Methods-> less than 40

				  Random randomizer = new Random();
				  int randomThresholdMLOC = randomizer.nextInt(2000); //This number needs to be changed to something a little greater 
				  													//the highest number of lines of code in the projects.
				  int randomThreshold = randomizer.nextInt(25);//This might be too small for NUM METHODS  
				  
				  if(availableMetrics[i].compareTo("MLOC") == 0)
					  this.ruleslist.add(new Rule(randomThresholdMLOC, availableMetrics[i], null));
				  else
					  this.ruleslist.add(new Rule(randomThreshold, availableMetrics[i], null));
			*/	
		}		
	}
	
    public boolean evaluate(Element element) {
    	boolean evaluatesTrue = true;
    	
    	for (int i = 0; i < thresholds.size(); i++) {
    		HashMap<String, Float> threshold = thresholds.get(i);
    		
    		if (element.getMetrics().get(threshold.get("metric")).compareTo(threshold.get("value")) < 0) {
    			evaluatesTrue = false;
    			break;
    		}
    	}
    	
		return evaluatesTrue;
    }
}
