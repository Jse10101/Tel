package vista;

import javax.swing.SwingUtilities;

import controlador.Controlador;
import controlador.ControladorInt;
import modelo.GestorModelo;
import modelo.ModeloInt;

public class Ejecuta {
	
	public static void main(String[] args) {
	
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				VistaInt vista = (VistaInt) new Vista();
				 
				ModeloInt modelo = new GestorModelo();
				
				ControladorInt controlador = new Controlador();
				
				 ((Vista) vista).setControlador(controlador);
				vista.setModelo(modelo);
				
				controlador.setVista(vista);
				controlador.setModelo(modelo);
				vista.dibujaVentana();
			}
		});
	}
	
}
