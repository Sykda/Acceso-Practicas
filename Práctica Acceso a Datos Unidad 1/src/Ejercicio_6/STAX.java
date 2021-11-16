package Ejercicio_6;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Iterator;

import javax.swing.JFileChooser;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class STAX {

	public static void main(String[] args) {

		boolean tipo = false;
		boolean fabricante = false;
		boolean os = false;
		boolean ip = false;

		try {
			
			System.out.println("Busca el archivo\n");
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setSelectedFile(new File("equipos.xml"));
			fileChooser.showOpenDialog(null);
			File file= fileChooser.getSelectedFile();
			
			XMLInputFactory factory = XMLInputFactory.newInstance();
			XMLEventReader eventReader = factory.createXMLEventReader(new FileReader(file));

			while (eventReader.hasNext()) {
				XMLEvent event = eventReader.nextEvent();

				switch (event.getEventType()) {

				case XMLStreamConstants.START_ELEMENT:
					StartElement startElement = event.asStartElement();
					String qName = startElement.getName().getLocalPart();

					if (qName.equalsIgnoreCase("Maquina")) {
						System.out.println("Elemento : Maquina");
						Iterator<Attribute> attributes = startElement.getAttributes();
						String nombre = attributes.next().getValue();
						System.out.println("\tNombre : " + nombre);
					} else if (qName.equalsIgnoreCase("Tipo")) {
						tipo = true;
					} else if (qName.equalsIgnoreCase("Fabricante")) {
						fabricante = true;
					} else if (qName.equalsIgnoreCase("OS")) {
						os = true;
					} else if (qName.equalsIgnoreCase("IP")) {
						ip = true;
					}
					break;

				case XMLStreamConstants.CHARACTERS:
					Characters characters = event.asCharacters();
					if (tipo) {
						System.out.println("\tTipo: " + characters.getData());
						tipo = false;
					}
					if (fabricante) {
						System.out.println("\tFabricante: " + characters.getData());
						fabricante = false;
					}
					if (os) {
						System.out.println("\tOS: " + characters.getData());
						os = false;
					}
					if (ip) {
						System.out.println("\tIP: " + characters.getData()+"\n");
						ip = false;
					}
					break;

				case XMLStreamConstants.END_ELEMENT:
					EndElement endElement = event.asEndElement();

					if (endElement.getName().getLocalPart().equalsIgnoreCase("student")) {
						System.out.println("End Element : student");
						System.out.println();
					}
					break;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (XMLStreamException e) {
			e.printStackTrace();
		}
	}

}
