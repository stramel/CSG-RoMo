package edu.mst.cs206.sp2012;

import java.util.HashMap;

public class Element {
	public enum Type { CLASS, METHOD }
	
	private String name;
	private Type type;
	private HashMap metrics;

	public Element(String name, Type type, HashMap metrics) {
		// TODO Auto-generated constructor stub
	}
	
	public Element(String name, Type type) {
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
