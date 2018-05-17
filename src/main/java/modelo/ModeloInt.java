package modelo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import cliente.Cliente;
import excepciones.CodigoInvalido;
import excepciones.ErrorFecha;
import excepciones.NifInvalido;
import factura.Factura;
import fecha.FechaInt;
import llamada.Llamada;
import tarifa.Tarifa;

public interface ModeloInt {

	boolean generarFactura(String nif) throws ErrorFecha, NifInvalido;

	boolean nuevoCliente(Cliente cliente);

	boolean recuperarFacturaPorCodigo(int codigo) throws CodigoInvalido;

	Factura getFacturaBuscada();

	boolean recuperarFacturasCliente(String nif) throws NifInvalido, CodigoInvalido;

	List<Factura> getFacturasCliente();

	void cargar();

	<T extends FechaInt> ArrayList<T> recuperaEntreFechas(ArrayList<T> datos, Calendar inicio, Calendar fin)
			throws ErrorFecha;

	Cliente getCliente(String nif);

	HashMap<Integer, Factura> getListaFacturas();

	boolean addLlamada(Llamada llamada, String nif) throws NifInvalido;

	boolean LlamadasDeUnCliente(String nif) throws NifInvalido;

	List<Llamada> getListLlamadasCliente();

	boolean borrarCliente(String nif);

	boolean cambiarTarifa(String NIF, Tarifa tarifa);

	Set<String> getAllClients();
	
	void guardar();
}