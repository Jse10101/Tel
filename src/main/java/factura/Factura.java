package factura;

import java.util.Calendar;
import tarifa.Tarifa;

public class Factura {
	Integer codigo;
	Tarifa tarifa;
	Calendar fechaDeEmision;
	Calendar fechaInicio;
	Calendar fechaFin;
	double importe;
	
	Factura(){
		super();
	}
	Factura(Tarifa tarifa, int codigo, Calendar fechaDeEmision, Calendar[] periodoDeFacturacion, double importe){
		super();
		this.codigo = codigo;
		this.tarifa = tarifa;
		this.fechaDeEmision = fechaDeEmision;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.importe = importe;
	}
}