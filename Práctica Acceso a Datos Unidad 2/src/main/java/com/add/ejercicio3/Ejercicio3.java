package com.add.ejercicio3;

import java.io.*;
import java.sql.*;
import java.util.*;

import javax.swing.JFileChooser;

public class Ejercicio3 {

	public static void main(String[] args) {

		try {
			Connection cn = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "root");

			Statement stm = cn.createStatement();
			stm.executeUpdate("CREATE SCHEMA IF NOT EXISTS AADU2;");

			PreparedStatement st = cn.prepareStatement("CREATE TABLE IF NOT EXISTS aadu2.usuarios (\r\n"
					+ "id MEDIUMINT NOT NULL AUTO_INCREMENT,\r\n" + "nombre varchar(100),\r\n"
					+ "apellido varchar(100),\r\n" + "mail varchar(100),\r\n" + "PRIMARY KEY (id));");
			st.execute();

			System.out.println("Busca tu archivo en primer lugar");
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setSelectedFile(new File("usuarios.csv"));
			fileChooser.showOpenDialog(null);
			File file = fileChooser.getSelectedFile();

			BufferedReader lector = new BufferedReader(new FileReader(file));

			String linea;
			cn.setAutoCommit(false);
			lector.readLine();

			while ((linea = lector.readLine()) != null) {

				StringTokenizer token = new StringTokenizer(linea, "\n");

				while (token.hasMoreTokens()) {

					PreparedStatement insercion = 
							cn.prepareStatement("INSERT INTO aadu2.usuarios (nombre,apellido,mail) VALUES (?,?,?)");

					insercion.setString(1, token.nextToken("|"));
					insercion.setString(2, token.nextToken("|"));
					insercion.setString(3, token.nextToken("|"));
					insercion.execute();
				}
			}

			cn.commit();
			lector.close();
			System.out.println("Se han añadido los campos a la base de datos correctamente");

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}

}
