package modelo;

import java.awt.EventQueue;
import vista.Vista;
import controlador.Controlador;

public class TestAplicacion {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Vista window = new Vista();
					new Controlador(window);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
