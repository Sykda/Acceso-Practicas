package com.add.ejercicio6;

import java.sql.DriverManager;
import java.sql.SQLException;


abstract class Conexion {

	public Conexion(String bbdd, String url, String user, String pwd) {

		try {

			DriverManager.getConnection(url,user, pwd);
			System.out.println("Se ha conectado correctamente : "+bbdd);

		} catch (SQLException e) {
			System.out.println(bbdd+" Tiene un error de conexión!\n\n"+e.getMessage());
		}
	}
}
