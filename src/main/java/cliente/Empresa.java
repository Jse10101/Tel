package cliente;

import java.util.Calendar;

import datos.Direccion;
import tarifa.Tarifa;

public class Empresa extends Cliente{
	
	//Constructor por defecto
	public Empresa() {
		super();
	}
	
	//Constructores
	public Empresa(String nombre, String nif, Direccion direccion, String correo, Calendar fechaAlta, Tarifa tarifa) {
		super(nombre, nif, direccion, correo, fechaAlta, tarifa);

	}
	public Empresa(String nombre, String nif) {
		super(nombre, nif);
	}
	
	//ToString
	public String toString() {
		return "Nombre: " + getNombre() + "\nNIF: " + getNif() + "\n" + getDireccion().toString() + "\nCorreo: "
				+ getCorreo() + "\nTarifa: " + getTarifa().toString();
	}
}