package factura;
import static org.junit.Assert.*;
import java.util.Calendar;
import org.junit.Before;
import org.junit.Test;
import cliente.Cliente;
import datos.BaseDeDatos;
import excepciones.CodigoInvalido;
import excepciones.ErrorFecha;
import excepciones.NifInvalido;
import generadorNombres.GeneradorNombres;
import llamada.Llamada;

public class FacturaTest {
	static BaseDeDatos bd = new BaseDeDatos();
	private Cliente clienteEmp, clientePar;
	
	@Before
	public void init() throws NifInvalido{
		//Tomamos un cliente empresa con llamadas y un particular sin llamadas.
		clienteEmp = GeneradorNombres.generaEmpresa();
		clientePar = GeneradorNombres.generaParticular();
		bd.nuevoCliente(clienteEmp);
		bd.nuevoCliente(clientePar);
		bd.addLlamada(new Llamada(123456789, 9), clienteEmp.getNif());
		bd.addLlamada(new Llamada(987654321, Calendar.getInstance(), 10.5), clienteEmp.getNif());
	}
	@Test
	public void generarFacturaNifNoExisteTest() throws ErrorFecha, NifInvalido { // Nif del cliente no existe
		assertEquals(false, bd.generarFactura("ABCD1984"));
	}
	
	@Test
	public void generarFacturaSinLlamadasTest() throws ErrorFecha, NifInvalido { // Nif del cliente sí existe pero no tiene llamadas ni facturas
		assertEquals(false, bd.generarFactura(clientePar.getNif()));
	}
	
	@Test
	public void recuperaFacturasClienteSinFacturasTest() throws NifInvalido { // Nif del cliente sí existe pero no tiene facturas 
		assertEquals(false, bd.recuperarFacturasCliente(clientePar.getNif()));
	}
	
	@Test
	public void recuperaFacturasClienteTest() throws NifInvalido {// Nif de cliente existe y tiene facturas
		assertEquals(false, bd.recuperarFacturasCliente(clienteEmp.getNif()));
	}
	
	@Test
	public void generarFacturaCalculaImporteTest() throws ErrorFecha, NifInvalido {//Comprobamos importe de factura
		bd.generarFactura(clienteEmp.getNif());
		assertEquals(561.6, bd.getListaFacturas().get((Integer) bd.getCodigoFactura() - 1).getImporte(), 0.0);
	}
	@Test
	public void generarFacturaVariasVecesTest() throws ErrorFecha, NifInvalido { // Cliente factura e intenta volver a facturar otra vez.
		assertEquals(true, bd.generarFactura(clienteEmp.getNif()));
		assertEquals(false, bd.generarFactura(clienteEmp.getNif()));
	}
	
	
	@Test
	public void recuperarFacturaCodigoSiExisteTest() throws CodigoInvalido {// Codigo cliente sí existe 
		assertEquals(true, bd.recuperarFacturaPorCodigo(bd.getCodigoFactura() - 1));
	}
	
	@Test
	public void recuperarFacturaCodigoNoExisteTest(){//Codigo cliente inexistente
		try {
			Integer codigo = bd.getCodigoFactura() + 150;
			bd.recuperarFacturaPorCodigo(codigo);
	        fail();
	    } 
	    catch (Exception e) {
	        final String expected = "El código introucido es inválido.";
	        assertEquals(expected, e.getMessage());
	    }
	}
}
