package edu.mst.cs206.sp2012;

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
	public static Vector<edu.mst.cs206.sp2012.Element> parseMetricsXML(String filename, String[] metricsToSave) {
		Vector<edu.mst.cs206.sp2012.Element> elements = new Vector<edu.mst.cs206.sp2012.Element>();
		
		try {
			File xmlFile = new File(filename);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFile);
			doc.getDocumentElement().normalize();
			
			NodeList classList = doc.getElementsByTagName("Type");
			
			for (int i = 0; i < classList.getLength(); i++)
			{
				Node classNode = classList.item(i);
				
				if (classNode.getNodeType() == Node.ELEMENT_NODE)
				{
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
			e.printStackTrace();
		}
			
		return elements;
	}

	
	public static Summary parseSummaryXML(String filename) {
	}
	
	
	private static HashMap<String, String> getElementMetrics(Node element, String[] metricsToSave)
	{
		Node metricsNode = getFirstChildByTagName("Metrics", element);
		if (metricsNode == null) { return new HashMap<String, String>(); }
		NodeList metricsNodeList = metricsNode.getChildNodes();
		
		HashMap<String, String> metrics = new HashMap<String, String>();
		
		for (int j = 0; j < metricsNodeList.getLength(); j++)
		{
			Node metricNode = metricsNodeList.item(j);
			
			if (metricNode.getNodeType() == Node.ELEMENT_NODE)
			{
				Element metricElement = (Element) metricNode;
				
				if (!metricElement.getAttribute("value").equals(""))
				{
					metrics.put(metricElement.getAttribute("id"), metricElement.getAttribute("value"));
				}
			}
		}
		
		return metrics;
	}
	
	
	private static String getTagValue(String tag, Element element)
	{
		NodeList nList = element.getElementsByTagName(tag).item(0).getChildNodes();
		Node node = (Node) nList.item(0);
		return node.getNodeValue();
	}
	
	private static Node getFirstChildByTagName(String tag, Node element)
	{
		element = element.getFirstChild();
		
		while (element.getNodeName() != tag && element.getNextSibling() != null)
		{
			element = element.getNextSibling();
		}
		
		if (element.getNodeName() == tag)
		{
			return element;
		}
		
		return null;
	}
	
	private static Node getNextSiblingByTagName(String tag, Node element)
	{
		do {
			element = element.getNextSibling();
		} while (element.getNodeName() != tag && element.getNextSibling() != null);
		
		if (element.getNodeName() == tag)
		{
			return element;
		}
		
		return null;
	}
}
