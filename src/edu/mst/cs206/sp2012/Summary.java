package edu.mst.cs206.sp2012;

import java.util.Vector;

public class Summary {

  private int SummarySize;
  private OpenSourceProject ProjectProposedSummary; 
  private Vector<Element> ElementNames = new Vector<Element>();
	
  public Summary(String url, OpenSourceProject Project) {
	// TODO Auto-generated constructor stub
	System.out.println("Constructing Summary from an input file located at " + url);
	ProjectProposedSummary = Project;
	SummarySize = ProjectProposedSummary.getElements().size();
  }
	
  public int Intersection(Summary GeneratedSummary){
	int numIntersections = 0;
	for(int i = 0; i < GeneratedSummary.getSummarySize(); i++){
	  for(int j = 0; j < SummarySize; j++){
		if((ElementNames.get(j).getName()).compareTo((getLocation(i)).getName()) == 0)
		  numIntersections++;
		}
	}
	return numIntersections;
  }
	
  private Element getLocation(int i){
	return ElementNames.get(i);
  }

  private int getSummarySize(){
	return SummarySize;
  }
	
  public Summary(String url) {
	// TODO Auto-generated constructor stub
	System.out.println("Constructing Summary from an input file located at " + url);
  }
}