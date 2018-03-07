package cliente;

import java.util.ArrayList;
import java.util.Calendar;

import datos.Direccion;
import fecha.FechaInt;
import llamada.Llamada;
import tarifa.Tarifa;

public abstract class Cliente {
	private String nombre;
	private String nif;
	private Direccion direccion;
	private String correoElectronico;
	private Calendar fechaAlta;
	private Tarifa tarifa;
	private ArrayList<Integer> listIdFacturas; 
	private ArrayList<Llamada> listLlamadas;
	private Calendar fechaultimafactura;
	//lmao
	public Cliente(){
		super();
		this.listIdFacturas = new ArrayList<Integer>();
		this.listLlamadas = new ArrayList<Llamada>();
	}
	
	public Cliente(String nombre, String nif, Direccion direccion, String correoElectronico, Calendar fechaAlta, Tarifa tarifa){
		super();
		this.nombre = nombre;
		this.nif = nif;
		this.direccion = direccion;
		this.correoElectronico = correoElectronico;
		this.fechaAlta = fechaAlta;
		this.tarifa = tarifa;
		this.listIdFacturas = new ArrayList<Integer>();
		this.listLlamadas = new ArrayList<Llamada>();
	}
	
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

	public ArrayList<Integer> getListIdFacturas() {
		return listIdFacturas;
	}

	public void setListIdFactuas(ArrayList<Integer> listFactuas) {
		this.listIdFacturas = listIdFacturas;
	}

	public ArrayList<Llamada> getListLlamadas() {
		return listLlamadas;
	}

	public void setListLlamadas(ArrayList<Llamada> listLlamadas) {
		this.listLlamadas = listLlamadas;
	}

	public Calendar getFechaultimafactura() {
		return fechaultimafactura;
	}

	public void setFechaultimafactura(Calendar fechaultimafactura) {
		this.fechaultimafactura = fechaultimafactura;
	}
}