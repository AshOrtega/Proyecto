package modelo;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import modelo.PersonaDAOImp;

public class modeloTabla extends AbstractTableModel{
	String[] cabecera;
	Object[][] datos;

	public modeloTabla (PersonaDAOImp personaDAO) {
		cabecera = personaDAO.getColumnas();
		datos    = personaDAO.getContenido();
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
		String nuevoValor = (String) valor;
		if (columna == 1) {
			personaDAO.actualizarNombrePersona(codigo, nuevoValor);
		} else if (columna == 2){
			personaDAO.actualizarApellidoPersona(codigo, nuevoValor);			
		} else {
			int valorInt = Integer.parseInt(nuevoValor) ;
			personaDAO.actualizarEdadPersona(codigo, valorInt);
		}
		
	}
	
	public void borrarRegistro (int fila) {
		int totalPersonas = (datos.length -1);
		for (int i = fila; i < totalPersonas; i++) {
			datos[i][0] = datos[i + 1][0];
			datos[i][1] = datos[i + 1][1];
			datos[i][2] = datos[i + 1][2];
			datos[i][3] = datos[i + 1][3];
		}
		fireTableDataChanged();
	}
		
}
