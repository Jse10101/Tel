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

	boolean listLlamadasCliente(String nif);

	boolean nuevoCliente(String nombre, String apellidos, String nif, int codigoPostal, String poblacion, String provincia, String correo, int dia, int hora, Double tarifa);

	boolean nuevaEmpresa(String nombre, String apellidos, String nif, int codigoPostal, String poblacion, String provincia, String correo, int dia, int hora, Double tarifa);

	boolean borrarCliente(String nif);

	boolean cambiarTarifa(String nif, Float tarifa);

	Cliente mostrarCliente(String nif);

	ArrayList<Cliente> mostrarTodos();
}