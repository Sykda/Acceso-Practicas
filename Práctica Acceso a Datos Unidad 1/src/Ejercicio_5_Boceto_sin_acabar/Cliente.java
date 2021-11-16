package Ejercicio_5_Boceto_sin_acabar;

public class Cliente {
	
	private static int ID_ACTUAL=1;

	private int id;
	private String nombre;
	private String apellidos;
	
	public Cliente(String nombre, String apellidos) {
		
		this.id=ID_ACTUAL++;
		
		this.nombre = nombre;
		this.apellidos = apellidos;
	}

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}


	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + "]";
	}
	
}
