package modelo;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class ConexionSQL {
private static Connection conexion;
	
	private ConexionSQL () {

		try {
			String DRIVER = "org.sqlite.JDBC";
			String DB_URL = "jdbc:sqlite:";
			String DB = "db/personas.db";
			
			Class.forName(DRIVER);
			conexion = DriverManager.getConnection(DB_URL + DB);
			
			
		} catch ( ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public static Connection getConexion() {
		
		if (conexion == null) {
			new ConexionSQL();
			Runtime.getRuntime().addShutdownHook(new ShutdownHook());
		}		
		return conexion; 
	
	}
	
	static class ShutdownHook extends Thread{
		@Override
		public void run() {
			if (conexion != null)
				try {
					System.out.println("Cerrando conexión");
					conexion.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	

}
