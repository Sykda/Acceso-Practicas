package com.add.ejercicio2;

import java.sql.*;

public class EjemploConexion {

	public static void main(String[] args) {

		PoolConexiones pc=new PoolConexiones();
				
		try {
			pc.getConexion();
			System.out.println("H2 se ha conectado correctamente\n");
			
		} catch (SQLException e) {
			System.out.println("H2 tiene un error de conexión!\n\n"+e.getMessage());
		}
	}
}