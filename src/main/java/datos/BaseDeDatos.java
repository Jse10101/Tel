package datos;

import java.util.ArrayList;
import java.util.Calendar;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import cliente.Cliente;
import excepciones.ErrorFecha;
import factura.Factura;
import fecha.FechaInt;
import llamada.Llamada;
import tarifa.Tarifa;

public class BaseDeDatos{
	private BaseDeDatos bd;
	private Integer codigo = 1;
	private Factura facturaBusc;
	private List<Factura> listaFacturasCliente;
	private List<Llamada> listaLlamadasCliente;
	private HashMap<Integer, Factura> listaFacturas;
	private HashMap<String, Cliente> listaClientes;
	
	//Constructor
	public BaseDeDatos() {
		super();
		listaFacturasCliente = new ArrayList<Factura>();
		listaLlamadasCliente = new ArrayList<Llamada>();
		listaFacturas = new HashMap<Integer, Factura>();
		listaClientes = new HashMap<String, Cliente>();
	}
	//Gets y Sets
	public HashMap<Integer, Factura> getListaFacturas() {
		return listaFacturas;
	}

	public void setListaFacturas(HashMap<Integer, Factura> listaFacturas) {
		this.listaFacturas = listaFacturas;
	}

	public HashMap<String, Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(HashMap<String, Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}
	
	public Cliente getCliente(String nif) {
		if (listaClientes.containsKey(nif)) {
			return listaClientes.get(nif);
		}
		return null;
	}

	public Set<String> getAllClients() {
		return listaClientes.keySet();
	}
	
	//Incremento del código de las facturas
	private void incrementaCodigoFactura() {
		codigo++;
	}
	
	//Crea un nuevo cliente
	public boolean nuevoCliente(Cliente cliente) {
		if (!listaClientes.containsKey(cliente.getNif())) {
			listaClientes.put(cliente.getNif(), cliente);
			return true;
		} else {
			return false;
		}
	}
	//Borra un cliente
	public boolean borrarCliente(String nif) {
		if (listaClientes.containsKey(nif)) {
			listaClientes.remove(nif);
			System.out.println("Cliente eliminado.");
			return true;
		} else {
			return false;
		}
	}

	//Cambia la tarifa
	public boolean cambiarTarifa(String nif, Tarifa tarifa) {
		if (listaClientes.containsKey(nif)) {
			listaClientes.get(nif).setTarifa(tarifa);
			return true;
		} else {
			return false;
		}
	}
	
	//Añade una llamada a un cliente
	public boolean addLlamada(Llamada llamada, String nif) {
		Cliente cliente = listaClientes.get(nif);
		if (cliente == null) {
			System.out.println("El nif introducido es incorrecto.");
			return false;
		}else{
			cliente.getListaLlamadas().add(llamada);
			System.out.println("Llamada añadida.");
			return true;
		}
			

	}

	//Muestra todas las llamadas de un cliente
	public boolean LlamadasDeUnCliente(String nif) {
		Cliente cliente = listaClientes.get(nif);
		if (cliente != null) {
			ArrayList<Llamada> lista = cliente.getListaLlamadas();
			if (lista.isEmpty()) {
				System.out.println("Cliente sin llamadas.");
			} else {
				setListLlamdasCliente(lista);
				System.out.println("Las llamadas del cliente son:");
				System.out.println(lista.toString());
			}
			return true;
		} else {

			System.out.println("El nif introducido es incorrecto.");
			return false;
		}
	}

	//Muestra facturas de un cliente con el código de factura
	public boolean recuperarFacturaPorCodigo(int codigo) {
		Factura factura = listaFacturas.get(codigo);
		if (factura != null) {
			System.out.println("Datos de la factura: ");
			System.out.println(factura.toString());
			setFacturaBuscada(factura);
			return true;
		} else {
			System.out.println("El código de factura incorrecto.");
			return false;
		}

	}

	//Muestra facturas de un cliente con el nif
	public boolean recuperarFacturasCliente(String nif) {
		listaFacturasCliente = new ArrayList<Factura>();
		Cliente cliente = listaClientes.get(nif);
		if (cliente == null) {
			//El cliente no existe.
			System.out.println("El nif introducido es incorrecto.");
			return false;
		} else {
			//El cliente sí existe
			ArrayList<Integer> facturas = cliente.getListaCodigoFacturas();
			if (!facturas.isEmpty()) {
				//El cliente no tiene facturas.
				System.out.println("Cliente sin facturas.");
				return false;
			} else {
				//Si la lista de facturas no está vacía, las muestra una a una recorriéndola y las guarda en una nueva lista
				System.out.println("Facturas: ");
				for (Integer contador : facturas) {
					listaFacturasCliente.add(listaFacturas.get(contador));
					System.out.println(listaFacturas.get(contador).toString());
				}
				return true;
			}

		}
	}
	
	//Genera una nueva factura
	public boolean generarFactura(String nif) {
		Cliente cliente = listaClientes.get(nif);
		Boolean LlamadasParaFacturar = false;
		double importe = 0.0;
		if (cliente == null) {
			//El cliente no existe
			System.out.println("El nif introducido es incorrecto.");
			return false;
		} else {
			//El cliente sí existe
			ArrayList<Integer> listCodigoFacturas = cliente.getListaCodigoFacturas();
			if (listCodigoFacturas.isEmpty()) { //Cliente sin facturas, facturamos sus llamadas.
				ArrayList<Llamada> listaLlamadas = cliente.getListaLlamadas();
				if (listaLlamadas.isEmpty()) {	//La lista de llamadas está vacía
					System.out.println("No hay llamadas.");
					return false;
				} else {
					//Si hay llamadas, calculamos el precio de todas las llamadas
					for (Llamada llamada : listaLlamadas) {
						importe += llamada.getDuracion() * cliente.getTarifa().getPrecio(llamada);
					}
					//Facturamos
					Calendar fechaFacturacion = Calendar.getInstance();
					Factura factura = new Factura(getCodigoFactura(), cliente.getFecha(), fechaFacturacion, fechaFacturacion, cliente.getTarifa(), importe);
					cliente.getListaCodigoFacturas().add(factura.getCodigo());
					listaFacturas.put(factura.getCodigo(), factura);
					incrementaCodigoFactura();
					cliente.setFechaUltimaFactura(fechaFacturacion);
					System.out.println("Facturado.");
					return true;
				}
			} else { //El cliente tiene facturas anteriores
				ArrayList<Llamada> llamadas = cliente.getListaLlamadas();
				//Si no tiene llamadas
				if (llamadas.isEmpty()) {
					System.out.println("Sin llamadas que facturar.");
					return false;
				} else { // Facturamos todas las llamadas desde la última fecha facturada
					for (Llamada llamada : llamadas) {
						if (cliente.getFechaUltimaFactura().compareTo(llamada.getFecha()) < 0) {
							LlamadasParaFacturar = true;
							importe += llamada.getDuracion() * cliente.getTarifa().getPrecio(llamada);
						}
					}
					//Si hay llamadas para facturar, facturamos como antes
					if (LlamadasParaFacturar) {
						Calendar fechaFacturacion = Calendar.getInstance();
						Factura factura = new Factura(getCodigoFactura(), cliente.getFecha(), fechaFacturacion, fechaFacturacion, cliente.getTarifa(), importe);
						cliente.getListaCodigoFacturas().add(factura.getCodigo());
						cliente.setFechaUltimaFactura(fechaFacturacion);
						incrementaCodigoFactura();
						listaFacturas.put(factura.getCodigo(), factura);
						System.out.println("Facturado.");
						return true;
					} else {
						//Si no, mostramos por pantalla que no hay llamadas
						System.out.println("Sin llamadas que facturar.");
						return false;
					}

				}
			}
		}
	}
	
	// ENTREGA 2 EN CONSTRUCCION
	//32123123123
	public <T extends FechaInt> ArrayList <T> recuperaEntreFechas(ArrayList <T> conjunto, Calendar inicio, Calendar fin ) 
			throws ErrorFecha {
		if(fin.before(inicio) || inicio.after(fin)){
			throw new ErrorFecha();
		}
		ArrayList <T> subconjunto = new ArrayList <T> ();
		for(int i = 0 ; i < conjunto.size() ; i++ )	{
			if(conjunto.get(i).getFecha().before(fin) && conjunto.get(i).getFecha().after(inicio)) {
				subconjunto.add(conjunto.get(i));	
			}
		}
		return subconjunto;
	}
	
	//Más sets y gets
	public Integer getCodigoFactura() {
		return codigo;
	}

	public Factura getFacturaBuscada() {
		return facturaBusc;
	}

	public void setFacturaBuscada(Factura facturabuscada) {
		this.facturaBusc = facturabuscada;
	}

	public List<Factura> getFacturasCliente() {
		return listaFacturasCliente;
	}

	public List<Llamada> getListLlamdasCliente() {
		return listaLlamadasCliente;
	}

	public void setListLlamdasCliente(List<Llamada> listLlamdasCliente) {
		this.listaLlamadasCliente = listLlamdasCliente;
	}

	public BaseDeDatos getBd() {
		return bd;
	}

}