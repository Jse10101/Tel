package factoria;

public enum TipoCliente {
	PERSONA("Cliente Particular."),
	EMPRESA("Cliente Empresa.");
	
	private String descripcion;
	private TipoCliente(String descripcion){
		this.descripcion=descripcion;
	}
	
	public static String opciones() {
		StringBuilder sb = new StringBuilder();
		for(TipoCliente tipo: values())
		sb.append(tipo.ordinal() + ".- " + tipo.descripcion + "\n");
		return sb.toString();
		}
		 public static TipoCliente enteroATipo(int posicion) {
		return values()[posicion];
		}
}