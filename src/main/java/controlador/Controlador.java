package controlador;

import java.util.ArrayList;
import java.util.Calendar;

import cliente.Cliente;
import cliente.Empresa;
import cliente.Particular;
import datos.Direccion;
import generadorNombres.GeneradorNombres;
import llamada.Llamada;
import modelo.ModeloInt;
import tarifa.Tarifa;

public class Controlador implements ControladorInt {
	private ModeloInt modelo;
	
	public boolean generarFactura(String nif) {
		boolean res = modelo.generarFactura(nif);
		return res;
	}
	
	public boolean recuperarDatosFactura(int id) {
		// TODO Auto-generated method stub
		boolean res = modelo.recuperarFacturaId(id);
		return res;
	}

	public boolean recuperarFacurasCliente(String nif) {
		boolean res = modelo.recuperarFacturasCliente(nif);
		return res;
	}

	public boolean altaLlamada(String nif, int telefono, double tiempo) {
		Calendar fecha = Calendar.getInstance();
		Llamada nuevaLlamada = new Llamada(telefono, fecha, tiempo);
		boolean res = modelo.addLlamada(nuevaLlamada, nif);
		return res;
	}

	public boolean listLlamdasCliente(String nif) {
		boolean res = modelo.LlamadasDeUnCliente(nif);
		return res;
	}

	public boolean nuevoCliente(String nombre, String apellidos, String nif, int codigoPostal, String poblacion, String provincia, String correo, int dia, int hora, Double tarifa) {
		Tarifa tar = new Tarifa(tarifa);
		Direccion dir = new Direccion(codigoPostal, poblacion, provincia);
		Calendar fecha = Calendar.getInstance();
		Cliente cliente = new Particular(nombre, nif, dir, correo, fecha, tar, apellidos);
		boolean result = modelo.nuevoCliente(cliente);
		return result;
	}

	public boolean nuevaEmpresa(String nombre, String apellidos, String nif, int codigoPostal, String poblacion, String provincia, String correo, int dia, int hora, Double tarifa) {
		Tarifa tar = new Tarifa(tarifa);
		Direccion direccion = new Direccion(codigoPostal, poblacion, provincia);
		Calendar fecha = Calendar.getInstance();
		Cliente cliente = new Empresa(nombre, nif, direccion, correo, fecha, tar);
		boolean result = modelo.nuevoCliente(cliente);
		return result;
	}

	public boolean borrarCliente(String nif) {
		boolean result = modelo.borrarCliente(nif);
		return result;
	}

	public boolean cambiarTarifa(String nif, Float tarifa) {
		Tarifa nuevaTarifa = new Tarifa(tarifa);
		Boolean result = modelo.cambiarTarifa(nif, nuevaTarifa);
		return result;
	}

	public Cliente mostrarCliente(String nif) {
		Cliente cliente = modelo.getCliente(nif);
		return cliente;
	}

	public ArrayList<Cliente> mostrarTodos() {
		ArrayList<Cliente> todosLosClientes = new ArrayList<Cliente>();
		for(String nif: modelo.getAllClients()) {
			todosLosClientes.add(modelo.getCliente(nif));
		}

		if (todosLosClientes.isEmpty()) {
			System.out.println("No hay clientes.");
		}
		for (Cliente cli : todosLosClientes) {
			System.out.println(cli.toString());
		}
		return todosLosClientes;
	}

	public boolean listLlamadasCliente(String nif) {
		boolean result = modelo.LlamadasDeUnCliente(nif);
		return result;
	}

}
