package puertos.control;

import java.text.DecimalFormat;
import puertos.entidades.Barco;

/**
 * Clase de control, que realiza validaciones preliminares de los datos de la interfaz,
 * antes de enviarlos a otras clases de control del negocio o de entidad.
 * @author Sandra V. Hurtado
 * @version 1.0
 */
public class ControlPuerto {
	private Puerto puerto;
	
	public ControlPuerto() {
		this.puerto = new Puerto();
	}
	
	/**
	 * Obtiene los valores del barco a partir de las cadenas enviadas por la GUI,
	 * y luego crea el objeto Barco para adicionarlo en el puerto.
	 * Antes de crear el objeto valida que los datos cumplan las condiciones
	 *  de formato básicas.
	 * @param datosBarco	un arreglo de cadenas, cada una con un valor para un atributo del barco
	 * @throws BarcoException cuando no cumple alguna de las validaciones para crear el barco
	 */
	public void adicionarBarco(String... datosBarco) throws BarcoException {
		// ****************** NOTA IMPORTANTE:
		// Solo para el ejemplo se deja todo este código en un solo método,
		// pero es recomendable dividirlo en varios métodos para hacer las validaciones.
		
		String matricula = datosBarco[0];
		if (matricula == null || matricula.trim().equals("")) {
			throw new BarcoException("La matrícula no puede quedar en blanco.");
		}
		
		String nacionalidad = datosBarco[1];	
		// Se pueden adicionar validaciones para este valor, por ejemplo que no esté en blanco.
		
		double volumen;
		try {
			volumen = Double.parseDouble(datosBarco[2]);
		} catch (NumberFormatException numeroIncorrecto) {
			throw new BarcoException("El volumen debe ser un valor numérico.");
		}
		if (volumen <= 0) {
			throw new BarcoException("El volumen debe ser positivo.");
		}
		
		char tipo = datosBarco[3].charAt(0);
		
		int pasajeros = 1;
		try {
			pasajeros = Integer.parseInt(datosBarco[4]);	
		} catch (NumberFormatException numeroIncorrecto) {
			throw new BarcoException("El valor en pasajeros debe ser un número entero.");
		}
		// Se pueden adicionar validaciones para este valor, por ejemplo que sea positivo
		
		boolean liquidos = datosBarco[5].equals("true")?true:false;
		
		// Usa la fábrica para crear el Barco (Velero o Carguero), y luego lo envía al puerto
		Barco barco = FabricaBarcos.crearBarco(matricula, nacionalidad, volumen, tipo, pasajeros, liquidos);
		if (barco == null) {
			throw new BarcoException("No se pudo identificar el tipo de barco que es. No se pudo crear.");
		}
		
		boolean pudoAdicionar = puerto.adicionarBarco(barco);
		if (!pudoAdicionar) {
			throw new BarcoException("No se pudo crear el barco. Ya existe uno registrado con esa matrícula.");
		}
	}

	/**
	 * Pide al puerto que calcule la capacidad, y formatea el resultado para que sea mostrado en la GUI
	 * @return	la capacidad, como cadena formateada a partir del valor calculado.
	 */
	public String calcularCapacidadTotal() {
		double capacidadTotal = puerto.calcularCapacidadTotal();
		return new DecimalFormat("#.0#").format(capacidadTotal);
	}
	
	/**
	 * Pide al puerto que elimine el barco del repositorio. 
	 * @param matricula	el número de la matrícula del barco que se desea borrar
	 * @throws BarcoException cuando no se pudo eliminar porque no se encuentra
	 *  un barco con la matrícula dada
	 */
	public void borrarBarco(String matricula)  throws BarcoException {
		puerto.borrarBarco(matricula);
	}
}
