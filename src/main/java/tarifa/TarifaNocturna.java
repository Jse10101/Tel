package tarifa;
import java.io.Serializable;
import java.util.Calendar;

import llamada.Llamada;

public class TarifaNocturna extends Tarifa implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Tarifa tarifa;
	private final int inicio=21;
	 private final  int fin=5;
	
	public TarifaNocturna(Tarifa tarifa,double precio){
		super(precio);
		this.tarifa=tarifa;
	}
	
	@Override
	public double getPrecio(Llamada llamada){
		
		
	if (llamada.getFecha().get(Calendar.HOUR_OF_DAY)<fin  || llamada.getFecha().get(Calendar.HOUR_OF_DAY)>=inicio ){
			return (Math.min(super.getPrecio(llamada),tarifa.getPrecio(llamada)));
		}
		
	    return tarifa.getPrecio(llamada);
	}
	
	@Override
	public String toString() {
		return "Noche" + super.toString() + tarifa.toString() + " ";
	}
	
	public Tarifa getTarifa() {
		return tarifa;
	}
	
	public void setTarifa(Tarifa tarifa) {
		this.tarifa = tarifa;
	}

}