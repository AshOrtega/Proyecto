package modelo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Log {
	private File fichero = new File("Logs/log");
	private PrintWriter reader;
	
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
			reader = new PrintWriter(new FileWriter(fichero, true));
			reader.printf("%-20s%-20s%-20s\n", "Persona", nombre + " " + apellido, "borrada");	
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

public void escribirPersonaActualizada(int textoCodigo) {
	existeFichero();
	try {
		reader = new PrintWriter(new FileWriter(fichero, true));
		reader.printf("%-20s%-20s%-20s\n", "Persona Código", textoCodigo, "actualizada");	
		reader.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public void escribirPersonaInsertada(String nombre, String apellido) {
	existeFichero();
	try {
		reader = new PrintWriter(new FileWriter(fichero, true));
		reader.printf("%-20s%-20s%-20s\n", "Persona", nombre + " " + apellido, "insertada");	
		reader.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

}
