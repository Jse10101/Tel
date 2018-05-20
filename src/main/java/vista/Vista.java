package vista;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

import cliente.Cliente;
import controlador.ControladorInt;
import factura.Factura;
import excepciones.CodigoInvalido;
import excepciones.ErrorFecha;
import excepciones.NifInvalido;
import llamada.Llamada;
import modelo.ModeloInt;

public class Vista implements VistaInt {

	String[] dias = { "Sabado", "Domingo" }, horario = { "Manyana", "Noche" };
	JComboBox dia = new JComboBox(dias), hora = new JComboBox(horario);

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
	JLabel tiempoLabel = new JLabel("Tiempo(m): ");
	JTextField tel = new JTextField(20);
	JLabel telLabel = new JLabel("Telefono: ");

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
		
		//Clientes
		JMenu menuCliente = new JMenu("Opciones Cliente");
		MenuClienteListener listener = new MenuClienteListener();
		
		//Opcion de dar de alta clientes
		JMenu submenu = new JMenu("Dar de alta un nuevo cliente");

		JMenuItem nuevoCliente = new JMenuItem();
		nuevoCliente.setText("Dar de alta un nuevo particular");
		nuevoCliente.addActionListener(listener);
		submenu.add(nuevoCliente);

		JMenuItem nuevaEmpresa = new JMenuItem();
		nuevaEmpresa.setText("Dar de alta una nueva empresa");
		nuevaEmpresa.addActionListener(listener); 
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
		recuperarCliente.setText("Recuperar un cliente");
		recuperarCliente.addActionListener(listener);
		menuCliente.add(recuperarCliente);

		// Opcion de recuperar todos
		JMenuItem recuperarTodos = new JMenuItem();
		recuperarTodos.setText("Recuperar clientes");
		recuperarTodos.addActionListener(listener);
		menuCliente.add(recuperarTodos);

		// Opcion de clientes entre fechas
		JMenuItem clienteEntreFechas = new JMenuItem();
		clienteEntreFechas.setText("Recuperar clientes creados entre dos fechas");
		clienteEntreFechas.addActionListener(listener);
		menuCliente.add(clienteEntreFechas);
		barra.add(menuCliente);

		// Llamadas
		JMenu menuLlamada = new JMenu("Opciones Llamada");
		MenuLlamadasListener listenerLlamadas = new MenuLlamadasListener();
		JMenuItem nuevaLlamada = new JMenuItem();
		nuevaLlamada.setText("Dar de alta una nueva llamada");
		nuevaLlamada.addActionListener(listenerLlamadas); 
		menuLlamada.add(nuevaLlamada);

		// Opcion de listar llamadas de un cliente
		JMenuItem listarLlamadas = new JMenuItem();
		listarLlamadas.setText("Listar llamadas de un cliente");
		listarLlamadas.addActionListener(listenerLlamadas);
		menuLlamada.add(listarLlamadas);

		// Opcion de listar llamadas entre dos fechas
		JMenuItem recuperarLlamadasEntreFechas = new JMenuItem();
		recuperarLlamadasEntreFechas.setText("Recuperar llamadas realizadas entre dos fechas");
		recuperarLlamadasEntreFechas.addActionListener(listenerLlamadas);
		menuLlamada.add(recuperarLlamadasEntreFechas);
		barra.add(menuLlamada);

		// Facturas
		JMenu menuFactura = new JMenu("Opciones Factura");
		MenuFacturasListener listenerFacturas = new MenuFacturasListener();
		
		// Opcion de emitir factura
		JMenuItem nuevaFactura = new JMenuItem();
		nuevaFactura.setText("Emitir una nueva factura");
		nuevaFactura.addActionListener(listenerFacturas);
		menuFactura.add(nuevaFactura);

		// Opcion de recperar datos de una factura
		JMenuItem recuperarFactura = new JMenuItem();
		recuperarFactura.setText("Recuperar datos de una factura");
		recuperarFactura.addActionListener(listenerFacturas);
		menuFactura.add(recuperarFactura);

		// Opcion de recuperar las facturas de un cliente
		JMenuItem recuperarFactCliente = new JMenuItem();
		recuperarFactCliente.setText("Recuperar facturas de un cliente");
		recuperarFactCliente.addActionListener(listenerFacturas);
		menuFactura.add(recuperarFactCliente);

		// Opcion de recuperar facturas emitidas entre dos fechas
		JMenuItem recuperarFactFechas = new JMenuItem();
		recuperarFactFechas.setText("Recuperar facturas emitidas entre dos fechas");
		recuperarFactFechas.addActionListener(listenerFacturas);
		menuFactura.add(recuperarFactFechas);
		barra.add(menuFactura);

		// Cargar y Guardar
		JMenu menuCargarGuardar = new JMenu("Opciones C/G");
		MenuCargarGuardarListener listenerCargarGuardar = new MenuCargarGuardarListener();

		// Opcion de cargar
		JMenuItem cargar = new JMenuItem();
		cargar.setText("Cargar");
		cargar.addActionListener(listenerCargarGuardar); 
	
		menuCargarGuardar.add(cargar);

		// Opcion de guardar
		JMenuItem guardar = new JMenuItem();
		guardar.setText("Guardar");
		guardar.addActionListener(listenerCargarGuardar);
		menuCargarGuardar.add(guardar);
		barra.add(menuCargarGuardar);
		frame.setJMenuBar(barra);

	}

	//EMPIEZA
	
	
	//Menu cliente
		private class MenuClienteListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				JMenuItem item = (JMenuItem) e.getSource();
				String texto = item.getText();
				switch (texto) {
				case "Dar de alta un nuevo particular":
					nuevoCliente();
					break;
				case "Dar de alta una nueva empresa":
					nuevaEmpresa();
					break;
				case "Borrar un cliente":
					borrarCliente();
					break;
				case "Cambiar la tarifa de un cliente":
					cambiarTarifa();
					break;
				case "Recuperar cliente":
					mostrarCliente();
					break;
				case "Recuperar clientes":
					mostrarTodosClientes();
					break;
				case "Recuperar clientes creados entre dos fechas":
					clientesEntreFechas();
					break;
				}
			}

			//Clientes entre dos fechas
			private void clientesEntreFechas() {
				form = new JDialog();
				Container contenedor = form.getContentPane();

				contenedor.setLayout(new FlowLayout());
				contenedor.add(inicioLabel);
				contenedor.add(inicio);
				contenedor.add(finLabel);
				contenedor.add(fin);

				clientesEntreFechasListener listener = new clientesEntreFechasListener();
				JButton boton = new JButton("CONSULTAR");
				boton.addActionListener(listener);
				contenedor.add(boton);
				boton.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						form.dispose();
					}
				});

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

			//Escuchador de clientes entre dos fechas
			private class clientesEntreFechasListener implements ActionListener {
				@Override
				public void actionPerformed(ActionEvent e) {
					ArrayList<Cliente> clientes = null;
					Calendar fechaInicio = Calendar.getInstance();
					Calendar fechaFin = Calendar.getInstance();
					String ini = inicio.getText();
					String fi = fin.getText();
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					try {
						Date fecha_date_in = sdf.parse(ini);
						fechaInicio.setTime(fecha_date_in);
					} catch (ParseException eini) {
						ventanaErrores("Fecha erronea");
					}
					try {
						Date fecha_date_fi = sdf.parse(fi);
						fechaFin.setTime(fecha_date_fi);
					} catch (ParseException efi) {
						ventanaErrores("Fecha erronea");
					}
					try {
						clientes = controlador.mostrarEntreFechas(fechaInicio, fechaFin);
					} catch (Exception e2) {
						ventanaErrores("Error al introducir las fechas.");
					}
					if (clientes == null) {
						ventanaErrores("No se ha podido realizar la operacion.");
					}else if(clientes.isEmpty()){
						mostrarTexto("No hay clientes entre estas fechas.");
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
			
			//Borrar un cliente
			private void borrarCliente() {
				final JDialog form = new JDialog();
				Container contenedor = form.getContentPane();

				contenedor.setLayout(new FlowLayout());
				contenedor.add(nifLabel);
				contenedor.add(nif);

				BorrarClienteListener listener = new BorrarClienteListener();
				JButton boton = new JButton("BORRAR");
				boton.addActionListener(listener);
				contenedor.add(boton);
				boton.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						form.dispose();
					}
				});

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
			}

			//Escuchador de borrar cliente
			private class BorrarClienteListener implements ActionListener {
				@Override
				public void actionPerformed(ActionEvent e) {
					String niff = nif.getText();
					Boolean result = controlador.borrarCliente(niff);
					if (result) {
						ventanaAviso();
						mostrarIntro();
					} else {
						ventanaErrores("No se ha podido realizar la operacion.");
					}
				}
			}

			//Nueva Empresa
			private void nuevaEmpresa() {
				final JDialog form = new JDialog();
				JPanel paneliz = new JPanel(), panelde = new JPanel(), panelab = new JPanel(), panelar = new JPanel(),
						panelex = new JPanel();
				Container contenedor = form.getContentPane();
				JButton boton1 = new JButton(), boton2 = new JButton();
				boton1.setText("ACEPTAR");
				boton1.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						form.dispose();
					}
				});
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

			//Nuevo Cliente 
			private void nuevoCliente() {
				final JDialog form = new JDialog();
				JPanel paneliz = new JPanel(), panelde = new JPanel(), panelab = new JPanel(), panelar = new JPanel(),
						panelex = new JPanel();
				Container contenedor = form.getContentPane();
				JButton boton1 = new JButton(), boton2 = new JButton();
				boton1.setText("ACEPTAR");
				boton1.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						form.dispose();
					}
				});
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
			
			//Escuchador nuevo cliente
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
					if (result) {
						ventanaAviso();
						mostrarIntro();	
					} else {
						ventanaErrores("No se ha podido realizar la operacion. ");
					}
				}
			}

			//Escuchador nueva empresa
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
					if (result) {
						ventanaAviso();
						mostrarIntro();

					} else {
						ventanaErrores("No se ha podido realizar la operacion. ");
					}
				}
			}
			
			//Cambiar tarifa
			private void cambiarTarifa() {
				final JDialog form = new JDialog();
				JPanel paneliz = new JPanel(), panelde = new JPanel(), panelab = new JPanel(), panelar = new JPanel(),
						panelex = new JPanel();
				Container contenedor = form.getContentPane();
				JButton boton1 = new JButton(), boton2 = new JButton();
				boton1.setText("ACEPTAR");
				boton1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						form.dispose();
					}
				});
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

			//Escuchador cambiar tarifa
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
					if (result) {
						ventanaAviso();
						mostrarIntro();
					} else {
						ventanaErrores("No se ha podido realizar la operacion. ");
					}
				}
			}

			//Mostrar cliente
			private void mostrarCliente() {
				final JDialog form = new JDialog();
				Container contenedor = form.getContentPane();

				contenedor.setLayout(new FlowLayout());
				contenedor.add(nifLabel);
				contenedor.add(nif);

				mostrarClienteListener listener = new mostrarClienteListener();
				JButton boton = new JButton("MOSTRAR");
				boton.addActionListener(listener);
				contenedor.add(boton);
				boton.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						form.dispose();
					}
				});

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
			}
			
			//Escuchador mostrar cliente
			private class mostrarClienteListener implements ActionListener {
				@Override
				public void actionPerformed(ActionEvent e) {
					String niff = nif.getText();
					Cliente cliente = controlador.mostrarCliente(niff);
					if(cliente==null){
						ventanaErrores("Cliente no existe. ");
					}else{
					mostrarTexto(cliente.toString());
					}
				}
			}
			
			//Mostrar todos los clientes
			private void mostrarTodosClientes() {
				ArrayList<Cliente> clientes = controlador.mostrarTodos();
				if (clientes == null) {
					ventanaErrores("No se ha podido realizar la operacion. ");
				}else if(clientes.isEmpty()){
					mostrarTexto("No hay clientes entre estas fechas");
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
	

	//Menu llamadas
	private class MenuLlamadasListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			JMenuItem item = (JMenuItem) e.getSource();
			String texto = item.getText();
			switch (texto) {
			case "Dar de alta una llamada":
				altaLlamada();
				break;
			case "Listar todas las llamadas de un cliente":
				listLlamdasCliente();
				break;
			case "Recuperar todas las llamadas realizadas entre dos fechas":
				recuperarLlamadasFechas();
				break;
			}
		}

		//Recuperar llamadas entre dos fechas
		private void recuperarLlamadasFechas() {
			form = new JDialog();
			Container contenedor = form.getContentPane();

			contenedor.setLayout(new FlowLayout());
			contenedor.add(nifLabel);
			contenedor.add(nif);
			contenedor.add(inicioLabel);
			contenedor.add(inicio);
			contenedor.add(finLabel);
			contenedor.add(fin);

			RecuperarLlamadasFechas listener = new RecuperarLlamadasFechas();
			JButton boton = new JButton("CONSULTAR");
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

		//Escuchador de recuperar llamadas entre dos fechas
		private class RecuperarLlamadasFechas implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				String niff = nif.getText();
				List<Llamada> llam = null;
				Calendar i = Calendar.getInstance();
				Calendar f = Calendar.getInstance();
				String ini = inicio.getText();
				String fi = fin.getText();
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				try {
					Date fecha_date = sdf.parse(ini);
					i.setTime(fecha_date);
				} catch (ParseException eini) {
					ventanaErrores("Fechas erronea");
				}
				try {
					Date fecha_date = sdf.parse(fi);
					f.setTime(fecha_date);
				} catch (ParseException efi) {
					ventanaErrores("Error Fecha");
				}
				try {
					llam = controlador.recuperarLlamadasFechas(i, f, niff);
				} catch (Exception e2) {
					ventanaErrores("Error al introducir las fechas.");
				}

				if (llam == null) {
					ventanaErrores("No se ha podido realizar la operacion.");
				} else {
					form.setVisible(false);
					String llamaTexto = "<html><body>";
					for (Llamada llamadas : llam) {
						llamaTexto += llamadas.toString();
						llamaTexto += "<br>";
					}
					llamaTexto += "</body></html>";
					mostrarTexto(llamaTexto);
				}
			}
		}

		private void listLlamdasCliente() {
			form = new JDialog();
			
			Container contenedor = form.getContentPane();
			contenedor.setLayout(new FlowLayout());
			contenedor.add(nifLabel);
			contenedor.add(nif);

			ListLlamdasCliente listener = new ListLlamdasCliente();
			JButton boton = new JButton("CONSULTAR");
			boton.addActionListener(listener);
			contenedor.add(boton);

			JButton cancelar = new JButton("CANCELAR");
			cancelar.setToolTipText("Salir y no anyadir.");
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
		
		//Listar llamadas de un cliente
		private class ListLlamdasCliente implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				String niff = nif.getText();
				boolean result = false;
				
				try {
					result = controlador.listLlamdasCliente(niff);
				} catch (NifInvalido e1) {
					e1.printStackTrace();
				}
				if (result) {
					form.setVisible(false);
					List<Llamada> llamadas = modelo.getListLlamadasCliente();
					String llam = "<html><body>";
					for (Llamada llamada : llamadas) {
						llam += llamada.toString();
						llam += "<br>";
					}
					llam += "</body></html>";
					mostrarTexto(llam);
				} else {
					ventanaErrores("No se ha podido realizar la operacion.");
				}
			}
		}
		
		//Dar de alta una llamada
		private void altaLlamada() {
			form = new JDialog();
			Container contenedor = form.getContentPane();

			contenedor.setLayout(new FlowLayout());
			contenedor.add(nifLabel);
			contenedor.add(nif);
			contenedor.add(tiempoLabel);
			contenedor.add(tiempo);
			contenedor.add(telLabel);
			contenedor.add(tel);

			AltaLlamada listener = new AltaLlamada();
			JButton boton = new JButton("ANYADIR");
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
	
	//Escuchador dar de alta una llamada
	private class AltaLlamada implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			double tiempoo = -1.0;
			boolean result = false;
			frame.pack();
			String niff = nif.getText();
			int tell = -1;
			try {
				tell = Integer.parseInt(tel.getText());
				tiempoo = Double.parseDouble(tiempo.getText());
			} catch (Exception e4) {
				ventanaErrores("Introduce un numero valido.");
			}
			if (tiempoo >= 0.0 && tell >= 0) {
				try {
					result = controlador.altaLlamada(niff, tell, tiempoo);
				} catch (NifInvalido e1) {
					e1.printStackTrace();
				}
			}
			if (result) {
				ventanaAviso();
				form.setVisible(false);
				mostrarIntro();
			} else {
				ventanaErrores("No se ha podido realizar la operacion.");
			}
		}
	}

	//Menu facturas
	private class MenuFacturasListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JMenuItem item = (JMenuItem) e.getSource();
			String texto = item.getText();
			switch (texto) {
			case "Emitir una factura":
				generarFactura();
				break;
			case "Recuperar los datos de una factura":
				recuperarDatosFactura();
				break;
			case "Recuperar todas las facturas de un cliente":
				recuperarFacurasCliente();
				break;
			case "Recuperar todas las facturas emitidas entre dos fechas":
				recuperarFactFechas();
				break;
			}
		}
		
		//Recuperar facturas entre fechas
		private void recuperarFactFechas() {
			form = new JDialog();
			Container contenedor = form.getContentPane();

			contenedor.setLayout(new FlowLayout());
			contenedor.add(nifLabel);
			contenedor.add(nif);
			contenedor.add(inicioLabel);
			contenedor.add(inicio);
			contenedor.add(finLabel);
			contenedor.add(fin);

			RecuperarFactFechas listener = new RecuperarFactFechas();
			JButton boton = new JButton("CONSULTAR");
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

		//Escuchador recuperar facturas entre fechas
		private class RecuperarFactFechas implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				String niff = nif.getText();
				List<Factura> fact = null;
				Calendar i = Calendar.getInstance();
				Calendar f = Calendar.getInstance();
				String ini = inicio.getText();
				String fi = fin.getText();
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				try {
					Date fecha_date = sdf.parse(ini);
					i.setTime(fecha_date);
				} catch (ParseException eini) {
					ventanaErrores("Fechas erronea");
				}
				try {
					Date fecha_date = sdf.parse(fi);
					f.setTime(fecha_date);
				} catch (ParseException efi) {
					ventanaErrores("Fecha erronea");
				}
				try {
					fact = controlador.recuperarFactFechas(i, f, niff);
				} catch (Exception e2) {
					ventanaErrores("Error al introducir las fechas.");
				}

				if (fact == null) {
					ventanaErrores("No se ha podido realizar la operacion.");
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
		
		//Recuperar facturas de cliente
		private void recuperarFacurasCliente() {
			form = new JDialog();
			Container contenedor = form.getContentPane();

			contenedor.setLayout(new FlowLayout());
			contenedor.add(nifLabel);
			contenedor.add(nif);

			RecuperarFacturasCliente listener = new RecuperarFacturasCliente();
			JButton boton = new JButton("CONSULTAR");
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
		
		//Escuchador recuperar facturas de cliente
		private class RecuperarFacturasCliente implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				String niff = nif.getText();

				boolean result = controlador.facurasCliente(niff);
				if (result) {
					form.setVisible(false);
					List<Factura> facturas = modelo.getFacturasCliente();
					String fact = "<html><body>";
					for (Factura factura : facturas) {
						fact += factura.toString();
						fact += "<br>";
					}
					fact += "</body></html>";
					mostrarTexto(fact);
				} else {
					ventanaErrores("No se ha podido realizar la operacion.");
				}
			}
		}
		
		//Recuperar datos de factura
		private void recuperarDatosFactura() {
			form = new JDialog();
			Container contenedor = form.getContentPane();

			contenedor.setLayout(new FlowLayout());
			contenedor.add(idFacturaLabel);
			contenedor.add(idFactura);

			RecuperarDatosFactura listener = new RecuperarDatosFactura();
			JButton boton = new JButton("RECUPERAR");
			boton.addActionListener(listener);
			contenedor.add(boton);

			JButton cancelar = new JButton("CANCELAR");
			cancelar.setToolTipText("Salir y no anyadir.");
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
		
		//Escuchador recuperar datos de factura
		private class RecuperarDatosFactura implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				int codigo = -1;
				
				try {
					codigo = Integer.parseInt(idFactura.getText());
				} catch (Exception e2) {
					ventanaErrores("Introduce un numero.");
				}
				boolean result = false;
				try {
					result = controlador.datosFactura(codigo);
				} catch (CodigoInvalido e1) {
					e1.printStackTrace();
				}
				if (result) {
					form.setVisible(false);
					Factura factura = modelo.getFacturaBuscada();
					mostrarTexto(factura.toString());
				} else {
					ventanaErrores("No se ha podido realizar la operacion.");
				}
			}
		}
		
		//Generar factura
		private void generarFactura() {
			form = new JDialog();
			Container contenedor = form.getContentPane();

			contenedor.setLayout(new FlowLayout());
			contenedor.add(nifLabel);
			contenedor.add(nif);

			GenerarFacturaListener listener = new GenerarFacturaListener();
			JButton boton = new JButton("GENERAR");
			boton.addActionListener(listener);
			contenedor.add(boton);

			JButton cancelar = new JButton("CANCELAR");
			cancelar.setToolTipText("Salir y no anyadir.");
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
		
		//Escuchador generar factura
		private class GenerarFacturaListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.pack();
				String niff = nif.getText();
				boolean result = false;
				try {
					result = controlador.generarFactura(niff);
				} catch (ErrorFecha e1) {
					e1.printStackTrace();
				} catch (NifInvalido e1) {
					e1.printStackTrace();
				}
				if (result) {
					ventanaAviso();
					form.setVisible(false);
					mostrarIntro();
				} else {
					ventanaErrores("No se ha podido realizar la operacion.");
				}
			}

		}
	}
	
	//Menu cargar y guardar
	private class MenuCargarGuardarListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JMenuItem item = (JMenuItem) e.getSource();
			String texto = item.getText();
			switch (texto) {
			case "Cargar":
				cargar();
				break;
			case "Guardar":
				guardar();
				break;
			}
		}

		private void guardar() {
			modelo.guardar();
		}

		private void cargar() {
			modelo.cargar();
		}
	}
	
	//Texto aplicacion
	private void mostrarIntro() {
		Container contenedor = frame.getContentPane();
		texto.setText("Bienvenido a la apicacion.");
		contenedor.add(texto);
		frame.pack();
		frame.setVisible(true);

	}
	
	//Texto para printear en aplicacion
	private void mostrarTexto(String text) {
		Container contenedor = frame.getContentPane();
		texto.setText(text);
		contenedor.add(texto);
		frame.pack();
		frame.setVisible(true);
	}
	
	//Ventana error
	public void ventanaErrores(String mensaje) {
		JOptionPane.showMessageDialog(frame, mensaje, "ERROR", JOptionPane.ERROR_MESSAGE);
	}

	//Ventana correctamente
	public void ventanaAviso() {
		JOptionPane.showMessageDialog(form, "¡Accion realizada correctamente!");
	}
	
}
