package excepciones;

public class RangoFechaNoValido extends Exception{

	public RangoFechaNoValido(){
		super("La fecha inicial es posterior a la final");
	}

}
