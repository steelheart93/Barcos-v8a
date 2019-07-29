package puertos.control;

/**
 * Excepciones relacionadas con el registro de barcos
 * @author Ingenieria Sistemas
 * @version 1.0
 */

@SuppressWarnings("serial")
public class BarcoException extends Exception {
	
	/**
	 * Constructor de una excepción de barco
	 * @param mensaje	la descripción del error
	 */
	public BarcoException(String mensaje) {
		super(mensaje);
	}

}
