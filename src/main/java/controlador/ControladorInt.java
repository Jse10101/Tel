package controlador;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import cliente.Cliente;
import excepciones.CodigoInvalido;
import excepciones.ErrorFecha;
import excepciones.NifInvalido;
import factura.Factura;
import llamada.Llamada;
import modelo.ModeloInt;
import vista.Vista;
import vista.VistaInt;

public interface ControladorInt {
	boolean generarFactura(String nif) throws ErrorFecha, NifInvalido;

	ModeloInt cargar();

	void guardar();

	void setVista(VistaInt vista);

	void setModelo(ModeloInt modelo);

	boolean recuperarDatosFactura(int id) throws CodigoInvalido;

	boolean recuperarFacurasCliente(String nif);

	List<Factura> recuperarFactFechas(Calendar i, Calendar f, String NIF) throws ErrorFecha;

	boolean altaLlamada(String nif, int tel, double tiempo) throws NifInvalido;

	boolean listLlamdasCliente(String nif) throws NifInvalido;

	List<Llamada> recuperarLlamadasFechas(Calendar i, Calendar f, String NIF) throws ErrorFecha;

	boolean nuevoCliente(String nombre, String apellidos, String dni, String pc, String prov, String pueblo,
			Float tarifaB, Float tarifaE, String correo, int opcionEsp);

	boolean nuevaEmpresa(String nombre, String dni, String pc, String prov, String pueblo, Float tarifaB, Float tarifaE, String correo, int esp);

	boolean borrarCliente(String niff);

	boolean cambiarTarifa(String dni, Float tarifaB, Float tarifaE, int esp);

	Cliente mostrarCliente(String niff);

	ArrayList<Cliente> mostrarTodos();
	
	ArrayList<Cliente> mostrarEntreFechas(Calendar i, Calendar f) throws ErrorFecha;

}