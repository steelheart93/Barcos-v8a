package puertos.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import puertos.entidades.Barco;

/**
 * Usa una base de datos como repositorio de los datos de los barcos,
 * y ofrece los servicios definidos en RepositorioBarcos.
 * 
 * Se usa como una base de datos llamada "barcos",
 * que tiene una sola tabla llamada "barcos", con campos: 
 * matricula,nacionalidad,volumen,pasajeros,liquidos y tipo 
 * (tipo puede ser carguero o velero)
 * 
 * @author Sandra V. Hurtado
 * @version 2.5
 */
public class BaseDatosBarcos implements RepositorioBarcos {
	
	private GestorConexionBD conecta;
	private ConversorSQLBarcos conversor;
	
	public BaseDatosBarcos() {
		conecta = new GestorConexionBD();
		conversor = new ConversorSQLBarcos();
	}
	
	/**
	 * Buscar un barco en la base de datos a partir de su matrícula
	 * En caso de presentarse alguna excepción, el error se escribe y se retorna null.
	 * 
	 * @param matricula	número de matrícula del barco que se desea buscar,
	 * 	  				debe ser diferente de null
	 * @return	el objeto barco con la matrícula buscada, o null si no se encuentra.
	 */
	@Override
	public Barco buscarBarco(String matricula) {		
		Connection conexion = conecta.getConexion();
		if (conexion != null) {
			String consultaSQL = "Select matricula,nacionalidad,volumen,pasajeros,liquidos,tipo "
					+ " from barcos "
					+ " where matricula = ?";
			
			try {
				PreparedStatement sentencia = conexion.prepareStatement(consultaSQL);
				sentencia.setString(1, matricula);
				ResultSet resultadoConsulta = sentencia.executeQuery();
				if (resultadoConsulta != null && resultadoConsulta.next()) {
					Barco barco = conversor.instanciarBarco(resultadoConsulta);
					return barco;
				}
			} catch (SQLException e) {
				System.err.println("No se pudo obtener la conexión con la Base de Datos:"+conexion);
			}
			finally {
				conecta.cerrarConexion(conexion);
			}
		}
		return null;
	}
	
	
	/**
	 * Adiciona un barco a la base de datos
	 * @param barco el objeto barco que se adicionará
	 * @return valor booleano indicando si se pudo guardar en la base de datos o no
	 */
	@Override
	public boolean adicionarBarco(Barco barco) {
		Connection conexion = conecta.getConexion();
		if (conexion != null) {
			String sentenciaSQL = conversor.crearSentenciaInsertarBarco(barco);
		
			try {
				Statement sentencia = conexion.createStatement();
				int cantidadInserciones = sentencia.executeUpdate(sentenciaSQL);
				
				return (cantidadInserciones > 0);
			} catch (SQLException e) {
				System.err.println("No se pudo obtener la conexión con la Base de Datos:"+conexion);
			}
			finally {
				conecta.cerrarConexion(conexion);
			}	
		}
		return false;
	}
	
	/**
	 * Consulta toda la lista de los barcos que hay
	 * @return	la lista (List) con los barcos
	 */
	@Override
	public List<Barco> consultarBarcos() {
		List<Barco> barcos = new ArrayList<Barco>();
		Connection conexion = conecta.getConexion();
		if (conexion != null) {
			String consultaSQL = "Select matricula,nacionalidad,volumen,pasajeros,liquidos,tipo "
					+ " from Barcos ";

			try {
				PreparedStatement sentencia = conexion.prepareStatement(consultaSQL);
				ResultSet resultadoConsulta = sentencia.executeQuery();
				if (resultadoConsulta != null) {
					while(resultadoConsulta.next()) {
						Barco barco = conversor.instanciarBarco(resultadoConsulta);
						barcos.add(barco);
					}
				}
			} catch (SQLException e) {
				System.err.println("No se pudo obtener la conexión con la Base de Datos:"+conexion);
			}
			finally {
				conecta.cerrarConexion(conexion);
			}
		}
		return barcos;
	}

	/**
	 * Elimina un barco del repositorio.
	 * @param barco	el objeto barco que se eliminará
	 * @return valor booleano indicando si se pudo borrar o no
	 * 				(porque no se encontró el barco en el repositorio).
	 */
	@Override
	public boolean borrarBarco(Barco barco) {
		Connection conexion = conecta.getConexion();
		if (conexion != null) {
			String sentenciaSQL = "Delete from barcos where matricula = ?";
			
			try {
				PreparedStatement sentencia = conexion.prepareStatement(sentenciaSQL);
				sentencia.setString(1, barco.getMatricula());
				int resultado = sentencia.executeUpdate();
				if (resultado > 0) {
					return true;
				}
			} catch (SQLException e) {
				System.err.println("No se pudo obtener la conexión con la Base de Datos:"+conexion);
			}
			finally {
				conecta.cerrarConexion(conexion);
			}
		}
		return false;
	}
}
