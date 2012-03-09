package edu.mst.cs206.sp2012;

import java.util.Vector;

public class Summary {

//  private int SummarySize;
//  private OpenSourceProject ProjectProposedSummary; 
  private Vector<Element> elements = new Vector<Element>();

/*  public Summary(OpenSourceProject Project) {
	// TODO Auto-generated constructor stub
	System.out.println("Constructing Summary from an input file located at " + url);
	ProjectProposedSummary = Project;
	SummarySize = ProjectProposedSummary.getElements().size();
  }*/
  
  public Summary(Vector<Element> elements)
  {
	  this.elements = elements;
  }
	
  public int Intersection(Summary GeneratedSummary){
	int numIntersections = 0;
	for(int i = 0; i < GeneratedSummary.getSummarySize(); i++){
	  for(int j = 0; j < elements.size(); j++){
		if((elements.get(j).getName()).compareTo((getElement(i)).getName()) == 0)
		  numIntersections++;
		}
	}
	return numIntersections;
  }
	
  private Element getElement(int i){
	return this.elements.get(i);
  }

  public int getSummarySize(){
	return this.elements.size();
  }
}