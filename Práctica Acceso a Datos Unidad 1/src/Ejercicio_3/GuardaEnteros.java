package Ejercicio_3;

import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.InputMismatchException;
import java.util.Scanner;
import javax.swing.JFileChooser;

public class GuardaEnteros {

	public static void main(String[] args) {

		long tamaño;
		int posicion;

		Scanner sc = new Scanner(System.in);
		RandomAccessFile lector = null;

		try {

			System.out.println("Busca tu archivo en primer lugar\n");
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setSelectedFile(new File("enteros.dat"));
			fileChooser.showOpenDialog(null);
			File file = fileChooser.getSelectedFile();
			lector = new RandomAccessFile(file, "rw");

			tamaño = file.length() / 4; // Tamaño en bytes de enteros

			System.out.print("Introduce un número entero para añadir al fichero: \n");
			int numero = sc.nextInt();

			do {

				System.out.print(
						"Introduzca la posición donde quiere almacenarlo. Recuerde, sólo son válidas las posiciones ya ocupadas y la última, entre 1 y "
								+ (tamaño + 1) + ":\n");
				posicion = sc.nextInt();
			} while (posicion < 1 || posicion > tamaño + 1);

			posicion--;

			lector.seek(posicion * 4); // Busca la posición
			lector.writeInt(numero);

			sc.close();

		} catch (InputMismatchException ex) {
			System.out.println("Debes introducir enteros");

		} catch (FileNotFoundException ex) {
			ex.printStackTrace();

		} catch (IOException ex) { //
			ex.printStackTrace();

		}

		try {

			lector.seek(0L);

			while (true) {
				int n = lector.readInt();
				long i = lector.getFilePointer() / 4;
				System.out.println("[" + i + "] " + n);
			}

		} catch (EOFException ex) {
			System.out.println("Fin de fichero");

		} catch (IOException ex) {
			System.out.println(ex.getMessage());

		}
	}
}
