package edu.mst.cs206.sp2012;

import java.util.Vector;

import javax.naming.InvalidNameException;
/**This class will build an OpenSourceProject from the XML*/
public class OpenSourceProject {
	private Vector<Element> elements;
	private Summary summary;
	/**public OpenSourceProject(String urlToMetricsXML, String urlToSampleSummaryXML, String[] metricsToSave)
	 * 
	 * This Constructor will set the elements in OpenSourceProject to the XML's elements that was ran on that same OpenSourceProject.
	 * The Constructor also does this for the summary of the OpenSourceProject. The Constructor will print out the location of  
	 * the manually-created summary table and the location of the Metrics-plugin XML. 
	 * 
	 * @param urlToMetricsXML of type String
	 * @param urlToSampleSummaryXML of type String
	 * @param metricsToSave of type String[]
	 * @throws InvalidNameException 
	 */
	public OpenSourceProject(String urlToMetricsXML, String urlToSampleSummaryXML, String[] metricsToSave) throws InvalidNameException {
		this.elements = XMLParser.parseMetricsXML(urlToMetricsXML, metricsToSave);
		this.summary = XMLParser.parseSummaryXML(urlToSampleSummaryXML);
	}
	
	/**public Vector<Element> getElements()
	 * Returns the elements of Vector<Element> elements.
	 * @return elements
	 */
	public Vector<Element> getElements() {
		return elements;
	}
	
	/**public Summary getSummary()
	 * Returns the summary of Summary.
	 * @return summary
	 */
	public Summary getSummary() {
		return summary;		
	}
}

