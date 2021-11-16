package com.add.ejercicio5;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Principal {

	public static void main(String[] args) {

		Conexion cn = new Conexion();
		Scanner sc = new Scanner(System.in);

		boolean salir = false;

		System.out.println("Bienvenido ¿Que desea hacer?");
		while (!salir) {
			
			System.out.println(
					"\n1. Crear una BBDD\n2. Crear las tablas\n"
					+ "3. Listado de todos los objetos guardados \n4. Introducir un nuevo usuario "
					+ "\n5. Buscar usuario por ID \n6. Eliminar usuario por ID"
					+ "\n7. Actualizar un usuario por ID\n8. Borrar todo"
					+ "\n9. Recuperar el último elemento borrado\n0. Salir");

			try {

				int opcion = sc.nextInt();

				switch (opcion) {
				
				case 0:
					System.out.println("Adiós");
					salir = true;
					break;

				case 1:
					cn.createDatabase();
					break;

				case 2:
					cn.createTable();
					break;

				case 3:
					cn.globalSelect();
					break;

				case 4:
					cn.insert();
					break;
					
				case 5:
					cn.searchByID();
					break;
				case 6:
					cn.delete();
					break;	
				
				case 7:
					cn.update();
					break;
				
				case 8:
					cn.deleteAll();
					break;
					
				case 9:
					cn.recoverLast();
					break;
				

				default:

					System.out.println("Selecciona una de las opciones");
				}
			} catch (InputMismatchException e) {
				System.out.println("Debes insertar un número");
				sc.next();
			}
		}
	}
}
