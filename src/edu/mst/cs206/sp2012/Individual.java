package edu.mst.cs206.sp2012;

import java.util.Random;
import java.util.Vector;

public class Individual {

	private int maxNumberOfRules;
	private Vector<Rule> ruleslist = new Vector<Rule>();
	
	public Individual(int maxNumberOfRulesPerSolution) {
		this.maxNumberOfRules = maxNumberOfRulesPerSolution;
		
		int numberOfRulesToBeCreated = new Random().nextInt(maxNumberOfRulesPerSolution);
		for (int i=0; i<numberOfRulesToBeCreated; i++)
		{
			this.ruleslist.add(new Rule());
		}
	}
	
	/*private Element ProposedSummary;
	/private Element GeneratedSummaryTable;
	
	
	public Individual(Element ProposedSummary ) {
		ProposedSummary = PropsedSummary;
		
	}
	
	public GenerateSummaryTable(){
		for(int i = 0; i < 5;i++){
			
		}
	}*/
}
