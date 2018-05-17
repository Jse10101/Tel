package modelo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import cliente.Cliente;
import datos.BaseDeDatos;
import excepciones.CodigoInvalido;
import excepciones.ErrorFecha;
import excepciones.NifInvalido;
import factura.Factura;
import entradaSalida.EntradaSalida;
import fecha.FechaInt;
import llamada.Llamada;
import tarifa.Tarifa;

public class GestorModelo implements ModeloInt{
	BaseDeDatos datos;
	public GestorModelo() {
		super();
		datos=new BaseDeDatos();
	}
	 public boolean generarFactura(String nif) throws ErrorFecha, NifInvalido{
		 return  datos.generarFactura(nif);
	 }
	public  boolean nuevoCliente(Cliente cliente){
		return datos.nuevoCliente(cliente);
	}
	 
	  public boolean recuperarFacturaPorCodigo(int codigo) throws CodigoInvalido{
		  return datos.recuperarFacturaPorCodigo(codigo);
	  }
	  public Factura getFacturaBuscada(){
		  return datos.getFacturaBuscada();
	  }
	  public boolean recuperarFacturasCliente(String nif) throws NifInvalido, CodigoInvalido{
		  return datos.recuperarFacturasCliente(nif);
	  }
	  public List<Factura> getFacturasCliente(){
		  return datos.getFacturasCliente();
	  }
	  public void cargar(){
		  datos=EntradaSalida.abrirDatos();
	  }
	  public <T extends FechaInt> ArrayList<T> recuperaEntreFechas(ArrayList<T>datos, Calendar inicio, Calendar fin) throws ErrorFecha{
		  return ((ModeloInt) datos).recuperaEntreFechas(datos, inicio ,fin);
	  }
	  public cliente.Cliente getCliente(String NIF){
		  return datos.getCliente(NIF);
	  }
	public   HashMap<Integer, Factura> getListaFacturas(){
		return datos.getListaFacturas();
	}
	 public  boolean addLlamada(Llamada llamada, String nif) throws NifInvalido{
		 return datos.addLlamada(llamada, nif);
	 }
	  public boolean LlamadasDeUnCliente(String nif) throws NifInvalido{
		  return datos.LlamadasDeUnCliente(nif);
	  }
	public   List<Llamada> getListLlamadasCliente(){
		return datos.getListLlamadasCliente();
	}

	public void guardar() {
		// TODO Auto-generated method stub
		EntradaSalida.GuardarDatos(datos);
	}
	public boolean borrarCliente(String nif) {
		return datos.borrarCliente(nif);
	}
	public boolean cambiarTarifa(String NIF, Tarifa tarifa) {
		return datos.cambiarTarifa(NIF, tarifa);
	}
	public Set<String> getAllClients() {
		return datos.getAllClients();
	}
}
