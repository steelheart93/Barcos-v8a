package puertos.persistencia;

import java.util.ArrayList;
import java.util.List;
import puertos.entidades.Barco;

/**
 * Repositorio básicos, que tiene la lista de barcos en memoria.
 * Generalmente usado para pruebas.
 * @author Sandra V. Hurtado
 * @version 2.0
 */
public class ListaBarcos implements RepositorioBarcos {
	
	private List<Barco> barcos;

	public ListaBarcos() {
		barcos = new ArrayList<Barco>();
	}
	
	/**
	 * Busca un barco en la lista del puerto, por su número de matrícula
	 * @param matricula	el número de matrícula del barco que se buscará
	 * @return el objeto barco con la matrícula dada, o null si no se encuentra
	 */
	@Override
	public Barco buscarBarco(String matricula) {
		for (Barco barco : barcos) {
			if (barco.getMatricula().equals(matricula)) {
				return barco;
			}
		}
		return null;
	}
	
	/**
	 * Consulta toda la lista de los barcos que hay
	 * @return	la lista (List) con los barcos
	 */
	@Override
	public List<Barco> consultarBarcos() {
		return barcos;
	}
	
	/**
	 * Adiciona un barco a la lista
	 * @param barco el objeto barco que se adicionará
	 * @return valor booleano indicando si se pudo guardar en la lista o no
	 */
	@Override
	public boolean adicionarBarco(Barco barco) {
		return barcos.add(barco);
	}
	
	/**
	 * Elimina un barco de la lista.
	 * Usa el método "remove" de List, por lo que necesita que esté
	 * definido el método equals en la clase Barco.
	 * @param barco	el objeto barco que se eliminará
	 * @return valor booleano indicando si se pudo borrar o no
	 * 				(porque no se encontró el barco en la lista).
	 */
	@Override
	public boolean borrarBarco(Barco barco) {
		return barcos.remove(barco);	
	}

}
