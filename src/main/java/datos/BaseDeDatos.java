package datos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import cliente.Cliente;
import factura.Factura;
import generradorNombres.GeneradorNombres;
import guardaryabrir.GuardaryAbrir;
import interfazFecha.Fecha;
import llamada.Llamada;
import modelo.ModeloInt;
import tarifa.Tarifa;

public class BaseDeDatos implements ModeloInt{
	private BaseDeDatos baseDatos;
	private static final long serialVersionUID = -7383930710295711000L;
	private HashMap<Integer, Factura> listaFacturas;
	private HashMap<String, Cliente> listaClientes;
	private Integer idFactura = 1;
	private Factura facturabuscada;
	private List<Factura> facturascliente = new ArrayList<Factura>();
	private List<Llamada> listLlamdasCliente = new ArrayList<Llamada>();
	
	public BaseDeDatos() {
		//Constructor
		super();
		listaClientes = new HashMap<String, Cliente>();
		listaFacturas = new HashMap<Integer, Factura>();
	}
	
	public boolean nuevoCliente(Cliente cliente) {
		//Crea un nuevo cliente y devuelve verdadero si en la lista de clientes no hay ningun cliente con ese nif.
		if (!listaClientes.containsKey(cliente.getNif())) {
			listaClientes.put(cliente.getNif(), cliente);
			return true;
		} else {
			return false;
		}
	}
	public boolean borrarCliente(String nif) {
		// TODO Auto-generated method stub
		//Si en la lista de clientes hay un cliente con el nif, lo borramos y devolvemos verdadero.
		if (listaClientes.containsKey(nif)) {
			listaClientes.remove(nif);
			System.out.println("Borrado con exito");
			return true;
		} else {
			return false;
		}
	}
	public boolean cambiarTarifa(String nif, Tarifa tarifa) {
		// TODO Auto-generated method stub
		//Si el nif está en la lista de clientes, cambiamos la tarifa por la tarifa dada y devolvemos verdadero.
		if (listaClientes.containsKey(nif)) {
			listaClientes.get(nif).setTarifa(tarifa);
			return true;
		} else {
			return false;
		}
	}
	public Cliente getCliente(String nif) {
		// TODO Auto-generated method stub
		//Si el nif está en la lista de clientes, devolvemos el cliente.
		if (listaClientes.containsKey(nif)) {
			return listaClientes.get(nif);
		}
		return null;
	}
	
	public Set<String> getAllClients() {
		// TODO Auto-generated method stub
		return listaClientes.keySet();
	}
	
	private void incrementaIdFactura() {
		idFactura++;
	}
	
	public boolean addLlamada(Llamada llamada, String nif) {
		// TODO Auto-generated method stub
		//Al cliente del cual nos pasan su nif, si existe, le añadimos una llamada a su lista de llamadas.
		Cliente cliente = listaClientes.get(nif);
		if (cliente != null) {
			cliente.getListLlamadas().add(llamada);
			System.out.println("Añadido.");
			return true;
		} else {
			System.out.println("Nif introducido incorrecto");
			return false;
		}

	}
	public boolean listaTodasLasLLamadasDeUnCliente(String nif) {
		// TODO Auto-generated method stub
		//Si el cliente existe y la lista de llamadas no esta vacia, muestra todas las llamadas de ese cliente.
		Cliente cliente = listaClientes.get(nif);
		if (cliente != null) {
			ArrayList<Llamada> lista = cliente.getListLlamadas();
			if (lista.isEmpty()) {
				System.out.println("El cliente no tiene llamadas");
			} else {
				setListLlamdasCliente(lista);
				System.out.println("Llamadas del cliente: ");
				System.out.println(lista.toString());
			}
			return true;
		} else {
			System.out.println("Nif introducido incorrecto.");
			return false;
		}
	}
	
	public List<Llamada> getListLlamdasCliente() {
		// TODO Auto-generated method stub
		return listLlamdasCliente;
	}
	public boolean generarFactura(String nif) {
		// TODO Auto-generated method stub
		Cliente cliente = listaClientes.get(nif);
		double importe = 0.0;
		Boolean hayLLamadaParaFacturar = false;
		if (cliente != null) {
			ArrayList<Integer> lista = cliente.getListIdFactuas();
			if (lista.isEmpty()) {// el cleinte nunca ha hecho facturas
									// porlotanto facturamos todas sus llamadas
				ArrayList<Llamada> llamadas = cliente.getListLlamadas();
				if (llamadas.isEmpty()) {
					System.out.println("No hay llamadas para facturar");
					return false;
				} else {

					for (Llamada llamada : llamadas) {
						importe += llamada.getDuracion() * cliente.getTarifa().getPrecio(llamada);

					}
					Calendar fechafactura = Calendar.getInstance();
					Factura factura = new Factura(getIdFactura(), cliente.getTarifa(), fechafactura, cliente.getFecha(),
							fechafactura, importe);
					cliente.getListIdFactuas().add(factura.getId());
					listaFacturas.put(factura.getId(), factura);
					incrementaIdFactura();
					cliente.setFechaultimafactura(fechafactura);
					System.out.println("Facturado con exito");
					return true;
				}
			} else {// el cliente ya tiene facturas anteriores
				ArrayList<Llamada> llamadas = cliente.getListLlamadas();
				if (llamadas.isEmpty()) {
					System.out.println("No hay llamadas para facturar");
					return false;
				} else {// facuramos todas las llamadas desde la ultima fecha
						// dia hora hasta el momento actual
					for (Llamada llamada : llamadas) {
						if (cliente.getFechaultimafactura().compareTo(llamada.getFecha()) < 0) {
							hayLLamadaParaFacturar = true;
							importe += llamada.getDuracion() * cliente.getTarifa().getPrecio(llamada);
						}
					}
					if (hayLLamadaParaFacturar) {
						Calendar fechafactura = Calendar.getInstance();
						Factura factura = new Factura(getIdFactura(), cliente.getTarifa(), fechafactura,
								cliente.getFechaultimafactura(), fechafactura, importe);
						cliente.getListIdFactuas().add(factura.getId());
						incrementaIdFactura();
						cliente.setFechaultimafactura(fechafactura);
						listaFacturas.put(factura.getId(), factura);
						System.out.println("Facturado con exito");
						return true;
					} else {
						System.out.println("No hay llamadas para facturar");
						return false;
					}

				}
			}

		} else {

			System.out.println("El nif es incorecto");
			return false;
		}
	}
	
	public boolean recuperarFacturaId(int id) {
		// TODO Auto-generated method stub
		//Si el id es correcto y la factura existe, muestra los datos de la factura.
		Factura factura = listaFacturas.get(id);
		if (factura != null) {
			System.out.println("Datos de la factura: ");
			System.out.println(factura.toString());
			setFacturaBuscada(factura);
			return true;
		} else {
			System.out.println("El id  es incorrecto");
			return false;
		}
	}
	public Factura getFacturaBuscada() {
		// TODO Auto-generated method stub
		return facturabuscada;
	}
	public boolean recuperarFacturasCliente(String nif) {
		// TODO Auto-generated method stub
		facturascliente = new ArrayList<Factura>();
		Cliente cliente = listaClientes.get(nif);
		if (cliente != null) {
			ArrayList<Integer> facturas = cliente.getListIdFactuas();
			if (facturas.isEmpty()) {
				System.out.println("Este cliente no tiene facturas");
				return false;
			} else {

				System.out.println("Las facturas son: ");
				for (Integer i : facturas) {
					facturascliente.add(listaFacturas.get(i));
					System.out.println(listaFacturas.get(i).toString());

				}

				return true;
			}
		} else {
			System.out.println("El nif es incorecto");
			return false;
		}
	}
	
	public Integer getIdFactura() {
		return idFactura;
	}
	
	public List<Factura> getFacturascliente() {
		// TODO Auto-generated method stub
		return facturascliente;
	}
	public HashMap<Integer, Factura> getListaFacturas() {
		// TODO Auto-generated method stub
		return listaFacturas;
	}
}
