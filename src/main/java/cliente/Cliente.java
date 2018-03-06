package cliente;

import java.util.ArrayList;
import java.util.Calendar;
import tarifa.Tarifa;
import datos.Direccion;
import factura.Factura;
import llamada.Llamada;
import datos.Direccion;
import interfazFecha.Fecha;
import llamada.Llamada;
import tarifa.Tarifa;

public abstract class Cliente {
	private String nombre;
	private String NIF;
	private Direccion direccion;
	private String correoElectronico;
	private Calendar fechaDeAlta;
	private Tarifa tarifa;
	private ArrayList<Factura> listFacturas; 
	private ArrayList<Llamada> listLlamadas;
	
	public Cliente(){
		super();
		this.listFacturas = new ArrayList<Factura>();
		this.listLlamadas = new ArrayList<Llamada>();
	}
	
	public Cliente(String nombre, String NIF, Direccion direccion, String correoElectronico, Calendar fechaDeAlta, Tarifa tarifa){
		super();
		this.nombre = nombre;
		this.NIF = NIF;
		this.direccion = direccion;
		this.correoElectronico = correoElectronico;
		this.fechaDeAlta = fechaDeAlta;
		this.tarifa = tarifa;
		this.listFacturas = new ArrayList<Factura>();
		this.listLlamadas = new ArrayList<Llamada>();
	}
	//No va aquí.
	public ArrayList<Llamada> getListLlamadas() {
		return listLlamadas;
	}
	
	public ArrayList<Factura> getListIdFactuas() {
		return listFacturas;
	}
	
	public void cambiaTarifa(Tarifa nuevaTarifa) {
		this.tarifa = nuevaTarifa;
	}
	
	public void addLlamada(int telefono, Calendar fecha, Calendar horaDeLaLlamada, int duracion) {
		listLlamadas.add(new Llamada(telefono, fecha, horaDeLaLlamada, duracion));
	}
}