package modelo;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import cliente.Cliente;
import factura.Factura;
import llamada.Llamada;
import tarifa.Tarifa;

public class GestorModelo implements ModeloInt {

	public boolean nuevoCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean borrarCliente(String nif) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean cambiarTarifa(String NIF, Tarifa tarifa) {
		// TODO Auto-generated method stub
		return false;
	}

	public Cliente getCliente(String nif) {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<String> getAllClients() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean addLlamada(Llamada llamada, String nif) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean listaTodasLasLLamadasDeUnCliente(String nif) {
		// TODO Auto-generated method stub
		return false;
	}

	public List<Llamada> getListLlamdasCliente() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean generarFactura(String nif) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean recuperarFacturaId(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	public Factura getFacturaBuscada() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean recuperarFacturasCliente(String nif) {
		// TODO Auto-generated method stub
		return false;
	}

	public List<Factura> getFacturascliente() {
		// TODO Auto-generated method stub
		return null;
	}

	public HashMap<Integer, Factura> getListaFacturas() {
		// TODO Auto-generated method stub
		return null;
	}

}
