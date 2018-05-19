package tarifa;
import java.io.Serializable;
import java.util.Calendar;

import llamada.Llamada;

public class TarifaManyana extends Tarifa implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Tarifa tarifa;
	private final int inicio=8;
	 private final  int fin=12;
	
	public TarifaManyana(Tarifa tarifa,double precio){
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
		return "Manyana" + super.toString() + tarifa.toString() + " ";
	}
	
	public Tarifa getTarifa() {
		return tarifa;
	}
	
	public void setTarifa(Tarifa tarifa) {
		this.tarifa = tarifa;
	}

}