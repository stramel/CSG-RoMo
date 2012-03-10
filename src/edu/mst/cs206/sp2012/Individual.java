package edu.mst.cs206.sp2012;

import java.util.Random;
import java.util.Vector;

public class Individual {
	private Summary generatedSummary;
	private OpenSourceProject project;
	private Vector<Rule> rulesList = new Vector<Rule>();
	
	public Individual(OpenSourceProject project, String[] availableMetrics, int maxNumberOfRules) {
		this.project = project;

		int rulesToCreate = new Random().nextInt(maxNumberOfRules) + 1;
		Integer[] maxMetricsThresholds = new Integer[availableMetrics.length];
		Vector<Element> elements = project.getElements();
		
		// Find the max of each metric
		for (int i = 0; i < maxMetricsThresholds.length; i++) {
			maxMetricsThresholds[i] = 0;
			
			for (int j = 0; j < elements.size(); j++) {
				if (elements.get(j).MetricValue(availableMetrics[i]).compareTo(maxMetricsThresholds[i]) > 0) {
					maxMetricsThresholds[i] = elements.get(j).MetricValue(availableMetrics[i]);
				}
			}
		}
		
		// Create the rules for this individual
		for (int i = 0; i < rulesToCreate; i++) {
			rulesList.add(new Rule(availableMetrics, maxMetricsThresholds));
		}
	}
  
	public void GenerateSummary() {
		Vector<Element> elements = project.getElements();
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
	
	public int FitnessFunction() {
		int intersectionValue = project.getSummary().Intersection(generatedSummary);
		return (intersectionValue / generatedSummary.getSummarySize()) + (intersectionValue / (project.getSummary().getSummarySize() / 2));
	}
}

