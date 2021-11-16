package Ejercicio_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BuscaArchivos {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		boolean salir = false;
		while (!salir) {

			System.out.println("\nEn que sistema se encuentra?\n\n1. Windows\n2. Linux\n");

			try {

				int opcion = sc.nextInt();

				switch (opcion) {

				case 1:

					System.out.println("Inserte la ruta a evaluar");
					String rutaWindows = sc.next();

					ProcessBuilder builderWindows = new ProcessBuilder("cmd", "/C", "dir", rutaWindows);
					BufferedReader brWindows = new BufferedReader(
							new InputStreamReader(builderWindows.start().getInputStream()));

					mostrar(brWindows);

					salir = true;
					break;

				case 2:
					System.out.println("Inserte la ruta a evaluar");
					String rutaLinux = sc.next();

					ProcessBuilder builderLinux = new ProcessBuilder("gnome-terminal", "ls", rutaLinux);
					BufferedReader brLinux = new BufferedReader(
							new InputStreamReader(builderLinux.start().getInputStream()));

					mostrar(brLinux);
					salir = true;
					break;

				default:

					System.out.println("Selecciona una de las opciones");
				}
			} catch (InputMismatchException e) {
				System.out.println("Debes insertar un número");
				sc.next();
			} catch (IOException ex) {
				System.out.println(ex.getMessage());
			}
		}

		sc.close();

	}

	public static void mostrar(BufferedReader br) {

		try {

			String linea;

			while ((linea = br.readLine()) != null) {
				System.out.println("\t" + linea);

			}

			br.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

}
