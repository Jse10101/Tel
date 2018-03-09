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
	private ArrayList<Integer> listCodigoFacturas; 
	private ArrayList<Llamada> listLlamadas;
	private Calendar fechaUltimaFactura;
	
	//Constructor por defecto
	public Cliente(){
		super();
		this.listCodigoFacturas = new ArrayList<Integer>();
		this.listLlamadas = new ArrayList<Llamada>();
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
		this.listCodigoFacturas = new ArrayList<Integer>();
		this.listLlamadas = new ArrayList<Llamada>();
	}
	
	public Cliente(String nombre, String nif) {
		super();
		this.nombre = nombre;
		this.nif = nif;
		this.fechaAlta = Calendar.getInstance();
		this.tarifa = new Tarifa(28.8);
		this.listCodigoFacturas = new ArrayList<Integer>();
		this.listLlamadas = new ArrayList<Llamada>();
	}
	//Sets y gets
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	public String getCorreo() {
		return correoElectronico;
	}

	public void setCorreo(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public Calendar getFecha() {
		return fechaAlta;
	}

	public void setFechaAlta(Calendar fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public Tarifa getTarifa() {
		return tarifa;
	}

	public void setTarifa(Tarifa tarifa) {
		this.tarifa = tarifa;
	}

	public ArrayList<Integer> getListCodigoFacturas() {
		return listCodigoFacturas;
	}

	public void setListCodigoFacturas(ArrayList<Integer> listCodigoFacturas) {
		this.listCodigoFacturas = listCodigoFacturas;
	}

	public ArrayList<Llamada> getListLlamadas() {
		return listLlamadas;
	}

	public void setListLlamadas(ArrayList<Llamada> listLlamadas) {
		this.listLlamadas = listLlamadas;
	}

	public Calendar getFechaUltimaFactura() {
		return fechaUltimaFactura;
	}

	public void setFechaUltimaFactura(Calendar fechaultimafactura) {
		this.fechaUltimaFactura = fechaultimafactura;
	}
}