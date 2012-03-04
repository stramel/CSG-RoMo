package edu.mst.cs206.sp2012;

public class Rule {
	private int Threshold;
	private String metric;
	private Element CodeElement;

	public Rule(int limit, String MetricName, Element CodeElement){
		Threshold = limit;
		metric = MetricName;
	}
	
    public boolean EvalutateMetric(){
    	
  }
    
    public String ToString(){
    	return "If " + metric + " is greater than " + threshold + " than add " + Element.ToString() + "to the summary"; 
    }
}
