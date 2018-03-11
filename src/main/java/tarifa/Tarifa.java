package tarifa;

import llamada.Llamada;

public class Tarifa {
	private double precio;
	
	//Constructor por defecto
	public Tarifa(){
		super();
	}
	//Constructor
	public Tarifa(double precio){
		super();
		this.precio = precio;
	}
	
	//Sets y gets
	public double getPrecio(Llamada llamada) {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	//ToString
	public String toString() {
		return "Tarifa: " + precio + "€";
	}
}