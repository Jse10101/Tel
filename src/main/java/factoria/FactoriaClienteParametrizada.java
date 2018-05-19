package factoria;

import java.util.Calendar;
import java.util.Scanner;

import cliente.Cliente;
import cliente.Empresa;
import cliente.Particular;
import direccion.Direccion;
import tarifa.Tarifa;

public class FactoriaClienteParametrizada implements FactoriaParametrizada {
	public Cliente getCliente(TipoCliente tipo,Scanner teclado) {
		
		String apellidos = null;
		FactoriaTarifas fact = new FactoriaTarifas();
		
		System.out.println("Nombre:");
		String nombre = teclado.nextLine();
		System.out.println("NIF:");
		String nif = teclado.nextLine();
		System.out.println("Codigo postal:");
		String codigopostal = teclado.nextLine();
		System.out.println("Provincia:");
		String provincia = teclado.nextLine();
		System.out.println("Poblacion:");
		String poblacion = teclado.nextLine();
		Direccion direccion = new Direccion(codigopostal, provincia, poblacion);
		Calendar fecha = Calendar.getInstance();
		System.out.println("Precio de la tarifa basica: ");
		float precio = teclado.nextFloat();
		teclado.nextLine();
		Tarifa tarifaB = new Tarifa(precio);
		System.out.println(TipoTarifaDia.getMenu());
		System.out.println("Tipo de tarifa dependiendo de el dia: ");
		TipoTarifaDia opcion = TipoTarifaDia.getOpcion(teclado.nextInt());
		
		System.out.println("Precio de la tarifa S/D: ");
		float precio1 = teclado.nextFloat();
		teclado.nextLine();
		
		Tarifa tarifaDia = fact.getTarifa(opcion, tarifaB, precio1);
		System.out.println(TipoTarifaHorario.getMenu());
		System.out.println("Tipo de tarifa dependiendo de  la hora : ");
		TipoTarifaHorario opcion1 = TipoTarifaHorario.getOpcion(teclado.nextInt());
		System.out.println("Precio de la tarifa Manyana/Noche: ");
		float precio2 = teclado.nextFloat();
		teclado.nextLine();
		Tarifa tarifaBarata = fact.getTarifa(opcion1, tarifaDia, precio2);
		
		System.out.println("Correo electronico:");
		String correo = teclado.nextLine();
	Cliente cliente = null;
		switch (tipo) {
		case PERSONA:
			
			System.out.println("Apellidos:");
			apellidos = teclado.nextLine();
			cliente = new Particular(nombre, nif, direccion, correo, fecha, tarifaBarata, apellidos);
			break;
		case EMPRESA:
			
			cliente = new Empresa(nombre, nif, direccion, correo, fecha, tarifaBarata);
			break;
		}
		
		return cliente;
	}
}
