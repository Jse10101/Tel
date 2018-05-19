package tarifa;

import java.io.Serializable;
import java.util.Calendar;

import llamada.Llamada;

public class TarifaSabado extends Tarifa implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Tarifa tarifa;
	private final int dia=7;

	
	public TarifaSabado(Tarifa tarifa,double precio){
		super(precio);
		this.tarifa=tarifa;
	}
	
	@Override
	public double getPrecio(Llamada llamada){
		if (llamada.getFecha().get(Calendar.DAY_OF_WEEK)==(dia)){
			return (Math.min(super.getPrecio(llamada),tarifa.getPrecio(llamada)));
		}
	    return tarifa.getPrecio(llamada);
	}
	
	@Override
	public String toString() {
		return "Sabado" + super.toString() + tarifa.toString() + " ";
	}
	
	public Tarifa getTarifa() {
		return tarifa;
	}
	
	public void setTarifa(Tarifa tarifa) {
		this.tarifa = tarifa;
	}
}
