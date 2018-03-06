package cliente;

import java.util.Calendar;
import datos.Direccion;
import tarifa.Tarifa;

public class Particular extends Cliente{
	private String apellidos;
	
	public Particular() {
		super();

	}

	public Particular(String nombre, String NIF, Direccion direccion, String correo, Calendar fechaAlta, Tarifa tarifa, String apellidos) {
		super(nombre, NIF, direccion, correo, fechaAlta, tarifa);
		this.apellidos = apellidos;

	}
}