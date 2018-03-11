package factura;

import java.util.Calendar;

import fecha.FechaInt;
import tarifa.Tarifa;

public class Factura implements FechaInt {
	private Integer codigo;
	private Tarifa tarifa;
	private Calendar fechaInicio;
	private Calendar fechaFin;
	private Calendar fechaDeEmision;
	private double importe;
	
	//Constructor por defecto
	public Factura(){
		super();
	}
	//Constructor
	public Factura(Integer codigo, Calendar fechaInicio, Calendar fechaFin, Calendar fechaDeEmision, Tarifa tarifa, double importe) {
		super();
		this.codigo = codigo;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.fechaDeEmision = fechaDeEmision;
		this.tarifa = tarifa;
		this.importe = importe;
	}
	
	//Sets y gets
	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Tarifa getTarifa() {
		return tarifa;
	}

	public void setTarifa(Tarifa tarifa) {
		this.tarifa = tarifa;
	}

	public Calendar getFecha() {
		return fechaDeEmision;
	}

	public void setFechaDeEmision(Calendar fechaDeEmision) {
		this.fechaDeEmision = fechaDeEmision;
	}
	
	public Calendar getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicial(Calendar fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Calendar getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Calendar fechaFin) {
		this.fechaFin = fechaFin;
	}

	public double getImporte() {
		return importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}
	
	//ToString
	public String toString() {
		return "Código: " + codigo + " | Tarifa: " + tarifa.toString() + " | Fecha Realizacion: "
				+ fechaDeEmision.getTime().toString() + " | Fecha Inicial: " + fechaInicio.getTime().toString()
				+ " | Fecha Fin: " + fechaFin.getTime().toString() + " | Importe: " + importe + "€" + "\n";

	}
}