package edu.mst.cs206.sp2012;

import java.util.Vector;

public class OpenSourceProject {
	private Vector<Element> elements;
	private Summary summary;
	
	public OpenSourceProject(String urlToXml, String urlToSampleSummary) {
		System.out.println("Initialize a new OpenSourceProject object.");
		System.out.println("OpenSourceProject: all elements in this object will be built from the data provided in the Metrics-plugin XML located at " + urlToXml );
		System.out.println("OpenSourceProject: a manually-created Summary table is located in the file located at " + urlToSampleSummary);
		parseMetrics(urlToXml);
		parseSummary(urlToSampleSummary);
	}
	
	private void parseMetrics(String pathToXml) {
		System.out.println("OpenSourceProject: NOT DEFINED YET :( parsing the Metrics-plugin XML file located at " + pathToXml);
	}
	
	private void parseSummary(String pathToSummaryFile) {
		System.out.println("OpenSourceProject: NOT DEFINED YET :( parsing the manually-created Summary Table file located at " + pathToSummaryFile);
	}
	
	public Vector<Element> getElements() {
		return elements;
	}
	
	public Summary getSummary() {
		return summary;		
	}
}

