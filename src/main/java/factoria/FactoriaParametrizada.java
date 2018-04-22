package factoria;

import java.util.Scanner;

import cliente.Cliente;

public interface FactoriaParametrizada {
    Cliente getCliente(TipoCliente tipo,Scanner teclado);
}