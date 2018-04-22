package factoria;

import tarifa.Tarifa;

public interface TarifaInt {
	Tarifa getTarifa(TipoTarifaEspecial tipo, Tarifa tarifaAnterior, double precio);
}