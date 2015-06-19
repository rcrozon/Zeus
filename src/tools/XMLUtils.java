package tools;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import utilEnum.ActionEnum;

import commands.Command;

public class XMLUtils {

	public static XMLUtils xmlUtils = null;

	public static XMLUtils getInstance() {
		if (xmlUtils == null) {
			xmlUtils = new XMLUtils();
		}
		return xmlUtils;
	}
	
	public static Command getCommand(File xmlFile, String sCommand) {

		Command command = new Command();
		String[] splitedCommand = sCommand.split(" ");
		
		if (splitedCommand.length > 0){
			try {

				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document document = dBuilder.parse(xmlFile);

				document.getDocumentElement().normalize();

				System.out.println("Root element :"
						+ document.getDocumentElement().getNodeName());
				
				System.out.println("----------------------------");
				int i = 0;
				NodeList nList = null;
				Element element = document.getDocumentElement(); 
				
				for(i = 0; i < splitedCommand.length; i++){
					nList = element.getElementsByTagName(splitedCommand[i]);
					if (nList != null){
						for (int nodeIndex = 0; nodeIndex < nList.getLength(); nodeIndex++) {
							
							Node nNode = nList.item(nodeIndex);
							System.out.println("\nCurrent Element :" + nNode.getNodeName());

							if (nNode.getNodeType() == Node.ELEMENT_NODE) {
								Element eElement = (Element) nNode;
								if (eElement.getTextContent() != null && !eElement.hasChildNodes()){ 
									System.out.println("action type : " + eElement.getTextContent().trim());
									ActionEnum actionType = ActionEnum.valueOf(eElement.getTextContent().trim());
									command.setActionType(actionType);
									command.setsCommand(sCommand.substring(sCommand.lastIndexOf(splitedCommand[i]))); 
								}else{
									element = eElement;		
								}
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return command;
	}
}
