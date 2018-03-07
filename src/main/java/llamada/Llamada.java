package llamada;

import java.util.Calendar;

public class Llamada {
	int telefono;
	Calendar fecha;
	double duracion;
	
	public Llamada(){
		super();
	}
	public Llamada(int telefono, Calendar fecha, double duracion){
		super();
		this.telefono = telefono;
		this.fecha = fecha;
		this.duracion = duracion;
	}
}