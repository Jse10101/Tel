package ejecutable;


import java.util.Calendar;
import java.util.Scanner;

import llamada.Llamada;
import cliente.Cliente;
import cliente.Empresa;
import cliente.Particular;
import datos.BaseDeDatos;
import datos.Direccion;
import menu.Aplicacion;
import menu.MenuClientes;
import menu.MenuFacturas;
import menu.MenuLlamadas;
import tarifa.Tarifa;

public class Menu {
	private static BaseDeDatos bd;
	Scanner teclado = new Scanner(System.in);

	public Menu() {
		super();
		bd = new BaseDeDatos();
	}

	public void ejecuta() {
		Aplicacion opcion;
		Scanner teclado = new Scanner(System.in);
		do {
			System.out.println(Aplicacion.getMenu());
			System.out.print("Introduce una opción:");
			opcion = Aplicacion.getOpcion(teclado.nextInt());
			filtrarAplicacion(opcion);
		} while (opcion != Aplicacion.SALIR);
		teclado.close();
	}

	private void filtrarAplicacion(Aplicacion op) {
		switch (op) {
		case CLIENTE:
			menuCliente();
			break;
		case LLAMADA:
			menuLlamada();
			break;
		case FACTURAS:
			menuFactura();
			break;
		case SALIR:
			salir();
			break;
		}
	}

	private void salir() {
		System.out.println("Adiós");
	}

	private void menuCliente() {
		System.out.println(MenuClientes.getMenu());
		System.out.print("¿Qué operación desea realizar?");
		MenuClientes opcion = MenuClientes.getOpcion(teclado.nextInt());
		filtrarMenuClientes(opcion);
	}

	private void filtrarMenuClientes(MenuClientes opcion) {
		switch (opcion) {
		case NUEVO_CLIENTE:
			anyadirCliente(teclado);
			break;
		case BORRAR_CLIENTE:
			borrarCliente(teclado);
			break;
		case CAMBIAR_TARIFA:
			cambiarTarifa(teclado);
			break;
		case RECUPERAR_CLIENTE:
			mostrarCliente(teclado);
			break;
		case RECUPERAR_TODOS_CLIENTES:
			mostrarTodos();
			break;
		}
	}

	private static void mostrarTodos() {
		for (String c : bd.getAllClients()) {
			System.out.println(bd.getCliente(c).toString());
		}
	}

	private static void mostrarCliente(Scanner teclado) {
		System.out.println("NIF del cliente buscado:");
		String nif = teclado.next();
		if (bd.getListaClientes().containsKey(nif)) {
			System.out.println(bd.getCliente(nif).toString());
		} else {
			System.out.println("El cliente no existe");
		}
	}

	private static void cambiarTarifa(Scanner teclado) {
		System.out.println("NIF del cliente:");
		String nif = teclado.next();

		Cliente cliente = bd.getListaClientes().get(nif);
		if (cliente != null) {
			System.out.println("Precio de la tarifa: ");
			float precio = teclado.nextFloat();
			teclado.nextLine();
			Tarifa tarifa = new Tarifa(precio);
			cliente.setTarifa(tarifa);
		} else {
			System.out.println("El cliente no existe");
		}
	}

	private static void borrarCliente(Scanner teclado) {
		System.out.println("NIF del cliente a borrar:");
		String nif = teclado.next();
		bd.borrarCliente(nif);
	}

	private static void anyadirCliente(Scanner teclado) {
		String apellidos;
		Cliente cliente = null;
		
		teclado.nextLine();
		System.out.println("Nombre:");
		String nombre = teclado.nextLine();
		System.out.println("NIF:");
		String nif = teclado.next();
		System.out.println("Codigo postal:");
		String cp = teclado.next();
		System.out.println("Provincia:");
		String provincia = teclado.next();
		teclado.nextLine();
		System.out.println("Poblacion:");
		String poblacion = teclado.nextLine();
		Direccion dir = new Direccion(cp, provincia, poblacion);
		Calendar fecha = Calendar.getInstance();
		System.out.println("Precio de la tarifa: ");
		float precio = teclado.nextFloat();
		teclado.nextLine();
		Tarifa tarifa = new Tarifa(precio);
		System.out.println("Correo electronico:");
		String correo = teclado.next();
		System.out.println("Si eres una EMPRESA, pulsa 1 \nSi eres un PARTICULAR, pulsa 2: ");
		int tipo = teclado.nextInt();
		
		switch (tipo) {
		case 1:
			cliente = new Empresa(nombre, nif, dir, correo, fecha, tarifa);
			break;
			
		case 2:
			System.out.println("Apellidos:");
			teclado.nextLine();
			apellidos = teclado.nextLine();
			cliente = new Particular(nombre, nif, dir, correo, fecha, tarifa, apellidos);
			break;

		}
		bd.nuevoCliente(cliente);
	}

	private void menuLlamada() {
		System.out.println(MenuLlamadas.getMenu());
		System.out.print("¿Qué operación desea realizar?");
		MenuLlamadas opcion = MenuLlamadas.getOpcion(teclado.nextInt());
		filtrarMenuLlamadas(opcion);
	}

	private void filtrarMenuLlamadas(MenuLlamadas opcion) {
		switch (opcion) {
		case NUEVA_LLAMADA:
			altaLlamada(teclado);
			break;
		case LISTAR_LLAMADAS:
			todasLlamadasCliente(teclado);
			break;
		}
	}

	private static void altaLlamada(Scanner teclado) {
		String nif = null;
		System.out.println("Nif del cliente:");
		nif = teclado.next();
		int numtelefono;
		System.out.println("Numero de telefono:");
		numtelefono = teclado.nextInt();
		double duracion;
		System.out.println("Duración en minutos:");
		duracion = teclado.nextDouble();
		Llamada llamada = new Llamada(numtelefono, duracion);
		bd.addLlamada(llamada, nif);
	}

	private static void todasLlamadasCliente(Scanner teclado) {
		String nif = null;
		System.out.println("Nif del cliente :");
		nif = teclado.next();
		bd.LlamadasDeUnCliente(nif);
	}

	private void menuFactura() {
		System.out.println(MenuFacturas.getMenu());
		System.out.print("¿Qué operación desea realizar?");
		MenuFacturas opcion = MenuFacturas.getOpcion(teclado.nextInt());
		filtrarMenuFacturas(opcion);
	}

	private void filtrarMenuFacturas(MenuFacturas opcion) {
		switch (opcion) {
		case EMITIR_FACTURA:
			emitirFactura(teclado);
			break;
		case RECUPERAR_FACTURA:
			recuperarFactura(teclado);
			break;
		case RECUPERAR_FACTURAS_CLIENTE:
			recuperarFacturasCliente(teclado);
			break;
		}
	}

	private static void emitirFactura(Scanner teclado) {
		String nif = null;
		System.out.println("Nif del cliente del que desea hacer la factura:");
		nif = teclado.next();
		bd.generarFactura(nif);
	}

	private static void recuperarFactura(Scanner teclado) {
		int codigo;
		System.out.println("Codigo de la factura:");
		codigo = teclado.nextInt();
		bd.recuperarFacturaPorCodigo(codigo);
	}

	private static void recuperarFacturasCliente(Scanner teclado) {
		String nif = null;
		System.out.println("Nif del cliente :");
		nif = teclado.next();
		bd.recuperarFacturasCliente(nif);
	}


}
