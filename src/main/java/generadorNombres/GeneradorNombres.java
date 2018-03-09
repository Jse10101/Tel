package generadorNombres;

import cliente.Cliente;
import cliente.Empresa;
import cliente.Particular;
import es.uji.www.GeneradorDatosINE;

public class GeneradorNombres {
	public static void main(String[] args) {
		GeneradorDatosINE generador = new GeneradorDatosINE();
		System.out.println(muestra(generador));
	}

	private static String muestra(GeneradorDatosINE generador) {
		StringBuilder sb = new StringBuilder();
		sb.append("Nombre: " + generador.getNombre() + " " + generador.getApellido() + "\n");
		sb.append("NIF: " + generador.getNIF() + "\t" + "Edad: " + generador.getEdad() + "\n");

		String provincia = generador.getProvincia();
		sb.append("Domicilio: " + generador.getPoblacion(provincia) + " (" + provincia + ")");

		return sb.toString();
	}
	
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