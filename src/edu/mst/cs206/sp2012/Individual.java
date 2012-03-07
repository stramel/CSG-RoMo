package edu.mst.cs206.sp2012;

import java.util.Random;
import java.util.Vector;

public class Individual {

  private int maxNumberOfRules;
  private OpenSourceProject ProposedSummary;
  private Vector<Rule> ruleslist = new Vector<Rule>();

  private String[] availableMetrics;
	
  public Individual(int maxNumberOfRulesPerSolution, OpenSourceProject Project) {
	this.maxNumberOfRules = maxNumberOfRulesPerSolution;
	ProposedSummary = Project;
	
    int numberOfRulesToBeCreated = new Random().nextInt(maxNumberOfRulesPerSolution);
    for (int i = 0; i<numberOfRulesToBeCreated; i++)
    {
    
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
    }
  }

  public void setAvailableMetrics(String[] availableMetrics) {
	// TODO Auto-generated method stub
	this.availableMetrics = availableMetrics;
  }
		
  public int FitnessFunction(Summary ProposedSummary, Summary GeneratedSummary){
	int NumFitnessFunction = (((ProposedSummary.Intersection(GeneratedSummary))/GeneratedSummary.getSummarySize()) + 
		                     ((ProposedSummary.Intersection(GeneratedSummary))/ProposedSummary.getSummarySize())/(2));
	return NumFitnessFunction;
  }
}

