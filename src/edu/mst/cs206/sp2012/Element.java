package edu.mst.cs206.sp2012;

import java.util.Vector;

public class Element {
	public enum Type { CLASS, METHOD }
	
	private String name;
	private final Type type;
	private Vector metrics;
	
	public Element(String string, Type type, Object object) {
		// TODO Auto-generated constructor stub
	}
	
	public String getName() {
		return name;
	}
	
	public Type getType() {
		return type;
	}
	
	public boolean isClass() {
		return (type == Type.CLASS);
	}
	
	public boolean isMethod() {
		return (type == Type.METHOD);		
	}
	
	public int MetricValue(String metricId) {
		
	}
	
	public boolean MetricExists(String metricId) {
		
	}
	
}
