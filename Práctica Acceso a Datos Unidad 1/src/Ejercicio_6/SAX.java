package Ejercicio_6;

import java.io.File;

import javax.swing.JFileChooser;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAX {

	public static void main(String[] args) {

		try {
			
			System.out.println("Busca el archivo\n");
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setSelectedFile(new File("equipos.xml"));
			fileChooser.showOpenDialog(null);
			File file= fileChooser.getSelectedFile();
			
			SAXParserFactory factoria = SAXParserFactory.newInstance();
			SAXParser SaxParser = factoria.newSAXParser();
			UserHandler manejador = new UserHandler();
			SaxParser.parse(file, manejador);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class UserHandler extends DefaultHandler {

	boolean Tipo = false;
	boolean Fabricante = false;
	boolean OS = false;
	boolean IP = false;

	@Override
	public void startElement(String uri, String localName, String nombreetiqueta, Attributes atributos)
			throws SAXException {

		if (nombreetiqueta.equalsIgnoreCase("Maquina")) {
			String Nombre = atributos.getValue("Nombre");
			System.out.println("Nombre: " + Nombre);
		}

		if (nombreetiqueta.equalsIgnoreCase("Tipo")) {
			Tipo = true;
		}
		if (nombreetiqueta.equalsIgnoreCase("Fabricante")) {
			Fabricante = true;
		}
		if (nombreetiqueta.equalsIgnoreCase("OS")) {
			OS = true;
		}
		if (nombreetiqueta.equalsIgnoreCase("IP")) {
			IP = true;
		}
	}

	@Override
	public void endElement(String uri, String localName, String nombreetiqueta) throws SAXException {
		if (nombreetiqueta.equalsIgnoreCase("Maquina")) {
			System.out.println("Fin del elemento: " + nombreetiqueta);
			System.out.println("  ");
		}
	}

	@Override
	public void characters(char ch[], int inicio, int tamaño) throws SAXException {
		if (Tipo) {
			System.out.println("Tipo: " + new String(ch, inicio, tamaño));
			Tipo = false;
		} else if (Fabricante) {
			System.out.println("Fabricante: " + new String(ch, inicio, tamaño));
			Fabricante = false;
		} else if (OS) {
			System.out.println("OS: " + new String(ch, inicio, tamaño));
			OS = false;
		} else if (IP) {
			System.out.println("IP: " + new String(ch, inicio, tamaño));
			IP = false;
		}

	}

}
