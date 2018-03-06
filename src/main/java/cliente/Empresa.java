package cliente;

import java.util.Calendar;

import datos.Direccion;
import tarifa.Tarifa;

public class Empresa extends Cliente{
	public Empresa() {
		super();
	}
	public Empresa(String nombre, String nif, Direccion direccion, String correo, Calendar fechaAlta, Tarifa tarifa) {
		super(nombre, nif, direccion, correo, fechaAlta, tarifa);

	}
}