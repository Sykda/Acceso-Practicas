package com.add.ejercicio6;

public class Principal {

	public static void main(String[] args) {
		
		new PostgreSQL("PostrgreSQL", "jdbc:postgresql://localhost/?","postgres","root","false");
		
		new MySQL("MySQL", "jdbc:mysql://localhost:3306", "root", "root");
		
		
	}

}
