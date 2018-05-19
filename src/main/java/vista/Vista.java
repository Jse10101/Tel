package vista;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.EOFException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.MenuListener;

import cliente.Cliente;
import cliente.Empresa;
import controlador.Controlador;
import controlador.ControladorInt;
import datos.BaseDeDatos;
import direccion.Direccion;
import factura.Factura;
import entradaSalida.EntradaSalida;
import excepciones.CodigoInvalido;
import excepciones.ErrorFecha;
import excepciones.NifInvalido;
import llamada.Llamada;
import modelo.ModeloInt;
import tarifa.Tarifa;

public class Vista implements VistaInt {

	String[] dias = { "Sabado", "Domingo" }, horas = { "Manyana", "Noche" };
	JComboBox dia = new JComboBox(dias), hora = new JComboBox(horas);

	JTextField nif = new JTextField(20), nombre = new JTextField(20), cp = new JTextField(20),
			provincia = new JTextField(20), poblacion = new JTextField(20), text1b = new JTextField(20),
			text2b = new JTextField(20), text3b = new JTextField(20), text4b = new JTextField(20),
			text5b = new JTextField(20);

	JLabel nifLabel = new JLabel("NIF: "), nombreLabel = new JLabel("Nombre: "), cpLabel = new JLabel("CP: "),
			provinciaLabel = new JLabel("Provincia"), poblacionLabel = new JLabel("Poblacion: "), text1 = new JLabel(),
			text2 = new JLabel(), text3 = new JLabel(), text4 = new JLabel(), text5 = new JLabel();

	private JFrame frame = null;
	private ModeloInt modelo;
	private ControladorInt controlador;

	JTextField tiempo = new JTextField(20);
	JLabel tiempoLabel = new JLabel("telefono: ");
	JTextField tel = new JTextField(20);
	JLabel telLabel = new JLabel("tiempo(m): ");

	JTextField inicio = new JTextField(20);
	JLabel inicioLabel = new JLabel("Fecha inicio dd/mm/aaaa: ");

	JTextField fin = new JTextField(20);
	JLabel finLabel = new JLabel("Fecha fin dd/mm/aaaa: ");
	JTextField idFactura = new JTextField(20);
	JLabel idFacturaLabel = new JLabel("Codigo de factura (numero): ");
	JDialog form;
	JLabel texto = new JLabel();

	public void setControlador(ControladorInt controlador2) {
		this.controlador = controlador2;
	}

	public void setModelo(ModeloInt modelo) {
		this.modelo = modelo;
	}

	public Vista() {
		super();
		this.frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void dibujaVentana() {
		anyadeMenu();

		mostrarIntro();
		frame.setVisible(true);
	}

	private void anyadeMenu() {
		JMenuBar barra = new JMenuBar(); 
		JMenu menuCliente = new JMenu("Opciones cliente");
		MenuClienteListener listener = new MenuClienteListener();
		
		
		JMenu submenu = new JMenu("Dar de alta un nuevo cliente");

		JMenuItem nuevoCliente = new JMenuItem();
		nuevoCliente.setText("Dar de alta un nuevo particular");
		nuevoCliente.addActionListener(listener); // Activamos el listener
		submenu.add(nuevoCliente);

		JMenuItem nuevaEmpresa = new JMenuItem();
		nuevaEmpresa.setText("Dar de alta una nueva empresa");
		nuevaEmpresa.addActionListener(listener); // Activamos el listener
		submenu.add(nuevaEmpresa);
		menuCliente.add(submenu);

		// Opcion de mostrar solucion
		JMenuItem borrarCliente = new JMenuItem();
		borrarCliente.setText("Borrar un cliente");
		borrarCliente.addActionListener(listener);
		menuCliente.add(borrarCliente);

		// Opcion de cambiarTarifa
		JMenuItem cambiarTarifa = new JMenuItem();
		cambiarTarifa.setText("Cambiar la tarifa de un cliente");
		cambiarTarifa.addActionListener(listener);
		menuCliente.add(cambiarTarifa);

		// Opcion de recuperar un cliente
		JMenuItem recuperarCliente = new JMenuItem();
		recuperarCliente.setText("Recuperar cliente");
		recuperarCliente.addActionListener(listener);
		menuCliente.add(recuperarCliente);

		// Opcion de recuperar todos
		JMenuItem recuperarTodos = new JMenuItem();
		recuperarTodos.setText("Recuperar todos los clientes");
		recuperarTodos.addActionListener(listener);
		menuCliente.add(recuperarTodos);

		// Opcion de clientes entre fechas
		JMenuItem clienteEntreFechas = new JMenuItem();
		clienteEntreFechas.setText("Recuperar todos los clientes entre dos fechas");
		clienteEntreFechas.addActionListener(listener);
		menuCliente.add(clienteEntreFechas);
		barra.add(menuCliente);

		// parte llamada
		JMenu menuLlamada = new JMenu("Opciones Llamada");
		MenuLlamadasListener listenerLlamadas = new MenuLlamadasListener();
		JMenuItem nuevaLlamada = new JMenuItem();
		nuevaLlamada.setText("Dar de alta una llamada");
		nuevaLlamada.addActionListener(listenerLlamadas); // Activamos el
															// listener
		menuLlamada.add(nuevaLlamada);

		JMenuItem listarLlamadas = new JMenuItem();
		listarLlamadas.setText("Listar todas las llamadas de un cliente");
		listarLlamadas.addActionListener(listenerLlamadas);
		menuLlamada.add(listarLlamadas);

		JMenuItem recuperarLlamadasEntreFechas = new JMenuItem();
		recuperarLlamadasEntreFechas.setText("Recuperar todas las llamadas realizadas entre dos fechas");
		recuperarLlamadasEntreFechas.addActionListener(listenerLlamadas);
		menuLlamada.add(recuperarLlamadasEntreFechas);
		barra.add(menuLlamada);

		// parte facturas
		JMenu menuFactura = new JMenu("Opciones Factura");
		MenuFacturasListener listenerFacturas = new MenuFacturasListener();
		JMenuItem nuevaFactura = new JMenuItem();
		nuevaFactura.setText("Emitir una factura");
		nuevaFactura.addActionListener(listenerFacturas); // Activamos el
															// listener
		menuFactura.add(nuevaFactura);

		JMenuItem recuperarFactura = new JMenuItem();
		recuperarFactura.setText("Recuperar los datos de una factura");
		recuperarFactura.addActionListener(listenerFacturas);
		menuFactura.add(recuperarFactura);

		JMenuItem recuperarFactCliente = new JMenuItem();
		recuperarFactCliente.setText("Recuperar todas las facturas de un cliente");
		recuperarFactCliente.addActionListener(listenerFacturas);
		menuFactura.add(recuperarFactCliente);

		JMenuItem recuperarFactFechas = new JMenuItem();
		recuperarFactFechas.setText("Recuperar todas las facturas emitidas entre dos fechas");
		recuperarFactFechas.addActionListener(listenerFacturas);
		menuFactura.add(recuperarFactFechas);
		barra.add(menuFactura);

		// parte cargar y guardar

		JMenu menuCargarGuardar = new JMenu("Opciones C/G");
		MenuCargarGuardarListener listenerCargarGuardar = new MenuCargarGuardarListener();

		// Anyadimos dos items de menu, todos siguen el mismo esquema de
		// creacion.
		// Opcion de cargar
		JMenuItem cargar = new JMenuItem();
		cargar.setText("Cargar");
		cargar.addActionListener(listenerCargarGuardar); // Activamos el
															// listener
		menuCargarGuardar.add(cargar);

		// Opcion de guardar
		JMenuItem guardar = new JMenuItem();
		guardar.setText("Guardar");
		guardar.addActionListener(listenerCargarGuardar);
		menuCargarGuardar.add(guardar);
		barra.add(menuCargarGuardar);
		frame.setJMenuBar(barra);

	}

	private class MenuLlamadasListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			JMenuItem item = (JMenuItem) e.getSource();
			String texto = item.getText();
			switch (texto) {
			case "Dar de alta una llamada":
				System.out.println(texto);
				altaLlamada();
				break;
			case "Listar todas las llamadas de un cliente":
				System.out.println(texto);
				listLlamdasCliente();
				break;
			case "Recuperar todas las llamadas realizadas entre dos fechas":
				System.out.println(texto);
				recuperarLlamadasFechas();
				break;
			}
		}

		private void recuperarLlamadasFechas() {
			form = new JDialog();// JDialog.setDefaultLookAndFeelDecorated(true);
			Container contenedor = form.getContentPane();

			contenedor.setLayout(new FlowLayout());
			contenedor.add(nifLabel);
			contenedor.add(nif);
			contenedor.add(inicioLabel);
			contenedor.add(inicio);
			contenedor.add(finLabel);
			contenedor.add(fin);

			RecuperarLlamadasFechas listener = new RecuperarLlamadasFechas();
			JButton boton = new JButton("Consultar llamadas fechas");
			boton.addActionListener(listener);
			contenedor.add(boton);

			JButton cancelar = new JButton("CANCELAR");
			cancelar.setToolTipText("Salir y no anyadir .");
			cancelar.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {

					form.setVisible(false);
					form.dispose();
				}
			});
			contenedor.add(cancelar);
			form.pack();
			form.setVisible(true);
			form.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

		}

		private class RecuperarLlamadasFechas implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				String niff = nif.getText();
				List<Llamada> llam = null;
				boolean result = false;
				Calendar i = Calendar.getInstance();
				Calendar f = Calendar.getInstance();
				String ini = inicio.getText();
				String fi = fin.getText();
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				try {
					Date fecha_date = sdf.parse(ini);
					i.setTime(fecha_date);
				} catch (ParseException eini) {
					System.out.println("Error al introducir la fecha recuerde el formato es dd/MM/yyyy ");
					System.out.println("Fecha mal");
					ventanaErrores("Fechas mal");
				}
				try {
					Date fecha_date = sdf.parse(fi);
					f.setTime(fecha_date);
				} catch (ParseException efi) {
					System.out.println("Error al introducir la fecha recuerde el formato es dd/MM/yyyy ");
					System.out.println("Fecha mal");
					ventanaErrores("Fecha mal");
				}

				try {

					llam = controlador.recuperarLlamadasFechas(i, f, niff);
				} catch (Exception e2) {
					// TODO: handle exception
					System.out.println("¿Fecha inicio es menor que fecha fin?");
					ventanaErrores("¿Fecha inicio es menor que fecha fin?");
				}

				if (llam == null) {
					System.out.println("No se pudo listar porque el cliente o no existe o no tiene facturas");
					ventanaErrores("No se ha podido realizar la operacion. ");
				} else {

					form.setVisible(false);

					String llamtexto = "<html><body>";
					for (Llamada llamada : llam) {
						llamtexto += llamada.toString();
						llamtexto += "<br>";
					}
					llamtexto += "</body></html>";
					mostrarTexto(llamtexto);

				}
			}

		}

		private void listLlamdasCliente() {
			// TODO Auto-generated method stub
			form = new JDialog();// JDialog.setDefaultLookAndFeelDecorated(true);
			Container contenedor = form.getContentPane();

			contenedor.setLayout(new FlowLayout());
			contenedor.add(nifLabel);
			contenedor.add(nif);

			ListLlamdasCliente listener = new ListLlamdasCliente();
			JButton boton = new JButton("Consultar llamadas");
			boton.addActionListener(listener);
			contenedor.add(boton);

			JButton cancelar = new JButton("CANCELAR");
			cancelar.setToolTipText("Salir y no anyadir .");
			cancelar.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {

					form.setVisible(false);
					form.dispose();
				}
			});
			contenedor.add(cancelar);
			form.pack();
			form.setVisible(true);
			form.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

		}

		private class ListLlamdasCliente implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				System.out.println("recuperando facturas cliente");
				String niff = nif.getText();

				boolean result = false;
				try {
					result = controlador.listLlamdasCliente(niff);
				} catch (NifInvalido e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (!result) {
					System.out.println("no se pudo crear la factura");
					ventanaErrores("NO se ha podido realizar la operacion. ");
				} else {

					form.setVisible(false);
					List<Llamada> llamadas = modelo.getListLlamadasCliente();
					String llam = "<html><body>";
					for (Llamada llamada : llamadas) {
						llam += llamada.toString();
						llam += "<br>";
					}
					llam += "</body></html>";
					mostrarTexto(llam);

				}

			}

		}

		private void altaLlamada() {
			// TODO Auto-generated method stub
			form = new JDialog();// JDialog.setDefaultLookAndFeelDecorated(true);
			Container contenedor = form.getContentPane();

			contenedor.setLayout(new FlowLayout());
			contenedor.add(nifLabel);
			contenedor.add(nif);
			contenedor.add(telLabel);
			contenedor.add(tel);
			contenedor.add(tiempoLabel);
			contenedor.add(tiempo);

			AltaLlamada listener = new AltaLlamada();
			JButton boton = new JButton("anyadir llamada");
			boton.addActionListener(listener);
			contenedor.add(boton);

			JButton cancelar = new JButton("CANCELAR");
			cancelar.setToolTipText("Salir y no anyadir .");
			cancelar.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {

					form.setVisible(false);
					form.dispose();
				}
			});
			contenedor.add(cancelar);
			form.pack();
			form.setVisible(true);
			form.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

		}
	}

	private class AltaLlamada implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Double tiempoo = -1.0;
			boolean result = false;
			frame.pack();
			// TODO Auto-generated method stub
			System.out.println("generando fact");
			String niff = nif.getText();
			int tell = -1;
			try {

				tell = Integer.parseInt(tel.getText());
				tiempoo = Double.parseDouble(tiempo.getText());
			} catch (Exception e4) {
				// TODO: handle exception
				System.out.println("introduce un numero >0 en tiempo y telefono");
				ventanaErrores("introduce un numero. ");
			}

			if (tiempoo >= 0.0 && tell >= 0) {
				System.out.println("creando");
				try {
					result = controlador.altaLlamada(niff, tell, tiempoo);
				} catch (NifInvalido e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			if (!result) {
				System.out.println("no se pudo crear la factura");
				ventanaErrores("NO se ha podido realizar la operacion. ");
			} else {
				ventanaAviso();
				form.setVisible(false);
				mostrarIntro();
			}
		}

	}

	private void central() {
		Container contenedor = frame.getContentPane();
		JTextField nif = new JTextField(20);
		JLabel nifLabel = new JLabel("NIF: ");
		contenedor.setLayout(new FlowLayout());
		contenedor.add(nifLabel);
		contenedor.add(nif);
		frame.pack();
	}

	private void mostrarIntro() {
		Container contenedor = frame.getContentPane();
		texto.setText("Bienvenido a la apicacion");
		contenedor.add(texto);
		frame.pack();
		frame.setVisible(true);

	}

	private void mostrarTexto(String text) {
		Container contenedor = frame.getContentPane();
		texto.setText(text);
		contenedor.add(texto);
		frame.pack();
		frame.setVisible(true);

	}

	private class MenuClienteListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JMenuItem item = (JMenuItem) e.getSource();
			String texto = item.getText();
			switch (texto) {
			case "Dar de alta un nuevo particular":
				System.out.println(texto);
				nuevoCliente();
				break;
			case "Dar de alta una nueva empresa":
				System.out.println(texto);
				nuevaEmpresa();
				break;
			case "Borrar un cliente":
				System.out.println(texto);
				borrarCliente();
				break;
			case "Cambiar la tarifa de un cliente":
				System.out.println(texto);
				cambiarTarifa();
				break;
			case "Recuperar cliente":
				System.out.println(texto);
				mostrarCliente();
				break;
			case "Recuperar todos los clientes":
				System.out.println(texto);
				mostrarTodosClientes();
				break;
			case "Recuperar todos los clientes entre dos fechas":
				System.out.println(texto);
				clientesEntreFechas();
				break;
			}
		}

		private void clientesEntreFechas() {
			// TODO Auto-generated method stub
			form = new JDialog();// JDialog.setDefaultLookAndFeelDecorated(true);
			Container contenedor = form.getContentPane();

			contenedor.setLayout(new FlowLayout());
			contenedor.add(inicioLabel);
			contenedor.add(inicio);
			contenedor.add(finLabel);
			contenedor.add(fin);

			clientesEntreFechasListener listener = new clientesEntreFechasListener();
			JButton boton = new JButton("Consultar clientes fechas");
			boton.addActionListener(listener);
			contenedor.add(boton);

			JButton cancelar = new JButton("CANCELAR");
			cancelar.setToolTipText("Salir y no consultar .");
			cancelar.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {

					form.setVisible(false);
					form.dispose();
				}
			});
			contenedor.add(cancelar);
			form.pack();
			form.setVisible(true);
			form.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

		}

		private class clientesEntreFechasListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<Cliente> clientes = null;
				boolean result = false;
				Calendar i = Calendar.getInstance();
				Calendar f = Calendar.getInstance();
				String ini = inicio.getText();
				String fi = fin.getText();
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				try {
					Date fecha_date = sdf.parse(ini);
					i.setTime(fecha_date);
				} catch (ParseException eini) {
					System.out.println("Error al introducir la fecha recuerde el formato es dd/MM/yyyy ");
					System.out.println("fecha mal");
					ventanaErrores("fechas mal");
				}
				try {
					Date fecha_date = sdf.parse(fi);
					f.setTime(fecha_date);
				} catch (ParseException efi) {
					System.out.println("Error al introducir la fecha recuerde el formato es dd/MM/yyyy ");
					System.out.println("fecha mal");
					ventanaErrores("fecha mal");
				}

				try {
					clientes = controlador.mostrarEntreFechas(i, f);
				} catch (Exception e2) {
					// TODO: handle exception
					System.out.println("fecha inicio es menor que fecha fin?");
					ventanaErrores("fecha inicio es menor que fecha fin?");
				}

				if (clientes == null) {
					System.out.println("no se pudo listar porque el cliente o no existe o no tiene facturas");
					ventanaErrores("NO se ha podido realizar la operacion. ");
				}else if(clientes.isEmpty()){
					mostrarTexto("no hay clientes entre estas fechas");
					form.setVisible(false);
				}
				else {

					form.setVisible(false);

					String cliente = "<html><body>";
					for (Cliente c : clientes) {
						cliente += c.toString();
						cliente += "<br>";
					}
					cliente += "</body></html>";
					mostrarTexto(cliente);

				}
			}
		}

		private void borrarCliente() {
			final JDialog form = new JDialog();// JDialog.setDefaultLookAndFeelDecorated(true);
			Container contenedor = form.getContentPane();

			contenedor.setLayout(new FlowLayout());
			contenedor.add(nifLabel);
			contenedor.add(nif);

			BorrarClienteListener listener = new BorrarClienteListener();
			JButton boton = new JButton("Borrar cliente");
			boton.addActionListener(listener);
			contenedor.add(boton);

			JButton cancelar = new JButton("CANCELAR");
			cancelar.setToolTipText("Salir sin borrar al cliente.");
			cancelar.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {

					form.setVisible(false);
					form.dispose();
				}
			});
			contenedor.add(cancelar);
			form.pack();
			form.setVisible(true);
			form.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

			System.out.println("s");

		}

		private class BorrarClienteListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				// borrar cliente
				String niff = nif.getText();
				Boolean result = controlador.borrarCliente(niff);

				if (!result) {
					System.out.println("no se pudo crear la factura");
					ventanaErrores("NO se ha podido realizar la operacion. ");
				} else {
					ventanaAviso();
					mostrarIntro();
					
				}
			}
		}

		private void nuevaEmpresa() {
			final JDialog form = new JDialog();// JDialog.setDefaultLookAndFeelDecorated(true);
			JPanel paneliz = new JPanel(), panelde = new JPanel(), panelab = new JPanel(), panelar = new JPanel(),
					panelex = new JPanel();
			Container contenedor = form.getContentPane();
			JButton boton1 = new JButton(), boton2 = new JButton();
			boton1.setText("ACEPTAR");
			boton2.setText("CANCELAR");
			boton2.setToolTipText("Salir y no a�adir cliente.");
			boton2.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {

					
					form.dispose();
				}
			});
			text1.setText("Tarifa basica: ");
			text2.setText("Tarifa segun dia: ");
			text3.setText("Tarifa segun hora: ");
			text4.setText("Correo electronico: ");

			form.setTitle("Nuevo cliente");
			contenedor.setLayout(new FlowLayout());
			paneliz.setLayout(new GridLayout(10, 0));
			paneliz.add(nombreLabel);
			paneliz.add(nifLabel);
			paneliz.add(cpLabel);
			paneliz.add(provinciaLabel);
			paneliz.add(poblacionLabel);
			paneliz.add(text1);
			paneliz.add(dia);
			paneliz.add(hora);
			paneliz.add(text4);

			panelde.setLayout(new GridLayout(10, 0));
			panelde.add(nombre);
			panelde.add(nif);
			panelde.add(cp);
			panelde.add(provincia);
			panelde.add(poblacion);
			panelde.add(text1b);
			panelde.add(text2b);
			panelde.add(text3b);
			panelde.add(text4b);

			panelar.setLayout(new GridLayout(1, 1));
			panelar.add(paneliz);
			panelar.add(panelde);
			panelab.setLayout(new FlowLayout());
			panelab.add(boton1);
			panelab.add(boton2);
			panelex.setLayout(new GridLayout(2, 0));
			panelex.add(panelar);
			panelex.add(panelab);
			contenedor.add(panelex);

			NuevaEmpresaListener listener = new NuevaEmpresaListener();
			boton1.addActionListener(listener);

			form.pack();
			form.setVisible(true);
			form.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		}

		private void nuevoCliente() {
			final JDialog form = new JDialog();// JDialog.setDefaultLookAndFeelDecorated(true);
			JPanel paneliz = new JPanel(), panelde = new JPanel(), panelab = new JPanel(), panelar = new JPanel(),
					panelex = new JPanel();
			Container contenedor = form.getContentPane();
			JButton boton1 = new JButton(), boton2 = new JButton();
			boton1.setText("ACEPTAR");
			boton2.setText("CANCELAR");
			boton2.setToolTipText("Salir y no anyadir cliente.");
			boton2.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {

					
					form.dispose();
				}
			});
			text1.setText("Tarifa basica: ");
			text2.setText("Tarifa segun dia: ");
			text3.setText("Tarifa segun hora: ");
			text4.setText("Correo electronico: ");
			text5.setText("Apellidos: ");

			form.setTitle("Nuevo cliente");
			contenedor.setLayout(new FlowLayout());
			paneliz.setLayout(new GridLayout(10, 0));
			paneliz.add(nombreLabel);
			paneliz.add(text5);
			paneliz.add(nifLabel);
			paneliz.add(cpLabel);
			paneliz.add(provinciaLabel);
			paneliz.add(poblacionLabel);
			paneliz.add(text1);
			paneliz.add(dia);
			paneliz.add(hora);
			paneliz.add(text4);

			panelde.setLayout(new GridLayout(10, 0));
			panelde.add(nombre);
			panelde.add(text5b);
			panelde.add(nif);
			panelde.add(cp);
			panelde.add(provincia);
			panelde.add(poblacion);
			panelde.add(text1b);
			panelde.add(text2b);
			panelde.add(text3b);
			panelde.add(text4b);

			panelar.setLayout(new GridLayout(1, 1));
			panelar.add(paneliz);
			panelar.add(panelde);
			panelab.setLayout(new FlowLayout());
			panelab.add(boton1);
			panelab.add(boton2);
			panelex.setLayout(new GridLayout(2, 0));
			panelex.add(panelar);
			panelex.add(panelab);
			contenedor.add(panelex);

			NuevoClienteListener listener = new NuevoClienteListener();
			boton1.addActionListener(listener);

			form.pack();
			form.setVisible(true);
			form.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		}

		private class NuevoClienteListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.pack();
				String name = nombre.getText();
				String apellidos = text5b.getText();
				String dni = nif.getText();
				String pc = cp.getText();
				String prov = provincia.getText();
				String pueblo = poblacion.getText();
				Float tarifaB = Float.parseFloat(text1b.getText());
				Float tarifaD = Float.parseFloat(text2b.getText());
				Float tarifaH = Float.parseFloat(text3b.getText());
				String correo = text4b.getText();
				int opcionDia = dia.getSelectedIndex();
				int opcionHorario = hora.getSelectedIndex();
				Boolean result = controlador.nuevoCliente(name, apellidos, dni, pc, prov, pueblo, tarifaB, tarifaD, tarifaH,
						 correo, opcionDia, opcionHorario);
				if (!result) {
					System.out.println("no se pudo crear la factura");
					ventanaErrores("NO se ha podido realizar la operacion. ");
				} else {
					ventanaAviso();
					mostrarIntro();
					
				}
			}

		}

		private class NuevaEmpresaListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = nombre.getText();
				String dni = nif.getText();
				String pc = cp.getText();
				String prov = provincia.getText();
				String pueblo = poblacion.getText();
				Float tarifaB = Float.parseFloat(text1b.getText());
				Float tarifaD = Float.parseFloat(text2b.getText());
				Float tarifaH = Float.parseFloat(text3b.getText());
				String correo = text4b.getText();
				int opcionDia = dia.getSelectedIndex();
				int opcionHorario = hora.getSelectedIndex();
				Boolean result = controlador.nuevaEmpresa(name, dni, pc, prov, pueblo, tarifaB, tarifaD, tarifaH,
						 correo, opcionDia, opcionHorario);
				if (!result) {
					System.out.println("no se pudo crear la factura");
					ventanaErrores("NO se ha podido realizar la operacion. ");
				} else {
					ventanaAviso();
					mostrarIntro();
					
				}
			}

		}

		private void cambiarTarifa() {
			final JDialog form = new JDialog();// JDialog.setDefaultLookAndFeelDecorated(true);
			JPanel paneliz = new JPanel(), panelde = new JPanel(), panelab = new JPanel(), panelar = new JPanel(),
					panelex = new JPanel();
			Container contenedor = form.getContentPane();
			JButton boton1 = new JButton(), boton2 = new JButton();
			boton1.setText("ACEPTAR");
			boton2.setText("CANCELAR");
			boton2.setToolTipText("Salir y no cambiar la tarifa.");
			boton2.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {

					
					form.dispose();
				}
			});
			text1.setText("Tarifa basica: ");

			form.setTitle("Cambiar tarifa");
			contenedor.setLayout(new FlowLayout());
			paneliz.setLayout(new GridLayout(4, 0));
			paneliz.add(nifLabel);
			paneliz.add(text1);
			paneliz.add(dia);
			paneliz.add(hora);

			panelde.setLayout(new GridLayout(4, 0));
			panelde.add(nif);
			panelde.add(text1b);
			panelde.add(text2b);
			panelde.add(text3b);

			panelar.setLayout(new GridLayout(1, 1));
			panelar.add(paneliz);
			panelar.add(panelde);
			panelab.setLayout(new FlowLayout());
			panelab.add(boton1);
			panelab.add(boton2);
			panelex.setLayout(new GridLayout(2, 0));
			panelex.add(panelar);
			panelex.add(panelab);
			contenedor.add(panelex);

			cambiarTarifaListener listener = new cambiarTarifaListener();
			boton1.addActionListener(listener);

			form.pack();
			form.setVisible(true);
			form.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		}

		private class cambiarTarifaListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				String dni = nif.getText();
				Float tarifaB = Float.parseFloat(text1b.getText());
				Float tarifaD = Float.parseFloat(text2b.getText());
				Float tarifaH = Float.parseFloat(text3b.getText());
				int opcionDia = dia.getSelectedIndex();
				int opcionHora = hora.getSelectedIndex();

				Boolean result = controlador.cambiarTarifa(dni, tarifaB, tarifaD, tarifaH, opcionDia, opcionHora);

				if (!result) {
					System.out.println("no se pudo cambiar");
					ventanaErrores("NO se ha podido realizar la operacion. ");
				} else {
					ventanaAviso();
					mostrarIntro();
					
				}
			}

		}

		private void mostrarCliente() {
			final JDialog form = new JDialog();// JDialog.setDefaultLookAndFeelDecorated(true);
			Container contenedor = form.getContentPane();

			contenedor.setLayout(new FlowLayout());
			contenedor.add(nifLabel);
			contenedor.add(nif);

			mostrarClienteListener listener = new mostrarClienteListener();
			JButton boton = new JButton("Mostrar cliente");
			boton.addActionListener(listener);
			contenedor.add(boton);

			JButton cancelar = new JButton("CANCELAR");
			cancelar.setToolTipText("Salir sin mostrar el cliente.");
			cancelar.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {

					
					form.dispose();
				}
			});
			contenedor.add(cancelar);
			form.pack();
			form.setVisible(true);
			form.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

			System.out.println("s");

		}

		private class mostrarClienteListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				String niff = nif.getText();
				Cliente cliente = controlador.mostrarCliente(niff);
				if(cliente==null){
					System.out.println("no se pudo mostrar");
					ventanaErrores("Cliente no existe. ");
				}else{
					
				mostrarTexto(cliente.toString());
				}
			}

		}

		private void mostrarTodosClientes() {
			ArrayList<Cliente> clientes = controlador.mostrarTodos();
			if (clientes == null) {
				System.out.println("no se pudo listar porque no hay");
				ventanaErrores("NO se ha podido realizar la operacion. ");
			}else if(clientes.isEmpty()){
				mostrarTexto("no hay clientes entre estas fechas");
				form.dispose();
			}
			else {
				String cliente = "<html><body>";
				for (Cliente c : clientes) {
					cliente += c.toString();
					cliente += "<br>";
				}
				cliente += "</body></html>";
				mostrarTexto(cliente);
				
			}
		}
	}

	private class MenuCargarGuardarListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JMenuItem item = (JMenuItem) e.getSource();
			String texto = item.getText();
			switch (texto) {
			case "Cargar":
				System.out.println(texto);
				cargar();
				break;
			case "Guardar":
				System.out.println(texto);
				guardar();
				break;

			}
		}

		private void guardar() {
			// TODO Auto-generated method stub
			modelo.guardar();
		}

		private void cargar() {
			// TODO Auto-generated method stub
			//GuardaryAbrir.abrirDatos();
			modelo.cargar();
			
		}

	}

	public void ventanaErrores(String mensaje) {
		JOptionPane.showMessageDialog(frame, mensaje, "ERROR", JOptionPane.ERROR_MESSAGE);
	}

	private class MenuFacturasListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JMenuItem item = (JMenuItem) e.getSource();
			String texto = item.getText();
			switch (texto) {
			case "Emitir una factura":
				System.out.println(texto);
				generarFactura();
				break;
			case "Recuperar los datos de una factura":
				System.out.println(texto);
				recuperarDatosFactura();
				break;
			case "Recuperar todas las facturas de un cliente":
				System.out.println(texto);
				recuperarFacurasCliente();
				break;
			case "Recuperar todas las facturas emitidas entre dos fechas":
				System.out.println(texto);
				recuperarFactFechas();
				break;
			}
		}

		private void recuperarFactFechas() {
			// TODO Auto-generated method stub
			form = new JDialog();// JDialog.setDefaultLookAndFeelDecorated(true);
			Container contenedor = form.getContentPane();

			contenedor.setLayout(new FlowLayout());
			contenedor.add(nifLabel);
			contenedor.add(nif);
			contenedor.add(inicioLabel);
			contenedor.add(inicio);
			contenedor.add(finLabel);
			contenedor.add(fin);

			RecuperarFactFechas listener = new RecuperarFactFechas();
			JButton boton = new JButton("Consultar facturas fechas");
			boton.addActionListener(listener);
			contenedor.add(boton);

			JButton cancelar = new JButton("CANCELAR");
			cancelar.setToolTipText("Salir y no anyadir .");
			cancelar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					form.setVisible(false);
					form.dispose();
				}
			});
			contenedor.add(cancelar);
			form.pack();
			form.setVisible(true);
			form.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

		}

		private class RecuperarFactFechas implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				String niff = nif.getText();
				List<Factura> fact = null;
				boolean result = false;
				Calendar i = Calendar.getInstance();
				Calendar f = Calendar.getInstance();
				String ini = inicio.getText();
				String fi = fin.getText();
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				try {
					Date fecha_date = sdf.parse(ini);
					i.setTime(fecha_date);
				} catch (ParseException eini) {
					System.out.println("Error al introducir la fecha recuerde el formato es dd/MM/yyyy ");
					System.out.println("fecha mal");
					ventanaErrores("fechas mal");
				}
				try {
					Date fecha_date = sdf.parse(fi);
					f.setTime(fecha_date);
				} catch (ParseException efi) {
					System.out.println("Error al introducir la fecha recuerde el formato es dd/MM/yyyy ");
					System.out.println("fecha mal");
					ventanaErrores("fecha mal");
				}

				try {

					fact = controlador.recuperarFactFechas(i, f, niff);
				} catch (Exception e2) {
					// TODO: handle exception
					System.out.println("fecha inicio es menor que fecha fin?");
					ventanaErrores("fecha inicio es menor que fecha fin?");
				}

				if (fact == null) {
					System.out.println("no se pudo listar porque el cliente o no existe o no tiene facturas");
					ventanaErrores("NO se ha podido realizar la operacion. ");
				} else {

					form.setVisible(false);

					String factu = "<html><body>";
					for (Factura factura : fact) {
						factu += factura.toString();
						factu += "<br>";
					}
					factu += "</body></html>";
					mostrarTexto(factu);

				}
			}
		}

		private void recuperarFacurasCliente() {
			form = new JDialog();// JDialog.setDefaultLookAndFeelDecorated(true);
			Container contenedor = form.getContentPane();

			contenedor.setLayout(new FlowLayout());
			contenedor.add(nifLabel);
			contenedor.add(nif);

			RecuperarFacturasCliente listener = new RecuperarFacturasCliente();
			JButton boton = new JButton("Consultar facturas");
			boton.addActionListener(listener);
			contenedor.add(boton);

			JButton cancelar = new JButton("CANCELAR");
			cancelar.setToolTipText("Salir y no anyadir .");
			cancelar.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {

					form.setVisible(false);
					form.dispose();
				}
			});
			contenedor.add(cancelar);
			form.pack();
			form.setVisible(true);
			form.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

		}

		private class RecuperarFacturasCliente implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				System.out.println("recuperando factturas cliente");
				String niff = nif.getText();

				boolean result = controlador.facurasCliente(niff);
				if (!result) {
					System.out.println("No se pudo crear la factura");
					ventanaErrores("No se ha podido realizar la operacion. ");
				} else {

					form.setVisible(false);
					List<Factura> facturas = modelo.getFacturasCliente();
					String fact = "<html><body>";
					for (Factura factura : facturas) {
						fact += factura.toString();
						fact += "<br>";
					}
					fact += "</body></html>";
					mostrarTexto(fact);

				}

			}

		}

		private void recuperarDatosFactura() {
			// TODO Auto-generated method stub
			form = new JDialog();// JDialog.setDefaultLookAndFeelDecorated(true);
			Container contenedor = form.getContentPane();

			contenedor.setLayout(new FlowLayout());
			contenedor.add(idFacturaLabel);
			contenedor.add(idFactura);

			RecuperarDatosFactura listener = new RecuperarDatosFactura();
			JButton boton = new JButton("Recupera Datos");
			boton.addActionListener(listener);
			contenedor.add(boton);

			JButton cancelar = new JButton("CANCELAR");
			cancelar.setToolTipText("Salir y no anyadir .");
			cancelar.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {

					form.setVisible(false);
					form.dispose();
				}
			});
			contenedor.add(cancelar);
			form.pack();
			form.setVisible(true);
			form.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

		}

		private class RecuperarDatosFactura implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				int codigo = -1;
				System.out.println("recuperando fact");
				try {

					codigo = Integer.parseInt(idFactura.getText());
				} catch (Exception e2) {
					// TODO: handle exception
					System.out.println("introduce un numero");
					ventanaErrores("introduce un numero. ");
				}
				boolean result = false;
				try {
					result = controlador.datosFactura(codigo);
				} catch (CodigoInvalido e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (!result) {
					System.out.println("no se pudo crear la factura");
					ventanaErrores("NO se ha podido realizar la operacion. ");
				} else {

					form.setVisible(false);
					Factura factura = modelo.getFacturaBuscada();
					mostrarTexto(factura.toString());

				}
			}

		}

		private void generarFactura() {
			form = new JDialog();// JDialog.setDefaultLookAndFeelDecorated(true);
			Container contenedor = form.getContentPane();

			contenedor.setLayout(new FlowLayout());
			contenedor.add(nifLabel);
			contenedor.add(nif);

			GenerarFacturaListener listener = new GenerarFacturaListener();
			JButton boton = new JButton("Generar Factura");
			boton.addActionListener(listener);
			contenedor.add(boton);

			JButton cancelar = new JButton("CANCELAR");
			cancelar.setToolTipText("Salir y no anyadir .");
			cancelar.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {

					form.setVisible(false);
					form.dispose();
				}
			});
			contenedor.add(cancelar);
			form.pack();
			form.setVisible(true);
			form.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

			System.out.println("s");

		}

		private class GenerarFacturaListener implements ActionListener {

			@Override
			public void actionPerformed(ActionEvent e) {

				frame.pack();
				// TODO Auto-generated method stub
				System.out.println("generando fact");
				String niff = nif.getText();
				boolean result = false;
				try {
					result = controlador.generarFactura(niff);
				} catch (ErrorFecha e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NifInvalido e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (!result) {
					System.out.println("no se pudo crear la factura");
					ventanaErrores("NO se ha podido realizar la operacion. ");
				} else {
					ventanaAviso();
					form.setVisible(false);
					mostrarIntro();
				}
			}

		}

		private void cogerDatosGenerarFactura() {
			Container contenedor = frame.getContentPane();

		}

	}

	public void ventanaAviso() {
		JOptionPane.showMessageDialog(form, "Accion realizada Correctamente");

	}
}
