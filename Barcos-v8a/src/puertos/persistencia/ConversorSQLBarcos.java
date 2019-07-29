package puertos.persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;

import puertos.entidades.Barco;
import puertos.entidades.Carguero;
import puertos.entidades.Velero;

/**
 * Se encarga de obtener las sentencias SQL para crear, modificar o borrar un barco,
 * o para crear el objeto a partir de la sentencia SQL. 
 * Es decir, realiza las "conversiones" entre objetos e instrucciones SQL
 * para el registro de la información en la base de datos.
 * 
 * Se tiene una sola tabla llamada "barcos", con campos: 
 * matricula,nacionalidad,volumen,pasajeros,liquidos y tipo 
 * (tipo puede ser carguero o velero).
 * 
 * @author Sandra V. Hurtado
 * @version 1.0
 */
class ConversorSQLBarcos {
		
	/**
	 * Elabora la instrucción SQL para insertar un barco en la base de datos
	 * @param barco objeto Barco que se desea insertar en la base de datos
	 * @return una cadena con la instrucción SQL para insertar los valore del barco en la base de datos
	 */
	String crearSentenciaInsertarBarco(Barco barco) {
		String tipo = "carguero";
		if (barco instanceof Velero) {
			tipo = "velero";
		}

		String sentenciaSQL = "Insert into barcos(matricula,nacionalidad,volumen,pasajeros,liquidos,tipo)"
				+ " values ('"+barco.getMatricula()+"','"+barco.getNacionalidad()+"',"
				+ barco.getVolumen()+",";
		if (barco instanceof Velero) {
			Velero velero = (Velero)barco;
			sentenciaSQL += velero.getPasajeros()+",null,'"+tipo+"')";
		}
		else if (barco instanceof Carguero) {
			Carguero carguero = (Carguero)barco;
			int liquido = carguero.getLiquidos()?1:0;
			sentenciaSQL += "null,"+liquido+","+"'"+tipo+"')";
		}
		return sentenciaSQL;
	}
	
	/**
	 * Crea un objeto barco a partir de los datos de un ResultSet.
	 * @param datosBarco el ResultSet resultante de una consulta de un barco en la base de datos.
	 * 						Debe ser diferente de null.
	 * @return	el objeto barco con sus valores (de la base de datos), o null
	 * 			si no se encuentra (es decir, el ResultSet está vacío).
	 */
	Barco instanciarBarco(ResultSet datosBarco) {
		Barco barco = null;
		try {
			String matricula = datosBarco.getString("matricula");
			String nacionalidad = datosBarco.getString("nacionalidad");
			double volumen = datosBarco.getDouble("volumen");
			int pasajeros = datosBarco.getInt("pasajeros");
			boolean liquidos = datosBarco.getBoolean("liquidos");
			String tipo = datosBarco.getString("tipo");
			if (tipo.equalsIgnoreCase("velero")) {
				barco = new Velero (matricula, nacionalidad,volumen, pasajeros);
			}
			else if (tipo.equalsIgnoreCase("carguero")) {
				barco = new Carguero (matricula, nacionalidad,volumen, liquidos);
			}
		} catch (SQLException e) {
			System.err.println("No se pudo obtener el barco con el ResultSet: "+datosBarco);
		}
		return barco;
	}
}
