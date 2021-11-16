package Ejercicio_6;

import java.io.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.*;
import org.w3c.dom.*;

import javax.swing.*;

public class XPATH {

	public static void main(String[] args) throws XPathExpressionException {

		XPathExpression exp;
		XPath xpath;
		String salida = "";

		try {

			System.out.println("Busca el archivo\n");
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setSelectedFile(new File("equipos.xml"));
			fileChooser.showOpenDialog(null);
			File file= fileChooser.getSelectedFile();

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document document = db.parse(new FileInputStream(file));

			xpath = XPathFactory.newInstance().newXPath();
			exp = xpath.compile("/Hardware/Maquina/@Nombre");

			Object result = exp.evaluate(document, XPathConstants.NODESET);
			NodeList nodeList = (NodeList) result;
			
			System.out.println("Nombre de las máquinas:");

			for (int i = 0; i < nodeList.getLength(); i++) {
				salida = salida + "\n" + nodeList.item(i).getChildNodes().item(0).getNodeValue();
			}
			System.out.println(salida);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
