package edu.mst.cs206.sp2012;

import java.util.Vector;

public class Summary {
	private Vector<Element> elements = new Vector<Element>();
  
	public Summary(Vector<Element> elements) {
		this.elements = elements;
	}

	public int Intersection(Summary otherSummary) {
		int numIntersections = 0;

		for (int i = 0; i < otherSummary.getSummarySize(); i++) {
			for (int j = 0; j < this.getSummarySize(); j++) {
				if ( (this.elements.get(j).getName()).compareTo((otherSummary.getElement(i)).getName()) == 0) {
					numIntersections++;
				}
			}
		}
	
		return numIntersections;
	}
  
	public boolean addElement(Element element) {
		return this.elements.add(element);
	}
	
	public Element getElement(int i) {
		return this.elements.get(i);
	}

	public int getSummarySize() {
		return this.elements.size();
	}
}