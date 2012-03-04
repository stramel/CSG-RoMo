package edu.mst.cs206.sp2012;

import java.util.Vector;

public class OpenSourceProject {
	private Vector<Element> elements;
	private Summary summary;
	
	public OpenSourceProject(String xml, String summary) {
		parseMetrics(xml);
		parseSummary(summary);
	}
	
	private void parseMetrics(String xml) {
		
	}
	
	private void parseSummary(String summary) {
		
	}
	
	public Vector<Element> getElements() {
		return elements;
	}
	
	public Summary getSummary() {
		return summary;		
	}
}

