package datos;

public class Direccion {
	int codigoPostal;
	String provincia;
	String poblacion;
	
	public Direccion(){
		super();
	}
	
	public Direccion(int codigoPostal, String provincia, String poblacion){
		super();
		this.codigoPostal = codigoPostal;
		this.provincia = provincia;
		this.poblacion = poblacion;
	}
}