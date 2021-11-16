package com.add.ejercicio6;

public class PostgreSQL extends Conexion {
	
	String ssl;

	public PostgreSQL(String bbdd, String url, String user, String pwd, String ssl) {
		super(bbdd, url, user, pwd);
		this.ssl=ssl;
	}


}
