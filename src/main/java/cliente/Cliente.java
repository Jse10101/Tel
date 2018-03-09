package cliente;

import java.util.ArrayList;
import java.util.Calendar;

import datos.Direccion;
import fecha.FechaInt;
import llamada.Llamada;
import tarifa.Tarifa;

public abstract class Cliente implements FechaInt {
	private String nombre;
	private String nif;
	private Direccion direccion;
	private String correoElectronico;
	private Calendar fechaAlta;
	private Tarifa tarifa;
	private ArrayList<Integer> listaCodigoFacturas; 
	private ArrayList<Llamada> listaLlamadas;
	private Calendar fechaUltimaFactura;
	
	//Constructor por defecto
	public Cliente(){
		super();
		this.listaCodigoFacturas = new ArrayList<Integer>();
		this.listaLlamadas = new ArrayList<Llamada>();
	}
	//Constructores
	public Cliente(String nombre, String nif, Direccion direccion, String correoElectronico, Calendar fechaAlta, Tarifa tarifa){
		super();
		this.nombre = nombre;
		this.nif = nif;
		this.direccion = direccion;
		this.correoElectronico = correoElectronico;
		this.fechaAlta = fechaAlta;
		this.tarifa = tarifa;
		this.listaCodigoFacturas = new ArrayList<Integer>();
		this.listaLlamadas = new ArrayList<Llamada>();
	}
	
	public Cliente(String nombre, String nif) {
		super();
		this.nombre = nombre;
		this.nif = nif;
		this.fechaAlta = Calendar.getInstance();
		this.tarifa = new Tarifa(28.8);
		this.listaCodigoFacturas = new ArrayList<Integer>();
		this.listaLlamadas = new ArrayList<Llamada>();
	}
	//Sets y gets
	public String getNombre() {
		return nombre;
	}

	public String getNif() {
		return nif;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public String getCorreo() {
		return correoElectronico;
	}

	public Calendar getFecha() {
		return fechaAlta;
	}

	public Tarifa getTarifa() {
		return tarifa;
	}

	public void setTarifa(Tarifa tarifa) {
		this.tarifa = tarifa;
	}

	public ArrayList<Integer> getListCodigoFacturas() {
		return listaCodigoFacturas;
	}

	public ArrayList<Llamada> getListLlamadas() {
		return listaLlamadas;
	}

	public Calendar getFechaUltimaFactura() {
		return fechaUltimaFactura;
	}

	public void setFechaUltimaFactura(Calendar fechaultimafactura) {
		this.fechaUltimaFactura = fechaultimafactura;
	}
}