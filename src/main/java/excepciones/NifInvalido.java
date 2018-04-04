package excepciones;

import java.io.Serializable;

public class NifInvalido extends Exception implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NifInvalido(){
		super("El nif introucido es inválido.");
	}
}
