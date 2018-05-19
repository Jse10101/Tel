package factoria;

public enum TipoTarifaDia {
	SABADO("Precio especial el Sabado"),
	DOMINGO("Precio especial el Domingo");
	private String descripcion;
	private TipoTarifaDia(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public static TipoTarifaDia getOpcion(int posicion) {
        return values()[posicion];
    }
	
	public static String getMenu() {
		StringBuilder sb = new StringBuilder();
		for (TipoTarifaDia opcion : TipoTarifaDia.values()) {
			sb.append(opcion.ordinal());
            sb.append(".- ");
            sb.append(opcion.descripcion);
            sb.append("\n");
		}
		return sb.toString();
	}
}
