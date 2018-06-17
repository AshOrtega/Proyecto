package vista;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

import modelo.PersonaDAOImp;
import modelo.modeloTabla;

public class Vista {
	
	private JFrame frame;
	private JTextField textoNombre;
	private JTextField textoApellido;
	private JTextField textoEdad;
	private JTextField textoCodigo;
	private JTextField textoEditarNombre;
	private JTextField textoEditarApellido;
	private JTable table;
	private JMenuItem mntmCargarDatos;
	private JMenuItem mntmSalir;
	private JMenuItem mntmAcercaDe;
	private JTabbedPane tabbedPane;
	private JSplitPane splitPane;
	private JPanel panelDerecho;
	private JPanel panelBotones;
	private JButton botonAtras;
	private JButton botonAdelante;
	
	private JPanel panelIzquierdo;
	private JLabel labelEditarNombre;
	private JPanel panelNombre;
	private JLabel labelEditarApellido;
	private JPanel panelApellido;
	private JPanel panelEdad;
	private JLabel labelEditarEdad;
	private JComboBox comboBox;
	private JPanel panelBotonesPersona;
	private JButton btnInsertar;
	private JButton btnBorrar;
	private JButton btnActualizar;
	
	private JScrollPane scrollPane;
	private JPanel panelBotonesTabla;
	private JPanel panelPestanaTabla;
	private JButton btnInsertarReg;
	private JButton btnBorrarReg;
	private JTextField textoNombreTabla;
	private JTextField textoApellidoTabla;
	private JComboBox comboBoxTabla;


	public JFrame getFrame() {
		return frame;
	}

	public JTextField getTextoNombre() {
		return textoNombre;
	}

	public JTextField getTextoApellido() {
		return textoApellido;
	}

	public JTextField getTextoEdad() {
		return textoEdad;
	}

	public JTextField getTextoCodigo() {
		return textoCodigo;
	}

	public JTextField getTextoEditarNombre() {
		return textoEditarNombre;
	}

	public JTextField getTextoEditarApellido() {
		return textoEditarApellido;
	}

	public JTable getTable() {
		return table;
	}

	public JMenuItem getMntmCargarDatos() {
		return mntmCargarDatos;
	}

	public JMenuItem getMntmSalir() {
		return mntmSalir;
	}

	public JMenuItem getMntmAcercaDe() {
		return mntmAcercaDe;
	}

	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}

	public JSplitPane getSplitPane() {
		return splitPane;
	}

	public JPanel getPanelDerecho() {
		return panelDerecho;
	}

	public JPanel getPanelBotones() {
		return panelBotones;
	}

	public JButton getBotonAtras() {
		return botonAtras;
	}

	public JButton getBotonAdelante() {
		return botonAdelante;
	}

	public JPanel getPanelIzquierdo() {
		return panelIzquierdo;
	}

	public JPanel getPanelNombre() {
		return panelNombre;
	}

	public JPanel getPanelApellido() {
		return panelApellido;
	}

	public JPanel getPanelEdad() {
		return panelEdad;
	}

	public JLabel getLabelEditarEdad() {
		return labelEditarEdad;
	}

	public JComboBox getComboBox() {
		return comboBox;
	}

	public JPanel getPanel_4() {
		return panelBotonesPersona;
	}

	public JButton getBtnInsertar() {
		return btnInsertar;
	}

	public JButton getBtnBorrar() {
		return btnBorrar;
	}

	public JButton getBtnActualizar() {
		return btnActualizar;
	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}
	
	public JButton getBtnInsertarReg() {
		return btnInsertarReg;
	}

	public JButton getBtnBorrarReg() {
		return btnBorrarReg;
	}
	
	public JTextField getTextoNombreTabla() {
		return textoNombreTabla;
	}

	public JTextField getTextoApellidoTabla() {
		return textoApellidoTabla;
	}	
	
	public JComboBox getComboBoxTabla() {
		return comboBoxTabla;
	}

	public Vista() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JMenuBar menuBar = new JMenuBar();
		panel.add(menuBar);
		
		JMenu mnDatos = new JMenu("Datos");
		menuBar.add(mnDatos);
		
		mntmCargarDatos = new JMenuItem("Cargar datos");
		mnDatos.add(mntmCargarDatos);
		
		mntmSalir = new JMenuItem("Salir");
		mnDatos.add(mntmSalir);
		
		JMenu mnAcercaDe = new JMenu("Acerca de");
		menuBar.add(mnAcercaDe);
		
		mntmAcercaDe = new JMenuItem("Acerca de");
		mnAcercaDe.add(mntmAcercaDe);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		splitPane = new JSplitPane();
		tabbedPane.addTab("CRUD", null, splitPane, null);
		
		panelDerecho = new JPanel();
		splitPane.setRightComponent(panelDerecho);
		panelDerecho.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblCdigo = new JLabel("C\u00F3digo");
		panelDerecho.add(lblCdigo);
		
		textoCodigo = new JTextField();
		textoCodigo.setEditable(false);
		panelDerecho.add(textoCodigo);
		textoCodigo.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre");
		panelDerecho.add(lblNombre);
		
		textoNombre = new JTextField();
		textoNombre.setEditable(false);
		panelDerecho.add(textoNombre);
		textoNombre.setColumns(10);
		
		JLabel lblApellido = new JLabel("Apellido");
		panelDerecho.add(lblApellido);
		
		textoApellido = new JTextField();
		textoApellido.setEditable(false);
		panelDerecho.add(textoApellido);
		textoApellido.setColumns(10);
		
		JLabel lblEdad = new JLabel("Edad");
		panelDerecho.add(lblEdad);
		
		textoEdad = new JTextField();
		textoEdad.setEditable(false);
		panelDerecho.add(textoEdad);
		textoEdad.setColumns(10);
		
		panelBotones = new JPanel();
		panelDerecho.add(panelBotones);
		
		botonAtras = new JButton("<");
		panelBotones.add(botonAtras);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		panelBotones.add(horizontalStrut);
		
		botonAdelante = new JButton(">");
		panelBotones.add(botonAdelante);
		
		panelIzquierdo = new JPanel();
		splitPane.setLeftComponent(panelIzquierdo);
		panelIzquierdo.setLayout(new GridLayout(0, 1, 0, 0));
		
		panelNombre = new JPanel();
		panelIzquierdo.add(panelNombre);
		
		labelEditarNombre = new JLabel("Nombre");
		panelNombre.add(labelEditarNombre);
		
		textoEditarNombre = new JTextField();
		panelNombre.add(textoEditarNombre);
		textoEditarNombre.setColumns(10);
		
		panelApellido = new JPanel();
		panelIzquierdo.add(panelApellido);
		
		labelEditarApellido = new JLabel("Apellido");
		panelApellido.add(labelEditarApellido);
		
		textoEditarApellido = new JTextField();
		panelApellido.add(textoEditarApellido);
		textoEditarApellido.setColumns(10);
		
		panelEdad = new JPanel();
		panelIzquierdo.add(panelEdad);
		
		labelEditarEdad = new JLabel("Edad");
		panelEdad.add(labelEditarEdad);
		
		comboBox = new JComboBox();
		comboBox.setEnabled(false);
		panelEdad.add(comboBox);
		
		panelBotonesPersona = new JPanel();
		panelIzquierdo.add(panelBotonesPersona);
		
		btnInsertar = new JButton("Insertar");
		panelBotonesPersona.add(btnInsertar);
		
		btnBorrar = new JButton("Borrar");
		panelBotonesPersona.add(btnBorrar);
		
		btnActualizar = new JButton("Actualizar");
		panelBotonesPersona.add(btnActualizar);
		
		panelPestanaTabla = new JPanel();
		tabbedPane.addTab("Tabla", null, panelPestanaTabla, null);

		panelBotonesTabla = new JPanel();
				panelBotonesTabla.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
				btnInsertarReg = new JButton("Insertar Registro");
				panelBotonesTabla.add(btnInsertarReg);
		
		textoNombreTabla = new JTextField();
		panelBotonesTabla.add(textoNombreTabla);
		textoNombreTabla.setColumns(10);
		
		textoApellidoTabla = new JTextField();
		panelBotonesTabla.add(textoApellidoTabla);
		textoApellidoTabla.setColumns(10);
		
		comboBoxTabla = new JComboBox();
		panelBotonesTabla.add(comboBoxTabla);
		
		btnBorrarReg = new JButton("Borrar Registro");
		panelBotonesTabla.add(btnBorrarReg);

		
		scrollPane = new JScrollPane();
		
		modeloTabla modelo = new modeloTabla();
		table = new JTable(modelo);
		scrollPane.setViewportView(table);
		
		GroupLayout gl_panelPestanaTabla = new GroupLayout(panelPestanaTabla);
		gl_panelPestanaTabla.setHorizontalGroup(
			gl_panelPestanaTabla.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelPestanaTabla.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 569, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(Alignment.TRAILING, gl_panelPestanaTabla.createSequentialGroup()
					.addContainerGap(77, Short.MAX_VALUE)
					.addComponent(panelBotonesTabla, GroupLayout.PREFERRED_SIZE, 441, GroupLayout.PREFERRED_SIZE)
					.addGap(61))
		);
		gl_panelPestanaTabla.setVerticalGroup(
			gl_panelPestanaTabla.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelPestanaTabla.createSequentialGroup()
					.addComponent(panelBotonesTabla, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 219, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		panelPestanaTabla.setLayout(gl_panelPestanaTabla);
	}
}
