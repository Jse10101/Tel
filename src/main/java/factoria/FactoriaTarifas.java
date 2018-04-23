package factoria;
import tarifa.Tarifa;
import tarifa.TarifaDomingo;
import tarifa.TarifaNocturna;
import tarifa.TarifaSabado;

public class FactoriaTarifas implements TarifaInt {

	public Tarifa getTarifa(TipoTarifaEspecial tipo, Tarifa tarifaAnterior, double precio) {
		Tarifa tarifa = null;
		
		switch (tipo) {
		case NOCTURNO:
			tarifa = new TarifaNocturna(tarifaAnterior, precio);
			break;
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
