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
		
		FactoriaTarifas nuevaFactoria = new FactoriaTarifas();
		
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
		System.out.println("Precio de la tarifa: ");
		float precio = teclado.nextFloat();
		teclado.nextLine();
		Tarifa tarifa = new Tarifa(precio);
		System.out.println(TipoTarifaEspecial.getMenu());
		System.out.println("Tipo de tarifa especial: ");
		TipoTarifaEspecial tarifaEspecial = TipoTarifaEspecial.getOpcion(teclado.nextInt());	
		Tarifa tarifaMasBarata = nuevaFactoria.getTarifa(tarifaEspecial, tarifa, tarifa.getPre());
		teclado.nextLine();
		System.out.println("Correo electronico:");
		String correo = teclado.nextLine();
	Cliente cliente = null;
		switch (tipo) {
		case PERSONA:
			
			System.out.println("Apellidos:");
			apellidos = teclado.nextLine();
			cliente = new Particular(nombre, nif, direccion, correo, fecha, tarifaMasBarata, apellidos);
			break;
		case EMPRESA:
			
			cliente = new Empresa(nombre, nif, direccion, correo, fecha, tarifaMasBarata);
			break;
		}
		
		return cliente;
	}
}
