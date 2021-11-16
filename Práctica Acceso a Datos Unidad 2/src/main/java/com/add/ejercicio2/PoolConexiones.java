package com.add.ejercicio2;

import java.sql.*;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;



public class PoolConexiones {
	
	private static BasicDataSource ds = null;

	public static DataSource getDataSource() {
		
		if (ds == null) {
			ds = new BasicDataSource();			
			ds.setUrl("jdbc:h2:~/test");
			ds.setUsername("sa");
			ds.setPassword("");
			
			
			// Definimos el tamaño del pool de conexiones
			ds.setInitialSize(5);// Conexiones iniciales
			ds.setMaxIdle(10);
			ds.setMaxTotal(20);
			ds.setMaxWaitMillis(5000);

		}
		return ds;
	}

	public static Connection getConexion() throws SQLException {
		return getDataSource().getConnection();
	}
	
	/*Ventajas de usar un pool de conexiones:
	 * 	1.El pool permite tener centralizado y controlado el manejo de las conexiones a la base de datos, 
	 * 		ya que el acceso a la misma no se hace desde el cliente, como en una aplicación en 2 capas, 
	 * 		sino que en este tipo de aplicación el acceso es realizado por el servidor de aplicaciones.
	 * 
	 * 2.La cantidad de conexiones abiertas a una base de datos es limitada, dado que consumen muchos recursos del servidor de base de datos, 
	 * 		y se requiere memoria y tiempo del procesador por cada nueva conexión. 
	 * 		El manejo de un pool favorece la escalabilidad y performance de una aplicación.
	 * 
	 * 3.  La carga al servidor de base de datos es menor al existir menos conexiones físicas.
	 */
}
