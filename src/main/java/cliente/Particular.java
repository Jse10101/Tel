package cliente;

import java.io.Serializable;
import java.util.Calendar;

import direccion.Direccion;
import tarifa.Tarifa;

public class Particular extends Cliente implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String apellidos;
	//Contructores por defecto
	public Particular() {
		super();

	}
	//Constructores
	public Particular(String nombre, String nif, Direccion direccion, String correo, Calendar fechaAlta, Tarifa tarifa, String apellidos) {
		super(nombre, nif, direccion, correo, fechaAlta, tarifa);
		this.apellidos = apellidos;
	}
	
	public Particular(String nombre, String apellidos, String nif) {
		super(nombre, nif);
		this.apellidos = apellidos;
	}
	
	//ToString
	public String toString() {
		return "Nombre: " + getNombre() + "\nApellidos: " + apellidos + "\nNIF: " + getNif() + "\n"
				+ getDireccion().toString() + "\nCorreo: " + getCorreo() + "\nTarifa: " + getTarifa().toString();
	}
}