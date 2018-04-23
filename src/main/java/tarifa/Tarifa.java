package tarifa;

import java.io.Serializable;

import llamada.Llamada;

public class Tarifa implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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

	@Override
	public String toString() {
		return "Tarifa: " + precio + "€";
	}
}