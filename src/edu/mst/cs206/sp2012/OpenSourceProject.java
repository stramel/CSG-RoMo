package edu.mst.cs206.sp2012;

import java.util.Vector;

public class OpenSourceProject {
	private Vector<Element> elements;
	private Summary summary;
	
	public OpenSourceProject(String urlToMetricsXML, String urlToSampleSummaryXML, String[] metricsToSave) {
		System.out.println("Initialize a new OpenSourceProject object.");
		System.out.println("OpenSourceProject: all elements in this object will be built from the data provided in the Metrics-plugin XML located at " + urlToMetricsXML );
		System.out.println("OpenSourceProject: a manually-created Summary table is located in the file located at " + urlToSampleSummaryXML);

		//	this.elements = XMLParser.parseMetricsXML(urlToMetricsXML, metricsToSave);
		//	this.summary = XMLParser.parseSummaryXML(urlToSummaryXML);
	}
	
	public Vector<Element> getElements() {
		return elements;
	}
	
	public Summary getSummary() {
		return summary;		
	}
}

