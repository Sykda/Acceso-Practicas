package Ejercicio_5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.JFileChooser;

public class SalidaYError {

	public static void main(String[] args) {

		try {

			System.out.println("Busca el fichero '.bat'\n");
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setSelectedFile(new File("fichero.bat"));
			fileChooser.showOpenDialog(null);
			File file = fileChooser.getSelectedFile();

			System.out.println("Dónde quieres guardar la salida estándar?\n");
			fileChooser.setSelectedFile(new File("salida.txt"));
			fileChooser.showSaveDialog(null);
			File salidaEstandar = fileChooser.getSelectedFile();

			System.out.println("Dónde quieres guardar la salida de error?\n");
			fileChooser.setSelectedFile(new File("error.txt"));
			fileChooser.showSaveDialog(null);
			File salidaError = fileChooser.getSelectedFile();

			System.out.println("Procesando...");

			Process p = new ProcessBuilder(file.getPath()).start();
			BufferedReader output = new BufferedReader(new InputStreamReader(p.getInputStream()));
			BufferedReader error = new BufferedReader(new InputStreamReader(p.getErrorStream()));

			cogeSalida(p, output, salidaEstandar);
			cogeError(p, error, salidaError);

		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}

		System.out.println("Proceso finalizado!");

	}

	private static void cogeSalida(Process p, BufferedReader b, File archivo) throws IOException {

		FileWriter fw = new FileWriter(archivo);
		fw.write("\n\nSALIDA ESTÁNDAR\n\n");

		String linea;

		while ((linea = b.readLine()) != null) {
			fw.write("\t" + linea);
			fw.write("\n");
		}

		fw.close();

	}

	private static void cogeError(Process p, BufferedReader b, File archivo) throws IOException {

		FileWriter fw = new FileWriter(archivo);
		fw.write("\n\nSALIDA ERROR\n\n");

		String linea;

		while ((linea = b.readLine()) != null) {
			fw.write("\t" + linea);
			fw.write("\n");
		}

		fw.close();

	}

}
