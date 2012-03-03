package edu.mst.cs206.sp2012;

import java.util.HashMap;
import java.util.*;

public class Element {
	public enum Type { CLASS, METHOD }
	
	private String name;
	private Type type;
	private HashMap<String, Integer> metrics;

	public Element(String name, Type type, HashMap<String, Integer> metrics) {
		this.name = name;
		this.type = type;
		this.metrics = metrics;
	}
	
	public Element(String name, Type type) {
		this(name, type, new HashMap(0));
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
		return metrics.get(metricId);
	}
	
	public boolean MetricExists(String metricId) {
		return metrics.containsKey(metricId);
	}
	
}
