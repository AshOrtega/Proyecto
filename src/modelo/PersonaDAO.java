package modelo;
import java.util.ArrayList;

public interface PersonaDAO {
		
		ArrayList<PersonaDTO> listarTodasPersonas();
		void obtenerCabeceraBaseDatos();
		boolean insertarPersona(PersonaDTO persona, ArrayList<PersonaDTO> listaPersonas);
		boolean borrarPersona(String nombre, String apellido);
		boolean actualizarNombrePersona(int codigo, String nombre);
		boolean actualizarApellidoPersona(int codigo, String apellido);
		boolean actualizarEdadPersona(int codigo, int edad);
		

}
