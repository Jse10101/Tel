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
	
	//Constructor
	public Menu() {
		super();
		bd = new BaseDeDatos();
	}
	
	//Abre el primer menú
	public void ejecuta() {
		Aplicacion opcion;
		Scanner teclado = new Scanner(System.in);
		do {
			System.out.println(Aplicacion.getMenu());
			System.out.print("Introduce una opción:");
			opcion = Aplicacion.getOpcion(teclado.nextInt());
			opcionesAplicacion(opcion);
		} while (opcion != Aplicacion.SALIR);
		teclado.close();
	}
	
	//Llama al menú de clientes, llamadas, facturas o sale.
	private void opcionesAplicacion(Aplicacion opcion) {
		switch (opcion) {
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
	
	//Menú para clientes
	private void menuCliente() {
		System.out.println(MenuClientes.getMenu());
		System.out.print("¿Qué operación desea realizar?");
		MenuClientes opcion = MenuClientes.getOpcion(teclado.nextInt());
		opcionesMenuClientes(opcion);
	}
	
	//Dependiendo de la opción elegida, llama a una función
	private void opcionesMenuClientes(MenuClientes opcion) {
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
	
	//Muestra todos los clientes
	private static void mostrarTodos() {
		int contador = 1;
		for (String c : bd.getAllClients()) {
			System.out.println("Cliente nº "+contador+": ");
			System.out.println(bd.getCliente(c).toString());
			System.out.println("\n");
			contador++;
		}
	}
	
	//Muestra un cliente introduciendo su nif
	private static void mostrarCliente(Scanner teclado) {
		System.out.println("NIF del cliente buscado:");
		String nif = teclado.next();
		if (bd.getListaClientes().containsKey(nif)) {
			System.out.println(bd.getCliente(nif).toString());
		} else {
			System.out.println("El cliente no existe");
		}
	}
	
	//Cambia la tarifa de un cliente
	private static void cambiarTarifa(Scanner teclado) {
		System.out.println("NIF del cliente:");
		String nif = teclado.next();
		Cliente cliente = bd.getListaClientes().get(nif);
		if (cliente == null) {
			System.out.println("El cliente no existe");
		} else {
			System.out.println("Introduce una nueva tarifa: ");
			float precio = teclado.nextFloat();
			Tarifa tarifa = new Tarifa(precio);
			cliente.setTarifa(tarifa);
		}
	}
	
	//Borra un cliente introduciendo su nif
	private static void borrarCliente(Scanner teclado) {
		System.out.println("NIF del cliente a borrar:");
		String nif = teclado.next();
		bd.borrarCliente(nif);
	}
	
	//Añade un nuevo cliente
	private static void anyadirCliente(Scanner teclado) {
		Cliente cliente = null;
		//Pedimos todos los datos de un cliente para añadirlo
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
			//Es una empresa y no hay apellido
			cliente = new Empresa(nombre, nif, dir, correo, fecha, tarifa);
			break;
			
		case 2:
			//Si es un particular también nos pedirá los apellidos
			System.out.println("Apellidos:");
			teclado.nextLine();
			String apellidos = teclado.nextLine();
			cliente = new Particular(nombre, nif, dir, correo, fecha, tarifa, apellidos);
			break;
		}
		//Una vez tenemos al cliente como tal, lo añadimos a la lista con todos los clientes.
		bd.nuevoCliente(cliente);
	}
	
	//Menú para llamadas
	private void menuLlamada() {
		System.out.println(MenuLlamadas.getMenu());
		System.out.print("¿Qué operación desea realizar?");
		MenuLlamadas opcion = MenuLlamadas.getOpcion(teclado.nextInt());
		opcionesMenuLlamadas(opcion);
	}
	
	//Dependiendo de la opción elegida, llama a una función
	private void opcionesMenuLlamadas(MenuLlamadas opcion) {
		switch (opcion) {
		case NUEVA_LLAMADA:
			altaLlamada(teclado);
			break;
		case LISTAR_LLAMADAS:
			todasLlamadasCliente(teclado);
			break;
		}
	}
	
	//Da de alta a una nueva llamada introduciendo el nif del cliente, número de teléfono y duración de la llamada
	private static void altaLlamada(Scanner teclado) {
		System.out.println("Nif del cliente:");
		String nif = teclado.next();
		System.out.println("Numero de telefono:");
		int telefono = teclado.nextInt();
		System.out.println("Duración en minutos:");
		double duracion = teclado.nextDouble();
		Llamada llamada = new Llamada(telefono, duracion);
		bd.addLlamada(llamada, nif);
	}

	//Muestra todas las llamadas de un cliente introduciendo el nif
	private static void todasLlamadasCliente(Scanner teclado) {
		String nif = null;
		System.out.println("Nif del cliente :");
		nif = teclado.next();
		bd.LlamadasDeUnCliente(nif);
	}
	
	//Menú para facturas
	private void menuFactura() {
		System.out.println(MenuFacturas.getMenu());
		System.out.print("¿Qué operación desea realizar?");
		MenuFacturas opcion = MenuFacturas.getOpcion(teclado.nextInt());
		opcionesMenuFacturas(opcion);
	}
	
	//Dependiendo de la opción elegida, llama a una función
	private void opcionesMenuFacturas(MenuFacturas opcion) {
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

	//Emite una factura introduciendo el nif del cliente
	private static void emitirFactura(Scanner teclado) {
		String nif = null;
		System.out.println("Nif del cliente del que desea hacer la factura:");
		nif = teclado.next();
		bd.generarFactura(nif);
	}

	//Recupera una factura introduciendo el codigo de la factura
	private static void recuperarFactura(Scanner teclado) {
		int codigo;
		System.out.println("Codigo de la factura:");
		codigo = teclado.nextInt();
		bd.recuperarFacturaPorCodigo(codigo);
	}

	//Recupera todas las facturas de un cliente introduciendo el nif del cliente
	private static void recuperarFacturasCliente(Scanner teclado) {
		String nif = null;
		System.out.println("Nif del cliente :");
		nif = teclado.next();
		bd.recuperarFacturasCliente(nif);
	}
}
