package com.add.ejercicio2;

import java.sql.SQLException;

public class EjemploConexion {

	public static void main(String[] args) {

		try {
			PoolConexiones.getConexion();
			System.out.println("H2 se ha conectado correctamente\n");

		} catch (SQLException e) {
			System.out.println("H2 tiene un error de conexión!\n\n" + e.getMessage());
		}
	}
}