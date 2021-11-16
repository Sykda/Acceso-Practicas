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
		
		Cliente cliente;
		MetodosCRUD metodos= new MetodosCRUD();

		try {

			File archivo = new File("C:\\Users\\Syk18\\Desktop\\Ejercicio_5.txt");

			if (archivo.createNewFile())
				System.out.println("El fichero se ha creado correctamente");
			else
				System.out.println("No ha podido ser creado el fichero");

			RandomAccessFile lector = new RandomAccessFile(archivo, "rw");
			BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));

			
			Scanner sn = new Scanner(System.in);
			boolean salir = false;
			int opcion;

			while (!salir) {
				

				System.out.println("\n1. Crear cliente");
				System.out.println("2. Mostrar clientes");
				System.out.println("3. Actualizar cliente");
				System.out.println("4. Eliminar cliente");
				System.out.println("5. Eliminar todos los datos");
				System.out.println("6. Recuperar el último elemento borrado");
				System.out.println("0. Salir");
				
				opcion = sn.nextInt();

				switch (opcion) {
				case 1:
					System.out.println("Al cliente se le asignará un ID automáticamente\n");
					Scanner opc1 = new Scanner(System.in);
					System.out.println("Introduce el nombre del cliente: ");
					String nombre = opc1.next();
					System.out.println("Introduce el apellido del cliente: ");
					String apellidos = opc1.next();
					

					cliente = new Cliente(nombre, apellidos);
					metodos.agregarCliente(cliente);
					break;
				
				case 2:
					
					metodos.mostrarClientes();
					break;

				case 3:
					metodos.actualizarClientes();
					System.out.println("¡El cliente se ha actualizado con éxito!");
					break;

				case 4:
					Scanner opcId = new Scanner(System.in);
					System.out.println("Introduzca el ID del cliente que quiere eliminar");
					int ID = opcId.nextInt();
					metodos.eliminarCliente(ID);
					break;
				case 5:
					bw.write("");
					bw.close();					
					break;
				case 6:
					JOptionPane.showMessageDialog(null, "" + "\nOpción no disponible, disculpe las molestias", "ADVERTENCIA!!!",
							JOptionPane.WARNING_MESSAGE);
					break;
				case 0:
					salir = true;
					break;
				default:
					System.out.println("Solo números entre 0 y 6");
				}
			}

			FileOutputStream fileOut = new FileOutputStream(archivo);
			//fileOut.write(inputBuffer.toString().getBytes());

			fileOut.close();
			lector.close();
			sn.close();


		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, "" + "\nNo se ha encontrado el archivo", "ADVERTENCIA!!!",
					JOptionPane.WARNING_MESSAGE);

		}catch(InputMismatchException ex2) {
			JOptionPane.showMessageDialog(null, "" + "\nIntroduce un número", "ADVERTENCIA!!!",
					JOptionPane.WARNING_MESSAGE);
		}

	}

}
