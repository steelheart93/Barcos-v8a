package puertos.control;

import java.util.List;
import puertos.entidades.Barco;
import puertos.persistencia.RepositorioBarcos;
import puertos.persistencia.FabricaRepositorios;

/**
 * Clase donde se registran los barcos que llegan al puerto,
 * y tiene la principales funciones del programa (es el control).
 * @author Sandra V. Hurtado
 * @version 5.0
 */
public class Puerto {

	private RepositorioBarcos repositorioBarcos;
	
	Puerto() {
		// Usa la fábrica para crear el repositorio (lista o base de datos)
		repositorioBarcos = FabricaRepositorios.crearRepositorio();
	}
	
	/**
	 * Calcula la capacidad de todos los barcos en el puerto,
	 * para poder determinar la carga que se puede llevar.
	 * @return	la capacidad total de los barcos, en m3
	 */
	double calcularCapacidadTotal() {
		List<Barco> barcos = repositorioBarcos.consultarBarcos();
		double capacidadTotal = 0;
		for (Barco barco : barcos) {
			capacidadTotal += barco.calcularCapacidad();
		}
		return capacidadTotal;
	}
	
	/**
	 * Se adiciona un barco al puerto (se guarda en un repositorio),
	 * haciendo previamente las validaciones del negocio (como que la matrícula no se repita).
	 * @param barco	el nuevo barco que se desea adicionará
	 * @return	un valor booleano indicando si se pudo adicionar el barco
	 * 			o no (porque ya existía otro con esa matrícula).
	 * @throws BarcoException cuando no cumple las validaciones del negocio,
	 * 		en este caso, porque ya existía un barco con esa matrícula.
	 */
	boolean adicionarBarco(Barco barco) throws BarcoException {
				
		Barco barcoBuscado = buscarBarco(barco.getMatricula());
		if (barcoBuscado != null) {
			return false;
		}
		repositorioBarcos.adicionarBarco(barco);
		return true;
	}

	/**
	 * Busca un barco a partir de la matrícula
	 * @param matricula	el número de matrícula del barco que se buscará
	 * @return el objeto barco con la matrícula dada, o null si no se encuentra
	 */
	Barco buscarBarco(String matricula) {
		Barco barco = repositorioBarcos.buscarBarco(matricula);
		return barco;
	}

	/**
	 * Elimina un barco, dada su matrícula. 
	 * @param matricula	el número de la matrícula del barco que se desea borrar
	 * @throws BarcoException cuando no se pudo eliminar porque no se encuentra
	 *  un barco con la matrícula dada
	 */
	void borrarBarco(String matricula)  throws BarcoException {
		Barco barcoBuscado = this.buscarBarco(matricula);
		if (barcoBuscado == null) {
			throw new BarcoException("No se encuentra un barco con esa matrícula. No se pudo borrar");
		}
		
		boolean pudoBorrar = repositorioBarcos.borrarBarco(barcoBuscado);
		if (!pudoBorrar) {
			throw new BarcoException("No se encuentra un barco con esa matrícula. No se pudo borrar");
		}
	}
}




