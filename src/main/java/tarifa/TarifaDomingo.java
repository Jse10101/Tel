package tarifa;

import java.io.Serializable;
import java.util.Calendar;

import llamada.Llamada;

public class TarifaDomingo extends Tarifa implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Tarifa tarifa;
	private final int domingo = 1;
	
	//Constructor
	public TarifaDomingo(Tarifa tarifa,double precio){
		super(precio);
		this.tarifa=tarifa;
	}
	
	@Override
	public double getPrecio(Llamada llamada){
		if (llamada.getFecha().get(Calendar.DAY_OF_WEEK)==(domingo)){
			return 0.0;
		}
	    return tarifa.getPrecio(llamada);
	}
	
	@Override
	public String toString() {
		return "Domingo" + super.toString() + tarifa.toString() + " ";
	}
	
	public Tarifa getTarifa() {
		return tarifa;
	}
	
	public void setTarifa(Tarifa tarifa) {
		this.tarifa = tarifa;
	}
}