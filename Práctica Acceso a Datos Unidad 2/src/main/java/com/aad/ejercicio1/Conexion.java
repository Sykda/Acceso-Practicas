package com.aad.ejercicio1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.add.ejercicio2.PoolConexiones;

public class Conexion {

	Connection cn;
	Statement st;
	ResultSet rs;
	Scanner scanner= new Scanner(System.in);

	public Conexion() {

		try {
					
			cn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
			st = cn.createStatement();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	void createDatabase() {

		try {
			//st.executeUpdate("DROP TABLE IF EXISTS aadu2.estudiantes;");
			st.executeUpdate("CREATE SCHEMA IF NOT EXISTS AADU2;");			
			st.executeUpdate("CREATE TABLE IF NOT EXISTS aadu2.estudiantes (\r\n"
					+ "id MEDIUMINT NOT NULL AUTO_INCREMENT,\r\n" + "nombre varchar(50),\r\n"
					+ "apellido varchar(50),\r\n" + "modulo varchar(50),\r\n" + "PRIMARY KEY (id));");

			rs = st.executeQuery("select * from AADU2.estudiantes;");

			while (rs.next()) {
				System.out.println("[" + rs.getInt("id") + "]");
				System.out.println("Nombre: " + rs.getString("nombre"));
				System.out.println("Apellido: " + rs.getString("apellido"));
				System.out.println("Modulo: " + rs.getString("modulo"));
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	void insert() {

		System.out.println("Introduce nombre: ");
		String nombre = scanner.next();
		System.out.println("Introduce apellido: ");
		String apellido = scanner.next();
		System.out.println("Introduce modulo: ");
		String modulo = scanner.next();

		try (PreparedStatement sInsert = cn
				.prepareStatement("INSERT INTO AADU2.estudiantes(Nombre,Apellido,Modulo) VALUES (?,?,?);")) {

			sInsert.setString(1, nombre);
			sInsert.setString(2, apellido);
			sInsert.setString(3, modulo);

			sInsert.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	void read() {

		try {

			rs = st.executeQuery("select * from AADU2.estudiantes;");
			while (rs.next()) {
				System.out.println("[" + rs.getInt("id") + "]");
				System.out.println("Nombre: " + rs.getString("nombre"));
				System.out.println("Apellido: " + rs.getString("apellido"));
				System.out.println("Modulo: " + rs.getString("modulo"));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	void update() {

		System.out.println("Introduce nombre: ");
		String nombre = scanner.next();
		System.out.println("Introduce apellido: ");
		String apellido = scanner.next();
		System.out.println("Introduce modulo: ");
		String modulo = scanner.next();
		System.out.println("Introduce id: ");
		int id = scanner.nextInt();

		String sentenciaSql = "UPDATE AADU2.estudiantes SET nombre = ?, apellido = ?,modulo = ? " + "WHERE id = ?";
		PreparedStatement sentencia = null;

		try {
			sentencia = cn.prepareStatement(sentenciaSql);
			sentencia.setString(1, nombre);
			sentencia.setString(2, apellido);
			sentencia.setString(3, modulo);
			sentencia.setInt(4, id);
			sentencia.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	void delete() {
		
		System.out.println("Introduce id: ");
		int id = scanner.nextInt();
		
		PreparedStatement sentencia = null;

		try {
			
			String sentenciaSql = "DELETE AADU2.estudiantes WHERE id = ?";
			sentencia = cn.prepareStatement(sentenciaSql);
			sentencia.setInt(1, id);
			sentencia.executeUpdate();
		
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}
}
