package controlador;

import java.util.ArrayList;
import java.util.Calendar;
import cliente.Cliente;
import datos.Direccion;
import tarifa.Tarifa;

public interface ControladorInt {
	boolean generarFactura(String nif);

	boolean recuperarDatosFactura(int id);

	boolean recuperarFacurasCliente(String nif);
	
	boolean altaLlamada(String nif, int telefono, double tiempo);

	boolean listLlamdasCliente(String nif);

	boolean nuevoCliente(String nombre, String apellidos, String nif, Direccion direccion, String correo, Calendar fechaAlta, Tarifa tarifa);

	boolean nuevaEmpresa(String nombre, String nif, Direccion direccion, String correo, Calendar fechaAlta, Tarifa tarifa);

	boolean borrarCliente(String nif);

	boolean cambiarTarifa(String nif, Float tarifa);

	Cliente mostrarCliente(String nif);

	ArrayList<Cliente> mostrarTodos();
}