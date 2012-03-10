package edu.mst.cs206.sp2012;

import java.util.Vector;
/** This class builds a Summary */
public class Summary {
	private Vector<Element> elements = new Vector<Element>();
  
	/** public Summary(Vector<Element> elements is a constructor that will
	   * pass in a Vector of elements setting the elements equal to each other.  
	   * 
	   * @param elements will be the Vector of type Elements.
	   *
	   */
	public Summary(Vector<Element> elements) {
		this.elements = elements;
	}

	/**public int Intersection(Summary otherSummary)
	 * 
	 * @param otherSummary of type Summary to intersect with the generatedSummary of type Summary.
	 * @return numIntersections - the number of intersections that occur between the two Summaries.  
	 */
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
  
	/**public boolean addElement(Element element)
	 * 
	 * @param element of type Element
	 * @return true
	 */
	public boolean addElement(Element element) {
		return this.elements.add(element);
	}
	
	/**public Element getElement(int i)
	 * 
	 * @param i of type int which is the position of the element we are grabbing.
	 * @return the location of that element
	 */
	public Element getElement(int i) {
		return this.elements.get(i);
	}

	/** public int getSummarySize()
	 * @return the size of the Summary
	 */
	public int getSummarySize() {
		return this.elements.size();
	}
}