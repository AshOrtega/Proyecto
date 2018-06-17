package modelo;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;
import modelo.PersonaDAOImp;

public class modeloTabla extends AbstractTableModel{
	String[] cabecera;
	Object[][] datos;

	public modeloTabla () {
		datos = new Object [40][4];
		cabecera = new String [4];
	}
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return cabecera.length;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return datos.length;
	}

	@Override
	public Object getValueAt(int fila, int col) {
		// TODO Auto-generated method stub
		return datos[fila][col];
	}
	@Override
	public String getColumnName(int columna) {
		// TODO Auto-generated method stub
		return cabecera[columna];
	}

	@Override
	public boolean isCellEditable(int fila, int columna) {
		if (columna >= 1) {
			return true;
		} else {
			return false;			
		}
	}
	
	@Override
	public void setValueAt(Object valor, int fila, int columna) {
		datos[fila][columna] = valor;
		fireTableCellUpdated(fila, columna);

		PersonaDAOImp personaDAO = new PersonaDAOImp(); 
		int codigo = (int) datos[fila][0];
		String valorString = (String) valor;
		if (columna == 1) {
			personaDAO.actualizarNombrePersona(codigo, valorString);
		} else if (columna == 2){
			personaDAO.actualizarApellidoPersona(codigo, valorString);			
		} else {
			
			try {
				if (valorString.matches(".*[a-zA-Z].*")) {
					throw new ExcepcionEdad();
				} else {
					int valorInt = Integer.parseInt(valorString);
					personaDAO.actualizarEdadPersona(codigo, valorInt);	
				}					
			} catch (ExcepcionEdad e) {
				System.out.println("Error: El valor introducido no es un número");
			}
		}
		
	}
	
	public void obtenerDatos (PersonaDAOImp personaDAO) {
		cabecera = personaDAO.getColumnas();
		datos    = personaDAO.getContenido();
	}
	
	public void borrarRegistro (int fila) {
		if (fila != -1) {
			int totalPersonas = (datos.length -1);
			for (int i = fila; i <= totalPersonas; i++) {
				System.out.println(datos[i][1]);
				datos[i][0] = datos[i + 1][0];
				datos[i][1] = datos[i + 1][1];
				datos[i][2] = datos[i + 1][2];
				datos[i][3] = datos[i + 1][3];
			}
			fireTableDataChanged();			
		}

	}
	
	public void insertarRegistro(PersonaDTO persona, ArrayList<PersonaDTO> listaPersonas) {
		PersonaDAOImp personaDAO = new PersonaDAOImp(); 
		personaDAO.insertarPersona(persona, listaPersonas);
		personaDAO.listarTodasPersonas();
		
		obtenerDatos(personaDAO);
		fireTableDataChanged();		

	}
		
}
