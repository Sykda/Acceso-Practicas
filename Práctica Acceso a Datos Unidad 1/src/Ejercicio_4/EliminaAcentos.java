package Ejercicio_4;

import java.io.*;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class EliminaAcentos {

	public static void main(String[] args) {

		try {

			System.out.println("Busca un archivo para quitar las tildes\n");
			JFileChooser chooser = new JFileChooser();
			chooser.showOpenDialog(null);
			File archivo = chooser.getSelectedFile();
			RandomAccessFile lector = new RandomAccessFile(archivo, "rw");

			StringBuffer inputBuffer = new StringBuffer();

			String linea;

			while ((linea = lector.readLine()) != null) {

				linea = linea.replaceAll("á", "a")
							.replaceAll("É", "E").replaceAll("é", "e")
							.replaceAll("í", "i")
							.replaceAll("Ó", "O").replaceAll("ó", "o")
							.replaceAll("Ú", "U").replaceAll("ú", "u")
							.replaceAll("ñ", "n");
				
				inputBuffer.append(linea);
				inputBuffer.append("\n");
			}

			FileOutputStream fileOut = new FileOutputStream(archivo);
			fileOut.write(inputBuffer.toString().getBytes());			

			fileOut.close();
			lector.close();
			
			System.out.println("�El fichero se ha actualizado con �xito!");

		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, "" + "\nNo se ha encontrado el archivo", "ADVERTENCIA!!!",
					JOptionPane.WARNING_MESSAGE);

		}
	}
}
