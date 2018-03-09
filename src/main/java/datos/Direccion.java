package datos;

public class Direccion {
	int codigoPostal;
	String provincia;
	String poblacion;
	//Constructor por defecto
	public Direccion(){
		super();
	}
	//Constructor
	public Direccion(int codigoPostal, String provincia, String poblacion){
		super();
		this.codigoPostal = codigoPostal;
		this.provincia = provincia;
		this.poblacion = poblacion;
	}
}