package factoria;

public enum TipoTarifaHorario {
	MANYANA("Precio especial por la manyana"),
	NOCTURNA("Precio especial por la noche");
	private String descripcion;
	private TipoTarifaHorario(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public static TipoTarifaHorario getOpcion(int posicion) {
        return values()[posicion];
    }
	
	public static String getMenu() {
		StringBuilder sb = new StringBuilder();
		for (TipoTarifaHorario opcion : TipoTarifaHorario.values()) {
			sb.append(opcion.ordinal());
            sb.append(".- ");
            sb.append(opcion.descripcion);
            sb.append("\n");
		}
		return sb.toString();
	}
}
