package edu.mst.cs206.sp2012;

import javax.naming.InvalidNameException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.HashMap;
import java.util.Vector;

public class XMLParser {
	public static Vector<edu.mst.cs206.sp2012.Element> parseMetricsXML(String filename, String[] metricsToSave) throws InvalidNameException {
		Vector<edu.mst.cs206.sp2012.Element> elements = new Vector<edu.mst.cs206.sp2012.Element>();
		
		try {
			File xmlFile = new File(filename);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);
			doc.getDocumentElement().normalize();
			
			NodeList classList = doc.getElementsByTagName("Type");
			
			for (int i = 0; i < classList.getLength(); i++) {
				Node classNode = classList.item(i);
				
				if (classNode.getNodeType() == Node.ELEMENT_NODE) {
					String className = ((Element) classNode).getAttribute("name");

					// Confirm that we have a className
					if (className.equals("")) { continue; }
					
					// Save this class into elements
					elements.add(new edu.mst.cs206.sp2012.Element(className, edu.mst.cs206.sp2012.Element.Type.CLASS, getElementMetrics(classNode, metricsToSave)));
					
					// Save the methods of this class into elements too
					Node methodNode = getFirstChildByTagName("Method", classNode);
					if (methodNode == null) { continue; }
					
					do {
						elements.add(new edu.mst.cs206.sp2012.Element(className + "::" + ((Element) methodNode).getAttribute("name") , edu.mst.cs206.sp2012.Element.Type.METHOD, getElementMetrics(methodNode, metricsToSave)));
						methodNode = getNextSiblingByTagName("Method", methodNode);
					} while (methodNode != null);
				}
			}
		} catch (Exception e) {
		//	e.printStackTrace(); // TODO Change this to some actual error message!
			throw new InvalidNameException("Metrics Results");
		}
			
		return elements;
	}

	
	public static Summary parseSummaryXML(String filename) throws InvalidNameException {
		Vector<edu.mst.cs206.sp2012.Element> elements = new Vector<edu.mst.cs206.sp2012.Element>();
		
		try {
			File xmlFile = new File(filename);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);
			doc.getDocumentElement().normalize();
			
			// Grab the classes and add them to the summary
			NodeList classList = doc.getElementsByTagName("class");
			
			for (int i = 0; i < classList.getLength(); i++) {
				Node classNode = classList.item(i);
				
				if (classNode.getNodeType() == Node.ELEMENT_NODE) {				
					elements.add(new edu.mst.cs206.sp2012.Element(getNodeValue(classNode), edu.mst.cs206.sp2012.Element.Type.CLASS));
				}
			}
			
			// Grab the methods and add them to the summary
			NodeList methodList = doc.getElementsByTagName("method");
			
			for (int i = 0; i < methodList.getLength(); i++) {
				Node methodNode = methodList.item(i);
				
				if (methodNode.getNodeType() == Node.ELEMENT_NODE) {
					elements.add(new edu.mst.cs206.sp2012.Element(getNodeValue(methodNode), edu.mst.cs206.sp2012.Element.Type.METHOD));
				}
			}
		} catch (Exception e) {
		//	e.printStackTrace(); // TODO Change this to some actual error message!
			throw new InvalidNameException("Sample Summary");
		}
			
		return new Summary(elements);
	}
	
	
	private static HashMap<String, Integer> getElementMetrics(Node element, String[] metricsToSave)
	{
		Node metricsNode = getFirstChildByTagName("Metrics", element);
		if (metricsNode == null) { return new HashMap<String, Integer>(); }
		NodeList metricsNodeList = metricsNode.getChildNodes();
		
		HashMap<String, Integer> metrics = new HashMap<String, Integer>();
		
		for (int j = 0; j < metricsNodeList.getLength(); j++) {
			Node metricNode = metricsNodeList.item(j);
			
			if (metricNode.getNodeType() == Node.ELEMENT_NODE) {
				Element metricElement = (Element) metricNode;
				
				if (!metricElement.getAttribute("value").equals("")) {
					// Check that this metric is in the list of ones to save
					boolean save = false;
					
					for (int i = 0; i < metricsToSave.length; i++) {
						if (metricsToSave[i].equals(metricElement.getAttribute("id"))) {
							save = true;
							break;
						}
					}
					
					// Save it if we are supposed to
					if (save) {
						metrics.put(metricElement.getAttribute("id"), Integer.parseInt(metricElement.getAttribute("value")));
					}
				}
			}
		}
		
		return metrics;
	}
	
	// TODO Delete this block if not needed!
	private static String getNodeValue(Node node)
	{
		return node.getChildNodes().item(0).getNodeValue();
	}
	
	private static Node getFirstChildByTagName(String tag, Node element)
	{
		element = element.getFirstChild();
		
		while (element.getNodeName() != tag && element.getNextSibling() != null) {
			element = element.getNextSibling();
		}
		
		if (element.getNodeName() == tag) {
			return element;
		}
		
		return null;
	}
	
	private static Node getNextSiblingByTagName(String tag, Node element)
	{
		do {
			element = element.getNextSibling();
		} while (element.getNodeName() != tag && element.getNextSibling() != null);
		
		if (element.getNodeName() == tag) {
			return element;
		}
		
		return null;
	}
}
