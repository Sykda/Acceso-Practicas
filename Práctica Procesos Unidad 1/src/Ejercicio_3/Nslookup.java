package Ejercicio_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Nslookup {

	public static void main(String[] args) {

		try {

			Scanner sn = new Scanner(System.in);
			System.out.println("Introduce dominio");
			String dominio = sn.next();

			ProcessBuilder pb = new ProcessBuilder("nslookup", dominio);
			BufferedReader br = new BufferedReader(new InputStreamReader(pb.start().getInputStream()));

			String linea;

			while ((linea = br.readLine()) != null) {
				System.out.println(linea);
			}

			sn.close();
			br.close();

		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}

	}

}
