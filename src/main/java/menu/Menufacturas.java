package menu;

public enum MenuFacturas {
	EMITIR_FACTURA("Emitir una factura"),
	RECUPERAR_FACTURA("Recuperar los datos de una factura"),
	RECUPERAR_FACTURAS_CLIENTE("Recuperar todas las facturas de un cliente");
	
	private String descripcion;

	private MenuFacturas(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public static MenuFacturas getOpcion(int posicion) {
		return values()[posicion];
	}

	public static String getMenu() {
		StringBuilder sb = new StringBuilder();
		for (MenuFacturas opcion : MenuFacturas.values()) {
			sb.append(opcion.ordinal());
			sb.append(".- ");
			sb.append(opcion.getDescripcion());
			sb.append("\n");
		}
		return sb.toString();
	}
}