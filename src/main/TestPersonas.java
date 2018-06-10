package main;

import java.util.ArrayList;

import modelo.ConexionSQL;
import modelo.Log;
import modelo.PersonaDAOImp;
import modelo.PersonaDTO;

public class TestPersonas {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.printf("Mostrando personas %n%n");
		String fichero = "db/personas.bd";
		Log log = new Log();
		PersonaDAOImp personaDAO = new PersonaDAOImp();
		
		//personaDAO.cargarContenido(fichero);
		
		//ArrayList<PersonaDTO> listaPersonas = personaDAO.listarTodasPersonas(fichero);
		/*
		for (int i = 0; i < listaPersonas.size(); i++) {
			System.out.println("Código " + listaPersonas.get(i).getCodigo());
			System.out.println("Nombre " + listaPersonas.get(i).getNombre());
			System.out.println("Apellido " + listaPersonas.get(i).getApellido());
			System.out.println("Edad " + listaPersonas.get(i).getEdad());
			System.out.printf(" -------------- %n");
			
		}
		*/
		/*
		PersonaDTO persona = new PersonaDTO();
		persona.setNombre("Eustaquia");
		persona.setApellido("Eustaquiez");
		persona.setEdad(20);
		
		System.out.println("¿Persona insertada? " + personaDAO.insertarPersona(persona, listaPersonas));
		*/
		
		//System.out.println("¿Persona borrada? " + personaDAO.borrarPersona("Eustaquia", "Eustaquiez"));
		//System.out.println("¿Edad actualizado? " + personaDAO.actualizarEdadPersona(21, 30));
		personaDAO.obtenerCabeceraBaseDatos();
		//log.escribirPersonaInsertada("Pepa", "Pepez");
		
		
	}

}
