package Ejercicio_1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.swing.JFileChooser;

public class Ping {

	public static void main(String[] args) {

		Scanner sn = new Scanner(System.in);

		try {
			
			
			System.out.println("Dónde quieres guardar la salida?\n");
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setSelectedFile(new File("ping.txt"));
			fileChooser.showSaveDialog(null);
			File file= fileChooser.getSelectedFile();
			 

			if (!file.exists()) {
				System.out.println("\t ¡El archivo se ha creado!\n");
			} else {
				System.out.println("\t ¡El archivo se ha sobreescrito!\n");
			}

			System.out.println("Introduce un numero de peticiones:\n");
			int opcion = sn.nextInt();
			System.out.println("Llamando...\n");

			pingPB(file, opcion);
			pingRT(file, opcion);

			System.out.println("¡El proceso ha terminado!");
			System.out.println("Verifica tu archivo y ¡que pases un buen día!");
			
			sn.close();

		} catch (InputMismatchException ex) {
			System.out.println("Has introducido una letra!\nEl programa ha parado.\nIntroduce un número la próxima vez =D.");
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
	}

	public static void pingPB(File archivo, int intentos) throws IOException {

		ProcessBuilder ping = new ProcessBuilder("ping", "-n", "" + intentos, "www.google.com");
		BufferedReader br = new BufferedReader(new InputStreamReader(ping.start().getInputStream()));

		FileWriter fw = new FileWriter(archivo, true);
		fw.write("\n\nPINGS CON PROCESSBUILDER\n");

		String linea;

		while ((linea = br.readLine()) != null) {
			fw.write("\t" + linea);
			fw.write("\n");
		}

		fw.close();

	}

	public static void pingRT(File archivo, int intentos) throws IOException {

		Process ping = Runtime.getRuntime().exec("ping -n " + intentos + " www.google.com");
		BufferedReader br = new BufferedReader(new InputStreamReader(ping.getInputStream()));

		FileWriter fw = new FileWriter(archivo, true);
		fw.write("\n\nPINGS CON RUNTIME\n");

		String linea;

		while ((linea = br.readLine()) != null) {
			fw.write("\t" + linea);
			fw.write("\n");
		}

		fw.close();
	}
}