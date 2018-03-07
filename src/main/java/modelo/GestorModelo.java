package modelo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import cliente.Cliente;
import datos.BaseDeDatos;
import factura.Factura;
import fecha.FechaInt;
import llamada.Llamada;
import tarifa.Tarifa;


public class GestorModelo implements ModeloInt {
	BaseDeDatos dato;
	public GestorModelo(){
		super();
		dato = new BaseDeDatos();
	}
	public boolean nuevoCliente(Cliente cliente) {
		return dato.nuevoCliente(cliente);
	}

	public boolean borrarCliente(String nif) {
		return dato.borrarCliente(nif);
	}

	public boolean cambiarTarifa(String nif, Tarifa tarifa) {
		return dato.cambiarTarifa(nif, tarifa);
	}

	public Cliente getCliente(String nif) {
		return dato.getCliente(nif);
	}

	public Set<String> getAllClients() {
		return dato.getAllClients();
	}

	public boolean addLlamada(Llamada llamada, String nif) {
		return dato.addLlamada(llamada,nif);
	}

	public boolean LlamadasDeUnCliente(String nif) {
		return dato.LlamadasDeUnCliente(nif);
	}

	public List<Llamada> getListLlamdasCliente() {
		return dato.getListLlamdasCliente();
	}

	public boolean generarFactura(String nif) {
		return dato.generarFactura(nif);
	}

	public boolean recuperarFacturaId(int id) {
		return dato.recuperarFacturaId(id);
	}

	public Factura getFacturaBuscada() {
		return dato.getFacturaBuscada();
	}

	public boolean recuperarFacturasCliente(String nif) {
		return dato.recuperarFacturasCliente(nif);
	}

	public List<Factura> getFacturasCliente() {
		return dato.getFacturasCliente();
	}

	public HashMap<Integer, Factura> getListaFacturas() {
		return dato.getListaFacturas();
	}

}
