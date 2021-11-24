package Ejercicio_5_Boceto_sin_acabar;

import java.io.File;
import java.io.FileOutputStream;
import java.io.*;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.InputMismatchException;
import java.util.Scanner;
import javax.swing.JOptionPane;

//En proceso

public class Ejercicio_5_Main {

	public static void main(String[] args) {

		
		MetodosCRUD mc = new MetodosCRUD();

		try {

			Scanner sn = new Scanner(System.in);
			boolean salir = false;
			int opcion;

			while (!salir) {

				System.out.println("\n1. Agregar cliente");
				System.out.println("2. Mostrar clientes");
				System.out.println("3. Actualizar cliente");
				System.out.println("4. Eliminar cliente");
				System.out.println("5. Eliminar todos los datos");
				System.out.println("6. Recuperar el último elemento borrado");
				System.out.println("0. Salir");

				opcion = sn.nextInt();

				switch (opcion) {
				case 1:
					mc.agregarCliente();
					break;

				case 2:

					mc.mostrarClientes();
					break;

				case 3:
					
					break;

				case 4:
					
					break;
				case 5:
					break;
				case 6:
					
					break;
				case 0:
					salir = true;
					break;
				default:
					System.out.println("Solo números entre 0 y 6");
				}
			}


			sn.close();

		} catch (InputMismatchException ex2) {
			JOptionPane.showMessageDialog(null, "" + "\nIntroduce un número", "ADVERTENCIA!!!",
					JOptionPane.WARNING_MESSAGE);
		}

	}

}
