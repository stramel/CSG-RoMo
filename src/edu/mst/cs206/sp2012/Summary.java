package edu.mst.cs206.sp2012;

import java.util.Vector;

public class Summary {

  /*private int SummarySize;
  private OpenSourceProject ProjectProposedSummary; 
  private Vector<Element> ElementNames = new Vector<Element>();
	
  public Summary(String url, OpenSourceProject Project) {
	// TODO Auto-generated constructor stub
	System.out.println("Constructing Summary from an input file located at " + url);
	ProjectProposedSummary = Project;
	SummarySize = ProjectProposedSummary.getElements().size();
  }
	
  public int Intersection(Summary GeneratedSummary){
	int numIntersections;
	for(int i = 0; i < GeneratedSummary.SummarySize;i++){
	  for(int j = 0; j < SummarySize; j++){
		if(compareTo(ElementNames.get(j) == compareTo(getGeneratedSummaryLocation(GeneratedSummary,i))))
		  numIntersections++;
		}
	}
	return numIntersections;
  }
	
  public int getGeneratedSummaryLocation(Summary GeneratedSummary,int i){
	return GeneratedSummary.ElementNames.get(i);
  }

  public int getSummarySize(int SummarySize){
	return SummarySize;
  }*/
	
  public Summary(String url) {
	// TODO Auto-generated constructor stub
	System.out.println("Constructing Summary from an input file located at " + url);
  }
}