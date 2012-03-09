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
	for (int i = 0; i < rulesToCreate; i++)
	{
		rulesList.add(new Rule(availableMetrics));
	}
  }
  
  public void GenerateSummary() {
	  Vector<Element> elements = project.getElements();
	  for (int i = 0; i < elements.size(); i++) {
		  for (int j = 0; j < rulesList.size(); j++) {
			  if (rulesList.get(j).evaluate(elements.get(i)))
			  {
				  // add this element to the generated summary
				  break;
			  }
		  }
	  }
  }
		
  public int FitnessFunction() {
	int NumFitnessFunction = (((project.getSummary().Intersection(generatedSummary))/generatedSummary.getSummarySize()) + 
		                     ((project.getSummary().Intersection(generatedSummary))/project.getSummary().getSummarySize())/(2));
	return NumFitnessFunction;
  }
}

