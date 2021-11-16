package com.add.ejercicio4;

public class PatronDAOejercicio2 {

	public static void main(String[] args) {

		Estudiante est1 = new Estudiante("Julia", "García", "DAM");
		Estudiante est2 = new Estudiante("Javier", "Fuentes", "DAW");
		Estudiante est3 = new Estudiante("Julia", "Rodríguez", "ASIR");
		Estudiante est4 = new Estudiante("Jacobo", "Ruiz", "SMR");
		Estudiante est5 = new Estudiante("Jonh", "Romero", "TAE");
		Estudiante actualizacion = new Estudiante("Javier", "Fuentes", "DAM");
		

		EstudianteDAO ed = new EstudianteDAO();
		
		
		ed.insert(est1);
		ed.insert(est2);
		ed.insert(est3);
		ed.insert(est4);
		ed.findAll();
		System.out.println("");
		
		ed.update(actualizacion);
		ed.read(2);
		System.out.println("");
		
		ed.deleteById(2);
		ed.findAll();
		ed.insert(est5);
		ed.read(5);
		ed.removeAll();
		ed.findAll();
	}
	
	/*Ventajas de usar el patrón de diseño DAO:
		
		1.Modificar el API de acceso: 
			Se podría cambiar el acceso a la base de datos de usar JDBC a usar Hibernate y sólo habría que modificar las clases DAO no 
			afectando al resto de la aplicación.
			
		2.Modificar el repositorio de datos: 
			Sería posible que el acceso a los usuarios se hiciera mediante LDAP a 
			un servicio de directorio en vez de estar dichos usuarios en una base de datos relacional. 
			Como en el caso contrario sólo sería necesario cambiar las clases DAO y el resto de la aplicación no sería necesaria modificarla.
			
		3.Implementación de triggers o listeners: 
			Al estar todo el código centralizado en las clases DAO podríamos fácilmente implementar políticas de seguridad en 
			el acceso al repositorio de datos.
			
		4.Usar objetos de acceso a datos:
		 	Cualquier objeto de negocio (aquel que contiene detalles específicos de operación o aplicación) 
		 	no requiere conocimiento directo del destino final de la información que manipula.
	*/
}
