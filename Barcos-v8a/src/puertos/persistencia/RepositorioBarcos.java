package puertos.persistencia;

import java.util.List;

import puertos.entidades.Barco;

/**
 * Servicios relacionados con la gestión de los datos de los barcos
 * en un repositorio (consultar, adicionar, buscar)
 * @author Sandra V. Hurtado
 * @version 2.0
 */
public interface RepositorioBarcos {
	
	/**
	 * Adiciona un barco al repositorio para hacerlo persistente
	 * @param barco el objeto barco que se adicionará
	 * @return valor booleano indicando si se pudo guardar en el repositorio o no
	 */
	public abstract boolean adicionarBarco(Barco barco);
	
	/**
	 * Buscar un barco en la base de datos a partir de su matrícula
	 * @param matricula	número de matrícula del barco que se desea buscar,
	 * 	  				debe ser diferente de null
	 * @return	el objeto barco con la matrícula buscada, o null si no se encuentra.
	 */
	public abstract Barco buscarBarco(String matricula);
	
	/**
	 * Consulta toda la lista de los barcos que hay registrados
	 * @return	la lista (List) con los barcos.
	 * 			En caso de no existir barcos registrados retorna una lista vacía.
	 */
	public List<Barco> consultarBarcos();

	/**
	 * Elimina un barco del repositorio.
	 * @param barco	el objeto barco que se eliminará
	 * @return valor booleano indicando si se pudo borrar o no
	 * 				(porque no se encontró el barco en el repositorio).
	 */
	public boolean borrarBarco(Barco barco);
}
