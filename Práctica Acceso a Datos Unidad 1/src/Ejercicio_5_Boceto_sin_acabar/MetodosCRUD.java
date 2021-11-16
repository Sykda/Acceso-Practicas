package Ejercicio_5_Boceto_sin_acabar;

public class MetodosCRUD {

	private Cliente[] lista;
	

	public MetodosCRUD(int filas) {
		lista = new Cliente[filas];
	}

	public MetodosCRUD() {
		lista = new Cliente[5];
	}

	public void agregarCliente(Cliente c) {

		boolean encontrado = false;
		
		for (int i = 0; i < lista.length && !encontrado; i++) {
			 
				if (lista[i] == null) {
					lista[i]= c;
					encontrado = true;
				}
			
		}

		if (encontrado) {
			System.out.println("Cliente añadido");
		} else {
			System.out.println("No se ha podido añadir el cliente");
		}
	}

	public void mostrarClientes() {

		for (int i = 0; i < lista.length; i++) {
			
				if (lista[i] != null) {
					System.out.println("Fila: " + i +  ", " + lista[i]);

				
			}
		}
	}

	public void actualizarClientes() {

	}

	public void eliminarCliente(int id) {

		boolean encontrado = false;
		for (int i = 0; i < lista.length && !encontrado; i++) {
			
				if (lista[i] != null) {
					if (lista[i].getId() == id) {
						lista[i]= null;
						encontrado = true;
					}
				}
			
		}
		
		if (encontrado) {
			System.out.println("Cliente eliminado");
		} else {
			System.out.println("No existe el cliente");
		}
	}
}
