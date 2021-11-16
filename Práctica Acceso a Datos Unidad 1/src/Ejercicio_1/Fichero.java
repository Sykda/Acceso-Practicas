package Ejercicio_1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.InputMismatchException;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Fichero {

	public static void main(String[] args) throws IOException {

		String ruta = JOptionPane.showInputDialog(null, "Inserte la ruta del fichero", "Inserción",
				JOptionPane.INFORMATION_MESSAGE);

		File fichero = new File(ruta);

		try (FileReader fr = new FileReader(ruta)) {

			if (fichero.isFile()) {
				System.out.println(fichero.getName() + " Es un archivo\n");
			}

			System.out.println("El archivo tiene las siguientes características: ");
			System.out.println("Nombre: " + fichero.getName());
			System.out.println("Tamaño: " + fichero.getTotalSpace() + " bytes");
			System.out.println("Tiene los siguientes permisos:");

			if (fichero.canRead() == true) {
				System.out.println("\tEl archivo tiene permiso de lectura");

			} else {
				System.out.println("\tEl archivo NO tiene permiso de lectura");
			}

			if (fichero.canWrite() == true) {
				System.out.println("\tEl archivo tiene permiso de escritura");

			} else {
				System.out.println("\tEl archivo NO tiene permiso de escritura");
			}

			Scanner sc = new Scanner(System.in);

			boolean salir = false;

			while (!salir) {

				System.out
						.println("\nQue desea hacer?\n\n1. Borrar fichero\n2. Realizar copia de seguridad\n3. Salir\n");

				try {

					int opcion = sc.nextInt();

					switch (opcion) {

						case 1:
	
							int confirmado = JOptionPane.showConfirmDialog(null, "¿Seguro que quieres borrar?");
	
							if (JOptionPane.OK_OPTION == confirmado) {
								fr.close();
	
								boolean estatus = fichero.delete();
	
								if (!estatus) {
	
									System.out.println("Error, no se ha podido eliminar el  archivo");
	
								} else {
	
									System.out.println("Se ha eliminado el archivo");
	
								}
	
							} else
								System.out.println("Vale... no borro nada...");
	
							break;
	
						case 2:
	
							if (fichero != null) {
								try {
	
									Path Origen = Paths.get(fichero.getPath());
	
									Path Destino = Paths.get(JOptionPane.showInputDialog(null,
											"Inserte la ruta de guardado para el fichero _backup", "Copia",
											JOptionPane.INFORMATION_MESSAGE)+"\\"
											+ (fichero.getName().replaceFirst("[.]", "_backup.")));
	
									Files.copy(Origen, Destino, StandardCopyOption.REPLACE_EXISTING);
	
									JOptionPane.showMessageDialog(null,
											"El archivo fué copiado con éxito a la carpeta: " + Destino);
	
								} catch (IOException ex) {
									System.out.println(ex.getMessage());
								}
							}
	
							break;
							
						case 3:
							
							System.out.println("Hasta pronto!");
							sc.close();
							fr.close();
							salir = true;
							break;
							
						default:
							
							System.out.println("Selecciona una de las opciones");
					}
				} catch (InputMismatchException e) {
					System.out.println("Debes insertar un número");
					sc.next();
				}
			}

		} catch (FileNotFoundException e) {
			if (fichero.isDirectory()) {
				System.out.println("Es un directorio, por favor introduce la ruta del fichero");
			} else {
				System.out.println("No se ha encontrado el fichero");
			}

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}

}
