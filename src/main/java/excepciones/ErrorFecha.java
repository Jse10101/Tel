package excepciones;

import java.io.Serializable;

public class ErrorFecha extends Exception implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ErrorFecha(){
		super("Error al introducir las fechas.");
	}
}