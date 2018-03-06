package menu;

public enum Aplicacion {
	CLIENTE("Operaciones con clientes"),
	LLAMADA("Operaciones con llamadas"),
	FACTURAS("Operaciones con facturas"),
	SALIR("Salir del menu");
	
	private String descripcion;
	
	private Aplicacion(String descripcion){
		this.descripcion = descripcion;
	}
	
	public String getDescripcion(){
		return descripcion;
	}
	
	public static Aplicacion getOpcion(int posicion) {
		return values()[posicion];
	}
	
	public static String getMenu(){
		StringBuilder sb = new StringBuilder();
		for(Aplicacion opcion : Aplicacion.values()){
			sb.append(opcion.ordinal());
			sb.append(".-");
			sb.append(opcion.getDescripcion());
			sb.append("\n");
		}
		return sb.toString();
	}
}
