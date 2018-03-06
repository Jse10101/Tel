package llamada;

import java.util.Calendar;

public class Llamada {
	int telefono;
	Calendar fecha;
	Calendar horaDeLaLlamada;
	int duracion;
	
	Llamada(){
		super();
	}
	Llamada(int telefono, Calendar fecha, Calendar horaDeLaLlamada, int duracion){
		super();
		this.telefono = telefono;
		this.fecha = fecha;
		this.horaDeLaLlamada = horaDeLaLlamada;
		this.duracion = duracion;
	}
}