package controlador;

import java.util.ArrayList;
import java.util.Calendar;

import cliente.Cliente;
import datos.Direccion;
import tarifa.Tarifa;

public class Controlador implements ControladorInt {
	private ModeloInt modelo;
	
	public boolean generarFactura(String nif) {
		// TODO Auto-generated method stub
		return result;
	}
	
	@Override
	public boolean recuperarDatosFactura(int id) {
		// TODO Auto-generated method stub

	}

	public boolean recuperarFacurasCliente(String nif) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean altaLlamada(String nif, int telefono, double tiempo) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean listLlamdasCliente(String nif) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean nuevoCliente(String nombre, String apellidos, String nif, Direccion direccion, String correo,
			Calendar fechaAlta, Tarifa tarifa) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean nuevaEmpresa(String nombre, String nif, Direccion direccion, String correo, Calendar fechaAlta,
			Tarifa tarifa) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean borrarCliente(String nif) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean cambiarTarifa(String nif, Float tarifa) {
		// TODO Auto-generated method stub
		return false;
	}

	public Cliente mostrarCliente(String nif) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Cliente> mostrarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

}
