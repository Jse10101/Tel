package ejecutable;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.Set;

import llamada.Llamada;
import cliente.Cliente;
import cliente.Empresa;
import cliente.Particular;
import datos.BaseDeDatos;
import direccion.Direccion;
import excepciones.CodigoInvalido;
import excepciones.ErrorFecha;
import excepciones.NifInvalido;
import factura.Factura;
import menu.Aplicacion;
import menu.MenuClientes;
import menu.MenuFacturas;
import menu.MenuLlamadas;
import tarifa.Tarifa;
import factoria.TipoTarifaEspecial;
import factoria.FactoriaClienteParametrizada;
import factoria.FactoriaParametrizada;
import factoria.FactoriaTarifas;
import factoria.TipoCliente;

public class Menu {
	private static BaseDeDatos bd;
	Scanner teclado = new Scanner(System.in);
	
	//Constructor
	public Menu() {
		super();
		bd = new BaseDeDatos();
	}
	
	//Abre el primer menú
	public void ejecuta() throws NifInvalido, CodigoInvalido, ErrorFecha {
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
	private void opcionesAplicacion(Aplicacion opcion) throws NifInvalido, CodigoInvalido, ErrorFecha {
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
		case CARGAR:
			cargar();
			break;
		case GUARDAR:
			guardar();
			break;
		case SALIR:
			salir();
			break;
		}
	}

	private void salir() {
		System.out.println("Adiós");
	}
	
	private void cargar() {
		try {
			FileInputStream fis = new FileInputStream("datos_telefonia.bin");
			ObjectInputStream ois = new ObjectInputStream(fis);
			bd = (BaseDeDatos) ois.readObject();
			ois.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error al cargar.");
		} catch (ClassNotFoundException e) {
			System.out.println("Error al cargar.");
		} catch (IOException e) {
			System.out.println("Error al cargar.");
		}
	}

	private void guardar() {
		try {
			FileOutputStream fos = new FileOutputStream("datos_telefonia.bin");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(bd);
			oos.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error al guardar.");
		} catch (IOException e) {
			System.out.println("Error al guardar.");
		}
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
		case CLIENTES_ENTRE_FECHAS:
			try {
				listaClientesEntreDosFechas();
			} catch (ErrorFecha e) {
				// TODO Auto-generated catch block
				System.out.println("Fecha inicial posterior a la de fin.");
			}
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
			System.out.println(TipoTarifaEspecial.getMenu());
			System.out.println("Tipo de tarifa especial: ");
			TipoTarifaEspecial tarifaEspecial = TipoTarifaEspecial.getOpcion(teclado.nextInt());	
			FactoriaTarifas nuevaFactoria = new FactoriaTarifas();
			nuevaFactoria.getTarifa(tarifaEspecial, tarifa, tarifa.getPre());
			teclado.nextLine();
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
		Cliente cliente;
		
		TipoCliente tipo = null;
		System.out.println(TipoCliente.opciones());
		System.out.print("Introduce un tipo de cliente:");
		int  opcion = teclado.nextInt();
		teclado.nextLine();
		tipo = TipoCliente.enteroATipo(opcion);
		FactoriaParametrizada fact = new FactoriaClienteParametrizada();
		cliente = fact.getCliente(tipo,teclado);
		
		bd.nuevoCliente(cliente);
	}
	
	// Segunda Entrega
	
	private void listaClientesEntreDosFechas() throws ErrorFecha {
		
		Calendar i = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println("Fecha de inicio(dd/mm/aaaa): ");
		String fechaInicio = teclado.next();
		boolean fechaCorrecta = true;
		try {
			Date fechaDate = sdf.parse(fechaInicio);
			i.setTime(fechaDate);
		} catch (ParseException e) {
			System.out.println("Fecha introducida incorrecta, recuerde el formato dd/mm/aaaa, por ejemplo: 28/8/1996");
			fechaCorrecta=false;
		}
		//Otra vez lo mismo
		if(fechaCorrecta){
			Calendar f = Calendar.getInstance();
			System.out.println("Fecha final(dd/mm/aaaa): ");
			String fechaFinal= teclado.next();
			try {
				Date fecha_date = sdf.parse(fechaFinal);
				f.setTime(fecha_date);
			} catch (ParseException e) {
				System.out.println("Fecha introducida incorrecta, recuerde el formato dd/mm/aaaa, por ejemplo: 28/8/1996");
				fechaCorrecta=false;
			}
			if(fechaCorrecta){
				Set<String> nifs = bd.getAllClients();
				ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();
				for (String iterador : nifs) {
					listaClientes.add(bd.getCliente(iterador));
				}
				ArrayList<Cliente> clientesEntreFechas = bd.recuperaEntreFechas(listaClientes, i, f);
				for (Cliente e : clientesEntreFechas) {
					System.out.println(e.toString());
				}
			}	
		}
	}
	
	
	
	
	//Menú para llamadas
	private void menuLlamada() throws NifInvalido {
		System.out.println(MenuLlamadas.getMenu());
		System.out.print("¿Qué operación desea realizar?");
		MenuLlamadas opcion = MenuLlamadas.getOpcion(teclado.nextInt());
		opcionesMenuLlamadas(opcion);
	}
	
	//Dependiendo de la opción elegida, llama a una función
	private void opcionesMenuLlamadas(MenuLlamadas opcion) throws NifInvalido {
		switch (opcion) {
		case NUEVA_LLAMADA:
			altaLlamada(teclado);
			break;
		case LISTAR_LLAMADAS:
			todasLlamadasCliente(teclado);
			break;
			
		case LLAMADAS_ENTRE_FECHAS:
			try {
				listarLlamadasDosFechas();
			} catch (ErrorFecha e) {
				// TODO Auto-generated catch block
				System.out.println("Fecha inicial posterior a la de fin.");
			}
			break;
		}
	}
	
	//Da de alta a una nueva llamada introduciendo el nif del cliente, número de teléfono y duración de la llamada
	private static void altaLlamada(Scanner teclado) throws NifInvalido {
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
	private static void todasLlamadasCliente(Scanner teclado) throws NifInvalido {
		String nif = null;
		System.out.println("Nif del cliente :");
		nif = teclado.next();
		bd.LlamadasDeUnCliente(nif);
	}
	
	//Segunda entrega
	private void listarLlamadasDosFechas() throws ErrorFecha {
		boolean fechamal=false;
		System.out.println("Nif del cliente: ");
		String NIF = teclado.next();
		Calendar i = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println("Fecha de inicio(dd/mm/aaaa): ");
		String fecha = teclado.next();
		try {
			Date fecha_date = sdf.parse(fecha);
			i.setTime(fecha_date);
		} catch (ParseException e) {
			System.out.println("Fecha introducida incorrecta, recuerde el formato dd/mm/aaaa, por ejemplo: 28/8/1996");
			fechamal=true;

		}
		if(fechamal==false){
			
		
			Calendar f = Calendar.getInstance();
			System.out.println("Fecha final(dd/mm/aaaa): ");
			String fechaFinal = teclado.next();
			try {
				Date fechaDate = sdf.parse(fechaFinal);
				f.setTime(fechaDate);
			} catch (ParseException e) {
				System.out.println("Fecha introducida incorrecta, recuerde el formato dd/mm/aaaa, por ejemplo: 28/8/1996");
				fechamal=true;

			}
			if(fechamal==false){
				if(bd.getCliente(NIF)!=null){
					ArrayList<Llamada> datosMostrar = bd.recuperaEntreFechas(bd.getCliente(NIF).getListaLlamadas(), i, f);
		
					if (datosMostrar.isEmpty()){
						System.out.println("No hay llamadas en este perido");
					}
					for (Llamada l : datosMostrar) {
						System.out.println(l.toString());
					}
				}else{
					System.out.println("El nif no existe");
				}
			}
		}
	}
	
	
	
	//Menú para facturas
	private void menuFactura() throws NifInvalido, CodigoInvalido, ErrorFecha {
		System.out.println(MenuFacturas.getMenu());
		System.out.print("¿Qué operación desea realizar?");
		MenuFacturas opcion = MenuFacturas.getOpcion(teclado.nextInt());
		opcionesMenuFacturas(opcion);
	}
	
	//Dependiendo de la opción elegida, llama a una función
	private void opcionesMenuFacturas(MenuFacturas opcion) throws NifInvalido, CodigoInvalido, ErrorFecha {
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
		case FACTURAS_ENTRE_FECHAS:
			try {
				listarFacturasEntreDosFechas();
			} catch (ErrorFecha e) {
				// TODO Auto-generated catch block
				System.out.println("Fecha inicial posterior a la de fin.");
			}
			break;
		}
	}

	//Emite una factura introduciendo el nif del cliente
	private static void emitirFactura(Scanner teclado) throws ErrorFecha, NifInvalido {
		String nif = null;
		System.out.println("Nif del cliente del que desea hacer la factura:");
		nif = teclado.next();
		bd.generarFactura(nif);
	}

	//Recupera una factura introduciendo el codigo de la factura
	private static void recuperarFactura(Scanner teclado) throws CodigoInvalido {
		int codigo;
		System.out.println("Codigo de la factura:");
		codigo = teclado.nextInt();
		bd.recuperarFacturaPorCodigo(codigo);
	}

	//Recupera todas las facturas de un cliente introduciendo el nif del cliente
	private static void recuperarFacturasCliente(Scanner teclado) throws NifInvalido {
		String nif = null;
		System.out.println("Nif del cliente :");
		nif = teclado.next();
		bd.recuperarFacturasCliente(nif);
	}
	
	private void listarFacturasEntreDosFechas() throws ErrorFecha, NifInvalido {
		System.out.println("NIF del cliente: ");
		String nif = teclado.next();
		Calendar i = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println("Fecha de inicio(dd/mm/yyyy): ");
		String fecha = teclado.next();
		boolean fechaCorrecta=false;
		try {
			Date fecha_date = sdf.parse(fecha);
			i.setTime(fecha_date);
		} catch (ParseException e) {
			System.out.println("Fecha introducida incorrecta, recuerde el formato dd/mm/aaaa, por ejemplo: 28/8/1996");
			fechaCorrecta=false;
		}
		if(fechaCorrecta){
			Calendar f = Calendar.getInstance();
			System.out.println("Fecha final(dd/mm/aaaa): ");
			String fechaFinal = teclado.next();
			try {
				Date fechaDate = sdf.parse(fechaFinal);
				f.setTime(fechaDate);
			} catch (ParseException e) {
				System.out.println("Fecha introducida incorrecta, recuerde el formato dd/mm/aaaa, por ejemplo: 28/8/1996");
				fechaCorrecta=false;
			}
			if(fechaCorrecta){
				if(bd.getCliente(nif)==null){
					throw new NifInvalido();
				}else{
					Cliente cliente = bd.getCliente(nif);
					ArrayList<Factura> datosFiltrar = new ArrayList<Factura>();
					for (int id : cliente.getListaCodigoFacturas()) {
						datosFiltrar.add(bd.getListaFacturas().get(id));
					}
					ArrayList<Factura> datosMostrar = bd.recuperaEntreFechas(datosFiltrar, i, f);
					if(datosMostrar.isEmpty()){
						System.out.println("este cliente no tiene facturas en este periodo");
					}
					for (Factura fac : datosMostrar) {
						System.out.println(fac.toString());
					}
				}
			}
		}
	}
}