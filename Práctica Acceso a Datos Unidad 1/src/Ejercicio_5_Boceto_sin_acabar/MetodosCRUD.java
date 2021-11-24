package Ejercicio_5_Boceto_sin_acabar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class MetodosCRUD {

	File archivo = new File("C:\\Users\\admin\\Desktop\\a.txt");
	RandomAccessFile lector = null;
	String linea;

	Scanner sn = new Scanner(System.in);

	{
		try {
			lector = new RandomAccessFile(archivo, "rw");

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public void agregarCliente() {

		System.out.println("Introduce el nombre del cliente: ");
		String nombre = sn.next();
		try {
			FileWriter escritor = new FileWriter(archivo);
			
			while ((linea = lector.readLine()) != null) {
				if (linea.isEmpty()) {
					escritor.write(nombre);
					escritor.close();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void mostrarClientes() {

		String linea;
		try {
			while ((linea = lector.readLine()) != null) {
				System.out.println(linea);
				System.out.println("\n");
				lector.readLine();

			}
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	public void actualizarClientes() {

	}

	public void eliminarCliente(int id) {

	}
}
