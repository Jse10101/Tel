package controlador;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import factoria.FactoriaTarifas;
import factoria.TipoTarifaEspecial;
import cliente.Cliente;
import cliente.Empresa;
import cliente.Particular;
import datos.BaseDeDatos;
import direccion.Direccion;
import excepciones.CodigoInvalido;
import excepciones.ErrorFecha;
import excepciones.NifInvalido;
import factura.Factura;
import generadorNombres.GeneradorNombres;
import entradaSalida.EntradaSalida;
import llamada.Llamada;
import modelo.ModeloInt;
import tarifa.Tarifa;
import vista.Ejecuta;
import vista.Vista;
import vista.VistaInt;

public class Controlador implements ControladorInt {
	private ModeloInt modelo;
	private VistaInt vista;

	public boolean generarFactura(String nif) throws ErrorFecha, NifInvalido {
		
		System.out.println("Soy el controlador  :" + nif);
		boolean result = modelo.generarFactura(nif);
		return result;
	}

	@Override
	public void setVista(VistaInt vista) {
		// TODO Auto-generated method stub
		this.vista = vista;

	}

	public void setModelo(ModeloInt modelo) {
		// TODO Auto-generated method stub
		this.modelo = modelo;
	}

	@Override
	public ModeloInt cargar() {
		// TODO Auto-generated method stub
		modelo.cargar();
		return modelo;

	}

	@Override
	public boolean recuperarDatosFactura(int codigo) throws CodigoInvalido {
		// TODO Auto-generated method stub
		boolean result = modelo.recuperarDatosFactura(codigo);
		return result;
	}

	@Override
	public boolean recuperarFacurasCliente(String nif) {
		// TODO Auto-generated method stub
		boolean result = modelo.recuperarFacturasCliente(nif);
		return result;
	}

	@Override
	public void guardar() {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Factura> recuperarFactFechas(Calendar i, Calendar f, String NIF) throws ErrorFecha {
		// TODO Auto-generated method stub
		if (modelo.getCliente(NIF) != null) {
			Cliente cliente = modelo.getCliente(NIF);
			ArrayList<Factura> datosFiltrar = new ArrayList<Factura>();
			for (int id : cliente.getListaCodigoFacturas()) {
				datosFiltrar.add(modelo.getListaFacturas().get(id));
			}
			ArrayList<Factura> datosMostrar = modelo.recuperaEntreFechas(datosFiltrar, i, f);
			return datosMostrar;
		}
		return null;
	}

	@Override
	public boolean altaLlamada(String nif, int tel, double tiempo) throws NifInvalido {
		// TODO Auto-generated method stub
		Calendar fecha = Calendar.getInstance();
		Llamada llamada = new Llamada(tel, fecha, tiempo);
		boolean result = modelo.addLlamada(llamada, nif);
		return result;
	}

	@Override
	public boolean listLlamdasCliente(String nif) throws NifInvalido {
		// TODO Auto-generated method stub
		boolean result = modelo.LlamadasDeUnCliente(nif);
		return result;
	}

	@Override
	public List<Llamada> recuperarLlamadasFechas(Calendar i, Calendar f, String NIF) throws ErrorFecha {
		// TODO Auto-generated method stub
		if (modelo.getCliente(NIF) != null) {
			ArrayList<Llamada> datosMostrar = modelo.recuperaEntreFechas(modelo.getCliente(NIF).getListaLlamadas(), i, f);

			if (datosMostrar.isEmpty()) {
				System.out.println("No hay llamadas en este perido");
			}
			for (Llamada l : datosMostrar) {
				System.out.println(l.toString());
			}
			return datosMostrar;
		}
		return null;
	}

	@Override
	public boolean nuevoCliente(String nombre, String apellidos, String dni, String pc, String prov, String pueblo,
			Float tarifaB, Float tarifaE, String correo, int esp) {
		FactoriaTarifas factoriaN = new FactoriaTarifas();
		Tarifa basica = new Tarifa(tarifaB);
		Tarifa tarifaEspecial = factoriaN.getTarifa(TipoTarifaEspecial.getOpcion(esp), basica, tarifaE);

		Direccion dir = new Direccion(pc, prov, pueblo);

		Calendar fecha = Calendar.getInstance();

		Cliente cliente = new Particular(nombre, dni, dir, correo, fecha, tarifaEspecial, apellidos);

		boolean result = modelo.nuevoCliente(cliente);
		return result;
	}

	@Override
	public boolean nuevaEmpresa(String name, String dni, String pc, String prov, String pueblo, Float tarifaB,
			Float tarifaE, String correo, int esp) {
		FactoriaTarifas factoriaN = new FactoriaTarifas();
		Tarifa basica = new Tarifa(tarifaB);
		Tarifa tarifaEspecial = factoriaN.getTarifa(TipoTarifaEspecial.getOpcion(esp), basica, tarifaE);

		Direccion dir = new Direccion(pc, prov, pueblo);

		Calendar fecha = Calendar.getInstance();

		Cliente cliente = new Empresa(name, dni, dir, correo, fecha, tarifaEspecial);

		boolean result = modelo.nuevoCliente(cliente);
		return result;
	}

	@Override
	public boolean borrarCliente(String niff) {
		boolean result = modelo.borrarCliente(niff);
		return result;
	}

	@Override
	public boolean cambiarTarifa(String dni, Float tarifaB, Float tarifaE, int esp) {
		FactoriaTarifas factoriaN = new FactoriaTarifas();
		Tarifa basica = new Tarifa(tarifaB);
		Tarifa tarifaEspecial = factoriaN.getTarifa(TipoTarifaEspecial.getOpcion(esp), basica, tarifaE);

		Boolean result = modelo.cambiarTarifa(dni, tarifaEspecial);
		return result;
	}

	@Override
	public Cliente mostrarCliente(String niff) {
		Cliente cliente = modelo.getCliente(niff);
		return cliente;
	}

	@Override
	public ArrayList<Cliente> mostrarTodos() {
		ArrayList<Cliente> datosMostrar = new ArrayList<Cliente>();
		for(String nif: modelo.getAllClients()) {
			datosMostrar.add(modelo.getCliente(nif));
		}

		if (datosMostrar.isEmpty()) {
			System.out.println("No hay clientes en este periodo");
		}
		for (Cliente cli : datosMostrar) {
			System.out.println(cli.toString());
		}
		return datosMostrar;
	}

	@Override
	public ArrayList<Cliente> mostrarEntreFechas(Calendar i, Calendar f) throws ErrorFecha {
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		for(String nif: modelo.getAllClients()) {
			clientes.add(modelo.getCliente(nif));
		}
		ArrayList<Cliente> datosMostrar = modelo.recuperaEntreFechas(clientes, i, f);

		if (datosMostrar.isEmpty()) {
			System.out.println("No hay clientes en este periodo");
		}
		for (Cliente cli : datosMostrar) {
			System.out.println(cli.toString());
		}
		return datosMostrar;
	}
}
