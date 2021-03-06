package edu.mst.cs206.sp2012;

import java.util.HashMap;
/**This class will build an Element*/
public class Element {
	public enum Type { CLASS, METHOD }
	
	private String name;
	private Type type;
	private HashMap<String, Integer> metrics;

	/**public Element(String name, Type type, HashMap<String,Integer> metrics)
	 * This constructor will pass in a name, type, and metrics setting them equal to each other in this class.
	 * @param name of type String
	 * @param type of type Type
	 * @param metrics of type HashMap
	 */
	public Element(String name, Type type, HashMap<String, Integer> metrics) {
		this.name = name;
		this.type = type;
		this.metrics = metrics;
	}
	
	/**public Element(String name, Type type)
	 * This constructor will create a new HashMap to be used.
	 * @param name of type String
	 * @param type of type Type
	 */
	public Element(String name, Type type) {
		this(name, type, new HashMap<String, Integer>(0));
	}
	
	/**private void setName(String name)
	 * Sets Name of Element
	 * @param name of type String
	 */
	public String getName() {
		return name;
	}
	
	/**public Type getType()
	 * Returns the type.
	 * @return type
	 */
	public Type getType() {
		return type;
	}
	
	/**public boolean isClass()
	 * Returns the type of type CLASS
	 * @return type if true when class
	 */
	public boolean isClass() {
		return (type == Type.CLASS);
	}
	
	/**public boolean isMethod()
	 * Returns the type of type METHOD
	 * @return type if true when method
	 */
	public boolean isMethod() {
		return (type == Type.METHOD);		
	}
	
	/**public HashMap<String, Integer> getMetrics()
	 * Returns metrics.
	 * @return metrics
	 */
	public HashMap<String, Integer> getMetrics() {
		return this.metrics;
	}
	
	/**public Integer MetricValue(String metricId)
	 * Returns the metrics Id.
	 * @param metricId of type String
	 * @return metrics
	 */
	public Integer MetricValue(String metricId) {
		return metrics.get(metricId);
	}
	
	/**public boolean MetricExists(String metricId)
	 * Returns the metrics true value if this map contains a mapping for the specified key of the metricId.
	 * @param metricId of type String
	 * @return metrics
	 */
	public boolean MetricExists(String metricId) {
		return metrics.containsKey(metricId);
	}
}
