package factoria;

import java.util.Scanner;

import tarifa.Tarifa;
import tarifa.TarifaDomingo;

import tarifa.TarifaManyana;
import tarifa.TarifaNocturna;
import tarifa.TarifaSabado;
import tarifa.TarifaDomingo;

public class FactoriaTarifas implements TarifaInt {

	@Override
	public Tarifa getTarifa(TipoTarifaHorario tipo, Tarifa tarifaAnterior, double precio) {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		Tarifa tarifa = null;
		switch (tipo) {
		
		case MANYANA:
			tarifa = new TarifaManyana(tarifaAnterior, precio);
			break;
		case NOCTURNA:
			tarifa = new TarifaNocturna(tarifaAnterior, precio);
			break;
		}
		return tarifa;
	}
	@Override
	public Tarifa getTarifa(TipoTarifaDia tipo, Tarifa tarifaAnterior, double precio) {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		Tarifa tarifa = null;
		switch (tipo) {

		case SABADO:
			tarifa = new TarifaSabado(tarifaAnterior, precio);
			break;
		case DOMINGO:
			tarifa = new TarifaDomingo(tarifaAnterior, precio);
			break;
		}
		return tarifa;
	}

}
