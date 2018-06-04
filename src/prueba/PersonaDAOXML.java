package prueba;
import java.util.ArrayList;

import modelo.PersonaDTO;

public interface PersonaDAOXML {
		
		ArrayList<PersonaDTO> listarTodasPersonas(String fichero);
		void obtenerCabeceraBaseDatos(String fichero);
		boolean insertarPersona(PersonaDTO persona, String fichero, ArrayList<PersonaDTO> listaPersona);
		boolean borrarPersona(String nombre, String apellido, String fichero);
		boolean actualizarNombrePersona(int codigo, String nombre, String fichero);
		boolean actualizarApellidoPersona(int codigo, String apellido, String fichero);
		boolean actualizarEdadPersona(int codigo, int edad, String fichero);
		

}
