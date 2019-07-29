package puertos.persistencia;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * Permite crear un repositorio de acuerdo con el tipo seleccionado
 * (por ejemplo, para pruebas la lista, para producción la base de datos).
 * El tipo se define en un archivo de propiedades:
 *   de esta manera no hay que cambiar el código para cambiar el repositorio.
 * @author Sandra V. Hurtado
 * @version 1.5
 */
public class FabricaRepositorios {
	
	/**
	 * Crea el repositorio de acuerdo con el tipo, que se obtiene de un archivo de propiedades
	 * @return	el repositorio creado (objeto de una clase que
	 * 		implemente la interfaz RepositorioBarcos)
	 */
	public static RepositorioBarcos crearRepositorio() {
		String tipo = leerArchivoPropiedades();
			
		switch (tipo) {
			case "BaseDatos":	
				return new BaseDatosBarcos();
			case "Orm":
				return new OrmBarcos();
			default:	
				return new ListaBarcos();
		}
	}

	/**
	 * Lee el archivo de propiedades "db.properties" para obtener
	 * el tipo de repositorio que se trabajará en el proyecto. 
	 * @return el tipo leído del archivo de propiedades, o cadena en blanco
	 * 			si no lo pudo leer
	 */
	private static String leerArchivoPropiedades() {
		String tipo = "";
		Properties propiedades = new Properties();
		try {
			FileReader archivoPropiedades = new FileReader("db.properties"); 
			propiedades.load(archivoPropiedades);
			tipo = propiedades.getProperty("repositorio");
			archivoPropiedades.close();
		} catch (IOException e) {
			System.err.println(e);
		}
		return tipo;
	}
}
