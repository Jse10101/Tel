package entradaSalida;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import datos.BaseDeDatos;

public class EntradaSalida {
	static String nombre="bd.bin";
	
	//Guardar
	public static void GuardarDatos(BaseDeDatos bd){
		ObjectOutputStream oos=null;
		
		try {
			try {
				FileOutputStream fos = new FileOutputStream(nombre);
				oos = new ObjectOutputStream(fos);
				oos.writeObject(bd);
			}
			finally {
				oos.close();
			}
		}
		catch(FileNotFoundException exc) {
			System.out.println("El fichero no existe.");
			exc.printStackTrace();
		}
		catch(IOException exc) {
			exc.printStackTrace();
		}
	}
	
	//Abrir
	public static BaseDeDatos abrirDatos() {
		BaseDeDatos bd=new BaseDeDatos();
		ObjectInputStream ois = null;
		
		try{
			try {
				FileInputStream fis = new FileInputStream(nombre);
				ois = new ObjectInputStream(fis);
				bd = (BaseDeDatos)ois.readObject();
			}
			finally {
				if(ois != null) ois.close();
			}
		}
		catch(FileNotFoundException exc) {
			System.err.println("Fichero de datos inexistente, crea una nueva agenda.");
		}
		catch(IOException exc) {
			exc.printStackTrace();
		}
		catch(ClassNotFoundException exc) {
			exc.printStackTrace();
		}
		return bd;
	}
}