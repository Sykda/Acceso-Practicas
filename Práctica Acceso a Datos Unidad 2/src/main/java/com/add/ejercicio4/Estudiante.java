package com.add.ejercicio4;

public class Estudiante {

	String nombre, apellido, modulo;

	public Estudiante(String nombre, String apellido, String modulo) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.modulo = modulo;
	}

	@Override
	public String toString() {
		return "'"+nombre+"'" + "," + "'"+apellido+"'" + "," +"'"+modulo+"'";
	}

}
