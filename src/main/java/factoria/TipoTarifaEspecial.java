package factoria;

public enum TipoTarifaEspecial {
	NOCTURNO("Precio especial Nocturno"),
	SABADO("Precio especial el Sabado"),
	DOMINGO("Precio especial el Domingo");
	
	private String descripcion;
	private TipoTarifaEspecial(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public static TipoTarifaEspecial getOpcion(int posicion) {
        return values()[posicion];
    }
	
	public static String getMenu() {
		StringBuilder sb = new StringBuilder();
		for (TipoTarifaEspecial opcion : TipoTarifaEspecial.values()) {
			sb.append(opcion.ordinal());
            sb.append(".- ");
            sb.append(opcion.descripcion);
            sb.append("\n");
		}
		return sb.toString();
	}
}