package llamada;

import java.io.Serializable;
import java.util.Calendar;
import fecha.FechaInt;

public class Llamada implements FechaInt, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int telefono;
	private Calendar fecha;
	private double duracion;
	
	//Constructor por defecto
	public Llamada(){
		super();
	}
	//Constructores
	public Llamada(int telefono, Calendar fecha, double duracion){
		super();
		this.telefono = telefono;
		this.fecha = fecha;
		this.duracion = duracion;
	}
	
	public Llamada(int telefono, double duracion) {
		super();
		this.telefono = telefono;
		this.fecha = Calendar.getInstance();
		this.duracion = duracion;
	}
	//Gets
	public int getTelefono() {
		return telefono;
	}

	public Calendar getFecha() {
		return fecha;
	}

	public double getDuracion() {
		return duracion;
	}
	//ToString
	public String toString() {
		return "Telefono: " + telefono + " | Fecha: " + fecha.getTime().toString() + " | Duracion: " + duracion+ "\n";
	}
}
