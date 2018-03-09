package cliente;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import datos.BaseDeDatos;
import generadorNombres.GeneradorNombres;
import tarifa.Tarifa;

public class clienteTest {
	static BaseDeDatos bd = new BaseDeDatos();
	private Cliente clienteEmp, clientePar;
	
	@Before
	public void init(){
		clienteEmp = GeneradorNombres.generaEmpresa();
		clientePar = GeneradorNombres.generaEmpresa();
		bd.nuevoCliente(clienteEmp);
		bd.nuevoCliente(clientePar);
	}
	
	@Test
	public void nuevoClienteEmpTest() { //Añadir empresa nueva.
		Cliente nuevoEmp = GeneradorNombres.generaEmpresa();
		assertEquals(true, bd.nuevoCliente(nuevoEmp));
	}
	
	@Test
	public void nuevoClienteParTest() { //Añadir particular nuevo.
		Cliente nuevoPar = GeneradorNombres.generaParticular();
		assertEquals(true, bd.nuevoCliente(nuevoPar));
	}
	
	@Test
	public void nuevoClienteEmpExistenteTest() { //Añadir empresa que ya existe
		assertEquals(false, bd.nuevoCliente(clienteEmp));
	}
	
	@Test
	public void nuevoClienteParExistenteTest() { //Añadir particular que ya existe
		assertEquals(false, bd.nuevoCliente(clientePar));
	}
	
	@Test
	public void cambiarTarifaEmpTest() { // Cambiar tarifa a empresa que existe
		assertEquals(true, bd.cambiarTarifa(clienteEmp.getNif(), new Tarifa(10)));
	}
	
	@Test
	public void cambiarTarifaParTest() { // Cambiar tarifa a particular que existe
		assertEquals(true, bd.cambiarTarifa(clientePar.getNif(), new Tarifa(15)));
	}
	
	@Test
	public void cambiarTarifaEmpNoExisteTest() { // Cambiar tarifa a empresa que no existe
		assertEquals(false, bd.cambiarTarifa("ABCD1984", new Tarifa(6)));
	}
	
	@Test
	public void cambiarTarifaParNoExisteTest() { // Cambiar tarifa a particular que no existe
		assertEquals(false, bd.cambiarTarifa("ABCD1984", new Tarifa(3)));
	}
	
	@Test
	public void cambiarTarifaEmpComprobacionTest() { // Comprobar que la tarifa de la empresa cambia
		assertEquals(true, bd.cambiarTarifa(clienteEmp.getNif(), new Tarifa(10)));
		
	}
	
	@Test
	public void cambiarTarifaParComprobacionTest() { // Comprobar que la tarifa del particular cambia
		assertEquals(true, bd.cambiarTarifa(clientePar.getNif(), new Tarifa(15)));
		
	}
	
	@Test
	public void borrarClienteEmpTest() { // Borrar empresa que existe
		assertEquals(true, bd.borrarCliente(clienteEmp.getNif()));
	}
	
	@Test
	public void borrarClienteParTest() { // Borrar particular que existe
		assertEquals(true, bd.borrarCliente(clientePar.getNif()));
	}
	
	@Test
	public void borrarClienteEmpNoExisteTest() { // Borrar empresa que no existe
		assertEquals(false, bd.borrarCliente("ABCD1984"));
	}
	@Test
	public void borrarClienteParNoExisteTest() { // Borrar particular que no existe
		assertEquals(false, bd.borrarCliente("ABCD1984"));
	}
}
