package factoria;

import tarifa.Tarifa;

public interface TarifaInt {
	Tarifa getTarifa(TipoTarifaHorario tipo, Tarifa tarifaAnterior, double precio);
	Tarifa getTarifa(TipoTarifaDia tipo, Tarifa tarifaAnterior, double precio);
}