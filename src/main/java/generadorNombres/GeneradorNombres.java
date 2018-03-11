package generadorNombres;

import cliente.Cliente;
import cliente.Empresa;
import cliente.Particular;
import es.uji.www.GeneradorDatosINE;

public class GeneradorNombres {
	public static Cliente generaEmpresa() {
		GeneradorDatosINE generador = new GeneradorDatosINE();
		Cliente cliente = new Empresa(generador.getNombre(), generador.getNIF());
		return cliente;
	}
	
	public static Cliente generaParticular() {
		GeneradorDatosINE generador = new GeneradorDatosINE();
		Cliente cliente = new Particular(generador.getNombre(), generador.getApellido(), generador.getNIF());
		return cliente;
	}

}