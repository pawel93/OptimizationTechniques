package TO.Util;

import TO.Model.Edge;
import TO.Model.Vertex;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class XmlReader 
{
		
	public static ArrayList<Vertex> getData() throws ParserConfigurationException, SAXException, IOException
	{
		ArrayList<Vertex> vertexList = new ArrayList<>();
		Vertex vertex;
		File fXmlFile = new File("data/kroA100.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
		doc.getDocumentElement().normalize();
		
		NodeList nList = doc.getElementsByTagName("vertex");
		
		for(int i = 0; i<nList.getLength(); i++)
		{
			Node nNode = nList.item(i);
			vertex = new Vertex(i);
			ArrayList<Edge> costList = new ArrayList<>();
			Element eElement = (Element) nNode;
			NodeList edgeList = eElement.getElementsByTagName("edge");
			for(int j = 0; j<edgeList.getLength(); j++)
			{
				Element edgeElement = (Element) edgeList.item(j);
				int tempCost = (int)Math.round( Double.valueOf(edgeElement.getAttribute("cost")) );
				int tempVertex = Integer.valueOf(edgeElement.getTextContent());
				Edge cost = new Edge(tempVertex, tempCost);
				costList.add(cost);
			}
			vertex.setEdges(costList);
			vertexList.add(vertex);
			
		}
		
		return vertexList;
	}
	
	

}
