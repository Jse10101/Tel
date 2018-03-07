package modelo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import cliente.Cliente;
import factura.Factura;
import fecha.FechaInt;
import llamada.Llamada;
import tarifa.Tarifa;

public interface ModeloInt {
	boolean nuevoCliente(Cliente cliente);
	boolean borrarCliente(String nif);
	boolean cambiarTarifa(String NIF, Tarifa tarifa);
	Cliente getCliente(String nif);
	Set<String> getAllClients();
	
	boolean addLlamada(Llamada llamada, String nif);
	boolean LlamadasDeUnCliente(String nif);
	List<Llamada> getListLlamdasCliente();

	boolean generarFactura(String nif);
	boolean recuperarFacturaId(int id);
	Factura getFacturaBuscada();
	boolean recuperarFacturasCliente(String nif);
	List<Factura> getFacturasCliente();
	HashMap<Integer, Factura> getListaFacturas();
}