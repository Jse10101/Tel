package datos;

public class Direccion {
	String codigoPostal;
	String provincia;
	String poblacion;
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
	
	// Sets y gets
	public String getCodPos() {
		return codigoPostal;
	}

	public void setCodPos(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getPoblacion() {
		return poblacion;
	}

	public void setPobl(String poblacion) {
		this.poblacion = poblacion;
	}

	@Override
	public String toString() {
		return "CP: " + getCodPos() + " | Provincia: " + getProvincia() + " | Poblacion: " + getPoblacion();
	}
}