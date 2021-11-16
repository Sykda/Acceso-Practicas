package com.aad.ejercicio1;

import java.util.*;


public class Principal {

	public static void main(String[] args) {

		Conexion connection = new Conexion();
		connection.createDatabase();

		boolean salir = false;
		Scanner sc = new Scanner(System.in);

		while (!salir) {

			System.out.println(
					"\nQue desea hacer?\n\n1. Anadir un alumno\n2. Ver la tabla\n3. Actualizar registro\n4. Borrar registro\n5. Salir");

			try {

				int opcion = sc.nextInt();

				switch (opcion) {

				case 1:

					connection.insert();
					break;

				case 2:
					connection.read();
					break;

				case 3:
					connection.update();
					break;
					
				case 4:
					connection.delete();
					break;
					
				case 5:
					System.out.println("Adiós");
					salir=true;
					break;

				default:

					System.out.println("Selecciona una de las opciones");
				}
			
			
			} catch (InputMismatchException e) {
				System.out.println("Debes insertar un número");
				sc.next();
			}
		}
		sc.close();
	}
}
