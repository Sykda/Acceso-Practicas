package Ejercicio_2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.time.LocalDate;
import java.util.*;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class CuentaLetras {

	public static void main(String[] args) {

		try {
			// Busco el archivo y lo meto en un RAF
			System.out.println("Busca tu archivo en primer lugar");
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setSelectedFile(new File("letras.txt"));
			fileChooser.showOpenDialog(null);
			File file = fileChooser.getSelectedFile();

			RandomAccessFile lector = new RandomAccessFile(file, "r");

			// Pido al usuario caracteres, y los separo en un array
			Scanner sc = new Scanner(System.in);
			System.out.println("\nIntroduce caraceteres para contar en el archivo:\n");
			String opcion = sc.nextLine();
			char[] letras = opcion.toCharArray();

			// Muestro los valores de los caracteres elegidos en pantalla solo para
			// asegurarme
			for (int i = 0; i < letras.length; i++) {
				System.out.println("Has seleccionado el caracter: " + letras[i]);
			}
			System.out.println("");

			// Creo la salida para elegir directorio.
			System.out.println("Dónde quieres guardar la salida?\n");
			fileChooser.setSelectedFile(new File("letras.txt"));
			fileChooser.showSaveDialog(null);
			File ficheroSalida = fileChooser.getSelectedFile();
			FileWriter escritor = new FileWriter(ficheroSalida);

			escritor.write("En el fichero " + file.getName() + " hay:\n");

			// Por cada elemento del array leo el documento y comparo las letras
			String lineaActual;
			int contador = 0;

			for (char i : letras) {
				while ((lineaActual = lector.readLine()) != null) {
					for (int x = 0; x < lineaActual.length(); x++) {
						if (lineaActual.charAt(x) == i) {
							contador++;
						}
					}
				}

				// Escribo la letra y el contador, vuelvo al principio y reseteo el contador
				escritor.write("\tDel caracter " + "'" + i + "'" + " hay: " + contador + "\n");
				lector.seek(0);
				contador = 0;

			}

			escritor.write("\nFecha: " + LocalDate.now());

			sc.close();
			lector.close();
			escritor.close();

			System.out.println("¡El archivo se ha creado con éxito!\n");

		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, "" + "\nNo se ha encontrado el archivo o ruta", "ADVERTENCIA!!!",
					JOptionPane.WARNING_MESSAGE);
		}
	}
}
