package Ejercicio_6;

import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DOM {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		
				//Crear el builder
				DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				DocumentBuilder builder = factory.newDocumentBuilder();
				 
				
				
				System.out.println("Busca el archivo\n");
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setSelectedFile(new File("equipos.xml"));
				fileChooser.showOpenDialog(null);
				File file= fileChooser.getSelectedFile();
				
				Document documento = builder.parse(file);
				 
				//Normaliza la estructura del XML; Importante !!
				documento.getDocumentElement().normalize();
				 
				//Va al root
				Element root = documento.getDocumentElement();
				System.out.println(root.getNodeName());
				 
				//Coje todos los elementos
				NodeList nList = documento.getElementsByTagName("Maquina");
				System.out.println("============================");
				 
				for (int temp = 0; temp < nList.getLength(); temp++){
					Node node = nList.item(temp); 
					System.out.println("");    //Separador
					
					 if (node.getNodeType() == Node.ELEMENT_NODE){
						
						 	//Pinta los detalles del elemento
						    Element eElement = (Element) node;
						    
						    System.out.println("Elemento "+eElement.getNodeName());						    
						    System.out.println("\tNombre : "    + eElement.getAttribute("Nombre"));
						    System.out.println("Información:");
						    System.out.println("\tTipo : "  + eElement.getElementsByTagName("Tipo").item(0).getTextContent());						   
						    System.out.println("\tFabricante : "   + eElement.getElementsByTagName("Fabricante").item(0).getTextContent());			
						    System.out.println("Configuración:");		 
						    System.out.println("\tOS : "  + eElement.getElementsByTagName("OS").item(0).getTextContent());						   
						    System.out.println("\tIP : "   + eElement.getElementsByTagName("IP").item(0).getTextContent());	
					}		 
				}

	}	
}
