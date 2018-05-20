package direccion;

import java.io.Serializable;

public class Direccion implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codigoPostal;
	private String provincia;
	private String poblacion;
	//Constructor por defecto
	public Direccion(){
		super();
	}
	//Constructor
	public Direccion(String codigoPostal, String provincia, String poblacion){
		super();
		this.codigoPostal = codigoPostal;
		this.provincia = provincia;
		this.poblacion = poblacion;
	}

	@Override
	public String toString() {
		return "CP: " + codigoPostal + "  Provincia: " + provincia + "  Poblacion: " + poblacion;
	}
}