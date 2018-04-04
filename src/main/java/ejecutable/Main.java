package ejecutable;

import excepciones.CodigoInvalido;
import excepciones.ErrorFecha;
import excepciones.NifInvalido;

public class Main {
	
	
	
	
	public static void main(String[] args) throws NifInvalido, CodigoInvalido, ErrorFecha {
		Menu nuevoMenu = new Menu();
		nuevoMenu.ejecuta();
	}
}