package com.add.ejercicio4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class EstudianteDAO implements EstudianteDAOInterfaz {

	Connection cn;
	ResultSet rs;
	Statement st;

	public EstudianteDAO() {

		try {

			cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "root");
			st = cn.createStatement();

			st.executeUpdate("CREATE DATABASE IF NOT EXISTS AADU2;");
			st.executeUpdate("CREATE TABLE IF NOT EXISTS aadu2.estudiantes (\r\n"
					+ "id MEDIUMINT NOT NULL AUTO_INCREMENT,\r\n" + "nombre varchar(50),\r\n"
					+ "apellido varchar(50),\r\n" + "modulo varchar(50),\r\n" + "PRIMARY KEY (id));");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public <T> void insert(T t) {

		try {

			cn.createStatement();

			PreparedStatement ps = cn
					.prepareStatement("INSERT INTO AADU2.estudiantes(nombre,apellido,modulo) VALUES (" + t + ");");

			ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public <T> boolean update(T t) {

		try {
			PreparedStatement ps = cn.prepareStatement("UPDATE AADU2.estudiantes set modulo="
					+ t.toString().split(",")[2] + " where nombre=" + t.toString().split(",")[0] + "and apellido=" + t.toString().split(",")[1]+";");

			ps.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	@Override
	public boolean deleteById(Integer id) {

		PreparedStatement sentencia = null;

		try {

			String sentenciaSql = "DELETE FROM AADU2.estudiantes WHERE id = " + id + ";";
			sentencia = cn.prepareStatement(sentenciaSql);
			sentencia.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	@Override
	public <T> T read(Integer id) {

		try {

			rs = st.executeQuery("select * from AADU2.estudiantes WHERE id='" + id + "';");
			while (rs.next()) {
				System.out.println("[" + rs.getInt("id") + "]");
				System.out.println("Nombre: " + rs.getString("nombre"));
				System.out.println("Apellido: " + rs.getString("apellido"));
				System.out.println("Modulo: " + rs.getString("modulo"));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	@Override
	public <T> List<T> findAll() {

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
		return null;

	}

	@Override
	public <T> List<T> findByName(String name) {

		try {

			rs = st.executeQuery("select * from AADU2.estudiantes WHERE nombre='" + name + "';");
			while (rs.next()) {
				System.out.println("[" + rs.getInt("id") + "]");
				System.out.println("Nombre: " + rs.getString("nombre"));
				System.out.println("Apellido: " + rs.getString("apellido"));
				System.out.println("Modulo: " + rs.getString("modulo"));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	@Override
	public boolean removeAll() {

		try {
			st.executeUpdate("TRUNCATE TABLE AADU2.estudiantes;");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return false;
	}
}