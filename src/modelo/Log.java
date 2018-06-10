package modelo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Log {
	private File fichero = new File("Logs/log.log");
	private PrintWriter writer;
	
	public void existeFichero()	{
		if (!fichero.exists()) {
			try {
				fichero.createNewFile();
			} catch (IOException e) {
			// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void escribirPersonaBorrada(String nombre, String apellido) {
		existeFichero();
		try {
			writer = new PrintWriter(new FileWriter(fichero, true));
			writer.printf("%-20s%-20s%-20s\n", "Persona", nombre + " " + apellido, "borrada");	
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

public void escribirPersonaActualizada(int textoCodigo) {
	existeFichero();
	try {
		writer = new PrintWriter(new FileWriter(fichero, true));
		writer.printf("%-20s%-20s%-20s\n", "Persona Código", textoCodigo, "actualizada");	
		writer.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public void escribirPersonaInsertada(String nombre, String apellido) {
	existeFichero();
	try {
		writer = new PrintWriter(new FileWriter(fichero, true));
		writer.printf("%-20s%-20s%-20s\n", "Persona", nombre + " " + apellido, "insertada");	
		writer.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

}
