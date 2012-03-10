package edu.mst.cs206.sp2012;

import java.util.HashMap;

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
		this(name, type, new HashMap<String, Integer>(0));
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setType(Type type) {
		this.type = type;
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
	
	public void setMetrics(HashMap<String, Integer> metrics) {
		this.metrics = metrics;
	}
	
	public void setMetric(String metricID, Integer value) {
		this.metrics.put(metricID, value);
	}
	
	public HashMap<String, Integer> getMetrics() {
		return this.metrics;
	}
	
	public Integer MetricValue(String metricId) {
		return metrics.get(metricId);
	}
	
	public boolean MetricExists(String metricId) {
		return metrics.containsKey(metricId);
	}
}
