package llamadas;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import cliente.Cliente;
import datos.BaseDeDatos;
import generadorNombres.GeneradorNombres;
import llamada.Llamada;

public class LlamadasTest {
	static BaseDeDatos bd = new BaseDeDatos();
	private Cliente clienteEmp, clientePar;
	
	@Before
	public void init() {
		clienteEmp = GeneradorNombres.generaEmpresa();
		clientePar = GeneradorNombres.generaEmpresa();
		bd.nuevoCliente(clienteEmp);
		bd.nuevoCliente(clientePar);
	}
	
	@Test
	public void addLlamadaEmpTest() { // Probamos el funcionamiento de añadir llamada y añadimos una a una empresa.
		assertEquals(true, bd.addLlamada(new Llamada(), clienteEmp.getNif()));
	}
	
	@Test
	public void addLlamadaParTest() {// Probamos el funcionamiento de añadir llamada y añadimos una a un particular.
		assertEquals(true, bd.addLlamada(new Llamada(987654321, Calendar.getInstance(), 10.5), clienteEmp.getNif()));
	}
	
	@Test
	public void listaLlamadasClienteNoExisteTest() {// El cliente del cual se quieren mostrar las llamadas no existe.
		assertEquals(false, bd.LlamadasDeUnCliente("ABCD1984"));
	}
	
	@Test
	public void listaLlamadasClienteParTest() {//El cliente particular hace un par de llamadas y muestra las llamadas.
		bd.addLlamada(new Llamada(987654321, 28), clientePar.getNif());
		bd.addLlamada(new Llamada(123456789, Calendar.getInstance(), 8), clientePar.getNif());
		assertEquals(true, bd.LlamadasDeUnCliente(clientePar.getNif()));

	}
	
}
