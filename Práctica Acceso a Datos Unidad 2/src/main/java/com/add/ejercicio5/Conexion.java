package com.add.ejercicio5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Conexion {

	Connection cn;
	Statement st;
	ResultSet rs;
	Scanner scanner = new Scanner(System.in);
	String backup;

	public Conexion() {

		try {

			cn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "root");
			st = cn.createStatement();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	void createDatabase() {

		try {
			st.executeUpdate("CREATE DATABASE IF NOT EXISTS ejercicio5;");

			System.out.println("Base de datos ejercicio5 creada correctamente");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	void createTable() {

		try {

			st.executeUpdate("CREATE TABLE IF NOT EXISTS ejercicio5.usuarios "
					+ "(id_usuario INT NOT NULL AUTO_INCREMENT, " + "nombre varchar(50) NOT NULL, "
					+ "apellido varchar(50), " + "ciudad varchar(50)," + "PRIMARY KEY (id_usuario));");

			System.out.println("Tabla usuarios creada correctamente");

			st.executeUpdate("CREATE TABLE IF NOT EXISTS ejercicio5.cuentas "
					+ "(id_cuenta MEDIUMINT NOT NULL AUTO_INCREMENT, " + "nick varchar(50), "
					+ "suscripcion varchar(50), " + "cuenta varchar(50)," + "id_usuario INT, "
					+ "PRIMARY KEY (id_cuenta), "
					+ "CONSTRAINT CHK_suscripcion CHECK (suscripcion='Mensual' OR suscripcion='Anual'), "
					+ "CONSTRAINT CHK_cuenta CHECK (cuenta='Básica' OR cuenta='Basica' OR cuenta='Premium'), "
					+ "CONSTRAINT fk_id_usuario FOREIGN KEY (id_usuario) REFERENCES ejercicio5.usuarios (id_usuario) on update cascade on delete cascade);");

			System.out.println("Tabla cuentas creada correctamente");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			scanner.next();
		}
	}

	void globalSelect() {
		try {

			System.out.println("Información del cliente \n");
			rs = st.executeQuery("select * from ejercicio5.usuarios;");
			while (rs.next()) {
				System.out.println("[" + rs.getInt("id_usuario") + "]");
				System.out.println("Nombre: " + rs.getString("nombre"));
				System.out.println("Apellido: " + rs.getString("apellido"));
				System.out.println("Ciudad: " + rs.getString("ciudad"));

			}
			System.out.println("\nInformación de las cuentas \n");
			rs = st.executeQuery("select * from ejercicio5.cuentas;");
			while (rs.next()) {
				System.out.println("[" + rs.getInt("id_cuenta") + "]");
				System.out.println("Cuenta: " + rs.getString("cuenta"));
				System.out.println("Nick: " + rs.getString("nick"));
				System.out.println("Suscripción: " + rs.getString("suscripcion"));
				System.out.println("ID de cliente: " + rs.getString("id_usuario"));
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
		System.out.println("Introduce tu ciudad: ");
		String ciudad = scanner.next();
		System.out.println("¿Que tipo de cuenta tiene? Básica/Premium ");
		String cuenta = scanner.next();
		System.out.println("Introduce un nick: ");
		String nick = scanner.next();
		System.out.println("¿Que tipo de suscripción tiene? Menusal/Anual");
		String suscripcion = scanner.next();

		try {
			cn.setAutoCommit(false);

			st.executeUpdate("INSERT INTO ejercicio5.usuarios(nombre,apellido,ciudad) VALUES ('" + nombre + "','"
					+ apellido + "','" + ciudad + "');", Statement.RETURN_GENERATED_KEYS);

			int autoIncKeyFromApi = -1;

			rs = st.getGeneratedKeys();

			if (rs.next()) {
				autoIncKeyFromApi = rs.getInt(1);
			}

			st.executeUpdate("INSERT INTO ejercicio5.cuentas(nick,cuenta,suscripcion,id_usuario) VALUES ('" + nick
					+ "','" + cuenta + "','" + suscripcion + "','" + autoIncKeyFromApi + "');");

			cn.commit();
			cn.setAutoCommit(true);

		} catch (SQLException e) {
			System.out.println("Tiene que elegir un tipo de cuenta o suscripción");
			try {
				cn.rollback();
			} catch (Exception er) {
				System.err.println("ERROR haciendo ROLLBACK: " + er.getMessage());
			}
		}
	}

	void searchByID() {

		System.out.println("Introduce la ID del usuario: ");
		int id = scanner.nextInt();

		try {

			rs = st.executeQuery("select * from ejercicio5.usuarios " + "join ejercicio5.cuentas "
					+ "on usuarios.id_usuario = cuentas.id_usuario " + "where usuarios.id_usuario=" + id + ";");

			while (rs.next()) {
				System.out.println("[" + rs.getInt("id_usuario") + "]");
				System.out.println("Nombre: " + rs.getString("nombre"));
				System.out.println("Apellido: " + rs.getString("apellido"));
				System.out.println("Ciudad: " + rs.getString("ciudad"));
				System.out.println("Cuenta: " + rs.getString("cuenta"));
				System.out.println("Nick: " + rs.getString("nick"));
				System.out.println("Suscripción: " + rs.getString("suscripcion"));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	void delete() {
		System.out.println("Introduce la ID del usuario que quiere eliminar: ");
		int id = scanner.nextInt();

		try {
			rs = st.executeQuery("select * from ejercicio5.usuarios " + "join ejercicio5.cuentas "
					+ "on usuarios.id_usuario = cuentas.id_usuario " + "where usuarios.id_usuario=" + id + ";");

			while (rs.next()) {
				backup += ("," + rs.getString("nombre") + ",");
				backup += (rs.getString("apellido") + ",");
				backup += (rs.getString("ciudad") + ",");
				backup += (rs.getString("cuenta") + ",");
				backup += (rs.getString("nick") + ",");
				backup += (rs.getString("suscripcion"));
			}

			st.executeUpdate("DELETE FROM ejercicio5.usuarios WHERE usuarios.id_usuario = " + id + ";");
			System.out.println("Se ha borrado correctamente");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	void update() {

		System.out.println("Introduce ID que quieres modificar: ");
		int id = scanner.nextInt();
		System.out.println("Introduce nuevo nombre: ");
		String nombre = scanner.next();
		System.out.println("Introduce nuevo apellido: ");
		String apellido = scanner.next();
		System.out.println("Introduce nuevo apellido: ");
		String ciudad = scanner.next();

		System.out.println("Introduce nuevo nick: ");
		String nick = scanner.next();
		System.out.println("Introduce el tipo de cuenta: Básica/Premium ");
		String cuenta = scanner.next();
		System.out.println("Introduce el tipo de suscripción: Mensual/Anual ");
		String suscripcion = scanner.next();

		String sentenciaSql = "UPDATE ejercicio5.usuarios SET nombre = ?, apellido = ?,ciudad = ? "
				+ "WHERE id_usuario = ?";
		PreparedStatement sentencia = null;

		String sentenciaSql2 = "UPDATE ejercicio5.cuentas SET nick = ?, suscripcion = ?, cuenta = ? "
				+ "WHERE id_usuario = ?";
		PreparedStatement sentencia2 = null;

		try {
			sentencia = cn.prepareStatement(sentenciaSql);
			sentencia.setString(1, nombre);
			sentencia.setString(2, apellido);
			sentencia.setString(3, ciudad);
			sentencia.setInt(4, id);
			sentencia.executeUpdate();

			sentencia2 = cn.prepareStatement(sentenciaSql2);
			sentencia2.setString(1, nick);
			sentencia2.setString(2, suscripcion);
			sentencia2.setString(3, cuenta);
			sentencia2.setInt(4, id);
			sentencia2.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	void deleteAll() {
		try {

			st.executeUpdate("DROP DATABASE ejercicio5;");

			System.out.println("Se ha borrado todo con éxito");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	void recoverLast() {

		try {
			String nombre = backup.split(",")[1];
			String apellido = backup.split(",")[2];
			String ciudad = backup.split(",")[3];
			String cuenta = backup.split(",")[4];
			String nick = backup.split(",")[5];
			String suscripcion = backup.split(",")[6];
			// null,nombre,apellido,ciudad,basica,nick,mensual

			st.executeUpdate("INSERT INTO ejercicio5.usuarios(nombre,apellido,ciudad) VALUES ('" + nombre + "','"
					+ apellido + "','" + ciudad + "');", Statement.RETURN_GENERATED_KEYS);

			int autoIncKeyFromApi = -1;

			rs = st.getGeneratedKeys();

			if (rs.next()) {
				autoIncKeyFromApi = rs.getInt(1);
			}

			st.executeUpdate("INSERT INTO ejercicio5.cuentas(nick,cuenta,suscripcion,id_usuario) VALUES ('" + nick
					+ "','" + cuenta + "','" + suscripcion + "','" + autoIncKeyFromApi + "');");

			System.out.println("Se ha recuperado el último registro borrado");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}
}
