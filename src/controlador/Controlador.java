package controlador;

import vista.Vista;
import modelo.PersonaDTO;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import modelo.modeloTabla;
import modelo.Log;
import modelo.PersonaDAOImp;

public class Controlador implements ActionListener, TableModelListener{
	private ArrayList<PersonaDTO> listaPersona;
	private Vista vista;
	private PersonaDAOImp personaDAO;
	private int contador = 0;
	private String fichero;
	private String DB = "db/personas.db";
	private String textoNombre;
	private String textoApellido;
	private int textoEdad;
	private int textoCodigo;

	public Controlador(Vista vista) {
		this.vista = vista;
		
		existeFichero(DB);
		registrarComponentes();
	}
	
	private void registrarComponentes() {
		vista.getMntmCargarDatos().addActionListener(this);
		vista.getMntmSalir().addActionListener(this);
		vista.getMntmAcercaDe().addActionListener(this);
		
		vista.getBotonAtras().addActionListener(this);
		vista.getBotonAdelante().addActionListener(this);
		vista.getBtnInsertar().addActionListener(this);
		vista.getBtnBorrar().addActionListener(this);
		vista.getBtnActualizar().addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource().getClass() == JMenuItem.class) {

			JMenuItem menuItem = (JMenuItem) e.getSource();

			if (menuItem.getText().equals("Salir"))
				salirDeAplicacion();
			else if (menuItem.getText().equals("Acerca de"))
				mostrarMensaje();
			else
				elegirFichero();
		}
		
		if (e.getSource().getClass() == JButton.class) {
			JButton jButton = (JButton) e.getSource();
			String textoBoton = jButton.getText();
			System.out.println(textoBoton);
			
			switch (textoBoton) {
			case ">":
				contador++;
				break;
			case "<":
				contador--;
				break;
				
			case "Insertar":		
				textoNombre = vista.getTextoEditarNombre().getText();
				textoApellido = vista.getTextoEditarApellido().getText();
				textoEdad = (int) vista.getComboBox().getSelectedItem();
				PersonaDTO persona = new PersonaDTO();
				persona.setNombre(textoNombre);
				persona.setApellido(textoApellido);
				persona.setEdad(textoEdad);
				
				personaDAO.insertarPersona(persona, listaPersona);
				listaPersona = personaDAO.listarTodasPersonas();
				mostrarFormulario(contador, listaPersona);
				break;
				
			case "Borrar" :
				textoNombre = vista.getTextoEditarNombre().getText();
				textoApellido = vista.getTextoEditarApellido().getText();
				
				personaDAO.borrarPersona(textoNombre, textoApellido);
				listaPersona = personaDAO.listarTodasPersonas();
				contador = 0;
				mostrarFormulario(contador, listaPersona);
				break;
				
			case "Actualizar":
				textoCodigo = Integer.parseInt(vista.getTextoCodigo().getText());
				textoNombre = vista.getTextoEditarNombre().getText();
				textoApellido = vista.getTextoEditarApellido().getText();
				textoEdad = (int) vista.getComboBox().getSelectedItem();
				
				personaDAO.actualizarNombrePersona(textoCodigo, textoNombre);
				personaDAO.actualizarApellidoPersona(textoCodigo, textoApellido);
				personaDAO.actualizarEdadPersona(textoCodigo, textoEdad);
				
				listaPersona = personaDAO.listarTodasPersonas();
				mostrarFormulario(contador, listaPersona);
				break;
				
			default:
				break;
			}
			
			contador = contador % listaPersona.size();
			contador %= listaPersona.size();  
			if (contador < 0)
				contador += listaPersona.size();
			mostrarFormulario(contador, listaPersona);
		}

	}
	
	private void elegirFichero() {
		JFileChooser fileChooser = new JFileChooser(".");
		int resultado = fileChooser.showOpenDialog(vista.getFrame());
		if (resultado == fileChooser.APPROVE_OPTION) {
			fichero = fileChooser.getSelectedFile().getPath();
			personaDAO = new PersonaDAOImp();
			personaDAO.cargarContenido(fichero);
			mostrarBaseDatos(personaDAO);			
		}
	}
	
	private void mostrarBaseDatos(PersonaDAOImp personaDAO)  {
		listaPersona = personaDAO.listarTodasPersonas();	
		for (int i = 0; i < 100; i++) {
			vista.getComboBox().addItem(i);
		}
		
		mostrarFormulario(contador, listaPersona);
		vista.getBotonAdelante().setEnabled(true);
		vista.getBotonAtras().setEnabled(true);
		vista.getMntmCargarDatos().setEnabled(false);
		vista.getComboBox().setEnabled(true);
		vista.getBtnInsertar().setEnabled(true);
		vista.getBtnBorrar().setEnabled(true);
		vista.getBtnActualizar().setEnabled(true);
		
		modeloTabla modeloTabla = new modeloTabla(personaDAO);
		JTable jTable = new JTable(modeloTabla);
		jTable.getModel().addTableModelListener(this);
		vista.getScrollPane().setViewportView(jTable);
	}
	
	private void mostrarFormulario(int i, List<PersonaDTO> listaPersona) {
		vista.getTextoCodigo().setText(String.valueOf(
				listaPersona.get(i).getCodigo()));
		vista.getTextoNombre().setText(
				listaPersona.get(i).getNombre());
		vista.getTextoApellido().setText(
				listaPersona.get(i).getApellido());
		vista.getTextoEdad().setText(String.valueOf(
				listaPersona.get(i).getEdad()));
	}
	
	private void mostrarMensaje() {
		JOptionPane optionPane = new JOptionPane();
		optionPane.showMessageDialog(vista.getFrame(), 
				"Creado por Sergio", "Autor",
				JOptionPane.INFORMATION_MESSAGE);
	}

	private void salirDeAplicacion() {
		System.exit(0);
	}
	
	public void existeFichero(String DB) {
		File fichero = new File(DB);
		personaDAO = new PersonaDAOImp();
			if (fichero.exists()) {
			 if (fichero.length() > 16) {
				mostrarBaseDatos(personaDAO); 
			 }
			} else {
				try {
					fichero.createNewFile();
					System.out.println("Fichero creado correctamente");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
	}

	@Override
	public void tableChanged(TableModelEvent e) {
		// TODO Auto-generated method stub
		
	}

}
